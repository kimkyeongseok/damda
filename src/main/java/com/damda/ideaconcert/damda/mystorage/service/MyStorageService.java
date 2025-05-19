package com.damda.ideaconcert.damda.mystorage.service;

import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.mystorage.domain.MyStorage;
import com.damda.ideaconcert.damda.mystorage.dto.MyStorageInsertReq;
import com.damda.ideaconcert.damda.mystorage.repository.MyStorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyStorageService {
    private final MyStorageRepository myStorageRepository;
    private final FileManager fileManager;

    @Transactional
    public void myStorageInsert(int userId, MyStorageInsertReq req){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        String now_dt = format.format(now);

        String imgUrl = fileManager.base64ToImage(req.getImgUrl());

        MyStorage myStorage = MyStorage.builder()
                .user_id(userId)
                .imgUrl(imgUrl)
                .regdate(now_dt)
                .build();

        myStorageRepository.save(myStorage);
    }
    @Transactional
    public void myStorageDelete(int myStorageNo){
        MyStorage myStorage = myStorageRepository.myStorageByIds(myStorageNo);

        myStorage.myStorageDelete(
                "Y"
        );
    }
    public List<MyStorage> myStorageList(int userId){
        List<MyStorage> myStorage = myStorageRepository.myStorageByList(userId);
        if(myStorage != null) {
            return myStorage;
        }else{
            return null;
        }
    }

}
