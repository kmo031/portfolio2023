package com.sangmin.portfolio.photoBoard.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sangmin.portfolio.dto.AttachFileDTO;
import com.sangmin.portfolio.photoBoard.dto.PhotoBoardDto;
import com.sangmin.portfolio.photoBoard.entity.PhotoBoard;
import com.sangmin.portfolio.photoBoard.repository.PhotoBoardRepository;
import com.sangmin.portfolio.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PhotoBoardService {
	
	private final PhotoBoardRepository photoBoardRepository;
	private final FileRepository fileRepository;
	
	@Transactional
	public Long save(PhotoBoardDto requestDto) {
		PhotoBoard  photoBoard = photoBoardRepository.save(requestDto.toEntity());
		Long pno = photoBoard.getId();
		for(AttachFileDTO attach :requestDto.getAttachList()) {
//			attach.setPno(pno);
			attach.setPhotoBoard(photoBoard);
			fileRepository.save(attach.toEntity());
		}

	
	    return pno;
	}
	
	public List<PhotoBoard> getList() {
		return photoBoardRepository.findAll();
	}
	
	@Transactional
	public Page<PhotoBoardDto> findByBoardList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10); // <- Sort 추가
//        Page<PhotoBoardDto> boardDtoList = new Page<PhotoBoardDto>(photoBoardRepository.findAll(pageable));
        Page<PhotoBoardDto> boardDtoList = new PhotoBoardDto().toDtoList(photoBoardRepository.findAll(pageable));
		return boardDtoList;
	}
	
	public PhotoBoardDto getId(Long uniqueId) throws Exception {
		Optional<PhotoBoard> photoBoardOptional =photoBoardRepository.findById(uniqueId);
		PhotoBoard photoBoard =null;
		if(photoBoardOptional.isPresent()) {
			photoBoard = photoBoardOptional.get();
		} else {
			throw new Exception();
		}
		PhotoBoardDto photoBoardDto = new PhotoBoardDto(photoBoard);
		
		return photoBoardDto;
	}
	
	public void deletById(Long uniqueId) {
		photoBoardRepository.deleteById(uniqueId);
	}


}
