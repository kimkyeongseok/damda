package com.damda.ideaconcert.damda.goods.repository;

import com.damda.ideaconcert.damda.goods.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Integer>, GoodsRepositoryCustom {
}
