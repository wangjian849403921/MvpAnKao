package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/17
 */

public class CreateOrderAssert {

    /**
     * code : 200
     * result : 获取数据成功
     * object : [{"id":1,"userIdFk":4,"assetName":"住院楼","assetIcon":"fileResource/imageUpload/59b475492b5b45978a0fb26c6438be71.jpg","assetLevel":0}]
     */

    private int code;
    private String result;
    private List<ObjectBean> object;

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

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * id : 1
         * userIdFk : 4
         * assetName : 住院楼
         * assetIcon : fileResource/imageUpload/59b475492b5b45978a0fb26c6438be71.jpg
         * assetLevel : 0
         */

        private String id;
        private int userIdFk;
        private String assetName;
        private String assetIcon;
        private int assetLevel;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserIdFk() {
            return userIdFk;
        }

        public void setUserIdFk(int userIdFk) {
            this.userIdFk = userIdFk;
        }

        public String getAssetName() {
            return assetName;
        }

        public void setAssetName(String assetName) {
            this.assetName = assetName;
        }

        public String getAssetIcon() {
            return assetIcon;
        }

        public void setAssetIcon(String assetIcon) {
            this.assetIcon = assetIcon;
        }

        public int getAssetLevel() {
            return assetLevel;
        }

        public void setAssetLevel(int assetLevel) {
            this.assetLevel = assetLevel;
        }
    }
}
