package com.news.cd.controllers;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.news.cd.constants.PostStatus;
import com.news.cd.entities.Category;
import com.news.cd.entities.Post;
import com.news.cd.entities.PostType;
import com.news.cd.helper.FileUpload;
import com.news.cd.services.CategoryService;
import com.news.cd.services.CommonService;
import com.news.cd.services.PostService;
import com.news.cd.services.UserService;
import com.news.cd.utils.StringUtils;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Category.class, "category", new PropertyEditorSupport() {
            @Override
            public void setAsText(String cateId) {
                Category category = categoryService.getCateById(Integer.parseInt(cateId));
                setValue(category);
            }
        });
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewPostByUrl(@RequestParam("u") String url, 
    		@CookieValue(value = "pageViewed", required = false, defaultValue = "") String pageViewed, 
            ModelMap model, HttpServletResponse response) {
	    Post post = postService.findPostByUrl(url);
	    if(null != post && !pageViewed.contains(String.valueOf(post.getPostId()))) {
            // Tăng số view của post
            post.setViews(post.getViews() + 1);
            postService.updatePost(post);
            Cookie cookie = new Cookie("pageViewed", pageViewed.concat(" " + post.getPostId())); 
            cookie.setMaxAge(31536000); // Xác định cookie 1 năm :3
            response.addCookie(cookie);
	    }
	    // View post
	    if(url.contains("http")) {
	        model.addAttribute("url", url);
	        return "viewPostByUrl";
	    } else {
	        if(null != post) {
	            model.addAttribute("post", post);
	            model.addAttribute("listLatest", postService.getPostsLatest(10));
	            model.addAttribute("listPopular", postService.getPostsPopular(10));
	            return "single";
	        }
	        return "redirect:/";
	    }
    }
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "admin/posts.html", method = RequestMethod.GET)
	public String getUnapproved(@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "page", required = true) int currentPage, ModelMap model) {
		Map<String, Object> result = postService.getPostsLimitResultByStatus(currentPage, 20, 5, getStatus(type));
		// Thêm link cho url đã được generate
		String pagination = ((String) result.get("html")).replaceAll("link",
				"posts.html?type=" + type + "&page=").replace("classContainer",
				"class='pagination pagination-sm no-margin pull-right'");
		if(((List<Post>)result.get("data")).size() > 0) {
		    model.addAttribute("posts", result.get("data"));
		    model.addAttribute("pagination", pagination);
		}
		return "adminPosts";
	}
	
	private int getStatus(String statusName) {
		switch (statusName) {
    		case "unapproved":
    			return PostStatus.UNAPPROVED;
    		case "approved":
    			return PostStatus.APPROVED;
    		default:
    			return PostStatus.WRITING;
		}
	}
	
	@RequestMapping(value = "admin/write.html", method = RequestMethod.GET)
	public String getWritePostView(ModelMap model) {
	    Post post = new Post();
	    post.setType(commonService.getPostTypeById(com.news.cd.constants.PostType.OWN));
	    post.setStatus(commonService.getStatusById(PostStatus.WRITING));
	    post.setCategory(new Category());
	    model.addAttribute("post", post);
	    model.addAttribute("categories", categoryService.getAll().values());
	    model.addAttribute("action", "addPost");
		return "adminWritePost";
	}
	
	@RequestMapping(value = "admin/addPost", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("post") Post post, BindingResult result, 
			@RequestParam(value = "statusId") int statusId,
	        @RequestParam(value = "avatar", required = false) MultipartFile file, ModelMap model) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String message = "";
        if (auth != null) {
        	model.addAttribute("categories", categoryService.getAll().values());
        	/*
    	     * postType 1: rss
    	     * postType 2: springnews
    	     * */
    	    post.setType(new PostType(2));
    	    post.setStatus(commonService.getStatusById(statusId));
        	
        	if(result.hasErrors()) {
        		System.out.println(result.getFieldError());
        		post.setType(commonService.getPostTypeById(com.news.cd.constants.PostType.OWN));
        		return "adminWritePost";
        	}
            if(null == post.getCategory()) {
                message = "Vui lòng chọn thể loại";
                model.addAttribute("action", "addPost");
            } else {
            	// Upload image file
            	if(null != file && !file.isEmpty()) {
        	        FileUpload fileUpload = new FileUpload();
        	        String destination = "\\resources\\upload\\images\\";
        	        post.setAvatarUrl(fileUpload.process(file, destination));
        	    }
                post.setAuthor(userService.findUserByEmail(auth.getName()));
        	    post.setlink(StringUtils.removeAccent(post.getTitle()).replaceAll(" ", "-"));
        	    post.setPublishedDate(new Date());
        	    post.setLastUpdate(new Date());
        	    if(postService.addPost(post)) {
        	        model.addAttribute("action", "updatePost");
        	    } else {
        	        message = "Lỗi hệ thống, vui lòng đổi tiêu đề và thử lại!";
        	        model.addAttribute("action", "addPost");
        	    }
            }
    	    model.addAttribute("post", post);
    	    model.addAttribute("message", message);
    	    return "adminWritePost";
        }
        return "redirect:/login.html";
	}
	
	@RequestMapping(value = "admin/editView", method = RequestMethod.GET)
	public String getEditPostView(@RequestParam("pid") int pid, ModelMap model) {
	    Post post = postService.findPostById(pid);
	    if(null == post) {
	        return "redirect:posts.html?type=unapproved&page=1";
	    }
	    model.addAttribute("categories", categoryService.getAll().values());
	    model.addAttribute("post", post);
	    model.addAttribute("action", "updatePost");
	    return "adminWritePost";
	}
	
	@RequestMapping(value = "admin/updatePost", method = RequestMethod.POST)
	public String updatePost(@ModelAttribute("post") Post post, 
			@RequestParam(value = "statusId") int statusId,
	        @RequestParam(value = "avatar", required = false) MultipartFile file, ModelMap model) {
	    Post oldPost = postService.findPostById(post.getPostId());
	    // Upload image file
	    if(!file.isEmpty()) {
	    	FileUpload fileUpload = new FileUpload();
	    	String destination = "\\resources\\upload\\images\\";
	    	oldPost.setAvatarUrl(fileUpload.process(file, destination));
	    }
	    oldPost.setStatus(commonService.getStatusById(statusId));
	    oldPost.setCategory(post.getCategory());
	    oldPost.setContent(post.getContent());
	    oldPost.setDesc(post.getDesc());
	    oldPost.setTitle(post.getTitle());
	    if(postService.updatePost(oldPost)) {
	        model.addAttribute("message", "Cập nhật thành công");
	    } else {
	        model.addAttribute("message", "Lỗi hệ thống...");
	    }
	    model.addAttribute("categories", categoryService.getAll().values());
        model.addAttribute("post", oldPost);
        model.addAttribute("action", "updatePost");
	    return "adminWritePost";
	}
	
	@RequestMapping(value = "/admin/deletePost", method = RequestMethod.GET)
    @ResponseBody
    public boolean deletePost(@RequestParam("postId") int postId) {
        return postService.deletePost(postId);
    }
}
