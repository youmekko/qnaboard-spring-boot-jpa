package com.youmekko.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youmekko.domain.Question;
import com.youmekko.domain.QuestionRepository;
import com.youmekko.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/form")
	public String form(HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}

		return "/qna/form";
	}

	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}

		User loginedUser = HttpSessionUtils.getUserFromSession(session);

		Question newQuestion = new Question(loginedUser, title, contents);
		questionRepository.save(newQuestion);

		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String showDetail(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findById(id).get());
		return "/qna/show";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findById(id).get());

		return "qna/updateForm";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents) {

		Question question = questionRepository.findById(id).get();
		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/questions/%d", id);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		questionRepository.deleteById(id);;
		return "redirect:/";
	}

}
