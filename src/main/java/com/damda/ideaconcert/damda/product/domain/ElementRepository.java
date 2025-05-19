package com.damda.ideaconcert.damda.product.domain;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends JpaRepository <Element, Integer>{
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM element WHERE img LIKE %:path%", nativeQuery = true)
    void deleteByImgLike(@Param("path") String path);
    void deleteByProductId(Integer productId);
}
