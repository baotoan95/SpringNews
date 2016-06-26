package com.news.cd.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {
    @Id
    @Column(name = "u_email")
    @NotEmpty(message = "Vui lòng nhập email") @Email(message = "Định dạng email không đúng")
    private String email;
    @Size(min = 4, max = 40, message = "Nhập mật khẩu")
    @Column(name = "u_password")
    private String password;
    @Column(name = "u_first_name")
    @Size(min = 1, max = 30, message = "FirstName phải được nhập và nhỏ hơn 30 kí tự")
    private String firstName;
    @Column(name = "u_last_name")
    @Size(min = 1, max = 30, message = "LastName phải được nhập và nhỏ hơn 30 kí tự")
    private String lastName;
    @Column(name = "u_birth_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    @Column(name = "u_gender")
    private String gender;
    @Column(name = "u_address")
    private String address;
    @Column(name = "u_code")
    private String code;
    @Column(name = "u_enabled")
    private boolean enabled;
    @Column(name = "u_non_locked")
    private boolean nonLocked;
    @Column(name = "u_avatar")
    private String avatarUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserRole> roles=new HashSet<UserRole>();
    @Column(name = "u_desc")
    private String desc;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String email, String password, String firstName,
            String lastName, Date birthDay, String gender, String address,
            String code, boolean enabled, boolean nonLocked, String avatarUrl,
            Set<UserRole> roles, String desc) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.address = address;
        this.code = code;
        this.enabled = enabled;
        this.nonLocked = nonLocked;
        this.avatarUrl = avatarUrl;
        this.roles = roles;
        this.desc = desc;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isNonLocked() {
        return nonLocked;
    }

    public void setNonLocked(boolean nonLocked) {
        this.nonLocked = nonLocked;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", birthDay=" + birthDay + ", gender=" + gender
                + ", address=" + address + ", code=" + code + ", enabled="
                + enabled + ", nonLocked=" + nonLocked + ", avatarUrl="
                + avatarUrl + "]";
    }
    
    private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return buildAuthorities(roles);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return nonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

}
