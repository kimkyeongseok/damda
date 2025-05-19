package com.damda.ideaconcert.damda.admin.post.representaion;

import java.util.List;

import com.damda.ideaconcert.damda.admin.post.application.AdminPostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/management/posts")
public class AdminPostController {
    private final AdminPostService adminPostService;

    @GetMapping("/total")
    public long getTotal() {
        return adminPostService.total();
    }

    @GetMapping("")
    public List<AdminPostReadDto> read() {
        return adminPostService.read();
    }
    @DeleteMapping("/{postId}")
    public void delete(@PathVariable int postId) {
        adminPostService.delete(postId);
    }
    
}
