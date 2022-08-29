package com.liu.himusic.model.bean;

import java.io.Serializable;
import java.util.List;

public class TopicDetailBean implements Serializable {

    /**
     * act : {"actId":111551188,"title":"闪闪发光的生活碎片","coverId":109951165580026560,"coverPCId":109951165580034050,"coverPCIdLong":109951165580057150,"coverPCIdList":109951165580026670,"displayType":0,"sharePicId":109951165580059580,"text":["一起来收集美好吧！"],"startTime":1609141106724,"endTime":1618991530244,"participateCount":1721538,"resourceType":-1,"resourceJson":null,"enableResourcePublish":true,"videoType":0,"commodityInfo":null,"publishTip":"","topicType":0,"meetingBeginTime":1618818720000,"meetingEndTime":1461052320000,"coverPCLongUrl":"http://p1.music.126.net/SwQkkrylAyWxjH0OhKuMnA==/109951165580057159.jpg","sharePicUrl":"http://p1.music.126.net/dm2zI-Hy5gFJOunrfE4y-A==/109951165580059588.jpg","isDefaultImg":false,"coverPCUrl":"http://p1.music.126.net/YEcFDLhW7X2D8qDHX4NrVg==/109951165580034048.jpg","coverMobileUrl":"http://p1.music.126.net/WkcOSWCg9HsrUhpiejyjhg==/109951165580026560.jpg","coverPCListUrl":"http://p1.music.126.net/zeu8N-hZ4eQULmeCtIPa_A==/109951165580026679.jpg"}
     * needBeginNotify : false
     * code : 200
     */

    public ActBean act;
    public boolean needBeginNotify;
    public int code;

    public static class ActBean implements Serializable {
        /**
         * actId : 111551188
         * title : 闪闪发光的生活碎片
         * coverId : 109951165580026560
         * coverPCId : 109951165580034050
         * coverPCIdLong : 109951165580057150
         * coverPCIdList : 109951165580026670
         * displayType : 0
         * sharePicId : 109951165580059580
         * text : ["一起来收集美好吧！"]
         * startTime : 1609141106724
         * endTime : 1618991530244
         * participateCount : 1721538
         * resourceType : -1
         * resourceJson : null
         * enableResourcePublish : true
         * videoType : 0
         * commodityInfo : null
         * publishTip :
         * topicType : 0
         * meetingBeginTime : 1618818720000
         * meetingEndTime : 1461052320000
         * coverPCLongUrl : http://p1.music.126.net/SwQkkrylAyWxjH0OhKuMnA==/109951165580057159.jpg
         * sharePicUrl : http://p1.music.126.net/dm2zI-Hy5gFJOunrfE4y-A==/109951165580059588.jpg
         * isDefaultImg : false
         * coverPCUrl : http://p1.music.126.net/YEcFDLhW7X2D8qDHX4NrVg==/109951165580034048.jpg
         * coverMobileUrl : http://p1.music.126.net/WkcOSWCg9HsrUhpiejyjhg==/109951165580026560.jpg
         * coverPCListUrl : http://p1.music.126.net/zeu8N-hZ4eQULmeCtIPa_A==/109951165580026679.jpg
         */

        public long actId;
        public String title;
        public long coverId;
        public long coverPCId;
        public long coverPCIdLong;
        public long coverPCIdList;
        public int displayType;
        public long sharePicId;
        public long startTime;
        public long endTime;
        public long participateCount;
        public long resourceType;
        public String resourceJson;
        public boolean enableResourcePublish;
        public long videoType;
        public String commodityInfo;
        public String publishTip;
        public long topicType;
        public long meetingBeginTime;
        public long meetingEndTime;
        public String coverPCLongUrl;
        public String sharePicUrl;
        public boolean isDefaultImg;
        public String coverPCUrl;
        public String coverMobileUrl;
        public String coverPCListUrl;
        public List<String> text;
    }
}
