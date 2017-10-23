package com.mvpankao.model.bean;

/**
 * Created by wangjian
 * On  2016/9/9
 */
public class Information {
    String infoTitle;

    public String getInfoContext() {
        return infoContext;
    }

    public void setInfoContext(String infoContext) {
        this.infoContext = infoContext;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
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

    String infoContext;
    String infoImg;
    String infotime;

}
