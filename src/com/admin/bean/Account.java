package com.admin.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {

    @Id
    private String id;//编号
    private String username;//账号
    private String email;//邮箱
    private String password;//密码
    private int types;//类型
    private boolean state;//账号状态
    private boolean istate;//身份状态是否激活
    private String code;//邮箱激活码
    private Date ctime;//注册时间
    private String phone;//手机号码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isIstate() {
        return istate;
    }

    public void setIstate(boolean istate) {
        this.istate = istate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", types=" + types +
                ", state=" + state +
                // ", istate=" + istate +
                ", code='" + code + '\'' +
                ", ctime=" + ctime +
                ", phone='" + phone + '\'' +
                '}';
    }
}
