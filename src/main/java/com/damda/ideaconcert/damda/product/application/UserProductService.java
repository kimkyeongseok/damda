package com.damda.ideaconcert.damda.product.application;

import java.util.List;

public interface UserProductService {
	List<ProductReadDto> getPaidProductList();
	List<ProductReadDto> getPurchasedProductListByUserPk(int userPk);
}
