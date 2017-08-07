package com.champion.service.models;

/**
 * Created by yongjie.pei on 2017/8/7.
 */
public class MD5IndexModel {

    private static final long serialVersionUID = 1L;
    private String id;
    private String md5Index;

    public MD5IndexModel() {
    }

    public MD5IndexModel(String md5Index) {
        this.md5Index = md5Index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMd5Index() {
        return md5Index;
    }

    public void setMd5Index(String md5Index) {
        this.md5Index = md5Index;
    }
}
