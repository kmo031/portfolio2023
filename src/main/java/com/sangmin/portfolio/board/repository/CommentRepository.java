package com.sangmin.portfolio.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sangmin.portfolio.board.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> , JpaSpecificationExecutor<Comment> {

	Page<Comment> findAll(Pageable pageable);
	
	Page<Comment> findAllByIdx(Specification<Comment> spec,Pageable pageable);
	

}
