package com.damda.ideaconcert.damda.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/page")
public class AdminController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "admin/login";
    }

    @GetMapping("/main")
    public String getMainPage() {
        return "admin/main";
    }
    @GetMapping("/management/users")
    public String getUserManagementPage(){
        return "admin/user/management";
    }
    @GetMapping("/management/posts")
    public String getPostManagementPage(){
        return "admin/post/management";
    }
    @GetMapping("/management/product/add")
    public String getProductAddPage() {
        return "admin/product/add";
    }
    @GetMapping("/management/product")
    public String getProductManagementPage() {
        return "admin/product/management";
    }
    @GetMapping("/management/payments")
    public String getPaymentManagementPage() {
        return "admin/payment/management";
    }
    @GetMapping("/management/background")
    public String getBackgroundManagementPage() {
        return "admin/admin_background_management";
    }
    
}
