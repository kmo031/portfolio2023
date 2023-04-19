package com.sangmin.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sangmin.portfolio.board.entity.PhotoBoard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor
public class File {

	@Id
	@GeneratedValue
	private Long id;
	private String uuid;
	@Column(nullable = false)
//    private Long pno;
//	@Column(nullable = false)
	private String uploadPath;


	 
	private String fileName;
	private boolean fileType;
	private boolean mainPicture;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id",  updatable = false, nullable=false)
	@JsonBackReference
	private PhotoBoard photoBoard; 


    
}