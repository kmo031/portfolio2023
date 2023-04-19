package com.sangmin.portfolio;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sangmin.portfolio.board.entity.PhotoBoard;
import com.sangmin.portfolio.board.repository.PhotoBoardRepository;
import com.sangmin.portfolio.model.File;
import com.sangmin.portfolio.repository.FileRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FileUploadTest {

    @Autowired
    PhotoBoardRepository photoBoardRepository;
    FileRepository fileRepository;

    @After(value = "")
    public void cleanup(){
    	photoBoardRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        
        File file = new File();

        List<File> attachList = new ArrayList<>();
        attachList.add(file);
        
        PhotoBoard photoBoard =  PhotoBoard.builder()
        	       .pcontent(content)
        	       .ptitle(title)
        	       .attachList(attachList)
        	        .build();
       
        photoBoardRepository.save(PhotoBoard.builder()
       .pcontent(content)
       .ptitle(title)
       .attachList(attachList)
        .build());

        //when
        List<File> postsList = fileRepository.findAll();
//
//        //then
////        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle()).isEqualTo(title);
//        assertThat(posts.getContent()).isEqualTo(content);
    }
}