package com.sangmin.portfolio.board.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
//    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getCommentsList(HttpServletRequest request,Model model, Pageable pageable) throws Exception {
    
	Long uniqueId =1L;
//	Page<Comment> comment = commentService.findByCommentList(pageable);
	SessionUser user = (SessionUser) httpSession.getAttribute("user");

//    model.addAttribute("comment", comment);
    
    if(user != null){
        model.addAttribute("userName", user.getName());
    }
    
    return "comment/commentsList";
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String getCommentId(HttpServletRequest request,Model model, CommentDto requestDto) throws Exception {
    Long uniqueId = requestDto.getUniqueId();
    Optional<Comment> comment = commentService.getId(uniqueId);
    if(comment.isPresent()) {
    	model.addAttribute("comment", comment.get());
    }
    return "comment/commentView";
    }
    
    
    
    @RequestMapping(value = "/write-bbs", method = RequestMethod.GET)
    public String writeBbs(HttpServletRequest request,Model model, CommentDto requestDto) throws Exception {
    
    SessionUser user = (SessionUser) httpSession.getAttribute("user");
    if(user != null){
        requestDto.setWriter(user.getName());
    }
    model.addAttribute("requestDto", requestDto);
    
 
    
    return "comment/writeBbs";
    }
    
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    @ResponseBody  
    public String writeBbs(HttpServletRequest request,CommentDto requestDto) throws Exception {
    
    commentService.save(requestDto);
    
    return "작성완료";
    }
    
    @RequestMapping(value = "/modify-bbs", method = RequestMethod.GET)
    public String modifyBbs(HttpServletRequest request,Model model, CommentDto requestDto) throws Exception {
	  Long uniqueId = requestDto.getUniqueId();
	    Optional<Comment> comment = commentService.getId(uniqueId);
	    if(comment.isPresent()) {
	    	model.addAttribute("requestDto", comment.get());
	    }
	    
	    
//    model.addAttribute("requestDto", requestDto);
    
    return "comment/modifyBbs";
    }
    
    @RequestMapping(value = "/modify-bbs", method = RequestMethod.POST)
    public String modifyBbs(HttpServletRequest request, CommentDto requestDto) throws Exception {

    	commentService.save(requestDto);
    return "redirect:/comments/list";
    }
    
    
    @RequestMapping(value = "/delete-bbs", method = RequestMethod.POST)
    public String deleteByIdBbs(HttpServletRequest request,Model model, CommentDto requestDto) throws Exception {
    Long uniqueId = requestDto.getUniqueId();
    commentService.deletById(uniqueId);
  
    return "redirect:/comments/list";
    }
//    @RequestMapping(value = "/comment", method = RequestMethod.PATCH)
//    public Comment updateComment(HttpServletRequest request) throws Exception {
//    Comment comment = commentService.getComment(request);	
//    return comment;
//    }
//    
//    @RequestMapping(value = "/comment", method = RequestMethod.DELETE)
//    public Comment deleteComment(HttpServletRequest request) throws Exception {
//    Comment comment = commentService.getComment(request);	
//    return comment;
//    }
//    
//    @RequestMapping(value = "/comment", method = RequestMethod.POST)
//    public Comment insertComment(HttpServletRequest request) throws Exception {
//    Comment comment = commentService.getComment(request);	
//    return comment;
//    }
}
