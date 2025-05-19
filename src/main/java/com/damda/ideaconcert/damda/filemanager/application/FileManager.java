package com.damda.ideaconcert.damda.filemanager.application;

import java.io.File;

import com.damda.ideaconcert.damda.product.domain.Element;
import com.damda.ideaconcert.damda.product.domain.Product;

import org.springframework.web.multipart.MultipartFile;

public interface FileManager {
    public String base64ToImage(String dataUrl);
    public void uploadfile(MultipartFile file, File folder, String fileName);
    // TODO 네이밍 확실하게 
    public File getFolderFromFolderName(String folderName);
    public String createPath(Product product, Element element);
	public void deleteFileFromExistFolder(File updateFolder);
    public void deleteFile(String deleteFilePath);
}
