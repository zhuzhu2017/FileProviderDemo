package com.allen.fileproviderdemo.model.splash;

import java.io.Serializable;

/**
 * 游客Token数据对象
 * Created by allen on 2017/12/21.
 */

public class TempTokenBean implements Serializable {
    private String accesstoken;     //授权令牌
    private String token_type;      //bearer
    private String expires_in;      //授权过期剩余totalseconds
    private String issued;          //授权时间unit time
    private String expires;         //授权过期时间,unit time
    private String sessionkey;      //sessionkey

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }
}
