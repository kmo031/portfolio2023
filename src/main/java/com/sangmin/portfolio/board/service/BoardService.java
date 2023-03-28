package com.sangmin.portfolio.board.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sangmin.portfolio.board.dto.BoardDto;
import com.sangmin.portfolio.board.entity.Board;
import com.sangmin.portfolio.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public Long save(BoardDto requestDto) {
	    return boardRepository.save(requestDto.toEntity()).getUniqueId();
	}
	
	public List<Board> getList() {
		return boardRepository.findAll();
	}
	
	public Page<Board> findByBoardList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10); // <- Sort 추가
		return boardRepository.findAll(pageable);
	}
	
	public Optional<Board> getId(Long uniqueId) {
		return boardRepository.findById(uniqueId);
	}
	
	public void deletById(Long uniqueId) {
		 boardRepository.deleteById(uniqueId);
	}


}
