package com.youmekko.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youmekko.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
}
