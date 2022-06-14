package com.liu.himusic.model.bean;

import java.io.Serializable;

public class SongPlayBean implements Serializable {


    /**
     * id : 470759757
     * url : http://m8.music.126.net/20220604154142/1a5b73add75fff487013b856e7c3a001/ymusic/e5da/e49a/0e6b/5e7e3d12d3d37eea00c6357e328bef15.mp3
     * br : 128000
     * size : 4890584
     * md5 : 5e7e3d12d3d37eea00c6357e328bef15
     * code : 200
     * expi : 1200
     * type : mp3
     * gain : -7.9681
     * fee : 8
     * uf : null
     * payed : 0
     * flag : 0
     * canExtend : false
     * freeTrialInfo : null
     * level : standard
     * encodeType : mp3
     * freeTrialPrivilege : {"resConsumable":false,"userConsumable":false,"listenType":null}
     * freeTimeTrialPrivilege : {"resConsumable":false,"userConsumable":false,"type":0,"remainTime":0}
     * urlSource : 0
     */

    public Long id;
    public String url;
    public Long br;
    public Long size;
    public String md5;
    public int code;
    public int expi;
    public String type;
    public double gain;
    public int fee;
    public Object uf;
    public int payed;
    public int flag;
    public boolean canExtend;
    public Object freeTrialInfo;
    public String level;
    public String encodeType;
    public FreeTrialPrivilegeBean freeTrialPrivilege;
    public FreeTimeTrialPrivilegeBean freeTimeTrialPrivilege;
    public int urlSource;

    public static class FreeTrialPrivilegeBean implements Serializable {
        /**
         * resConsumable : false
         * userConsumable : false
         * listenType : null
         */

        public boolean resConsumable;
        public boolean userConsumable;
        public Object listenType;
    }

    public static class FreeTimeTrialPrivilegeBean implements Serializable {
        /**
         * resConsumable : false
         * userConsumable : false
         * type : 0
         * remainTime : 0
         */

        public boolean resConsumable;
        public boolean userConsumable;
        public int type;
        public int remainTime;
    }
}
