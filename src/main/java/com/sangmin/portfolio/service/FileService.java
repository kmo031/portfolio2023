package com.sangmin.portfolio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sangmin.portfolio.dto.AttachFileDTO;
import com.sangmin.portfolio.model.File;
import com.sangmin.portfolio.repository.FileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

	
	private final FileRepository fileRepository;
	
	
	public List<AttachFileDTO> findByBoardId(Long Unique_id) {
		List<File> fileEntity =fileRepository.findByBoardId(Unique_id);
		 List<AttachFileDTO>collect  = fileEntity.stream()
		            .map(m-> new AttachFileDTO(m))
		            .collect(Collectors.toList());
		return collect;
	}
}
