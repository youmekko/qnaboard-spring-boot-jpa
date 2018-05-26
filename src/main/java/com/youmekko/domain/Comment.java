package com.youmekko.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Comment extends AbstractEntity {

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_comment_writer"))
	@JsonProperty
	private User writer;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_comment_to_question"))
	@JsonProperty
	private Question question;

	@Lob
	@JsonProperty
	private String contents;

	public Comment() {
	}

	public Comment(User writer, Question question, String contents) {
		this.writer = writer;
		this.question = question;
		this.contents = contents;
	}

	public boolean isSameWriter(User loginedUser) {
		return loginedUser.equals(this.writer);
	}

	@Override
	public String toString() {
		return "Comment [" + super.toString() + ", writer=" + writer + ", contents=" + contents + "]";
	}

}
