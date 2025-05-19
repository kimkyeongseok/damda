package com.damda.ideaconcert.damda.banner.repository;

import com.damda.ideaconcert.damda.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner,Integer>, BannerRepositoryCustom {
}
