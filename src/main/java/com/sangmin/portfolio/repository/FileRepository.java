package com.sangmin.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sangmin.portfolio.model.File;

public interface FileRepository extends JpaRepository<File, Long> {
	
	@Query("select f from File f where f.photoBoard.id = :boardId")
	public List<File> findByBoardId(@Param(value = "boardId")Long boardId);

  
}