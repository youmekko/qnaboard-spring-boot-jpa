package com.youmekko.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youmekko.domain.Comment;
import com.youmekko.domain.CommentRepository;
import com.youmekko.domain.Question;
import com.youmekko.domain.QuestionRepository;
import com.youmekko.domain.Result;
import com.youmekko.domain.User;

@RestController
@RequestMapping("/api/questions/{questionId}/comments")
public class ApiCommentController {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private CommentRepository commentRepository;

	@PostMapping("")
	public Comment create(@PathVariable Long questionId, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}

		User loginedUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findById(questionId).get();
		Comment comment = new Comment(loginedUser, question, contents);

		question.addComment();

		return commentRepository.save(comment);

	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인 해야 합니다.");
		}

		Comment comment = commentRepository.findById(id).get();

		User loginedUser = HttpSessionUtils.getUserFromSession(session);

		if (!comment.isSameWriter(loginedUser)) {
			return Result.fail("자신의 댓글만 삭제할 수 있습니다.");
		}

		commentRepository.deleteById(id);

		Question question = questionRepository.findById(questionId).get();
		question.deleteComment();
		questionRepository.save(question);

		return Result.ok();

	}

}
