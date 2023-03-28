package com.sangmin.portfolio.board.dto;

import com.sangmin.portfolio.board.entity.Board;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {
	
	private Long uniqueId;
	private String title;
	private String contents;
	private String userId;
	private String writer;
	@Builder
	public BoardDto(String title, String contents) {
	    this.title = title;
	    this.contents = contents;
	}
		
	public Board toEntity() {
	    return Board.builder()
	    		.uniqueId(uniqueId)
	            .title(title)
	            .contents(contents)
	            .writer(writer)
	            .build();
	}

}
