package com.damda.ideaconcert.damda.video.representation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.damda.ideaconcert.damda.utils.DevicesCheck;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    @GetMapping("/play")
    public ResponseEntity<Object> getVideo(HttpServletRequest request) {
        // String currentUrl = request.getRequestURL().toString();
        // String origin = currentUrl.split("api")[0];
        String videoPath;
        if(DevicesCheck.getDevices(request).equals("PC")){
            videoPath = "video/pc.mp4";
        }else {
            videoPath = "video/mobile.mp4";
        }
        Map<String , String> response = new HashMap<>();
        response.put("path",  videoPath);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
