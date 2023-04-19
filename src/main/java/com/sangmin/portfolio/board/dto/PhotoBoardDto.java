package com.sangmin.portfolio.board.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.sangmin.portfolio.board.entity.PhotoBoard;
import com.sangmin.portfolio.dto.AttachFileDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoBoardDto {

	private Long id;
	private Long pno;
	private String ptitle;
	private String pcontent;
	private String writer;

	private LocalDateTime regdate;
	private LocalDateTime updatedate;
	
	private List<AttachFileDTO> attachList;
	
	
	@Builder
	public PhotoBoardDto(String ptitle, String pcontent,Long id, String writer, LocalDateTime regdate,LocalDateTime updatedate ,List<AttachFileDTO> attachList) {
	    this.pcontent = pcontent;
	    this.ptitle = ptitle;
	    this.id =id;
	    this.writer = writer;
	    this.regdate = regdate;
	    this.updatedate = updatedate;
	    this.attachList = attachList;
	}
	
//	public PhotoBoardDto(PhotoBoard entity) {
//	    this.uniqueId = entity.getUniqueId();
//	    this.ptitle = entity.getPtitle();
//	    this.pcontent = entity.getPcontent();
//	    this.writer = entity.getWriter();
//	    this.regdate =entity.getCreatedDate();
//	    this.updatedate = entity.getModifiedDate();
//	}
	
	 public Page<PhotoBoardDto> toDtoList(Page<PhotoBoard> PhotoBoardList){
	        Page<PhotoBoardDto> boardDtoList = PhotoBoardList.map(m -> PhotoBoardDto.builder()
	                .ptitle(m.getPtitle())
	                .id(m.getId())
	                .pcontent(m.getPcontent())
	                .writer(m.getWriter())
	                .regdate(m.getCreatedDate())
	                .updatedate(m.getModifiedDate())
	                .attachList(m.getAttachList().stream()
	                        .map(o -> new AttachFileDTO(o))
	                        .collect(Collectors.toList()))
	                .build());
	        return boardDtoList;
	    }
		
	public PhotoBoard toEntity() {
	    return PhotoBoard.builder()
	    		.id(id)
	            .ptitle(ptitle)
	            .pcontent(pcontent)
	            .writer(writer)
	            .attachList(attachList.stream()
                        .map(AttachFileDTO::toEntity)
                        .collect(Collectors.toList()))
	            .build();
	}
}