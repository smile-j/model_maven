package com.dong.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/8/24.
 * 组织机构实体类
 */
@Entity
@Table(name = "metar_orgtable")
public class OrgEntity extends BaseForm {
    private Long orgId;
    private String orgCode;//组织编码
    private String orgName;//组织名称
    private String parentOrgCode;//父组织编码
    private Long parentOrgId;
    private String orgType;//组织类型  集团UNI  省分 PRO  部门DEP
    private String proCode;//省分编码
    private Integer orgLevel;//组织级别
    private Integer orderBy;//排序  默认和主键一样
    private Boolean allowModify=true;//是否允许修改 默认 true  允许修改
    private Boolean allowExtended=true;//是否允许扩展即添加下级节点  允许扩展
    private String description;//描述
    private String remark;//备注
    private String professional;//专业
    private String address;
    //是否有子部门  false 为有  true 为无   默认无
    private Boolean leaf = true;
    private String shortName;//简称
    private String areaCode;//区域编码
    private Long areaId;//区域id
    private Boolean professionalOffice;//是否专业处室
    private String ucloudOrgCode;
    private String ucloudName;
    private String ucloudParentOrgCode;
    private String ucloudParentName;
    private Integer sortNum;//排序
    private String fullOrgName;
//    private Set<UserEntity> userEntitySet = new HashSet<UserEntity>();
    public OrgEntity(){}

    public OrgEntity(Long orgId) {
        this.orgId = orgId;
    }

    @Id
    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentOrgCode() {
        return parentOrgCode;
    }

    public void setParentOrgCode(String parentOrgCode) {
        this.parentOrgCode = parentOrgCode;
    }

    public Long getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Long parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @Column(updatable = false,columnDefinition="BIT default 1")
    public Boolean getAllowModify() {
        return allowModify;
    }

    public void setAllowModify(Boolean allowModify) {
        this.allowModify = allowModify;
    }

    @Column(updatable = false,columnDefinition="BIT default 1")
    public Boolean getAllowExtended() {
        return allowExtended;
    }

    public void setAllowExtended(Boolean allowExtended) {
        this.allowExtended = allowExtended;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /*@OneToMany(mappedBy = "orgEntity")
    public Set<UserEntity> getUserEntitySet() {
        return userEntitySet;
    }

    public void setUserEntitySet(Set<UserEntity> userEntitySet) {
        this.userEntitySet = userEntitySet;
    }*/

    @Column(columnDefinition="BIT default 1")
    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    @Column(columnDefinition="BIT default 0")
    public Boolean getProfessionalOffice() {
        return professionalOffice;
    }

    public void setProfessionalOffice(Boolean professionalOffice) {
        this.professionalOffice = professionalOffice;
    }

    @Column(updatable = false)
    public String getUcloudOrgCode() {
        return ucloudOrgCode;
    }

    public void setUcloudOrgCode(String ucloudOrgCode) {
        this.ucloudOrgCode = ucloudOrgCode;
    }

    @Column(updatable = false)
    public String getUcloudName() {
        return ucloudName;
    }

    public void setUcloudName(String ucloudName) {
        this.ucloudName = ucloudName;
    }

    @Column(updatable = false)
    public String getUcloudParentOrgCode() {
        return ucloudParentOrgCode;
    }

    public void setUcloudParentOrgCode(String ucloudParentOrgCode) {
        this.ucloudParentOrgCode = ucloudParentOrgCode;
    }

    @Column(updatable = false)
    public String getUcloudParentName() {
        return ucloudParentName;
    }

    public void setUcloudParentName(String ucloudParentName) {
        this.ucloudParentName = ucloudParentName;
    }

    @Column(updatable = false)
    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getFullOrgName() {
        return fullOrgName;
    }

    public void setFullOrgName(String fullOrgName) {
        this.fullOrgName = fullOrgName;
    }
}
