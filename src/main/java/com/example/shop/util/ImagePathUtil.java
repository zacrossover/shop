package com.example.shop.util;

import org.springframework.boot.system.ApplicationHome;

import io.micrometer.common.util.StringUtils;

public class ImagePathUtil {
	private static final String sep = System.getProperty("file.separator");
	private static final String IMAGE_URI = sep + "image" + sep;
	private static String IMAGE_URL = "";
	
    public static String getImagePath() {
		if(StringUtils.isBlank(IMAGE_URL)) {
			ImagePathUtil u = new ImagePathUtil();
			u.init();
		}
        return IMAGE_URL;
    }
    
    private void init() {
    	ApplicationHome applicationHome = new ApplicationHome(getClass());
        IMAGE_URL = applicationHome.getSource().getParentFile().toString() + IMAGE_URI;
    }

}
