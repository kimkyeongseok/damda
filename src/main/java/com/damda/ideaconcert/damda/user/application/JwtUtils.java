package com.damda.ideaconcert.damda.user.application;

public interface JwtUtils {
    String createToken(String userPk, String nickName, String userId, String role);
    boolean isValidToken(String token);
	String getUserIdFromJWT(String token);
    Payload getPayloadFromJWT(String token);
    
}
