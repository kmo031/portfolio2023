package com.sangmin.portfolio.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sangmin.portfolio.board.entity.Board;
import com.sangmin.portfolio.board.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Page<Comment> findAll(Pageable pageable);

}
