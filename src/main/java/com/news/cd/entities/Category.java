package com.news.cd.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctg_id")
    private int cateId;
    @Column(name = "ctg_name")
    @NotEmpty(message = "Vui lòng nhập tên thể loại")
    private String name;
    @Column(name = "ctg_desc")
    private String desc;
    @Column(name = "ctg_avatar")
    private String avatarUrl = "";
    @Column(name = "ctg_show_index")
    private boolean show;
    @Column(name = "ctg_menu")
    private boolean menu;
    @Column(name = "ctg_parent")
    private int parent;
    @Column(name = "ctg_url")
    private String url;

    public Category() {
        // TODO Auto-generated constructor stub
    }

    public Category(String name, String desc, String avatarUrl, boolean show,
            boolean menu, int parent, String url) {
        this.name = name;
        this.desc = desc;
        this.avatarUrl = avatarUrl;
        this.show = show;
        this.menu = menu;
        this.parent = parent;
        this.url = url;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Category [cateId=" + cateId + ", name=" + name + ", desc="
                + desc + ", avatarUrl=" + avatarUrl + ", show=" + show
                + ", menu=" + menu + ", parent=" + parent + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Category) {
            return cateId == ((Category) obj).getCateId();
        }
        return false;
    }

}
