package com.damda.ideaconcert.damda.admin.post.domain;

import lombok.Getter;

@Getter
public class DeletePostEvent {
    private String folderName;

    public DeletePostEvent(String folderName) {
        this.folderName = folderName;
	}
}
