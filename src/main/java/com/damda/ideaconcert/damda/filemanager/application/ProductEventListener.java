package com.damda.ideaconcert.damda.filemanager.application;

import java.io.File;

import com.damda.ideaconcert.damda.product.domain.DeleteProductEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductEventListener {
    private final FileManager fileManager;
    
    @EventListener
    public void deleteFile(DeleteProductEvent deleteProductEvent) {
        String folderName = deleteProductEvent.getFolderName();
        File productLocalFolder = fileManager.getFolderFromFolderName(folderName);
        fileManager.deleteFileFromExistFolder(productLocalFolder);
        productLocalFolder.delete();
    }
}
