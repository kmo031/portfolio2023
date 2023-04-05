package com.sangmin.portfolio.board.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sangmin.portfolio.board.dto.CommentDto;
import com.sangmin.portfolio.board.entity.Comment;
import com.sangmin.portfolio.board.service.CommentService;
import com.sangmin.portfolio.model.SessionUser;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/boards/comment")
@RequiredArgsConstructor
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	private final HttpSession httpSession;
	
	private static final Integer POSTS_PER_PAGE = 10;
	private static final Integer PAGES_PER_BLOCK = 5;

//    @RequestMapping(value = "/comment", method = RequestMethod.GET)
//    public Comment getComment(HttpServletRequest request) throws Exception {
//    Comment comment = commentService.getComment(request);	
//    return comment;
//    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getCommentsList(HttpServletRequest request,Model model, Pageable pageable,CommentDto requestDto) throws Exception {
    
    	 Page<Comment> comment = commentService.findComments(requestDto,pageable);
    	    
	    SessionUser user = (SessionUser) httpSession.getAttribute("user");

	 
	    
	    if(user != null){
	        model.addAttribute("userName", user.getName());
	    }
	    
	    if(comment !=null) {
	    	model.addAttribute("commentList", comment);
	    }

	    return "/board/boardView :: #commentTable"; // template html 파일 이름 + '::' + fragment의 id
    }
    
   
    
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writeBbs(HttpServletRequest request,CommentDto requestDto, Model model, Pageable pageable) throws Exception {
    
    	
    SessionUser user = (SessionUser) httpSession.getAttribute("user");
    commentService.save(requestDto);
    
    Page<Comment> comment = commentService.findComments(requestDto,pageable);
   

    if(user != null){
        model.addAttribute("userName", user.getName());
    }
    if(comment !=null) {
    	model.addAttribute("commentList", comment);
    }
    
    return "/board/boardView :: #commentTable";
    }
    

    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteByIdBbs(HttpServletRequest request,Model model, CommentDto requestDto, Pageable pageable) throws Exception {
    Long uniqueId = requestDto.getUniqueId();
    SessionUser user = (SessionUser) httpSession.getAttribute("user");
    commentService.deletById(uniqueId);
    
    Page<Comment> comment = commentService.findComments(requestDto,pageable);
    

    if(user != null){
        model.addAttribute("userName", user.getName());
    }
    if(comment !=null) {
    	model.addAttribute("commentList", comment);
    }
    
  
    return "/board/boardView :: #commentTable";
    }

}
