package com.sangmin.portfolio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sangmin.portfolio.board.service.PhotoBoardService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PhotoViewTest {
	
	   @Autowired
	    PhotoBoardService photoBoardSercive;
	   
	   
	   @Test
	    public void 게시글저장_불러오기(){
		   Pageable pageable = PageRequest.of(0, 5); ;
		   photoBoardSercive.findByBoardList(pageable);
	   
	   }

}
