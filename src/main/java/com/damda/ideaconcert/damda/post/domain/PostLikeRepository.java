package com.damda.ideaconcert.damda.post.domain;

import java.util.Optional;

import com.damda.ideaconcert.damda.post.application.PostLikeUserDto;
import com.damda.ideaconcert.damda.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    Page<PostLike> findByUserPk(Integer id, Pageable pageable);
	Optional<PostLike> findByPostIdAndUserPk(int postId, int userPk);
    Page<PostLike> findByUserPkOrderByLikedDateDesc(int userPk, Pageable pageable);
    Page<PostLike> findByUserPkOrderByLikedDateAsc(int userPk, Pageable pageable);
    void deleteByPostId(int postId);
    @Query(value = "SELECT * FROM post_like WHERE BINARY post_id=:postId ORDER BY liked_date ASC LIMIT 1 ", nativeQuery = true)
    PostLike findByIds(@Param("postId")int postId);
}
