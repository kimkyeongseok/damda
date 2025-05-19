package com.damda.ideaconcert.damda.product.domain;

import lombok.Getter;

@Getter
public class DeleteProductEvent {
	private String folderName;

    public DeleteProductEvent(String folderName) {
        this.folderName = folderName;
	}
    
}
