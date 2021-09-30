package com.dong.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 用户实体
 */

@Entity
@Table(name = "forumUser")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements java.io.Serializable{
    private Integer id;//编号
    private String uId;//用户ID
    private String pwd;//用户密码
    private String nickName;//用户昵称
    private String realName;//用户真实姓名
    private String gender;//用户性别
    private String address;//用户地址
    private String email;//电子邮件地址
    private String tel;//电话号码
    private Timestamp birth;//出生日期
    private String sign;//用户自我简介
    private Integer loginCounts;//登录论坛次数
    private Integer publishCounts;//用户发表文章数
    private Timestamp lastAccessTime;//最后访问时间


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "Uid",length = 12)
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @Column(name = "Pwd",length = 12)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Column(name="Nickname",length =12 )
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name="Realname",length =12 )
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name="Gender",length =12 )
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name="Address",length =100 )
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="Email",length =50 )
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="Tel",length =20 )
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name="Birth")
    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    @Column(name="Sign",length =100 )
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Column(name="loginCounts")
    public Integer getLoginCounts() {
        return loginCounts;
    }

    public void setLoginCounts(Integer loginCounts) {
        this.loginCounts = loginCounts;
    }

    @Column(name="publishCounts")
    public Integer getPublishCounts() {
        return publishCounts;
    }

    public void setPublishCounts(Integer publishCounts) {
        this.publishCounts = publishCounts;
    }

    @Column(name="lastAccessTime")
    public Timestamp getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Timestamp lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
}
