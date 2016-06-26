package com.news.cd.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.news.cd.constants.PostStatus;
import com.news.cd.dao.AbstractDAO;
import com.news.cd.dao.PostDAO;
import com.news.cd.dao.RSSChannelDAO;
import com.news.cd.dao.UserDAO;
import com.news.cd.entities.Post;
import com.news.cd.entities.RSSChannel;

@Repository("postDAO")
public class PostDAOImpl extends AbstractDAO<Integer, Post> implements PostDAO {
	@Autowired
	private RSSChannelDAO rssChannelDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean addPost(Post post) {
		// Nếu bài viết đã tồn tại thì không được thêm
		if (!isExist(post.getlink())) {
			persist(post);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deletePost(int id) {
		return delete(getByKey(id));
	}

	@Override
	public boolean updatePost(Post post) {
	    Post oldPost = findPostById(post.getPostId());
	    if(null != oldPost) {
	        oldPost.setAuthor(post.getAuthor());
	        oldPost.setAvatarUrl(post.getAvatarUrl());
	        oldPost.setCategory(post.getCategory());
	        oldPost.setContent(post.getContent());
	        oldPost.setDesc(post.getDesc());
	        oldPost.setLastUpdate(new Date());
	        oldPost.setPublishedDate(post.getPublishedDate());
	        oldPost.setRssChannel(post.getRssChannel());
	        oldPost.setStatus(post.getStatus());
	        oldPost.setTitle(post.getTitle());
	        oldPost.setType(post.getType());
	        oldPost.setViews(post.getViews());
	        oldPost.setVotes(post.getVotes());
	        getSession().update(oldPost);
	        return true;
	    } 
		return false;
	}

	@Override
	public Post findPostById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Post> getPostsPopular(int size) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status.sttId", PostStatus.APPROVED));
		criteria.addOrder(Order.desc("views"));
		criteria.setFirstResult(1);
		criteria.setMaxResults(size);
		return (List<Post>) criteria.list();
	}

	/**
	 * @see com.news.cd.dao.PostDAO#getPostsLatest(int)
	 * @param size
	 *     number of record want to get
	 * @return
	 *     list of post with status was approved
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Post> getPostsLatest(int size) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status.sttId", PostStatus.APPROVED));
		criteria.addOrder(Order.desc("publishedDate"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(size);
		return (List<Post>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getPostsLatestOwn(int size) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status.sttId", PostStatus.APPROVED));
		criteria.add(Restrictions.isNotNull("author"));
		criteria.addOrder(Order.desc("publishedDate"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(size);
		return (List<Post>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getPostsLimitResultByStatus(int indexMin, int numbOfResult, int statusId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status.sttId", statusId));
		criteria.addOrder(Order.desc("publishedDate"));
		criteria.setFirstResult(indexMin);
		criteria.setMaxResults(numbOfResult);
		criteria.setFetchMode("category", FetchMode.JOIN);
		criteria.setFetchMode("type", FetchMode.JOIN);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getPostsLimitResultByCategory(int indexMin, int numbOfResult, int cateId) {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.desc("publishedDate"));
		criteria.add(Restrictions.eq("status.sttId", PostStatus.APPROVED));
		criteria.add(Restrictions.eq("category.cateId", cateId));
		criteria.setFirstResult(indexMin);
		criteria.setMaxResults(numbOfResult);
		return (List<Post>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
    @Override
    public List<Post> getPostsLimitResultByCategory(int indexMin, int numbOfResult, String cateLink) {
	    Query query = getSession().createQuery("from Post p where p.category.url = :cateLink and p.status.sttId = :sttId order by p.publishedDate desc");
	    query.setParameter("sttId", PostStatus.APPROVED);
	    query.setParameter("cateLink", cateLink);
        query.setFirstResult(indexMin);
        query.setMaxResults(numbOfResult);
        return (List<Post>) query.list();
    }

	@Override
	public boolean isExist(String link) {
		Query query = getSession().createQuery("select p.postId from Post p where p.link = :link");
		query.setParameter("link", link);
		return query.list().size() > 0 ? true : false;
	}

	@Override
	public void deletePostByRSS(int rssId) {
		Query query = getSession().createQuery("delete from Post p where p.rssChannel.rssChannelId = :rssId");
		query.executeUpdate();
	}

	@Override
	public void updateCateForPostByRSS(RSSChannel rssChannel) {
		Query query = getSession().createQuery("update Post p set p.category = :category where p.rssChannel.rssChannelId = :rssId");
		query.setParameter("category", rssChannel.getCategory());
		query.setParameter("rssId", rssChannel.getRssChannelId());
		query.executeUpdate();
	}

	@Override
	public int countResultByStatus(int status) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status.sttId", status));
		return ((Long)criteria.setProjection(Projections.count("postId")).uniqueResult()).intValue();
	}

	@Override
	public int countResultByCategory(int cateId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("category.cateId", cateId));
		return ((Long)criteria.setProjection(Projections.count("postId")).uniqueResult()).intValue();
	}

    @Override
    public int countResultByCategory(String cateLink) {
        Query query = getSession().createQuery("select count(p.postId) from Post p where p.category.url = :cateLink");
        query.setParameter("cateLink", cateLink);
        return ((Long) query.list().get(0)).intValue();
    }

    @Override
    public Post findPostByUrl(String url) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("link", url));
            return (Post) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getPostsByAuthor(String author) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("author", userDAO.findUserByEmail(author)));
		criteria.addOrder(Order.desc("publishedDate"));
		return criteria.list();
	}

	@Override
	public boolean deletePostsByCate(int cateId) {
		Query query = getSession().createQuery("delete Post p where p.category.cateId = :cateId");
		query.setParameter("cateId", cateId);
		return query.executeUpdate() > 0;
	}

}
