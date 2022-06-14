package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class LoginBean  {

    /**
     * loginType : 1
     * code : 200
     * account : {"id":7851767652,"userName":"1_15730364992","type":1,"status":0,"whitelistAuthority":0,"createTime":1652234695685,"salt":"[B@34c29c28","tokenVersion":0,"ban":0,"baoyueVersion":0,"donateVersion":0,"vipType":0,"viptypeVersion":0,"anonimousUser":false,"uninitialized":false}
     * token : 95d9fa3e04d5bc34662fbc60b5480384863d7893856b4545a3be41fab1d982ae993166e004087dd381ad234619ad30512c08b7d231713c712eb7dd4c060531f35d3dccaf923476bc7a561ba977ae766d
     * profile : {"followed":false,"backgroundUrl":"https://p3.music.126.net/_f8R60U9mZ42sSNvdPn2sQ==/109951162868126486.jpg","detailDescription":"","avatarImgIdStr":"109951165647004069","backgroundImgIdStr":"109951162868126486","userId":7851767652,"userType":0,"gender":2,"accountStatus":0,"vipType":0,"nickname":"佛系少年2205","avatarImgId":109951165647004060,"backgroundImgId":109951162868126480,"birthday":-2209017600000,"avatarUrl":"https://p4.music.126.net/SUeqMM8HOIpHv9Nhl9qt9w==/109951165647004069.jpg","city":510100,"defaultAvatar":false,"province":510000,"expertTags":null,"experts":{},"authStatus":0,"mutual":false,"remarkName":null,"djStatus":0,"description":"","signature":"","authority":0,"avatarImgId_str":"109951165647004069","followeds":0,"follows":1,"eventCount":0,"avatarDetail":null,"playlistCount":1,"playlistBeSubscribedCount":0}
     * bindings : [{"userId":7851767652,"url":"","expired":false,"tokenJsonStr":"{\"countrycode\":\"\",\"cellphone\":\"15730364992\",\"hasPassword\":true}","bindingTime":1652234740893,"expiresIn":2147483647,"refreshTime":1652234740,"id":13593088926,"type":1},{"userId":7851767652,"url":"","expired":true,"tokenJsonStr":"{\"birthday\":-2209017600000,\"country\":\"\",\"unionid\":\"oZoefuNqA7n7OoFg2JZD6P-wDS80\",\"gender\":0,\"avatarUrl\":\"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKotwgsX9quR5FUGH2e4D9WTLAyEcpo2zlIftmlfTINzxHVI7YFIXEfepsDiciaicVusOxWK76cDWjFg/132\",\"city\":100,\"signature\":null,\"openid\":\"okvmMjrag9qyBcY3PWlU8cb1B1Ek\",\"mobile\":null,\"screenName\":null,\"access_token\":\"56_wikvgtU6-T78RI_7jh0kuDgEHTH_AaZ9oggbAUmA57C12nmic64h8N_y7Y8yZFLSkTFl5kcUsJgZiIgAPjfZPlb5WBAYZ10-XFh6s3xmpGs\",\"refresh_token\":\"56_nHK-VYFi3nDYgELI1qf4jDjmUvFGTRhHcE8QFwZOpM5JtSkyHV1v_aChJRpLARioXOQWgjFmemJVk8eebqjHFIMRpgXYw968n29vmcyGeV4\",\"uid\":\"oZoefuNqA7n7OoFg2JZD6P-wDS80\",\"province\":0,\"scope\":\"snsapi_userinfo\",\"nickname\":\"佛系少年\",\"followersCount\":0,\"expires_in\":7200,\"followingsCount\":0}","bindingTime":1652234695844,"expiresIn":7200,"refreshTime":1652541313,"id":13593062906,"type":10}]
     * cookie : MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/wapi/feedback;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/weapi/feedback;;MUSIC_U=95d9fa3e04d5bc34662fbc60b5480384863d7893856b4545a3be41fab1d982ae993166e004087dd381ad234619ad30512c08b7d231713c712eb7dd4c060531f35d3dccaf923476bc7a561ba977ae766d; Max-Age=1296000; Expires=Tue, 31 May 2022 03:37:55 GMT; Path=/;;MUSIC_SNS=; Max-Age=0; Expires=Mon, 16 May 2022 03:37:55 GMT; Path=/;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/weapi/clientlog;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/wapi/clientlog;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/wapi/clientlog;;__csrf=bcf8fb0154537045232ffed5f44dace2; Max-Age=1296010; Expires=Tue, 31 May 2022 03:38:05 GMT; Path=/;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/weapi/clientlog;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/weapi/feedback;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/neapi/clientlog;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/api/feedback;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/openapi/clientlog;;__remember_me=true; Max-Age=1296000; Expires=Tue, 31 May 2022 03:37:55 GMT; Path=/;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/wapi/feedback;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/neapi/clientlog;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/eapi/feedback;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/neapi/feedback;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/api/feedback;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/api/clientlog;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/api/clientlog;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/eapi/clientlog;;MUSIC_A_T=1652234695685; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/neapi/feedback;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/eapi/feedback;;MUSIC_R_T=1652234744043; Max-Age=2147483647; Expires=Sat, 03 Jun 2090 06:52:02 GMT; Path=/openapi/clientlog;
     */

    private int loginType;
    private int code;
    private AccountBean account;
    private String token;
    private ProfileBean profile;
    private String cookie;
    private List<BindingsBean> bindings;

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ProfileBean getProfile() {
        return profile;
    }

    public void setProfile(ProfileBean profile) {
        this.profile = profile;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public List<BindingsBean> getBindings() {
        return bindings;
    }

    public void setBindings(List<BindingsBean> bindings) {
        this.bindings = bindings;
    }

    public static class AccountBean  {
        /**
         * id : 7851767652
         * userName : 1_15730364992
         * type : 1
         * status : 0
         * whitelistAuthority : 0
         * createTime : 1652234695685
         * salt : [B@34c29c28
         * tokenVersion : 0
         * ban : 0
         * baoyueVersion : 0
         * donateVersion : 0
         * vipType : 0
         * viptypeVersion : 0
         * anonimousUser : false
         * uninitialized : false
         */

        private long id;
        private String userName;
        private int type;
        private int status;
        private int whitelistAuthority;
        private long createTime;
        private String salt;
        private int tokenVersion;
        private int ban;
        private int baoyueVersion;
        private int donateVersion;
        private int vipType;
        private int viptypeVersion;
        private boolean anonimousUser;
        private boolean uninitialized;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getWhitelistAuthority() {
            return whitelistAuthority;
        }

        public void setWhitelistAuthority(int whitelistAuthority) {
            this.whitelistAuthority = whitelistAuthority;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public int getTokenVersion() {
            return tokenVersion;
        }

        public void setTokenVersion(int tokenVersion) {
            this.tokenVersion = tokenVersion;
        }

        public int getBan() {
            return ban;
        }

        public void setBan(int ban) {
            this.ban = ban;
        }

        public int getBaoyueVersion() {
            return baoyueVersion;
        }

        public void setBaoyueVersion(int baoyueVersion) {
            this.baoyueVersion = baoyueVersion;
        }

        public int getDonateVersion() {
            return donateVersion;
        }

        public void setDonateVersion(int donateVersion) {
            this.donateVersion = donateVersion;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public int getViptypeVersion() {
            return viptypeVersion;
        }

        public void setViptypeVersion(int viptypeVersion) {
            this.viptypeVersion = viptypeVersion;
        }

        public boolean isAnonimousUser() {
            return anonimousUser;
        }

        public void setAnonimousUser(boolean anonimousUser) {
            this.anonimousUser = anonimousUser;
        }

        public boolean isUninitialized() {
            return uninitialized;
        }

        public void setUninitialized(boolean uninitialized) {
            this.uninitialized = uninitialized;
        }
    }

    public static class ProfileBean {
        /**
         * followed : false
         * backgroundUrl : https://p3.music.126.net/_f8R60U9mZ42sSNvdPn2sQ==/109951162868126486.jpg
         * detailDescription :
         * avatarImgIdStr : 109951165647004069
         * backgroundImgIdStr : 109951162868126486
         * userId : 7851767652
         * userType : 0
         * gender : 2
         * accountStatus : 0
         * vipType : 0
         * nickname : 佛系少年2205
         * avatarImgId : 109951165647004060
         * backgroundImgId : 109951162868126480
         * birthday : -2209017600000
         * avatarUrl : https://p4.music.126.net/SUeqMM8HOIpHv9Nhl9qt9w==/109951165647004069.jpg
         * city : 510100
         * defaultAvatar : false
         * province : 510000
         * expertTags : null
         * experts : {}
         * authStatus : 0
         * mutual : false
         * remarkName : null
         * djStatus : 0
         * description :
         * signature :
         * authority : 0
         * avatarImgId_str : 109951165647004069
         * followeds : 0
         * follows : 1
         * eventCount : 0
         * avatarDetail : null
         * playlistCount : 1
         * playlistBeSubscribedCount : 0
         */

        private boolean followed;
        private String backgroundUrl;
        private String detailDescription;
        private String avatarImgIdStr;
        private String backgroundImgIdStr;
        private long userId;
        private int userType;
        private int gender;
        private int accountStatus;
        private int vipType;
        private String nickname;
        private long avatarImgId;
        private long backgroundImgId;
        private long birthday;
        private String avatarUrl;
        private int city;
        private boolean defaultAvatar;
        private int province;
        private String expertTags;
        private ExpertsBean experts;
        private int authStatus;
        private boolean mutual;
        private String remarkName;
        private int djStatus;
        private String description;
        private String signature;
        private int authority;
        private String avatarImgId_str;
        private int followeds;
        private int follows;
        private int eventCount;
        private String avatarDetail;
        private int playlistCount;
        private int playlistBeSubscribedCount;

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public String getAvatarImgIdStr() {
            return avatarImgIdStr;
        }

        public void setAvatarImgIdStr(String avatarImgIdStr) {
            this.avatarImgIdStr = avatarImgIdStr;
        }

        public String getBackgroundImgIdStr() {
            return backgroundImgIdStr;
        }

        public void setBackgroundImgIdStr(String backgroundImgIdStr) {
            this.backgroundImgIdStr = backgroundImgIdStr;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getAvatarImgId() {
            return avatarImgId;
        }

        public void setAvatarImgId(long avatarImgId) {
            this.avatarImgId = avatarImgId;
        }

        public long getBackgroundImgId() {
            return backgroundImgId;
        }

        public void setBackgroundImgId(long backgroundImgId) {
            this.backgroundImgId = backgroundImgId;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public boolean isDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(boolean defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(String expertTags) {
            this.expertTags = expertTags;
        }

        public ExpertsBean getExperts() {
            return experts;
        }

        public void setExperts(ExpertsBean experts) {
            this.experts = experts;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public boolean isMutual() {
            return mutual;
        }

        public void setMutual(boolean mutual) {
            this.mutual = mutual;
        }

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public int getDjStatus() {
            return djStatus;
        }

        public void setDjStatus(int djStatus) {
            this.djStatus = djStatus;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getAuthority() {
            return authority;
        }

        public void setAuthority(int authority) {
            this.authority = authority;
        }

        public String getAvatarImgId_str() {
            return avatarImgId_str;
        }

        public void setAvatarImgId_str(String avatarImgId_str) {
            this.avatarImgId_str = avatarImgId_str;
        }

        public int getFolloweds() {
            return followeds;
        }

        public void setFolloweds(int followeds) {
            this.followeds = followeds;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getEventCount() {
            return eventCount;
        }

        public void setEventCount(int eventCount) {
            this.eventCount = eventCount;
        }

        public Object getAvatarDetail() {
            return avatarDetail;
        }

        public void setAvatarDetail(String avatarDetail) {
            this.avatarDetail = avatarDetail;
        }

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public int getPlaylistBeSubscribedCount() {
            return playlistBeSubscribedCount;
        }

        public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
            this.playlistBeSubscribedCount = playlistBeSubscribedCount;
        }

        public static class ExpertsBean {
        }
    }

    public static class BindingsBean {
        /**
         * userId : 7851767652
         * url :
         * expired : false
         * tokenJsonStr : {"countrycode":"","cellphone":"15730364992","hasPassword":true}
         * bindingTime : 1652234740893
         * expiresIn : 2147483647
         * refreshTime : 1652234740
         * id : 13593088926
         * type : 1
         */

        private long userId;
        private String url;
        private boolean expired;
        private String tokenJsonStr;
        private long bindingTime;
        private int expiresIn;
        private int refreshTime;
        private long id;
        private int type;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isExpired() {
            return expired;
        }

        public void setExpired(boolean expired) {
            this.expired = expired;
        }

        public String getTokenJsonStr() {
            return tokenJsonStr;
        }

        public void setTokenJsonStr(String tokenJsonStr) {
            this.tokenJsonStr = tokenJsonStr;
        }

        public long getBindingTime() {
            return bindingTime;
        }

        public void setBindingTime(long bindingTime) {
            this.bindingTime = bindingTime;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public int getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(int refreshTime) {
            this.refreshTime = refreshTime;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
