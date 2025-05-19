package com.damda.ideaconcert.damda.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.codec.binary.Base64;

public interface DecodeJWT {
	public static Map<String, Object> decode(String token, int part ) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String , Object> resultMap = new HashMap<>();
        // 0 : 헤더
        // 1 : 페이로드
        // 2 : 서명 
        String[] splitToken = token.split("\\.");
        Base64 base64Url = new Base64(true);
        String result = new String(base64Url.decode(splitToken[part]));
        try {
            resultMap = objectMapper.readValue(result, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultMap;

    }
   
}
