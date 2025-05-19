package com.damda.ideaconcert.damda.follower.repository;

import com.damda.ideaconcert.damda.follower.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower,Integer>,FollowerRepositoryCustom {
}
