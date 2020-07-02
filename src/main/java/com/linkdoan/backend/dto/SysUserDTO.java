package com.linkdoan.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.BaseDTO;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUserDTO extends BaseDTO {
    private Long userId;

    @NotNull
    @AdjHistory(field = "EMAIL")
    private String email;

    @AdjHistory(field = "PASSWORD")
    private String password;

    @NotNull
    @AdjHistory(field = "FIRST_NAME")
    private String firstName;

    @NotNull
    @AdjHistory(field = "LAST_NAME")
    private String lastName;

    @AdjHistory(field = "LOCATION_ID")
    private Long locationId;

    @AdjHistory(field = "STATUS")
    private Long status;

    @NotNull
    @AdjHistory(field = "MOBILE_PHONE")
    private String mobilePhone;

    @AdjHistory(field = "HOME_PHONE")
    private String homePhone;

    @AdjHistory(field = "GENDER")
    private Long gender;

    @AdjHistory(field = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @AdjHistory(field = "IDENTITY_CODE")
    private String identityCode;

    @AdjHistory(field = "IDENTITY_TYPE")
    private Long identityType;

    @AdjHistory(field = "KEY_FOB_ID")
    private String keyFobId;

    @AdjHistory(field = "DESCRIPTION")
    private String description;

    @AdjHistory(field = "VALID_FROM")
    private Date validFrom;

    @AdjHistory(field = "VALID_TO")
    private Date validTo;

    @AdjHistory(field = "CHECK_IP")
    private Long checkIp;

    @AdjHistory(field = "IP_ADDRESS")
    private String ipAddress;

    @AdjHistory(field = "LAST_CHANGE_PASSWORD")
    private Date lastChangePassword;

    @AdjHistory(field = "CREATE_USER_ID")
    private String createUserId;

    @AdjHistory(field = "CREATE_DATE")
    private Date createDate;

    @AdjHistory(field = "LOGIN_FAILURE_COUNT")
    private Long loginFailureCount;

    @AdjHistory(field = "LAST_LOCK_DATE")
    private Date lastLockDate;

    private List<SysObjectsDTO> sysObjectsList = Lists.newArrayList();
    private List<SysRoleUserDTO> sysRoleList= Lists.newArrayList();
    private String locationName;


    public SysUser toModel(){
        SysUser sysUser= new SysUser();
        sysUser.setCheckIp(this.checkIp);
        sysUser.setCreateDate(this.createDate);
        sysUser.setCreateUserId(createUserId);
        sysUser.setDateBirth(dateBirth);
        sysUser.setDescription(description);
        sysUser.setEmail(email);
        sysUser.setFirstName(firstName);
        sysUser.setGender(gender);
        sysUser.setHomePhone(homePhone);
        sysUser.setIdentityCode(identityCode);
        sysUser.setIdentityType(identityType);
        sysUser.setIpAddress(ipAddress);
        sysUser.setKeyFobId(keyFobId);
        sysUser.setLastChangePassword(lastChangePassword);
        sysUser.setLastLockDate(lastLockDate);
        sysUser.setLastName(lastName);
        sysUser.setLocationId(locationId);
        sysUser.setLoginFailureCount(loginFailureCount);
        sysUser.setMobilePhone(mobilePhone);
        sysUser.setPassword(password);
        sysUser.setStatus(status);
        sysUser.setUserId(userId);
        sysUser.setValidFrom(validFrom);
        sysUser.setValidTo(validTo);

        return sysUser;
    }
}
