package com.youmekko.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youmekko.domain.Comment;
import com.youmekko.domain.CommentRepository;
import com.youmekko.domain.Question;
import com.youmekko.domain.QuestionRepository;
import com.youmekko.domain.User;

@Controller
@RequestMapping("/questions/{questionId}/comments")
public class CommentController {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private CommentRepository commentRepository;

	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}

		User loginedUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findById(questionId).get();

		Comment comment = new Comment(loginedUser, question, contents);
		commentRepository.save(comment);

		return String.format("redirect:/questions/%d", questionId);
	}

}
