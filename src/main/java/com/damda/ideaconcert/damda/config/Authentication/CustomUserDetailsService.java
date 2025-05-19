// package com.damda.ideaconcert.damda.config.Authentication;

// import java.util.Arrays;
// import java.util.Optional;

// import com.damda.ideaconcert.damda.admin.account.domain.Admin;
// import com.damda.ideaconcert.damda.admin.account.domain.AdminRepository;
// import com.damda.ideaconcert.damda.user.domain.User;
// import com.damda.ideaconcert.damda.user.domain.UserRepository;

// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class CustomUserDetailsService implements UserDetailsService {
//     private final UserRepository userRepository;
//     private final AdminRepository adminRepository;
    
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         // TODO 시큐리티에서 /api/admin 전용 필터 추가하는 법 
//         if(username.equals("admin")) {
//             Optional<Admin> admin = adminRepository.findByAdminId(username);
//             if(admin.isPresent()){
//                 if(admin.get().getRole().equals("ROLE_ADMIN")){
//                     return new UserAuthentication(
//                         admin.get().getAdminId(), 
//                         admin.get().getAdminPw(), 
//                         Arrays.asList(new SimpleGrantedAuthority(admin.get().getRole()))
//                     );
//             }
//         }}   
//         User user = userRepository.findByUserName(username);
//         return new UserAuthentication(
//             user.getUserId(), 
//             user.getUserPw(), 
//             Arrays.asList(new SimpleGrantedAuthority(user.getRole()))
//         );
        
//     }

// }
