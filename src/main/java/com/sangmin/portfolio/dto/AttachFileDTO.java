package com.sangmin.portfolio.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.sangmin.portfolio.model.File;
import com.sangmin.portfolio.photoBoard.entity.PhotoBoard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachFileDTO {

	private Long id;
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private boolean mainPicture;
	private boolean image;
	
	private Long pno;
	private PhotoBoard photoBoard;
	
	private List<File> file;
	
	public File toEntity() {
	    return File.builder()
	    		.uuid(uuid)
	            .uploadPath(uploadPath)
	            .fileName(fileName)
	            .fileType(fileType)
	            .mainPicture(mainPicture)
	            .photoBoard(photoBoard)
	            .build();
	}
	

	public AttachFileDTO(File file) {
		this.uuid = file.getUuid();
		this.uploadPath = file.getUploadPath();
		this.fileName = file.getFileName();
		this.fileType = file.isFileType();
		this.mainPicture= file.isMainPicture();
//		this.photoBoard = file.getPhotoBoard();
	}

	public void setPhotoBoard(PhotoBoard photoBoard) {
		this.photoBoard =photoBoard;
		if(!photoBoard.getAttachList().contains(this)) {
			photoBoard.getAttachList().add(this.toEntity());
		}
	}
	


		
}