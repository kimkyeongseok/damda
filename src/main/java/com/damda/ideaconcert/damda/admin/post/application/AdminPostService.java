package com.damda.ideaconcert.damda.admin.post.application;

import java.util.List;

import com.damda.ideaconcert.damda.admin.post.representaion.AdminPostReadDto;

public interface AdminPostService {
    List<AdminPostReadDto> read();
    void delete(int postId);
    long total();
}
