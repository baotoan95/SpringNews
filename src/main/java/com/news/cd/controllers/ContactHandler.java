package com.news.cd.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.cd.constants.ContactStatus;
import com.news.cd.entities.Contact;
import com.news.cd.services.CommonService;
import com.news.cd.services.ContactService;
import com.news.cd.services.PostService;
import com.news.cd.services.UserService;
import com.news.cd.services.impl.MailService;

@Controller
@RequestMapping(value = "/")
public class ContactHandler {
	@Autowired
	private ContactService contactService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;

	/*
	 * Url contain one param @type Inbox, Sent, Draft, Trash Load Mail by type
	 */

	@RequestMapping(value = "/contact.html", method = RequestMethod.GET)
	public String userContact(ModelMap model) {
		model.addAttribute("contact", new Contact());
		model.addAttribute("listLatest", postService.getPostsLatest(10));
		model.addAttribute("listPopular", postService.getPostsPopular(10));
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (null != auth) {
			model.addAttribute("user",
					userService.findUserByEmail(auth.getName()));
		}
		return "contact";
	}

	@RequestMapping(value = "/contact.html", method = RequestMethod.POST)
	public String userContact(
			@Valid @ModelAttribute("contact") Contact contact,
			BindingResult result, ModelMap model, HttpSession session) {
		model.addAttribute("listLatest", postService.getPostsLatest(10));
		model.addAttribute("listPopular", postService.getPostsPopular(10));
		if (result.hasErrors()) {
			return "contact";
		}
		contact.setSubmitDate(new Date());
		contact.setStatus(commonService.getStatusById(ContactStatus.WAIT_TO_READ));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null != auth) {
			contact.setUser(userService.findUserByEmail(auth.getName()));
		}
		if (contactService.addContact(contact)) {
			model.addAttribute("message", "Cảm ơn bạn đã đóng góp, chúng tôi sẽ liên hệ với bạn khi cần thiết");
			session.setAttribute("totalInbox", contactService.countAll("wait_to_read"));
		} else {
			model.addAttribute("message",
					"Xin lỗi, đã xảy ra lỗi, vui lòng báo cho chúng tôi vào số 1234354");
		}
		model.addAttribute("contact", new Contact());
		return "contact";
	}

	// chauphi90
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "admin/contact.html", method = RequestMethod.GET)
	public String listMails(ModelMap model, @RequestParam("repo") String repo, @RequestParam("p") int page, 
			HttpSession session) {
		Map<String, Object> map = contactService.getMailsForPagination(repo, page, 5, 5);
		session.setAttribute("totalInbox", contactService.countAll("wait_to_read"));
		model.addAttribute("pagination", map.get("html"));
		model.addAttribute("contacts", (List<Contact>) map.get("data"));
		model.addAttribute("repoTitle", (String) map.get("title"));

		return "adminContact";
	}

	@RequestMapping(value = "admin/read-mail.html", method = RequestMethod.GET)
	public String readMail(ModelMap model, @RequestParam("mail") Integer contactId, HttpSession session) {
		Contact contact = contactService.getContactById(contactId);
		model.addAttribute("contact", contact);
		session.setAttribute("totalInbox", contactService.countAll("wait_to_read"));
		// Remove bold style
		contactService.readContact(contactId);

		return "adminReadMail";
	}

	@RequestMapping(value = { "admin/write-mail.html", "admin/reply-mail.html" }, method = RequestMethod.GET)
	public String writeMail(ModelMap model,
			@RequestParam(value = "mail", required = false) Integer contactId) {
		// Reply a mail
		if (contactId != null) {
			Contact contact = contactService.getContactById(contactId);
			model.addAttribute("contact", contact);
		}

		return "adminWriteMail";
	}

	@RequestMapping(value = "admin/send-mail.html", method = RequestMethod.POST)
	public String sendMail(ModelMap model, @ModelAttribute("contact") Contact contact) {
		boolean success = mailService.sender(
				new String[] { contact.getEmail() }, contact.getName(),
				contact.getContent());
		contact.setSubmitDate(new Date());
		contact.setStatus(commonService.getStatusById(ContactStatus.SENT));
		if (success) {
			contactService.addContact(contact);
		}

		return "adminReadMail";
	}

	@RequestMapping(value = "admin/draft-mail.html", method = RequestMethod.POST)
	public String draftMail(ModelMap model,
			@ModelAttribute("contact") Contact contact) {
		contactService.draftContact(contact);

		return "adminReadMail";
	}

	@RequestMapping(value = "admin/delete-mail.html", method = RequestMethod.GET)
	@ResponseBody
	public String deleteMails(ModelMap model, HttpSession session,
			@RequestParam("mails[]") int[] contactIds) {
		if (contactService.deleteContacts(contactIds) == false) {
			return "fail";
		}
		session.setAttribute("totalInbox", contactService.countAll("wait_to_read"));
		return "success";
	}

	@RequestMapping(value = "admin/delete-a-mail.html", method = RequestMethod.GET)
	public String deleteSingleMail(ModelMap model, HttpSession session,
			@RequestParam("mail") int contactId) {
		contactService.deleteContact(contactId);
		session.setAttribute("totalInbox", contactService.countAll("wait_to_read"));
		String repo;
		int statusId = contactService.getContactById(contactId).getStatus()
				.getSttId();
		switch (statusId) {
		case ContactStatus.TRASH_READ:
		case ContactStatus.TRASH_UNREAD:
			repo = "trash";
			break;
		case ContactStatus.SENT:
			repo = "sent";
			break;
		case ContactStatus.DRAFT:
			repo = "draft";
			break;
		default:
			repo = "inbox";
			break;
		}

		return "redirect:/admin/contact.html?repo=" + repo;
	}

}
