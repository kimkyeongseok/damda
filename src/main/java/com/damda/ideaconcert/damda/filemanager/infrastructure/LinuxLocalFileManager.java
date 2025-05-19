package com.damda.ideaconcert.damda.filemanager.infrastructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Base64;

import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.product.domain.Element;
import com.damda.ideaconcert.damda.product.domain.Product;
import com.damda.ideaconcert.damda.utils.NameUtils;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Component
@Profile({ "dev","prod" })
public class LinuxLocalFileManager implements FileManager {
    @Override
    public void uploadfile(MultipartFile file, File folder, String fileName) {
        File uploadFiles = new File(folder.getAbsolutePath() + "/" + fileName);
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(uploadFiles));
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public File getFolderFromFolderName(String folderName) {
        String path = "./images/" + folderName + "/";
        File folder = new File(path);

        if(!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    @Override
    public String createPath(Product product, Element element) {
        return "/images/" + product.getPreview() + "/" + element.getImg();
    }

    @Override
    public void deleteFileFromExistFolder(File updateFolder) {
        File[] deleteFolderList = updateFolder.listFiles();
        for(int i = 0; i < deleteFolderList.length; i++) {
            deleteFolderList[i].delete();
        }
    }

    @Override
    public String base64ToImage(String dataUrl) {
        String uuid = NameUtils.createUniqueName();
        String currentDate =  Instant.now().toString().split("T")[0];
        String path = "./upload/" + currentDate;
        /*
        * base64ToPng
        */ 
        File folder = new File(path);

        if(!folder.exists()) {
            folder.mkdirs();
        }

        try {
            File file = new File(path + "/" + uuid + ".png");
            byte[] decodedBytes = Base64.getMimeDecoder().decode(dataUrl.split(",")[1]);
            
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(decodedBytes);
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/upload/"+ currentDate  + "/" + uuid + ".png";
    }

    @Override
    public void deleteFile(String deleteFilePath) {
        File deleteFile = new File("." + deleteFilePath);
        deleteFile.delete();
        
    }
    
}
