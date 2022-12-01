package com.liu.himusic.model.bean;


import java.io.Serializable;
import java.util.List;

public class FindBean implements Serializable {
    private String cursor;//上一次数据
    private List<BlockBean> blocks;
    private boolean hasMore;
    private boolean refresh;
    private String blockUUIDs;
    private Object pageConfig;
    private Object guideToast;
    private String internalTest;
    private List<Object> titles;
    private String blockCodeOrderList;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public List<BlockBean> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockBean> blocks) {
        this.blocks = blocks;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getBlockUUIDs() {
        return blockUUIDs;
    }

    public void setBlockUUIDs(String blockUUIDs) {
        this.blockUUIDs = blockUUIDs;
    }

    public Object getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(Object pageConfig) {
        this.pageConfig = pageConfig;
    }

    public Object getGuideToast() {
        return guideToast;
    }

    public void setGuideToast(Object guideToast) {
        this.guideToast = guideToast;
    }

    public String getInternalTest() {
        return internalTest;
    }

    public void setInternalTest(String internalTest) {
        this.internalTest = internalTest;
    }

    public List<Object> getTitles() {
        return titles;
    }

    public void setTitles(List<Object> titles) {
        this.titles = titles;
    }

    public String getBlockCodeOrderList() {
        return blockCodeOrderList;
    }

    public void setBlockCodeOrderList(String blockCodeOrderList) {
        this.blockCodeOrderList = blockCodeOrderList;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}
