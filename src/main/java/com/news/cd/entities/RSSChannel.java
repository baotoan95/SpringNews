package com.news.cd.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "rss_channels")
public class RSSChannel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rssc_id")
    private int rssChannelId;
    @Column(name = "rssc_title")
    private String title;
    @Column(name = "rssc_link")
    private String link;
    @Column(name = "rssc_desc")
    private String desc;
    @Column(name = "rssc_lang")
    private String lang;
    @Column(name = "rssc_published_date")
    private Date pubDate;
    @Column(name = "rssc_last_build_date")
    private Date lastBuildDate;
    @Column(name = "rssc_docs")
    private String docs;
    @Column(name = "rssc_generator")
    private String generator;
    @Column(name = "rssc_managing_editor")
    private String managingEditor;
    @Column(name = "rssc_web_master")
    private String webMaster;
    @ManyToOne
    @JoinColumn(name = "rssc_category")
    private Category category;
    @OneToMany(mappedBy = "postId", cascade = {CascadeType.ALL})
    private List<Post> items = new ArrayList<>();

    public RSSChannel() {
        // TODO Auto-generated constructor stub
    }

    public RSSChannel(String title, String link, String desc, String lang,
            Date pubDate, Date lastBuildDate, String docs, String generator,
            String managingEditor, String webMaster, Category category) {
        this.title = title;
        this.link = link;
        this.desc = desc;
        this.lang = lang;
        this.pubDate = pubDate;
        this.lastBuildDate = lastBuildDate;
        this.docs = docs;
        this.generator = generator;
        this.managingEditor = managingEditor;
        this.webMaster = webMaster;
        this.category = category;
    }

    public int getRssChannelId() {
        return rssChannelId;
    }

    public void setRssChannelId(int rssChannelId) {
        this.rssChannelId = rssChannelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(Date lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getManagingEditor() {
        return managingEditor;
    }

    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }

    public String getWebMaster() {
        return webMaster;
    }

    public void setWebMaster(String webMaster) {
        this.webMaster = webMaster;
    }

    public List<Post> getItems() {
        return items;
    }

    public void setItems(List<Post> items) {
        this.items = items;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "RSSChannel [rssChannelId=" + rssChannelId + ", title=" + title
                + ", link=" + link + ", desc=" + desc + ", lang=" + lang
                + ", pubDate=" + pubDate + ", lastBuildDate=" + lastBuildDate
                + ", docs=" + docs + ", generator=" + generator
                + ", managingEditor=" + managingEditor + ", webMaster="
                + webMaster + ", category=" + category + "]";
    }
    
    public void setCategoryForAllPost(Category category) {
        for(Post post : items) {
            post.setCategory(category);
        }
    }
    
    public void addPosts(List<Post> listPost) {
        for(Post post : listPost) {
            items.add(post);
        }
    }

}
