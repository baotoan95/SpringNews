package com.news.cd.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.news.cd.dto.FileUploadForm;
import com.news.cd.entities.Category;
import com.news.cd.helper.FileUpload;
import com.news.cd.services.CategoryService;
import com.news.cd.services.PostService;
import com.news.cd.utils.StringUtils;

@Controller
@RequestMapping("/")
public class CategoryHandler {
	@Autowired
	private CategoryService categorySerivce;
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/review")
	public String reviewCategory(@RequestParam("ctg") String link, @RequestParam("p") int page, ModelMap model) {
	    Category category = categorySerivce.getCateByLink(link);
	    if(null != category) {
	        model.addAttribute("category", category);
	        if(postService.countResultByCategory(category.getCateId()) <= 0) {
	            model.addAttribute("message", "Xin lỗi, thể loại này chưa được cập nhật nội dung...");
	        } else {
        	    Map<String, Object> result = postService.getPostsLimitResultByCategory(page, 10, 4, link);
        	    model.addAttribute("posts", result.get("data"));
        	    model.addAttribute("pagination", ((String)result.get("html")).replaceAll("link", "review?ctg=" + link + "&p="));
	        }
	        model.addAttribute("listLatest", postService.getPostsLatest(10));
	        model.addAttribute("listPopular", postService.getPostsPopular(10));
    	    return "reviews";
	    } else {
	        return "redirect:/";
	    }
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/categories.html", method = RequestMethod.GET)
	public String adminListCates(@RequestParam("page") int currentPage, ModelMap model) {
		Map<String, Object> listCates = categorySerivce.getForPagination(currentPage, 5, 4);
		String pagination = ((String) listCates.get("html")).replaceAll("link",
				"categories.html?page=").replace("classContainer",
				"class='pagination pagination-sm no-margin pull-right'");
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("data", (List<Category>) listCates.get("data"));
		return "adminCates";
	}

	@RequestMapping(value = "/admin/editCategory", method = RequestMethod.GET)
	public String getEditCatePage(ModelMap model, @RequestParam("cate") int cateId) {
		// Get category by id
		Category cate = categorySerivce.getCateById(cateId);
		if (null == cate) {
			model.addAttribute("message", "Thể loại vừa chọn không tồn tại trong hệ thống");
			return "redirect:/categories.html";
		}
		model.addAttribute("categories", getListCates());
		model.addAttribute("title", "Chỉnh sửa thể loại");
		model.addAttribute("category", cate);
		model.addAttribute("action", "editCategory");
		return "adminEditCates";
	}

	@RequestMapping(value = "/admin/editCategory", method = RequestMethod.POST)
	public String editCatePage(ModelMap model, @Valid @ModelAttribute("category") Category category, 
			BindingResult result,  
			@RequestParam(value = "avatar", required = false) MultipartFile file, HttpSession session) {
		model.addAttribute("categories", getListCates());
		if(result.hasErrors()) {
			return "adminEditCates";
		}
		if(!file.isEmpty()) {
	        FileUpload fileUpload = new FileUpload();
	        String destination = "\\resources\\upload\\images\\";
	        category.setAvatarUrl(fileUpload.process(file, destination));
	    }
		if(categorySerivce.updateCate(category)) {
			model.addAttribute("message", "Cập nhật thể loại \"" + category.getName() + "\" thành công");
			session.setAttribute("updated", "updated");
		} else {
			model.addAttribute("message", "Lỗi hệ thống...");
		}
		return "adminEditCates";
	}

	@RequestMapping(value = "/admin/add-category.html", method = RequestMethod.GET)
	public String getAddCategoryView(ModelMap model) {
		model.addAttribute("categories", getListCates());
		model.addAttribute("category", new Category());
		model.addAttribute("action", "addCategory");
		model.addAttribute("fileUploadForm", new FileUploadForm());
		return "adminEditCates";
	}

	@RequestMapping(value = "/admin/addCategory", method = RequestMethod.POST)
	public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, ModelMap model, HttpSession session,
	        @RequestParam("avatar") MultipartFile file) {
		model.addAttribute("categories", getListCates());
		if(result.hasErrors()) {
			return "adminEditCates";
		}
	    if(!file.isEmpty()) {
	        FileUpload fileUpload = new FileUpload();
	        String destination = "/resources/upload/images/";
	        category.setAvatarUrl(fileUpload.process(file, destination));
	    }
	    category.setUrl(StringUtils.removeAccent(category.getName()).replace(" ", "-"));
		if(categorySerivce.addCate(category)) {
			model.addAttribute("message", "Đã thêm \""  + category.getName() +  "\" thành công");
			model.addAttribute("action", "editCategory");
			session.setAttribute("updated", "updated");
		} else {
			model.addAttribute("message", "Lỗi hệ thống...");
			model.addAttribute("action", "addCategory");
		}
		model.addAttribute("category", category);
		return "adminEditCates";
	}
	
	@RequestMapping(value = "/admin/deleteCategory", method = RequestMethod.GET)
	@ResponseBody
	public String deleteCategory(@RequestParam("cateId") int cateId, HttpSession session) {
		if(categorySerivce.delCate(cateId)) {
		    session.setAttribute("updated", "updated");
			return "success";
		}
		return "fail";
	}

	private List<Category> getListCates() {
		// Get list category to show combo box category parent
		Map<Integer, Category> rs = categorySerivce.getAll();
		List<Category> cates = new ArrayList<Category>();
		Set<Integer> keys = rs.keySet();
		Iterator<Integer> iters = keys.iterator();
		while (iters.hasNext()) {
			cates.add(rs.get(iters.next()));
		}
		return cates;
	}
}
