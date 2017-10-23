package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/9/12
 */
public class Informations {

    /**
     * code : 200
     * data : [{"idPk":36,"infoTitle":"ded","infoContext":"<p><img src=\"http://192.168.1.23:8080/gsp/upfiles/f0fe5fd1b2634a3aa0e4b28c1a20acfd.jpg\" _src=\"http://192.168.1.23:8080/gsp/upfiles/f0fe5fd1b2634a3aa0e4b28c1a20acfd.jpg\"/><\/p>","infoImg":"http://192.168.1.23:8080/gsp/upfiles/f0fe5fd1b2634a3aa0e4b28c1a20acfd.jpg","infotime":"Sep 6, 2016 12:00:00 AM"}]
     * AllItems : 1
     */

    private String code;
    private int AllItems;
    /**
     * idPk : 36
     * infoTitle : ded
     * infoContext : <p><img src="http://192.168.1.23:8080/gsp/upfiles/f0fe5fd1b2634a3aa0e4b28c1a20acfd.jpg" _src="http://192.168.1.23:8080/gsp/upfiles/f0fe5fd1b2634a3aa0e4b28c1a20acfd.jpg"/></p>
     * infoImg : http://192.168.1.23:8080/gsp/upfiles/f0fe5fd1b2634a3aa0e4b28c1a20acfd.jpg
     * infotime : Sep 6, 2016 12:00:00 AM
     */

    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAllItems() {
        return AllItems;
    }

    public void setAllItems(int AllItems) {
        this.AllItems = AllItems;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int idPk;
        private String infoTitle;
        private String infoContext;
        private String infoImg;
        private String infotime;

        public int getIdPk() {
            return idPk;
        }

        public void setIdPk(int idPk) {
            this.idPk = idPk;
        }

        public String getInfoTitle() {
            return infoTitle;
        }

        public void setInfoTitle(String infoTitle) {
            this.infoTitle = infoTitle;
        }

        public String getInfoContext() {
            return infoContext;
        }

        public void setInfoContext(String infoContext) {
            this.infoContext = infoContext;
        }

        public String getInfoImg() {
            return infoImg;
        }

        public void setInfoImg(String infoImg) {
            this.infoImg = infoImg;
        }

        public String getInfotime() {
            return infotime;
        }

        public void setInfotime(String infotime) {
            this.infotime = infotime;
        }
    }
}
