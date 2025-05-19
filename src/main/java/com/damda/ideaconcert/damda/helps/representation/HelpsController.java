package com.damda.ideaconcert.damda.helps.representation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/helps")
public class HelpsController {
    @GetMapping("")
    public String getWebHelpPage(HttpServletRequest request) {
        return "helps/mobile";
    }
  
    @GetMapping("/m")
    public String getMobileHelpPage() {
        return "helps/mobile";
    }
    @GetMapping("/license")
    public String getOpensourceLicense() {
        return "helps/license";
    }
    
    @GetMapping("/privacy/policy")
    public String getPrivacyPolicy() {
      return "helps/privacy_policy";
    }
    @GetMapping("/terms")
    public String getServiceTerms() {
      return "helps/service_terms";
    }

    @GetMapping("/verify")
    public String getVerifyUserPageForResetPassword(){
        return "helps/verify_user";
    }
    
    @GetMapping("/deactivate")
    public String getDeactivateUserPage() {
        return "helps/deactivate_user";
    }
    
}
