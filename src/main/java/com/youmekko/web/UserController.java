package com.youmekko.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youmekko.domain.User;
import com.youmekko.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(Long id, String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			System.out.println("LOGIN FAILURE!");
			return "redirect:/users/loginForm";
		}

		if (!password.equals(user.getPassword())) {
			System.out.println("LOGIN FAILURE!");
			return "redirect:/users/loginForm";
		}

		System.out.println("LOGIN SUCCESS!");
		session.setAttribute("loginedUser", user);

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginedUser");
		return "redirect:/";
	}

	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@PostMapping("")
	public String create(User user) {
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Object tempUser = session.getAttribute("loginedUser");

		if (tempUser == null) {
			return "redirect:/users/form";
		}

		User loginedUser = (User) tempUser;
		// if(!id.equals(loginedUser.getId())) {
		// throw new IllegalStateException("you can olny modify your infomation!");
		// }

		User user = userRepository.findById(loginedUser.getId()).get();
		model.addAttribute("user", user);

		return "/user/updateForm";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updateUser, HttpSession session) {

		Object tempUser = session.getAttribute("loginedUser");

		if (tempUser == null) {
			return "redirect:/users/form";
		}

		User loginedUser = (User) tempUser;
		 if(!id.equals(loginedUser.getId())) {
		 throw new IllegalStateException("you can olny update your infomation!");
		 }

		User user = userRepository.findById(id).get();
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("/profile")
	public String profile() {
		return "/user/profile";
	}

}
