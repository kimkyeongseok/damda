package com.damda.ideaconcert.damda.mystorage.repository;

import com.damda.ideaconcert.damda.mystorage.domain.MyStorage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyStorageRepositoryCustom {
    List<MyStorage> myStorageByList(int userId);
    MyStorage myStorageByIds(int myStorageNo);
}
