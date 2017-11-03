package com.data.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 主题展示
 */
@Entity
@Table(name = "theme")
public class Theme {
    @Id
    private String id;//标题
    private String uid;//所属人的id
    private String title;//主题名称
    private String content;//主题介绍
    private String cid;//所属分类的id
    private Date date;//时间
    private byte[] image;//展示图
    private byte[] bimage;//banner图
    private boolean state;//状态

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getBimage() {
        return bimage;
    }

    public void setBimage(byte[] bimage) {
        this.bimage = bimage;
    }
}
