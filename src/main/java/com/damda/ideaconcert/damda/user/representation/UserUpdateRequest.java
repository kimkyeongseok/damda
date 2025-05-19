package com.damda.ideaconcert.damda.user.representation;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Valid
public class UserUpdateRequest {
    @NotBlank(message = "닉네임을 입력해주세요")
    @Pattern(regexp="^(?=[가-힣a-zA-Z0-9._-]*$)(?=\\S+$).{2,20}", 
        message = "닉네임은 숫자,영어,한국어와 언더스코어가 포함된 2 ~ 20자의 닉네임이어야 합니다.")
    String nickName;
    String snsUrl;
    MultipartFile imgUrl;
}
