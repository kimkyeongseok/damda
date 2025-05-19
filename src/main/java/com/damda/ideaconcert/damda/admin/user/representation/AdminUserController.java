package com.damda.ideaconcert.damda.admin.user.representation;

import java.util.List;

import com.damda.ideaconcert.damda.admin.user.application.AdminUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/admin/management/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;

    @GetMapping("/total")
    public long getTotal() {
        return adminUserService.total();
    }
    @GetMapping("")
    public List<UserReadDto> getUserList() {
        return adminUserService.read();
    }
    @PutMapping("/deactivate/{id}")
    public void deactivateUser(@PathVariable int id) {
       adminUserService.deactivate(id);
    }
    @PutMapping("/active/{id}")
    public void activeUser(@PathVariable int id) {
       adminUserService.active(id);
    }
    
}
