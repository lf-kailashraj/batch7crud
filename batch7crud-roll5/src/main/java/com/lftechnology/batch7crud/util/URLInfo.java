package com.lftechnology.batch7crud.util;

import javax.servlet.http.HttpServletRequest;

public class URLInfo {
    private URLInfo() {

    }

    public static String[] getPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        String[] pathParts = null;
        if (path != null) {
            pathParts = path.split("/");
        }
        return pathParts;
    }

}
