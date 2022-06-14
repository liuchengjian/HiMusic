package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class ExtInfo implements Serializable {

    private List<BannersBean> banners;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public static class BannersBean implements Serializable {
        /**
         * bannerId : 1652623851833926
         * pic : http://p1.music.126.net/wXrY8dnQ9kpcPTIy0zmw9g==/109951167422849807.jpg
         * titleColor : blue
         * requestId :
         * exclusive : false
         * scm : 1.music-homepage.homepage_banner_force.banner.4444967.728775073.null
         * alg : banner-feature-1652623851833926
         * s_ctrp : homepage_bannerId_1652623851833926
         * targetId : 0.0
         * showAdTag : true
         * targetType : 3000.0
         * typeTitle : 活动
         * url : https://mp.music.163.com/5f474fc752cc126d508d4894?token=7c6d93d1a77e5261aeee14f88a29b3ff&width=750&height=1334
         * encodeId : 0
         * song : {"name":"M八七","id":1.942372651E9,"pst":0,"t":0,"ar":[{"id":159300,"name":"米津玄師","tns":[],"alias":[]}],"alia":["电影《新·奥特曼》主题曲"],"pop":100,"st":0,"rt":"","fee":8,"v":6,"cf":"","al":{"id":1.44145961E8,"name":"M八七","picUrl":"http://p4.music.126.net/FvzXEIHj0rEnWx9WcV7CjQ==/109951167350916239.jpg","tns":[],"pic_str":"109951167350916239","pic":1.0995116735091624E17},"dt":263183,"h":{"br":320001,"fid":0,"size":1.0527391E7,"vd":-41053,"sr":44100},"m":{"br":192001,"fid":0,"size":6316452,"vd":-38499,"sr":44100},"l":{"br":128001,"fid":0,"size":4210982,"vd":-36849,"sr":44100},"cd":"01","no":1,"ftype":0,"rtUrls":[],"djId":0,"copyright":1,"s_id":0,"mark":270336,"originCoverType":0,"resourceState":true,"version":6,"single":0,"rtype":0,"mst":9,"cp":2706476,"mv":0,"publishTime":1.6523712E12,"videoInfo":{"moreThanOne":false,"video":{"vid":"a1UqZmNoef042YR","type":3,"playTime":0,"publishTime":0}},"alg":"banner-feature-1652612698118183"}
         */

        private String bannerId;
        private String pic;
        private String titleColor;
        private String requestId;
        private boolean exclusive;
        private String scm;
        private String alg;
        private String s_ctrp;
        private double targetId;
        private boolean showAdTag;
        private double targetType;
        private String typeTitle;
        private String url;
        private String encodeId;
        private SongBean song;

        public String getBannerId() {
            return bannerId;
        }

        public void setBannerId(String bannerId) {
            this.bannerId = bannerId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public boolean isExclusive() {
            return exclusive;
        }

        public void setExclusive(boolean exclusive) {
            this.exclusive = exclusive;
        }

        public String getScm() {
            return scm;
        }

        public void setScm(String scm) {
            this.scm = scm;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }

        public String getS_ctrp() {
            return s_ctrp;
        }

        public void setS_ctrp(String s_ctrp) {
            this.s_ctrp = s_ctrp;
        }

        public double getTargetId() {
            return targetId;
        }

        public void setTargetId(double targetId) {
            this.targetId = targetId;
        }

        public boolean isShowAdTag() {
            return showAdTag;
        }

        public void setShowAdTag(boolean showAdTag) {
            this.showAdTag = showAdTag;
        }

        public double getTargetType() {
            return targetType;
        }

        public void setTargetType(double targetType) {
            this.targetType = targetType;
        }

        public String getTypeTitle() {
            return typeTitle;
        }

        public void setTypeTitle(String typeTitle) {
            this.typeTitle = typeTitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getEncodeId() {
            return encodeId;
        }

        public void setEncodeId(String encodeId) {
            this.encodeId = encodeId;
        }

        public SongBean getSong() {
            return song;
        }

        public void setSong(SongBean song) {
            this.song = song;
        }

        public static class SongBean implements Serializable {
            /**
             * name : M八七
             * id : 1.942372651E9
             * pst : 0.0
             * t : 0.0
             * ar : [{"id":159300,"name":"米津玄師","tns":[],"alias":[]}]
             * alia : ["电影《新·奥特曼》主题曲"]
             * pop : 100.0
             * st : 0.0
             * rt :
             * fee : 8.0
             * v : 6.0
             * cf :
             * al : {"id":1.44145961E8,"name":"M八七","picUrl":"http://p4.music.126.net/FvzXEIHj0rEnWx9WcV7CjQ==/109951167350916239.jpg","tns":[],"pic_str":"109951167350916239","pic":1.0995116735091624E17}
             * dt : 263183.0
             * h : {"br":320001,"fid":0,"size":1.0527391E7,"vd":-41053,"sr":44100}
             * m : {"br":192001,"fid":0,"size":6316452,"vd":-38499,"sr":44100}
             * l : {"br":128001,"fid":0,"size":4210982,"vd":-36849,"sr":44100}
             * cd : 01
             * no : 1.0
             * ftype : 0.0
             * rtUrls : []
             * djId : 0.0
             * copyright : 1.0
             * s_id : 0.0
             * mark : 270336.0
             * originCoverType : 0.0
             * resourceState : true
             * version : 6.0
             * single : 0.0
             * rtype : 0.0
             * mst : 9.0
             * cp : 2706476.0
             * mv : 0.0
             * publishTime : 1.6523712E12
             * videoInfo : {"moreThanOne":false,"video":{"vid":"a1UqZmNoef042YR","type":3,"playTime":0,"publishTime":0}}
             * alg : banner-feature-1652612698118183
             */

            private String name;
            private double id;
            private double pst;
            private double t;
            private double pop;
            private double st;
            private String rt;
            private double fee;
            private double v;
            private String cf;
            private AlBean al;
            private double dt;
            private HBean h;
            private MBean m;
            private LBean l;
            private String cd;
            private double no;
            private double ftype;
            private double djId;
            private double copyright;
            private double s_id;
            private double mark;
            private double originCoverType;
            private boolean resourceState;
            private double version;
            private double single;
            private double rtype;
            private double mst;
            private double cp;
            private double mv;
            private double publishTime;
            private VideoInfoBean videoInfo;
            private String alg;
            private List<ArBean> ar;
            private List<String> alia;
            private List<?> rtUrls;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getId() {
                return id;
            }

            public void setId(double id) {
                this.id = id;
            }

            public double getPst() {
                return pst;
            }

            public void setPst(double pst) {
                this.pst = pst;
            }

            public double getT() {
                return t;
            }

            public void setT(double t) {
                this.t = t;
            }

            public double getPop() {
                return pop;
            }

            public void setPop(double pop) {
                this.pop = pop;
            }

            public double getSt() {
                return st;
            }

            public void setSt(double st) {
                this.st = st;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public double getV() {
                return v;
            }

            public void setV(double v) {
                this.v = v;
            }

            public String getCf() {
                return cf;
            }

            public void setCf(String cf) {
                this.cf = cf;
            }

            public AlBean getAl() {
                return al;
            }

            public void setAl(AlBean al) {
                this.al = al;
            }

            public double getDt() {
                return dt;
            }

            public void setDt(double dt) {
                this.dt = dt;
            }

            public HBean getH() {
                return h;
            }

            public void setH(HBean h) {
                this.h = h;
            }

            public MBean getM() {
                return m;
            }

            public void setM(MBean m) {
                this.m = m;
            }

            public LBean getL() {
                return l;
            }

            public void setL(LBean l) {
                this.l = l;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public double getNo() {
                return no;
            }

            public void setNo(double no) {
                this.no = no;
            }

            public double getFtype() {
                return ftype;
            }

            public void setFtype(double ftype) {
                this.ftype = ftype;
            }

            public double getDjId() {
                return djId;
            }

            public void setDjId(double djId) {
                this.djId = djId;
            }

            public double getCopyright() {
                return copyright;
            }

            public void setCopyright(double copyright) {
                this.copyright = copyright;
            }

            public double getS_id() {
                return s_id;
            }

            public void setS_id(double s_id) {
                this.s_id = s_id;
            }

            public double getMark() {
                return mark;
            }

            public void setMark(double mark) {
                this.mark = mark;
            }

            public double getOriginCoverType() {
                return originCoverType;
            }

            public void setOriginCoverType(double originCoverType) {
                this.originCoverType = originCoverType;
            }

            public boolean isResourceState() {
                return resourceState;
            }

            public void setResourceState(boolean resourceState) {
                this.resourceState = resourceState;
            }

            public double getVersion() {
                return version;
            }

            public void setVersion(double version) {
                this.version = version;
            }

            public double getSingle() {
                return single;
            }

            public void setSingle(double single) {
                this.single = single;
            }

            public double getRtype() {
                return rtype;
            }

            public void setRtype(double rtype) {
                this.rtype = rtype;
            }

            public double getMst() {
                return mst;
            }

            public void setMst(double mst) {
                this.mst = mst;
            }

            public double getCp() {
                return cp;
            }

            public void setCp(double cp) {
                this.cp = cp;
            }

            public double getMv() {
                return mv;
            }

            public void setMv(double mv) {
                this.mv = mv;
            }

            public double getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(double publishTime) {
                this.publishTime = publishTime;
            }

            public VideoInfoBean getVideoInfo() {
                return videoInfo;
            }

            public void setVideoInfo(VideoInfoBean videoInfo) {
                this.videoInfo = videoInfo;
            }

            public String getAlg() {
                return alg;
            }

            public void setAlg(String alg) {
                this.alg = alg;
            }

            public List<ArBean> getAr() {
                return ar;
            }

            public void setAr(List<ArBean> ar) {
                this.ar = ar;
            }

            public List<String> getAlia() {
                return alia;
            }

            public void setAlia(List<String> alia) {
                this.alia = alia;
            }

            public List<?> getRtUrls() {
                return rtUrls;
            }

            public void setRtUrls(List<?> rtUrls) {
                this.rtUrls = rtUrls;
            }

            public static class AlBean implements Serializable {
                /**
                 * id : 1.44145961E8
                 * name : M八七
                 * picUrl : http://p4.music.126.net/FvzXEIHj0rEnWx9WcV7CjQ==/109951167350916239.jpg
                 * tns : []
                 * pic_str : 109951167350916239
                 * pic : 1.0995116735091624E17
                 */

                private double id;
                private String name;
                private String picUrl;
                private String pic_str;
                private double pic;
                private List<?> tns;

                public double getId() {
                    return id;
                }

                public void setId(double id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getPic_str() {
                    return pic_str;
                }

                public void setPic_str(String pic_str) {
                    this.pic_str = pic_str;
                }

                public double getPic() {
                    return pic;
                }

                public void setPic(double pic) {
                    this.pic = pic;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }
            }

            public static class HBean implements Serializable {
                /**
                 * br : 320001.0
                 * fid : 0.0
                 * size : 1.0527391E7
                 * vd : -41053.0
                 * sr : 44100.0
                 */

                private double br;
                private double fid;
                private double size;
                private double vd;
                private double sr;

                public double getBr() {
                    return br;
                }

                public void setBr(double br) {
                    this.br = br;
                }

                public double getFid() {
                    return fid;
                }

                public void setFid(double fid) {
                    this.fid = fid;
                }

                public double getSize() {
                    return size;
                }

                public void setSize(double size) {
                    this.size = size;
                }

                public double getVd() {
                    return vd;
                }

                public void setVd(double vd) {
                    this.vd = vd;
                }

                public double getSr() {
                    return sr;
                }

                public void setSr(double sr) {
                    this.sr = sr;
                }
            }

            public static class MBean implements Serializable {
                /**
                 * br : 192001.0
                 * fid : 0.0
                 * size : 6316452.0
                 * vd : -38499.0
                 * sr : 44100.0
                 */

                private double br;
                private double fid;
                private double size;
                private double vd;
                private double sr;
            }

            public static class LBean implements Serializable {
                /**
                 * br : 128001.0
                 * fid : 0.0
                 * size : 4210982.0
                 * vd : -36849.0
                 * sr : 44100.0
                 */

                private double br;
                private double fid;
                private double size;
                private double vd;
                private double sr;

                public double getBr() {
                    return br;
                }

                public void setBr(double br) {
                    this.br = br;
                }

                public double getFid() {
                    return fid;
                }

                public void setFid(double fid) {
                    this.fid = fid;
                }

                public double getSize() {
                    return size;
                }

                public void setSize(double size) {
                    this.size = size;
                }

                public double getVd() {
                    return vd;
                }

                public void setVd(double vd) {
                    this.vd = vd;
                }

                public double getSr() {
                    return sr;
                }

                public void setSr(double sr) {
                    this.sr = sr;
                }
            }

            public static class VideoInfoBean implements Serializable {
                /**
                 * moreThanOne : false
                 * video : {"vid":"a1UqZmNoef042YR","type":3,"playTime":0,"publishTime":0}
                 */

                private boolean moreThanOne;
                private VideoBean video;

                public boolean isMoreThanOne() {
                    return moreThanOne;
                }

                public void setMoreThanOne(boolean moreThanOne) {
                    this.moreThanOne = moreThanOne;
                }

                public VideoBean getVideo() {
                    return video;
                }

                public void setVideo(VideoBean video) {
                    this.video = video;
                }

                public static class VideoBean implements Serializable {
                    /**
                     * vid : a1UqZmNoef042YR
                     * type : 3.0
                     * playTime : 0.0
                     * publishTime : 0.0
                     */

                    private String vid;
                    private double type;
                    private double playTime;
                    private double publishTime;

                    public String getVid() {
                        return vid;
                    }

                    public void setVid(String vid) {
                        this.vid = vid;
                    }

                    public double getType() {
                        return type;
                    }

                    public void setType(double type) {
                        this.type = type;
                    }

                    public double getPlayTime() {
                        return playTime;
                    }

                    public void setPlayTime(double playTime) {
                        this.playTime = playTime;
                    }

                    public double getPublishTime() {
                        return publishTime;
                    }

                    public void setPublishTime(double publishTime) {
                        this.publishTime = publishTime;
                    }
                }
            }

            public static class ArBean implements Serializable {
                /**
                 * id : 159300.0
                 * name : 米津玄師
                 * tns : []
                 * alias : []
                 */

                private double id;
                private String name;
                private List<?> tns;
                private List<?> alias;

                public double getId() {
                    return id;
                }

                public void setId(double id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }
    }
}
