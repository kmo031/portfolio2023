package com.sangmin.portfolio.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sangmin.portfolio.board.dto.CommentDto;
import com.sangmin.portfolio.board.entity.Comment;
import com.sangmin.portfolio.board.repository.CommentRepository;
import com.sangmin.portfolio.board.specification.CommentSpecification;

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
	
	public Page<Comment> findByCommentList(Pageable pageable) {
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
	

	public Page<Comment> findComments(CommentDto commentDTO, Pageable pageable){
		Map<String, Object> searchKeys = new HashMap<>();

//		int firstIdx = (int) pageable.getPageNumber("pageIndex")-1;
//	    int lastIdx = (int) pageable.get("recordCountPerPage");
//	    Pageable paging = PageRequest.of(firstIdx, lastIdx
//	        , GetSort.getSort((String) map.get("sortColumn"), (String) map.get("sortOrder")));
	    Specification<Comment> spec = CommentSpecification.getSingleSpec(commentDTO); /*복수조건의 경우*/
	    //Specification<Member> spec = getSingleSpec(map); /*단일 조건의 경우*/
	    return commentRepository.findAll(spec, pageable);
}
	
	


}
