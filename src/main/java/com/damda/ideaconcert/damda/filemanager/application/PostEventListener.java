package com.damda.ideaconcert.damda.filemanager.application;

import com.damda.ideaconcert.damda.admin.post.domain.DeletePostEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostEventListener {
    private final FileManager fileManager;
    
    @EventListener
    public void deleteFile(DeletePostEvent deletePostEvent) {
        String filePath = deletePostEvent.getFolderName();
        fileManager.deleteFile(filePath);
        
    }
}
