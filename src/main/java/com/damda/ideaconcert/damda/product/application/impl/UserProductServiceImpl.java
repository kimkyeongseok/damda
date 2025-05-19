package com.damda.ideaconcert.damda.product.application.impl;

import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.payment.application.PaymentService;
import com.damda.ideaconcert.damda.payment.application.UserPaymentInfoDto;
import com.damda.ideaconcert.damda.product.application.ElementReadDto;
import com.damda.ideaconcert.damda.product.application.ProductReadDto;
import com.damda.ideaconcert.damda.product.application.UserProductService;
import com.damda.ideaconcert.damda.product.domain.Product;
import com.damda.ideaconcert.damda.product.domain.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProductServiceImpl implements UserProductService {
  private final ProductRepository productRepository;
  private final PaymentService paymentService;
  private final FileManager fileManager;

  @Override
  @Transactional
  public List<ProductReadDto> getPaidProductList() {
    List<ProductReadDto> response = getProductListByIsFree(false);
    return response;
  }

  @Override
  @Transactional
  public List<ProductReadDto> getPurchasedProductListByUserPk(int userPk) {
    List<ProductReadDto> productList = getProductListByIsFree(true);
    UserPaymentInfoDto userPaymentInfo = paymentService.getUserPaymentInfo(userPk);
    List<ProductReadDto> response = userPaymentInfo
      .getPaymentList()
      .stream()
      .map(
        order -> {
          Product product = order.getProduct();
          List<ElementReadDto> elementList = order
            .getProduct()
            .getElements()
            .stream()
            .map(
              element -> {
                return new ElementReadDto(
                  element.getId(),
                  element.getName(),
                  fileManager.createPath(product, element)
                );
              }
            )
            .collect(Collectors.toList());

          return new ProductReadDto(
            product.getId(),
            product.getName(),
            product.getCategory(),
            product.getIsFree(),
            getPreviewUrl(elementList),
            product.getPrice(),
            product.getUploadDate(),
            elementList
          );
        }
      )
      .collect(Collectors.toList());
      response.addAll(productList);
    return response;
  }

  private String getPreviewUrl(List<ElementReadDto> elementList) {
    return elementList.get(0).getImg();
  }

  private List<ProductReadDto> getProductListByIsFree(boolean isFree) {
    List<Product> productList = productRepository.findByIsFreeOrderByUploadDateDesc(
      isFree
    );
    List<ProductReadDto> response = productList
      .stream()
      .map(
        product -> {
          List<ElementReadDto> elementList = product
            .getElements()
            .stream()
            .map(
              element -> {
                return new ElementReadDto(
                  element.getId(),
                  element.getName(),
                  fileManager.createPath(product, element)
                );
              }
            )
            .collect(Collectors.toList());
          return new ProductReadDto(
            product.getId(),
            product.getName(),
            product.getCategory(),
            product.getIsFree(),
            getPreviewUrl(elementList),
            product.getPrice(),
            product.getUploadDate(),
            elementList
          );
        }
      )
      .collect(Collectors.toList());
    return response;
  }
}
