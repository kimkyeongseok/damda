package com.damda.ideaconcert.damda.user.application;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Valid
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DamdaUserRequest extends AbstractUserRequest {
    @NotBlank(message = "이메일을 입력 해주세요.")
    @Email(message = "이메일 형식으로 입력 해주세요")
    private String userId;
    @NotBlank(message = "닉네임을 입력 해주세요.")
    @Pattern(regexp="^(?=[가-힣a-zA-Z0-9._-]*$)(?=\\S+$).{2,20}", 
    message = "닉네임은 숫자,영어,한국어와 언더스코어가 포함된 2 ~ 20자의 닉네임이어야 합니다.")
    private String nickName;
    @NotBlank(message = "비밀번호를 입력 해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", 
        message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String userPw;
    @NotBlank(message = "이메일을 입력 해주세요.")
    @Email(message = "이메일 형식으로 입력 해주세요")
    private String userEmail;
    @Transient
    @NotBlank(message = "비밀번호를 확인 해주세요.")
    private String userPwCnf;
    private String userAgent;
    //선택 사항 체크
    private String selectReception;
}
