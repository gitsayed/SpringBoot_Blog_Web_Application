package com.blog.domain;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.model.Blog;
import com.blog.model.BlogUser;
import com.blog.model.SignupForm;
import com.blog.repository.UserDao;
import com.blog.security.UserService;
import com.blog.service.BlogService;
import com.blog.service.BlogService2;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	@Autowired
	BlogService blogService;
	@Autowired
	BlogService2 blogService2;
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;

	@GetMapping("/")
	public String startWeb(ModelMap model) {
		List<Blog> ablist = blogService2.getActiveBlogkList();
          	model.addAttribute("blogs", ablist);
		return "home";
	}

	@GetMapping("/login")
	public String loginWeb(@ModelAttribute("sms") Object sms, ModelMap model) {
		model.addAttribute("sms", sms);
		return "login";
	}

	@GetMapping(value = "/signup")
	public String signUpForm(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}

	@GetMapping(value = "/success")
	public String loginSuccess() {
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		boolean authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		SecurityContextHolderAwareRequestWrapper.isUserInRole(String role);
		String context = "home";
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = userDetails.getUsername();
		Object arr[] = userDetails.getAuthorities().toArray();
		for (int i = 0; i < arr.length; i++) {
			String role = arr[i].toString();
			if (role.equals("ADMIN")) {
				context = "redirect:/api/v1/admin/";
			} else if (role.equals("USER")) {
				context = "redirect:/api/v2/blogs/";
			}
		}
		return context;

	}

	@PostMapping(value = "/signup")
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm,
			BindingResult bindingResult,
			ModelMap model) {

		BlogUser b_user = userDao.findByUsername(signupForm.getUsername());
		System.out.println("bindingResult : " + bindingResult.toString());
		if (!bindingResult.hasErrors()) {
			if (b_user != null) {
				model.addAttribute("sms", "Username : \"" + b_user.getUsername() + "\" \t already exists");
				return "signup";
			}
			// validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				BlogUser newUser = new BlogUser();
				newUser.setPassword(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				userService.saveUser(newUser);
//				if (repository.findUserByname(signupForm.getUsername()) == null) {
//					repository.save(newUser);
//				} else {
//					bindingResult.rejectValue("username", "error.userexists", "Username already exists");
//					return "signup";
//				}
				model.addAttribute("sms", "Account created successfully. Wait for Admin apporval");

			} else {
				bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");
				return "signup";
			}
		} else {

			return "signup";
		}
		return "signup";
	}
	

//	@PostMapping(value = "/comment")
//	public String saveComment(@ModelAttribute Comment comment, Model model) {
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = userDetails.getUsername();
//		System.out.println("ctx user name " + username);
//		BlogUser adu 	= userService.findByUsername(username);
//		Integer id 		= comment.getBlog_id();
//		comment.setUsername(username);
//		blogService.saveComment(comment);
//		BlogDto blogDto = blogService.bookDetailsById(id);
//		model.addAttribute("dto", blogDto);
//		return "redirect:/api/v2/blogs/view/" + id;
//
//	}
}
