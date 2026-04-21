package com.nimbasms.nimbasms.common;

import okhttp3.OkHttpClient;

public class ApiBase {
    // Static singleton instance for OkHttpClient
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static OkHttpClient getHttpClient() {
        return okHttpClient;
    }

    public Response fetchResponse(String url, int offset) {
        // Validate offset parameter
        if (offset < 0) {
            throw new IllegalArgumentException("Offset must be a non-negative integer.");
        }
        // implement fetch logic using singleton instance
        //...
    }
}
