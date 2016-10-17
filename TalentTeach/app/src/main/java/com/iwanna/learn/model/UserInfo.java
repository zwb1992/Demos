package com.iwanna.learn.model;

import java.io.Serializable;

/**
 * Created by zwb
 * Description
 * Date 16/8/26.
 */
public class UserInfo implements Serializable{

    /**
     * UserID : 60
     * LoginUserName : null
     * LoginPhone : 18779103860
     * LoginPassword : null
     * UserType : 2
     * NickName : 周周周
     * Email : null
     * UserSex : null
     * UserAge : null
     * UserLike : null
     * ContactPerson : null
     * ContactPhone : null
     * LastLoginTime : null
     * LastLoginIP : null
     * AuditName : null
     * AuditStatus : null
     * CreateTime : null
     * UpdateTime : null
     * UserImg
     */

    private int UserID;
    private String LoginUserName;
    private String LoginPhone;
    private int UserType;
    private String NickName;
    private String Email;
    private String UserSex;
    private String UserAge;
    private String UserLike;
    private String ContactPerson;
    private String ContactPhone;
    private String LastLoginTime;
    private String LastLoginIP;
    private String AuditName;
    private String AuditStatus;
    private String CreateTime;
    private String UpdateTime;
    private String UserImg;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getLoginUserName() {
        return LoginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        LoginUserName = loginUserName;
    }

    public String getLoginPhone() {
        return LoginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        LoginPhone = loginPhone;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

    public String getUserAge() {
        return UserAge;
    }

    public void setUserAge(String userAge) {
        UserAge = userAge;
    }

    public String getUserLike() {
        return UserLike;
    }

    public void setUserLike(String userLike) {
        UserLike = userLike;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getContactPhone() {
        return ContactPhone;
    }

    public void setContactPhone(String contactPhone) {
        ContactPhone = contactPhone;
    }

    public String getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        LastLoginTime = lastLoginTime;
    }

    public String getLastLoginIP() {
        return LastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        LastLoginIP = lastLoginIP;
    }

    public String getAuditName() {
        return AuditName;
    }

    public void setAuditName(String auditName) {
        AuditName = auditName;
    }

    public String getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        AuditStatus = auditStatus;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public String getUserImg() {
        return UserImg;
    }

    public void setUserImg(String userImg) {
        UserImg = userImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (UserID != userInfo.UserID) return false;
        if (UserType != userInfo.UserType) return false;
        if (LoginUserName != null ? !LoginUserName.equals(userInfo.LoginUserName) : userInfo.LoginUserName != null)
            return false;
        if (LoginPhone != null ? !LoginPhone.equals(userInfo.LoginPhone) : userInfo.LoginPhone != null)
            return false;
        if (NickName != null ? !NickName.equals(userInfo.NickName) : userInfo.NickName != null)
            return false;
        if (Email != null ? !Email.equals(userInfo.Email) : userInfo.Email != null) return false;
        if (UserSex != null ? !UserSex.equals(userInfo.UserSex) : userInfo.UserSex != null)
            return false;
        if (UserAge != null ? !UserAge.equals(userInfo.UserAge) : userInfo.UserAge != null)
            return false;
        if (UserLike != null ? !UserLike.equals(userInfo.UserLike) : userInfo.UserLike != null)
            return false;
        if (ContactPerson != null ? !ContactPerson.equals(userInfo.ContactPerson) : userInfo.ContactPerson != null)
            return false;
        if (ContactPhone != null ? !ContactPhone.equals(userInfo.ContactPhone) : userInfo.ContactPhone != null)
            return false;
        if (LastLoginTime != null ? !LastLoginTime.equals(userInfo.LastLoginTime) : userInfo.LastLoginTime != null)
            return false;
        if (LastLoginIP != null ? !LastLoginIP.equals(userInfo.LastLoginIP) : userInfo.LastLoginIP != null)
            return false;
        if (AuditName != null ? !AuditName.equals(userInfo.AuditName) : userInfo.AuditName != null)
            return false;
        if (AuditStatus != null ? !AuditStatus.equals(userInfo.AuditStatus) : userInfo.AuditStatus != null)
            return false;
        if (CreateTime != null ? !CreateTime.equals(userInfo.CreateTime) : userInfo.CreateTime != null)
            return false;
        return UpdateTime != null ? UpdateTime.equals(userInfo.UpdateTime) : userInfo.UpdateTime == null;

    }

    @Override
    public int hashCode() {
        int result = UserID;
        result = 31 * result + (LoginUserName != null ? LoginUserName.hashCode() : 0);
        result = 31 * result + (LoginPhone != null ? LoginPhone.hashCode() : 0);
        result = 31 * result + UserType;
        result = 31 * result + (NickName != null ? NickName.hashCode() : 0);
        result = 31 * result + (Email != null ? Email.hashCode() : 0);
        result = 31 * result + (UserSex != null ? UserSex.hashCode() : 0);
        result = 31 * result + (UserAge != null ? UserAge.hashCode() : 0);
        result = 31 * result + (UserLike != null ? UserLike.hashCode() : 0);
        result = 31 * result + (ContactPerson != null ? ContactPerson.hashCode() : 0);
        result = 31 * result + (ContactPhone != null ? ContactPhone.hashCode() : 0);
        result = 31 * result + (LastLoginTime != null ? LastLoginTime.hashCode() : 0);
        result = 31 * result + (LastLoginIP != null ? LastLoginIP.hashCode() : 0);
        result = 31 * result + (AuditName != null ? AuditName.hashCode() : 0);
        result = 31 * result + (AuditStatus != null ? AuditStatus.hashCode() : 0);
        result = 31 * result + (CreateTime != null ? CreateTime.hashCode() : 0);
        result = 31 * result + (UpdateTime != null ? UpdateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "UserID=" + UserID +
                ", LoginUserName='" + LoginUserName + '\'' +
                ", LoginPhone='" + LoginPhone + '\'' +
                ", UserType=" + UserType +
                ", NickName='" + NickName + '\'' +
                ", Email='" + Email + '\'' +
                ", UserSex='" + UserSex + '\'' +
                ", UserAge='" + UserAge + '\'' +
                ", UserLike='" + UserLike + '\'' +
                ", ContactPerson='" + ContactPerson + '\'' +
                ", ContactPhone='" + ContactPhone + '\'' +
                ", LastLoginTime='" + LastLoginTime + '\'' +
                ", LastLoginIP='" + LastLoginIP + '\'' +
                ", AuditName='" + AuditName + '\'' +
                ", AuditStatus='" + AuditStatus + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", UpdateTime='" + UpdateTime + '\'' +
                ", UserImg='" + UserImg + '\'' +
                '}';
    }
}
