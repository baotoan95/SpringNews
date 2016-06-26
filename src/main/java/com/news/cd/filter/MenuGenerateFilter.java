package com.news.cd.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.news.cd.entities.Category;
import com.news.cd.services.CategoryService;

/*
 * This filter will check if menu is not created then it will be create menu
 * and save into main_menu session
 * */

public class MenuGenerateFilter implements HandlerInterceptor {
	@Autowired
	private CategoryService categoryService;

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception modelAndView)
			throws Exception {

	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {

	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		HttpSession session = request.getSession();
		String menu = (String) session.getAttribute("main_menu");
		if (null == menu || null != session.getAttribute("updated")) {
		    StringBuilder menuHTML = new StringBuilder(generateMenu(categoryService.getAll(), 0).replaceAll("class", "class='sub-menu'").replaceFirst("class='sub-menu", "class='sf-menu sf-js-enabled'").replaceAll("link", "review?ctg="));
            menuHTML.insert(menuHTML.length() - 5, "<li><a href='contact.html'>Liên Hệ</a></li>"); // Thêm menu liên hệ
            menuHTML.append("</ul>");
			session.setAttribute("main_menu", menuHTML.toString());
			session.setMaxInactiveInterval(5000);
		}
		return true;
	}
	
	private String generateMenu(Map<Integer, Category> listCates, int parent) {
		// get danh sách cate có parent == parent truyền vào
		Map<Integer, Category> listCatesTemp = new HashMap<Integer, Category>(listCates); // Danh sách tạm
		List<Category> listMenu = new ArrayList<Category>(); // Danh sách menu sẽ được in ra ở vòng lặp hiện tại
		
		Set<Integer> keyMenus = listCatesTemp.keySet();
		Iterator<Integer> iter = keyMenus.iterator();
		while(iter.hasNext()) {
			int currentKey = iter.next();
			Category cate = listCatesTemp.get(currentKey);
			if(cate.getParent() == parent && cate.isMenu()) {
				// Nếu có parent bằng với parent truyền vào thì add vào listMenu để generate html
				listMenu.add(cate);
				// remove trong listCates để khỏi lặp lại
				listCates.remove(currentKey);
			}
		}
		// generate menu html
		StringBuilder menu = new StringBuilder("<ul class>");
		// Menu đầu tiên sẽ là menu cha + Trang chủ
		if(parent == 0) menu.append("<li><a href='home.html'> Trang chủ </a></li>");
		for (Category item : listMenu) {
			if(isContainer(listCates, item.getCateId())) {
				menu.append("<li><a href='link" + item.getUrl() + "&p=1'> " + item.getName() + generateMenu(listCates, item.getCateId()) + " </a></li>");
			} else {
				menu.append("<li><a href='link" + item.getUrl() + "&p=1'> " + item.getName() + " </a></li>");
			}
		}
		menu.append("</ul>");
		return menu.toString();
	}
	
	private boolean isContainer(Map<Integer, Category> listCates, int parentId) {
		Set<Integer> keyMenus = listCates.keySet();
		Iterator<Integer> iter = keyMenus.iterator();
		while(iter.hasNext()) {
			if(listCates.get(iter.next()).getParent() == parentId) {
				return true;
			}
		}
		return false;
	}
	
	

}
