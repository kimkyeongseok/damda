package com.damda.ideaconcert.damda.product.representation;

import java.util.List;
import java.util.Map;

import com.damda.ideaconcert.damda.product.application.ProductReadDto;
import com.damda.ideaconcert.damda.product.application.UserProductService;
import com.damda.ideaconcert.damda.utils.DecodeJWT;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/product")
public class UserProductController {
    private final UserProductService userProductService;
    
    @GetMapping("/store")
    public List<ProductReadDto> getPaidProductList() {
        return userProductService.getPaidProductList();
    }
    
    @GetMapping("/list")
    public List<ProductReadDto> getPurchasedProductListByUserPk(@CookieValue("_dawt") String jwt) {
        Map<String, Object> userInfo = DecodeJWT.decode(jwt, 1);
        int userPk = Integer.parseInt(userInfo.get("aud").toString());
        return userProductService.getPurchasedProductListByUserPk(userPk);
    }
}
