package com.damda.ideaconcert.damda.following.repository;

import com.damda.ideaconcert.damda.following.domain.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowingRepository extends JpaRepository<Following,Integer>, FollowingRepositoryCustom  {
}
