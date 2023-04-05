package com.sangmin.portfolio.board.dto;

import com.sangmin.portfolio.board.entity.Comment;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
	
	private Long uniqueId;
	private String comment;
	private String writer;
	private Long idx;
	@Builder
	public CommentDto(String comment) {
	    this.comment = comment;
	}
	
	public CommentDto(Comment comment) {
	    this.comment = comment.getComment();
	    this.uniqueId =comment.getUniqueId();
	    this.writer = comment.getWriter();
	    this.idx = comment.getIdx();
	}
		
	public Comment toEntity() {
	    return Comment.builder()
	    		.uniqueId(uniqueId)   
	            .comment(comment)
	            .idx(idx)
	            .writer(writer)
	            .build();
	}

}
