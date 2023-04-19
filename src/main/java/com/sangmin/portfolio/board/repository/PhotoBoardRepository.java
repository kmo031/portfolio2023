package com.sangmin.portfolio.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sangmin.portfolio.board.entity.PhotoBoard;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard, Long> {

	Page<PhotoBoard> findAll(Pageable pageable);

}
