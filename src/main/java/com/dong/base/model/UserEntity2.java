package com.dong.base.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by metarnet on 14-2-8.
 */
@Entity
@Table(name="metar_usertable")
public class UserEntity2 extends BaseForm {

    private Long userId;//用户id
    private String userName;//账号
    private String trueName;//真实姓名
    private String passWord;//密码
    private Integer level;//用户级别
    private int ji;//用户级别
    private String mobilePhone;//移动电话
    private String telephone;//办公电话
    private String fax;//传真
    private String address;//地址
    private String email;//电子邮件
    private Integer sex = 0;//性别 0：保密 1：男 2：女 默认是0
    private Integer personType = 0;//人员类别，1 代维人员 0非代维人员，默认值是0
    private OrgEntity orgEntity;//所属部门
    private String job;//职务
    private String professional;//专业
    private String jobNum;//工号
    private Boolean admin = false;//是否管理员 true 是  false 不是  默认 不是
    private String remark;//备注
    private Long orgID;
    private String orgCode;
    private String category; //UNI集团，PRO省分，CITY 地市
    private String ucloudOrgCode;
    private String ucloudOrgName;
    private Boolean firstLogin;//首次登录
    private Date pwdUpdateDate;//密码修改时间
    private Date pwdDeadDate;//密码过期时间
    private Boolean commonPwdUse=true;//通用密码可登录

    @Id
    @Column(name= "user_id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name= "user_name", nullable = false, insertable = true, updatable = true, length = 60, precision = 0)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name= "user_truename", nullable = false, insertable = true, updatable = true, length = 60, precision = 0)
    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    @Column(name= "user_password", nullable = false)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Column(name= "user_level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(columnDefinition="INT default 0")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Column(columnDefinition="INT default 0")
    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    public OrgEntity getOrgEntity() {
        return orgEntity;
    }

    public void setOrgEntity(OrgEntity orgEntity) {
        this.orgEntity = orgEntity;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    @Column(columnDefinition="BIT default 0")
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrgID() {
        return orgID;
    }

    public void setOrgID(Long orgID) {
        this.orgID = orgID;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(updatable = false)
    public String getUcloudOrgCode() {
        return ucloudOrgCode;
    }

    public void setUcloudOrgCode(String ucloudOrgCode) {
        this.ucloudOrgCode = ucloudOrgCode;
    }

    @Column(updatable = false)
    public String getUcloudOrgName() {
        return ucloudOrgName;
    }

    public void setUcloudOrgName(String ucloudOrgName) {
        this.ucloudOrgName = ucloudOrgName;
    }

    @Column(columnDefinition="INT default 1")
    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Date getPwdUpdateDate() {
        return pwdUpdateDate;
    }

    public void setPwdUpdateDate(Date pwdUpdateDate) {
        this.pwdUpdateDate = pwdUpdateDate;
    }

    public Date getPwdDeadDate() {
        return pwdDeadDate;
    }

    public void setPwdDeadDate(Date pwdDeadDate) {
        this.pwdDeadDate = pwdDeadDate;
    }

    @Column(columnDefinition="INT default 1")
    public Boolean getCommonPwdUse() {
        return commonPwdUse;
    }

    public void setCommonPwdUse(Boolean commonPwdUse) {
        this.commonPwdUse = commonPwdUse;
    }

    @Column
    public int getJi() {
        return ji;
    }

    public void setJi(int ji) {
        this.ji = ji;
    }

    static {
        System.out.println("子类的静态代码块");
    }
    {
        System.out.println("子类的代码块");
    }
    public UserEntity2(){
        System.out.println("UserEntity2构造方法！");
    }
    {
        System.out.println("子类的代码块2");
    }
}
