package com.damda.ideaconcert.damda.user.application;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.damda.ideaconcert.damda.filemanager.application.FileManager;
import com.damda.ideaconcert.damda.helps.application.HelpsService;
import com.damda.ideaconcert.damda.mail.infrastructure.AuthUserMailSender;
import com.damda.ideaconcert.damda.user.domain.User;
import com.damda.ideaconcert.damda.user.domain.UserRepository;
import com.damda.ideaconcert.damda.user.representation.LoginResponse;
import com.damda.ideaconcert.damda.user.representation.SignUpResponse;
import com.damda.ideaconcert.damda.user.representation.UserUpdateRequest;
import com.damda.ideaconcert.damda.user.representation.UserUpdateResponse;

import com.damda.ideaconcert.damda.utils.NameUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DamdaService extends AbstractUserService<DamdaUserRequest> {
    private final HelpsService helpsService;

    public DamdaService(
        UserRepository userRepository, 
        PasswordEncoder passwordEncoder, 
        JwtUtils jwtUtils,
        FileManager fileManager,
        HelpsService helpsService,
        AuthUserMailSender authUserMailSender
    ) {
        super(userRepository, passwordEncoder, jwtUtils,fileManager,authUserMailSender);
        this.helpsService = helpsService;
    }

    @Override
    @Transactional
    public LoginResponse login(DamdaUserRequest userRequest, String userAgent) { 
        boolean userCheck = this.userInfoExistFromServer(userRequest);
        if(userCheck){
            Optional<User> userInfo = userRepository.findByUserId(userRequest.getUserId());
            User user = userRepository.findByUserIds(userRequest.getUserId());
            boolean isAuth = userInfo.get().isAuth();
            if(isAuth){
                DamdaUserRequest damdaUserRequest = new DamdaUserRequest(
                    userRequest.getUserId(),
                    user.getNickName(),
                    userRequest.getUserPw(),
                    userRequest.getUserPw(),
                    userRequest.getUserEmail(),
                    userAgent,
                    userRequest.getSelectReception()
                );
                return doLogin(damdaUserRequest);
            }else{
                return helpsService.sendEmailForAuthUser(
                        userRequest.getUserId(), 
                        userInfo.get().getNickName()
                    ); 
            }
        } else {
            return new LoginResponse(
                400, 
                "", 
                "가입하지 않은 아이디이거나, 잘못된 비밀번호 입니다.", 
                null
            );
        }
        
    }

    public SignUpResponse damdaSignUp(DamdaUserRequest userRequest) {
        String inputUserId = userRequest.getUserId();
        String inputUserPw = userRequest.getUserPw();
        // 동일한 아이디가 없다면
        if (idCheck(inputUserId)) {
            return new SignUpResponse(409, "email already exists", "이미 가입된 이메일 입니다");
        } else {
            if (!inputUserPw.equals(userRequest.getUserPwCnf())) {
                 return new SignUpResponse(401, "password mismatch", "비밀번호가 일치하지 않습니다.");
            }
            String encodedPassword = passwordEncode(inputUserPw);
            DamdaUserRequest damdaUserRequest = new DamdaUserRequest(
                userRequest.getUserId(), 
                userRequest.getNickName(), 
                encodedPassword,
                userRequest.getUserEmail(),
                userRequest.getUserPwCnf(), 
                userRequest.getUserAgent(),
                userRequest.getSelectReception()
            );
            doDamdaSignUp(damdaUserRequest);
            return new SignUpResponse(200,"sign up success","담다 회원가입에 성공하였습니다.");
        }
    }
    @Transactional
    public boolean userInfoExistFromServer(DamdaUserRequest userRequest) {
        Optional<User> userInfo = userRepository.findByUserId(userRequest.getUserId());
        if(userInfo.isPresent()) {
            String rawPassword = userRequest.getUserPw();
            String encodedPassword = userInfo.get().getUserPw();
            boolean isCorrect = passwordMatch(rawPassword, encodedPassword);
            return isCorrect ? true : false; 
        }else{
            return false;
        }
    }

    private boolean passwordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String passwordEncode(String inputUserPw) {
        return passwordEncoder.encode(inputUserPw);
    }

    @Transactional
    public UserUpdateResponse updateUserInfo(UserUpdateRequest request, int userPk) {

        User user = userRepository.findById(userPk).orElseThrow(() -> new NoSuchElementException());

        String folderName = NameUtils.createUniqueName();
        File updateFolder = fileManager.getFolderFromFolderName(folderName);

        MultipartFile updateImg = request.getImgUrl();
        if (updateImg.getSize() != 0) {
            fileManager.deleteFileFromExistFolder(updateFolder);
        }

        String filePath = NameUtils.createUniqueName();
        String imageName = request.getImgUrl().getOriginalFilename();
        String extension = getImageFileExtension(imageName);
        filePath += "." + extension;
        String fileUrl = updateFolder+"/"+filePath;

        fileManager.uploadfile(request.getImgUrl(), updateFolder, filePath);

        user.setNickName(request.getNickName());
        user.setSnsUrl(request.getSnsUrl());
        user.setImgUrl(fileUrl);


        return new UserUpdateResponse(
            200, 
            "수정 완료"
        );
    }
    private String getImageFileExtension(String imageName) {
        return imageName.substring(imageName.lastIndexOf(".") + 1);
    }
    @Override
    public LoginResponse signUp(DamdaUserRequest t) {
        return null;
    }
}
