package com.liu.himusic.http.api;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import org.jetbrains.annotations.NotNull;

public final class FindHomeApi implements IRequestApi  {
    @NonNull
    @Override
    public String getApi() {
        return "homepage/block/page";
    }

    private boolean refresh;
    private  String cursor;
    private  String cookie;

    public FindHomeApi toFind( boolean refresh,
                                  String cursor,
                                  String cookie) {
        this.refresh = refresh;
        this.cursor = cursor;
        this.cookie = cookie;
        return this;
    }

}
