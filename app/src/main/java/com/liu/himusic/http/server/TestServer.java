package com.liu.himusic.http.server;

import androidx.annotation.NonNull;

/**
 *    desc   : 测试环境
 */
public class TestServer extends ReleaseServer {

    @NonNull
    @Override
    public String getHost() {
        return "http://192.168.3.8:3000/";
    }
}