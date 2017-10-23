package com.mvpankao.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/2/15
 */

public class WarningDetailBean {

    /**
     * code : 200
     * result : 获取数据成功
     * object : {"alarm_name":"接地电流B1组A相电流","alarm_begindate":1482920891000,"alarm_position":"B1组","asset_icon":"fileResource/imageUpload/59b475492b5b45978a0fb26c6438be71.jpg","asset_name":"住院楼","alarm_status":"已解除","alarm_level":"高级","timePoint":[{"data":1482920891000,"name":"0"},{"data":1482920976000,"name":"1"}],"id":1,"alarm_point":"A相","alarm_enddate":1482920976000,"alarm_data":"设备在工作过程中，因某种原因\u201c丧失规定功能\u201d或危害安全现象"}
     */

    private int code;
    private String result;
    private ObjectBean object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * alarm_name : 接地电流B1组A相电流
         * alarm_begindate : 1482920891000
         * alarm_position : B1组
         * asset_icon : fileResource/imageUpload/59b475492b5b45978a0fb26c6438be71.jpg
         * asset_name : 住院楼
         * alarm_status : 已解除
         * alarm_level : 高级
         * timePoint : [{"data":1482920891000,"name":"0"},{"data":1482920976000,"name":"1"}]
         * id : 1
         * alarm_point : A相
         * alarm_enddate : 1482920976000
         * alarm_data : 设备在工作过程中，因某种原因“丧失规定功能”或危害安全现象
         */

        private String alarm_name;
        @SerializedName("alarm_begindate")
        private long alarm_time;
        @SerializedName("alarm_position")
        private String groupname;
        private String asset_icon;
        @SerializedName("asset_name")
        private String asset_area;
        private String alarm_status;
        private String alarm_level;
        private String id;
        private String alarm_point;
        private long alarm_enddate;
        @SerializedName("alarm_data")
        private String alarm_note;
        private String alarm_assetsname_id;
        private String alarm_assets_level;

        private List<TimePointBean> timePoint;

        public String getAlarm_assets_level() {
            return alarm_assets_level;
        }

        public void setAlarm_assets_level(String alarm_assets_level) {
            this.alarm_assets_level = alarm_assets_level;
        }

        public String getAlarm_assetsname_id() {
            return alarm_assetsname_id;
        }

        public void setAlarm_assetsname_id(String alarm_assetsname_id) {
            this.alarm_assetsname_id = alarm_assetsname_id;
        }

        public String getAlarm_name() {
            return alarm_name;
        }

        public void setAlarm_name(String alarm_name) {
            this.alarm_name = alarm_name;
        }

        public long getAlarm_time() {
            return alarm_time;
        }

        public void setAlarm_time(long alarm_time) {
            this.alarm_time = alarm_time;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }

        public String getAsset_icon() {
            return asset_icon;
        }

        public void setAsset_icon(String asset_icon) {
            this.asset_icon = asset_icon;
        }

        public String getAsset_area() {
            return asset_area;
        }

        public void setAsset_area(String asset_area) {
            this.asset_area = asset_area;
        }

        public String getAlarm_status() {
            return alarm_status;
        }

        public void setAlarm_status(String alarm_status) {
            this.alarm_status = alarm_status;
        }

        public String getAlarm_level() {
            return alarm_level;
        }

        public void setAlarm_level(String alarm_level) {
            this.alarm_level = alarm_level;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAlarm_point() {
            return alarm_point;
        }

        public void setAlarm_point(String alarm_point) {
            this.alarm_point = alarm_point;
        }

        public long getAlarm_enddate() {
            return alarm_enddate;
        }

        public void setAlarm_enddate(long alarm_enddate) {
            this.alarm_enddate = alarm_enddate;
        }

        public String getAlarm_note() {
            return alarm_note;
        }

        public void setAlarm_note(String alarm_note) {
            this.alarm_note = alarm_note;
        }

        public List<TimePointBean> getTimePoint() {
            return timePoint;
        }

        public void setTimePoint(List<TimePointBean> timePoint) {
            this.timePoint = timePoint;
        }

        public static class TimePointBean {
            /**
             * data : 1482920891000
             * name : 0
             */

            private long data;
            private String name;

            public long getData() {
                return data;
            }

            public void setData(long data) {
                this.data = data;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
