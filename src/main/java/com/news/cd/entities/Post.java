package com.news.cd.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int postId;
    @NotEmpty(message = "Vui lòng nhập tên bài viết")
    @Size(min = 4, max = 200, message = "Tên bài viết phải lớn hơn 4 và bé hơn 200 kí tự")
    @Column(name = "p_title")
    private String title;
    @NotEmpty(message = "Vui lòng nhập nội dung")
    @Column(name = "p_content")
    private String content;
    @Column(name = "p_last_update")
    private Date lastUpdate;
    @Column(name = "p_published_date")
    private Date publishedDate;
    @NotEmpty(message = "Vui lòng thêm mô tả cho bài viết")
    @Size(max = 500, message = "Mô tả quá nhiều, chỉ được chứa 500 kí tự")
    @Column(name = "p_desc")
    private String desc;
    @Column(name = "p_img")
    private String avatarUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_author")
    private User author;
    @Column(name = "p_guid", unique = true)
    private String link;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_status")
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
    @JoinColumn(name = "p_category")
    private Category category;
    @Column(name = "p_views")
    private int views;
    @Column(name = "p_votes")
    private int votes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_type")
    private PostType type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_rsschannel", referencedColumnName = "rssc_id")
    private RSSChannel rssChannel;

    public Post() {
        // TODO Auto-generated constructor stub
    }

    public Post(String title, String content, Date lastUpdate,
            Date publishedDate, String desc, String avatarUrl, User author,
            String link, Status status, Category category, int views,
            int votes, PostType type, RSSChannel rssChannel) {
        this.title = title;
        this.content = content;
        this.lastUpdate = lastUpdate;
        this.publishedDate = publishedDate;
        this.desc = desc;
        this.avatarUrl = avatarUrl;
        this.author = author;
        this.link = link;
        this.status = status;
        this.category = category;
        this.views = views;
        this.votes = votes;
        this.type = type;
        this.rssChannel = rssChannel;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getlink() {
        return link;
    }

    public void setlink(String link) {
        this.link = link;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public RSSChannel getRssChannel() {
        return rssChannel;
    }

    public void setRssChannel(RSSChannel rssChannel) {
        this.rssChannel = rssChannel;
    }

    @Override
    public String toString() {
        return "Post [postId=" + postId + ", title=" + title + ", content="
                + content + ", lastUpdate=" + lastUpdate + ", publishedDate="
                + publishedDate + ", desc=" + desc + ", avatarUrl=" + avatarUrl
                + ", author=" + author + ", link=" + link + ", status="
                + status + ", category=" + category + ", views=" + views
                + ", votes=" + votes + ", type=" + type + ", rssChannel="
                + rssChannel + "]";
    }

    @Override
    public boolean equals(Object obj) {
    	try {
	        if (obj instanceof Post) {
	            Post other = (Post) obj;
	            return this.title.equals(other.getTitle()) 
	            		&& this.rssChannel.getRssChannelId() == other.getRssChannel().getRssChannelId();
	        }
    	} catch (Exception e) {}
    	return false;
    }

}
