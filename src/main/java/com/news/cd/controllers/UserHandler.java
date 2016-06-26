package com.news.cd.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.news.cd.constants.ApplicationConstant;
import com.news.cd.constants.RoleCode;
import com.news.cd.dto.ChangePassForm;
import com.news.cd.entities.User;
import com.news.cd.entities.UserRole;
import com.news.cd.services.PostService;
import com.news.cd.services.RoleService;
import com.news.cd.services.UserRoleService;
import com.news.cd.services.UserService;
import com.news.cd.services.impl.MailService;
import com.news.cd.utils.GenerationCommon;

@Controller
public class UserHandler {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private MailService mailService;
	@Autowired
	private PostService postService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				false));
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginPage(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			ModelMap model, HttpServletRequest request) {
		if (null != error && !error.isEmpty()) {
			model.addAttribute("message",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (null != logout) {
			model.addAttribute("message", "Đã đăng xuất");
		}
		return "login";
	}

	private String getErrorMessage(HttpServletRequest request, String code) {
		Exception exception = (Exception) request.getSession().getAttribute(
				code);
		return exception.getMessage();
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		// You can redirect wherever you want, but generally it's a good
		// practice to show login screen again.
		return "redirect:/home.html";
	}

	@RequestMapping(value = "/registry.html", method = RequestMethod.GET)
	public String registerPage(ModelMap model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(value = "/registry.html", method = RequestMethod.POST)
	public String registerProccess(@Valid @ModelAttribute("user") User user,
			BindingResult result,
			@RequestParam("passConfirm") String passConfirm, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("message", result.getFieldError());
			return "register";
		}
		if (!user.getPassword().equals(passConfirm)) {
			model.addAttribute("message", "Xác nhận mật khẩu không khớp");
			return "register";
		}
		user.setCode(GenerationCommon.generateCode(20));
		user.setEnabled(false);
		user.setNonLocked(true);
		Set<UserRole> roles = new HashSet<>();
		roles.add(new UserRole(user, roleService.getRoleByName("ROLE_AUDIENT")));
		user.setRoles(roles);
		user.setAvatarUrl("/resources/upload/images/user1-128x128.jpg");
		try {
			// Registry success
			userService.addUser(user);
			// Create mail body
			StringBuilder mailBody = new StringBuilder();
			mailBody.append("Xin chào " + user.getLastName()
					+ ", kích hoạt tài khoản hãy vào link sau: <br/>");
			mailBody.append("http://localhost:8080/SpringNews/active?uid=");
			mailBody.append(user.getEmail());
			mailBody.append("&k=");
			mailBody.append(user.getCode());

			mailService.sender(new String[] { user.getEmail() },
					"SpringNews - User confirm", mailBody.toString());
			model.addAttribute("message",
					"Đăng ký thành công, vui lòng kiểm tra email");
			return "login";
		} catch (Exception e) {
			// Registry failed
			model.addAttribute("user", user);
			model.addAttribute("message", "Email đã tồn tại trong hệ thống");
			return "register";
		}
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String activeAccount(@RequestParam("uid") String uid,
			@RequestParam("k") String code, ModelMap model) {
		if (userService.activeAccount(uid, code)) {
			model.addAttribute("message", "Tài khoản đã được kích hoạt");
		} else {
			model.addAttribute("message",
					"Kích hoạt tài khoản thất bại, vui lòng kiểm tra lại");
		}
		return "login";
	}

	@RequestMapping(value = "/remember", method = RequestMethod.GET)
	public String requestChangePass(@RequestParam("uid") String email,
			ModelMap model) {
		model.addAttribute("message", userService.rememberPass(email));
		return "login";
	}

	@RequestMapping(value = "/changePass", method = RequestMethod.GET)
	public String getChangePassView(@RequestParam("uid") String email,
			@RequestParam("k") String code, ModelMap model) {
		String error = userService.checkCodeError(email, code);
		if (null != error) {
			model.addAttribute("message", error);
			return "login";
		}
		ChangePassForm changePassForm = new ChangePassForm();
		changePassForm.setEmail(email);
		changePassForm.setCode(code);
		model.addAttribute("changePassForm", changePassForm);
		return "changePass";
	}

	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public String changePass(
			@ModelAttribute("changePassForm") ChangePassForm changePassForm,
			ModelMap model) {
		if (changePassForm.getPassConfirm().equals(
				changePassForm.getPassConfirm())) {
			model.addAttribute("message", userService.changePassword(
					changePassForm.getEmail(), changePassForm.getCode(),
					changePassForm.getPassword()));
			return "login";
		} else {
			model.addAttribute("message", "Xác nhận mật khẩu không khớp");
			model.addAttribute("changePassForm", changePassForm);
			return "changePass";
		}
	}

	public String updateUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "profile";
		}

		return "profile";
	}

	@RequestMapping(value = "/profile.html", method = RequestMethod.GET)
	public String getProfile(Authentication authentication, ModelMap model) {
		User user = userService.findUserByEmail(authentication.getName());
		if (null != user) {
			model.addAttribute("user", user);
			model.addAttribute("listLatest", postService.getPostsLatest(10));
			model.addAttribute("listPopular", postService.getPostsPopular(10));
			model.addAttribute("listPost",
					postService.getPostsByAuthor(user.getEmail()));
			model.addAttribute("genders", userService.listGender());
		} else {
			model.addAttribute("user", new User());
			return "login";
		}
		return "profile";
	}

	// chauphi90
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/mems-admin.html", method = RequestMethod.GET)
	public String userAdmins(@RequestParam("page") int currentPage,
			ModelMap model) {
		Map<String, Object> listAdmins = userService.getForPaginationByRole(
				currentPage, 5, 4, RoleCode.ADMIN);
		String pagination = ((String) listAdmins.get("html")).replaceAll(
				"link", "mems-admin.html?page=").replace("classContainer",
				"class='pagination pagination-sm no-margin pull-right'");
		model.addAttribute("pagination", pagination);
		model.addAttribute("data", (List<User>) listAdmins.get("data"));
		model.addAttribute("title", "Danh sách quản trị viên");
		model.addAttribute("role", "quản trị viên");

		return "members";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/mems-writer.html", method = RequestMethod.GET)
	public String userWriter(@RequestParam("page") int currentPage,
			ModelMap model) {
		Map<String, Object> listWriter = userService.getForPaginationByRole(
				currentPage, 5, 4, RoleCode.WRITER);
		String pagination = ((String) listWriter.get("html")).replaceAll(
				"link", "mems-writer.html?page=").replace("classContainer",
				"class='pagination pagination-sm no-margin pull-right'");
		model.addAttribute("pagination", pagination);
		model.addAttribute("data", (List<User>) listWriter.get("data"));
		model.addAttribute("title", "Danh sách tác giả");
		model.addAttribute("role", "tác giả");

		return "members";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/mems-audient.html", method = RequestMethod.GET)
	public String adminAudient(@RequestParam("page") int currentPage,
			ModelMap model) {
		Map<String, Object> listAudient = userService.getForPaginationByRole(
				currentPage, 5, 4, RoleCode.AUDIENT);
		String pagination = ((String) listAudient.get("html")).replaceAll(
				"link", "mems-audient.html?page=").replace("classContainer",
				"class='pagination pagination-sm no-margin pull-right'");
		model.addAttribute("pagination", pagination);
		model.addAttribute("page", currentPage);
		model.addAttribute("data", (List<User>) listAudient.get("data"));
		model.addAttribute("title", "Danh sách người dùng thường");
		model.addAttribute("role", "người dùng thường");

		return "members";
	}

	@RequestMapping(value = "/admin/add-mem.html", method = RequestMethod.GET)
	public String getAddUserPage(ModelMap model) {
		User user = new User();
		user.setBirthDay(new Date());
		model.addAttribute("user", user);
		model.addAttribute("action", "addUser");
		model.addAttribute("title", "Thêm tài khoản");
		model.addAttribute("genders", userService.listGender());

		return "adminEditMem";
	}

	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, ModelMap model,
			@RequestParam(value = "roleList", required = false) int[] roles,
			@RequestParam(value = "avatar", required = false) MultipartFile file) {

		// Validate
		if (result.hasErrors()) {
			model.addAttribute("title", "Thêm người dùng");
			model.addAttribute("genders", userService.listGender());
			return "adminEditMem";
		}

		if (null == roles) {
			model.addAttribute("message", "Vui lòng phân quyền cho người dùng");
			model.addAttribute("user", user);
			return "adminEditMem";
		}

		userService.addUser(user, file, roles);

		return "redirect:/admin/mems-audient.html?page=1";
	}

	// chauphi90
	@RequestMapping(value = "/admin/editUser", method = RequestMethod.GET)
	public String getEditUserPage(ModelMap model,
			@RequestParam("email") String email) {
		User user = userService.findUserByEmailWithRoles(email);
		model.addAttribute("user", user);
		model.addAttribute("action", ApplicationConstant.ROOT_PATH
				+ "/updateUser");
		model.addAttribute("title", "Chỉnh sửa tài khoản");
		model.addAttribute("genders", userService.listGender());

		return "adminEditMem";
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, ModelMap model, HttpServletRequest request,
			@RequestParam(value = "roleList", required = false) int[] roles,
			@RequestParam(value = "avatar", required = false) MultipartFile file) {
		// Set redirect
		String previousURL = request.getHeader("Referer");
		if (previousURL.contains("profile")) {
			previousURL = "redirect:/profile.html";
		} else {
			previousURL = "adminEditMem";
		}

		// Validate
		if (result.hasErrors()) {
			model.addAttribute("genders", userService.listGender());
			return previousURL;
		}

		userService.updateUser(user, file, roles);

		model.addAttribute("user", user);
		model.addAttribute("message", "Cập nhật thành công");
		return previousURL;
	}

	@RequestMapping(value = "/admin/deleteUser", method = RequestMethod.GET)
	@ResponseBody
	public String deleteUser(@RequestParam("email") String email) {
		if (userService.deleteUser(email)) {
			return "success";
		}
		return "fail";
	}

}
