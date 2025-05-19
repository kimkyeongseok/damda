package com.damda.ideaconcert.damda.user.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    NOT_BLANK("ERROR_001", "필수 값 누락"),
    MIN_VALUE("ERROR_002", "최소값보다 커야 합니다.");
    private String code;
    private String desc;

}