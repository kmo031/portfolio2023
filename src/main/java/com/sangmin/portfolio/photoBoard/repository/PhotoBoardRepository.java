package com.sangmin.portfolio.photoBoard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sangmin.portfolio.photoBoard.entity.PhotoBoard;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard, Long> {

	Page<PhotoBoard> findAll(Pageable pageable);

}
