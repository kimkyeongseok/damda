package com.damda.ideaconcert.damda.utils;

import java.util.UUID;

public interface NameUtils {
	public static String createUniqueName() {
        String uniqueName = UUID.randomUUID().toString();
        return uniqueName;
    }
    
}
