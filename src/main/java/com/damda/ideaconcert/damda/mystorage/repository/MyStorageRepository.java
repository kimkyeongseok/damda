package com.damda.ideaconcert.damda.mystorage.repository;

import com.damda.ideaconcert.damda.mystorage.domain.MyStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyStorageRepository extends JpaRepository<MyStorage,Integer>, MyStorageRepositoryCustom {
}
