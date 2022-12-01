package com.liu.himusic.http.server;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestServer;

/**
 *    desc   : 正式环境
 */
public class ReleaseServer implements IRequestServer {

    @NonNull
    @Override
    public String getHost() {
        return "http://81.69.240.210:3000/";
    }
}