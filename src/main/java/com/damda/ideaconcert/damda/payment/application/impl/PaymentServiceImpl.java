package com.damda.ideaconcert.damda.payment.application.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.damda.ideaconcert.damda.payment.application.ImpResponse;
import com.damda.ideaconcert.damda.payment.application.PaymentData;
import com.damda.ideaconcert.damda.payment.application.PaymentService;
import com.damda.ideaconcert.damda.payment.application.UserPaymentInfoDto;
import com.damda.ideaconcert.damda.payment.domain.Payment;
import com.damda.ideaconcert.damda.payment.domain.PaymentRepository;
import com.damda.ideaconcert.damda.payment.domain.Point;
import com.damda.ideaconcert.damda.payment.domain.PointRepository;
import com.damda.ideaconcert.damda.payment.representation.AppPaymentRequest;
import com.damda.ideaconcert.damda.payment.representation.AppPaymentResponse;
import com.damda.ideaconcert.damda.payment.representation.CurrentPointResponse;
import com.damda.ideaconcert.damda.payment.representation.PaymentCompleteRequest;
import com.damda.ideaconcert.damda.payment.representation.PurchaseRequest;
import com.damda.ideaconcert.damda.payment.representation.PurchaseResponse;
import com.damda.ideaconcert.damda.product.domain.Product;
import com.damda.ideaconcert.damda.product.domain.ProductRepository;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.domain.UserRepository;
import com.damda.ideaconcert.damda.utils.NameUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final PointRepository pointRepository;

    @Value("${imp.key}")
    private String IMP_KEY;
    @Value("${imp.secret}")
    private String IMP_SECRET;

    @Override
    @Transactional
    public UserPaymentInfoDto getUserPaymentInfo(int userPk) {
        User userInfo = userRepository.findById(userPk).orElseThrow(() -> new NoSuchElementException());
        List<Payment> paymentList = paymentRepository.findByUserPkOrderByDateDesc(userPk);

        UserPaymentInfoDto userInfoRequest = 
            new UserPaymentInfoDto(
                userInfo.getId(),
                userInfo.getUserId(),
                userInfo.getNickName(), 
                userInfo.getSnsUrl(),
                paymentList
            );
        return userInfoRequest;
    }

    @Override
    @Transactional
    public boolean complete(PaymentCompleteRequest paymentCompleteRequest, int userPk) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.add("imp_key", IMP_KEY);
        request.add("imp_secret", IMP_SECRET);

        WebClient webClient = 
            WebClient.builder()
                .baseUrl("https://api.iamport.kr")
                .build();
                
        ImpResponse response = 
            webClient.post()
                .uri("/users/getToken")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ImpResponse.class)
                .flux()
                .toStream()
                .findFirst() 
                .orElse(null);

        String accessToken = response.getResponse().get("access_token").toString();
       
        PaymentData paymentData = 
            webClient.get()
                .uri("/payments/{imp_id}", paymentCompleteRequest.getImpUid())
                .header("Authorization", accessToken)
                .retrieve()
                .bodyToMono(PaymentData.class)
                .flux()
                .toStream()
                .findFirst()
                .orElse(null);
      
        Product paymentProduct =
            productRepository.findById(paymentCompleteRequest.getProductId()).orElse(null);
            int amount = Integer.parseInt(paymentData.getResponse().get("amount").toString());
            int amountToBePaid = paymentProduct.getPrice();

            if(amount == amountToBePaid){
                Payment order = new Payment(
                    paymentCompleteRequest.getMerchantUid(),
                    userPk,
                    paymentProduct
                );
                paymentRepository.save(order); 
                return true;
            }else{
                return false;
            }
    }

	@Override
    @Transactional
	public boolean validatePayment(int userPk, int productId) {
		Optional<Payment> result = paymentRepository.findByUserPkAndProductId(userPk, productId);
        return result.isPresent();
	}

    @Override
    @Transactional
    public AppPaymentResponse appPaymentComplete(int userPk, AppPaymentRequest paymentRequest) {
        Optional<Point> userPoint = pointRepository.findByUserPk(userPk);
        userPoint.ifPresentOrElse(
            it -> {
                Point updatePoint = new Point(
                    userPk, 
                    Integer.parseInt(paymentRequest.getProductId().split("\\.")[1]), 
                    paymentRequest.getStore(),
                    paymentRequest.getTransactId()
                );
                it.update(updatePoint);
                pointRepository.save(it);
            }, 
            () -> {
                Point point = new Point(
                    userPk, 
                    Integer.parseInt(paymentRequest.getProductId().split("\\.")[1]) ,
                    paymentRequest.getStore(),
                    paymentRequest.getTransactId()
                );
                pointRepository.save(point);
            }
        );
        Point currentPoint = pointRepository.findByUserPk(userPk).orElse(null);
        return new AppPaymentResponse(
            200, 
            currentPoint.getPoint(), 
            "포인트 구매가 완료 되었습니다."
        );
    }

    @Override
    @Transactional
    public PurchaseResponse purchase(int userPk, PurchaseRequest paymentRequest) {  
        Product paymentProduct =
        productRepository.findById(paymentRequest.getProductId()).orElse(null);
        Point userPoint = pointRepository.findByUserPk(userPk).orElse(null);
        if(userPoint == null || userPoint.getPoint() < paymentProduct.getPrice()){
            int currentPoint = userPoint == null? 0 : userPoint.getPoint();
            return new PurchaseResponse(400, currentPoint, "보유하신 포인트가 모자랍니다.");
        }else {
            Payment order = new Payment(
                NameUtils.createUniqueName(),
                userPk,
                paymentProduct
            );
            paymentRepository.save(order); 
            userPoint.purchase(paymentProduct.getPrice());
        }
        return new PurchaseResponse(200, userPoint.getPoint(), "상품 구매가 완료 되었습니다.");
    }

    @Override
    public CurrentPointResponse getUserCurrentPoint(int userPk) {
        Point point = pointRepository.findByUserPk(userPk).orElse(null);

        if(point == null) {
            return new CurrentPointResponse(
                0
            );
        }else {
            return new CurrentPointResponse(
                point.getPoint()
            );
        }
        

    }
}
        
           
       
