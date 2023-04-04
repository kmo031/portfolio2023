package com.sangmin.portfolio.board.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sangmin.portfolio.board.dto.CommentDto;
import com.sangmin.portfolio.board.entity.Board;
import com.sangmin.portfolio.board.entity.Comment;
import com.sangmin.portfolio.board.repository.BoardRepository;
import com.sangmin.portfolio.board.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	@Transactional
	public Long save(CommentDto requestDto) {
	    return commentRepository.save(requestDto.toEntity()).getUniqueId();
	}
	
	public List<Comment> getList() {
		return commentRepository.findAll();
	}
	
	public Page<Comment> findByBoardList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10); // <- Sort 추가
		return commentRepository.findAll(pageable);
	}
	
	public Optional<Comment> getId(Long uniqueId) {
		return commentRepository.findById(uniqueId);
	}
	
	public void deletById(Long uniqueId) {
		commentRepository.deleteById(uniqueId);
	}


}
