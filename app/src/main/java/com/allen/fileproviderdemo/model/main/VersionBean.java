package com.allen.fileproviderdemo.model.main;

import java.io.Serializable;

/**
 * 版本数据对象
 * Created by allen on 2017/12/21.
 */

public class VersionBean implements Serializable {

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {

        private String downloadurl;
        private String desc;
        private String miniver;
        private String iver;
        private String vercode;

        public String getDownloadurl() {
            return downloadurl;
        }

        public void setDownloadurl(String downloadurl) {
            this.downloadurl = downloadurl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getMiniver() {
            return miniver;
        }

        public void setMiniver(String miniver) {
            this.miniver = miniver;
        }

        public String getIver() {
            return iver;
        }

        public void setIver(String iver) {
            this.iver = iver;
        }

        public String getVercode() {
            return vercode;
        }

        public void setVercode(String vercode) {
            this.vercode = vercode;
        }
    }
}
