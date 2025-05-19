package com.damda.ideaconcert.damda.payment.representation;

import com.damda.ideaconcert.damda.payment.application.PaymentService;
import com.damda.ideaconcert.damda.payment.application.UserPaymentInfoDto;
import com.damda.ideaconcert.damda.utils.DecodeJWT;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("")
    public UserPaymentInfoDto getUserPaymentInfo(@CookieValue("_dawt") String jwt) {
        int userPk = getUserPkFromJWT(jwt);
        return paymentService.getUserPaymentInfo(userPk);
    }

    @GetMapping("/point")
    public ResponseEntity<Object> getUserCurrentPoint( @CookieValue("_dawt") String jwt){
        int userPk = getUserPkFromJWT(jwt);
        CurrentPointResponse response = paymentService.getUserCurrentPoint(userPk);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/point")
    public ResponseEntity<AppPaymentResponse> appPaymentCompleteOrder(  
        @RequestBody AppPaymentRequest paymentRequest,
        @CookieValue("_dawt") String jwt
    ) {
        int userPk = getUserPkFromJWT(jwt);
        AppPaymentResponse response = paymentService.appPaymentComplete(userPk, paymentRequest);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping("/point/complete")
    public ResponseEntity<PurchaseResponse> purchaseOrder(
        @RequestBody PurchaseRequest paymentRequest,
        @CookieValue("_dawt") String jwt
    ) {
        int userPk = getUserPkFromJWT(jwt);
        PurchaseResponse response = paymentService.purchase(userPk, paymentRequest);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping("/complete")
    public ResponseEntity<CompleteResponse> completeOrder(
            @RequestBody PaymentCompleteRequest paymentRequest,
            @CookieValue("_dawt") String jwt
        ){
        int userPk = getUserPkFromJWT(jwt);
        if(paymentService.complete(paymentRequest, userPk)){
            CompleteResponse response = new CompleteResponse(200, "결제 성공");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            CompleteResponse response = new CompleteResponse(400, "위조된 결제 시도");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/validate/product/{productId}")
    public ResponseEntity<PaymentValidateDto> validatePayment(
        @CookieValue("_dawt") String jwt,
        @PathVariable("productId") Integer productId
    ) {
        int userPk = getUserPkFromJWT(jwt);
        if(paymentService.validatePayment(userPk, productId)){
            PaymentValidateDto response = 
            new PaymentValidateDto(
                400,
                "이미 구매하신 상품입니다. 다른 상품을 선택해보세요!"
            );
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }else{
            PaymentValidateDto response = 
            new PaymentValidateDto(
                200,
                "해당 상품의 구매 이력이 없습니다."
            );
            return new ResponseEntity<>(response , HttpStatus.OK);
        }
    }
    private int getUserPkFromJWT(String token) {
        int userPk = Integer.parseInt(DecodeJWT.decode(token, 1).get("aud").toString());
        return userPk;
    }
}
