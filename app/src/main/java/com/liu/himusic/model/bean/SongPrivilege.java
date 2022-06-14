package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class SongPrivilege implements Serializable {

    /**
     * id : 30431367
     * fee : 8
     * payed : 0
     * realPayed : 0
     * st : 0
     * pl : 128000
     * dl : 0
     * sp : 7
     * cp : 1
     * subp : 1
     * cs : false
     * maxbr : 999000
     * fl : 128000
     * pc : null
     * toast : false
     * flag : 4
     * paidBigBang : false
     * preSell : false
     * playMaxbr : 999000
     * downloadMaxbr : 999000
     * maxBrLevel : lossless
     * playMaxBrLevel : lossless
     * downloadMaxBrLevel : lossless
     * plLevel : standard
     * dlLevel : none
     * flLevel : standard
     * rscl : null
     * freeTrialPrivilege : {"resConsumable":false,"userConsumable":false,"listenType":1}
     * chargeInfoList : [{"rate":128000,"chargeUrl":null,"chargeMessage":null,"chargeType":0},{"rate":192000,"chargeUrl":null,"chargeMessage":null,"chargeType":1},{"rate":320000,"chargeUrl":null,"chargeMessage":null,"chargeType":1},{"rate":999000,"chargeUrl":null,"chargeMessage":null,"chargeType":1}]
     */

    public int id;
    public int fee;
    public int payed;
    public int realPayed;
    public int st;
    public int pl;
    public int dl;
    public int sp;
    public int cp;
    public int subp;
    public boolean cs;
    public int maxbr;
    public int fl;
    public Object pc;
    public boolean toast;
    public int flag;
    public boolean paidBigBang;
    public boolean preSell;
    public int playMaxbr;
    public int downloadMaxbr;
    public String maxBrLevel;
    public String playMaxBrLevel;
    public String downloadMaxBrLevel;
    public String plLevel;
    public String dlLevel;
    public String flLevel;
    public Object rscl;
    public FreeTrialPrivilegeBean freeTrialPrivilege;
    public List<ChargeInfoListBean> chargeInfoList;

    public static class FreeTrialPrivilegeBean implements Serializable {
        /**
         * resConsumable : false
         * userConsumable : false
         * listenType : 1
         */

        public boolean resConsumable;
        public boolean userConsumable;
        public int listenType;
    }

    public static class ChargeInfoListBean implements Serializable {
        /**
         * rate : 128000
         * chargeUrl : null
         * chargeMessage : null
         * chargeType : 0
         */

        public int rate;
        public String chargeUrl;
        public String chargeMessage;
        public int chargeType;
    }
}
