package com.damda.ideaconcert.damda.user.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE BINARY id=:userPk", nativeQuery = true)
    Optional<User> findById(@Param("userPk")int userPk);
    @Query(value = "SELECT * FROM user WHERE BINARY id=:userPk ", nativeQuery = true)
    User findByIds(@Param("userPk")int userPk);
    @Query(value = "SELECT * FROM user WHERE BINARY user_id=:userId", nativeQuery = true)
    Optional<User> findByUserId(@Param("userId")String userId);
    @Query(value = "SELECT * FROM user WHERE BINARY user_id=:userId", nativeQuery = true)
    User findByUserIds(@Param("userId")String userId);
    @Query(value = "SELECT * FROM user WHERE BINARY user_email=:userEmail", nativeQuery = true)
    User findByUserEmail(@Param("userEmail")String userEmail);
    Optional<User> findByUserIdAndNickName(String userId, String nickName);
    List<User> findByIdIn(Set<Integer> userIds);
}