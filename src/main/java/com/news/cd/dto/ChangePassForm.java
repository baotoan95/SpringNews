package com.news.cd.dto;

import java.io.Serializable;

/*
 * @author BaoToan
 * @version 1.0
 * @date: May 14, 2016
 */

@SuppressWarnings("serial")
public class ChangePassForm implements Serializable {
    private String email;
    private String password;
    private String passConfirm;
    private String code;

    public ChangePassForm() {
        // TODO Auto-generated constructor stub
    }

    public ChangePassForm(String email, String password, String passConfirm, String code) {
        this.email = email;
        this.password = password;
        this.passConfirm = passConfirm;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassConfirm() {
        return passConfirm;
    }

    public void setPassConfirm(String passConfirm) {
        this.passConfirm = passConfirm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
