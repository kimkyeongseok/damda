package com.damda.ideaconcert.damda.authkey.application;

import lombok.Getter;

@Getter
public class AuthKeyStorageRequest {
    private Type type;
    private String userId;
    private String authKey;

    public AuthKeyStorageRequest(Type type,String userId, String authKey) {
        this.type = type;
        this.userId = userId;
        this.authKey = authKey;
    }

    public enum Type {
        RESET_PASSWORD("resetPassword"),
        AUTH_EMAIL("authEmail");

        private String type;

        Type(String type) {
            this.type = type;
        }
        
        public String getType() {
            return type;
        }
    }
}
