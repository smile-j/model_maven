package com.dong.base.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/8/24.
 */
@MappedSuperclass
public class BaseForm implements Serializable {

    static {
        System.out.println("父类的静态代码块");
    }
     {
        System.out.println("父类的代码块");
    }
    private Date createDate;//创建时间
    private Date lastUpdateDate;//最后更新时间
    private String createPerson;//创建人
    private String lastUpdatePerson;//最后修改人
    private Boolean deleteFlag;//是否删除  做逻辑删除使用
    private Integer createType=1;//创建类型 1 代表 系统添加  2代表导入  3 代表同步
    private String attribute1;//预留字段1
    private String attribute2;//预留字段2
    private String attribute3;//预留字段3
    private String attribute4;//预留字段4
    private String attribute5;//预留字段5


    @Column(updatable = false)
//    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

//    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(updatable = false)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getLastUpdatePerson() {
        return lastUpdatePerson;
    }

    public void setLastUpdatePerson(String lastUpdatePerson) {
        this.lastUpdatePerson = lastUpdatePerson;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    @Column(columnDefinition="BIT(1) default 0")
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public final  void show(){

    }
}
