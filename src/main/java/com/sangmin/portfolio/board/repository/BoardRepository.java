package com.sangmin.portfolio.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sangmin.portfolio.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	Page<Board> findAll(Pageable pageable);

}
