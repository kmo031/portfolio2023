package com.sangmin.portfolio.board.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sangmin.portfolio.board.dto.BoardDto;
import com.sangmin.portfolio.board.entity.Board;
import com.sangmin.portfolio.board.service.BoardService;
import com.sangmin.portfolio.board.service.CommentService;
import com.sangmin.portfolio.config.CustomUserDetails;
import com.sangmin.portfolio.model.SessionUser;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	private final HttpSession httpSession;
	
	private static final Integer POSTS_PER_PAGE = 10;
	private static final Integer PAGES_PER_BLOCK = 5;

//    @RequestMapping(value = "/board", method = RequestMethod.GET)
//    public Board getBoard(HttpServletRequest request) throws Exception {
//    Board board = boardService.getBoard(request);	
//    return board;
//    }
//    @ResponseBody
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String getBoardsList(HttpServletRequest request,Model model, Pageable pageable,@AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception {
    
	Long uniqueId =1L;
	Page<Board> board = boardService.findByBoardList(pageable);
//	SessionUser user = (SessionUser) httpSession.getAttribute("user");

//	PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

    model.addAttribute("board", board);
    
    if(customUserDetails.getName() != null){
        model.addAttribute("userName", customUserDetails.getName());
    }
    
    return "board/boardsList";
    }
    
    @RequestMapping( method = RequestMethod.GET)
    public String getBoardId(HttpServletRequest request,Model model, BoardDto requestDto, Pageable pageable,@AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception {
    Long uniqueId = requestDto.getUniqueId();
    Optional<Board> board = boardService.getId(uniqueId);
    
    SessionUser user = (SessionUser) httpSession.getAttribute("user");

    if(customUserDetails != null){
        model.addAttribute("userName",  customUserDetails.getName());
    }
    if(board.isPresent()) {
    	model.addAttribute("board", board.get());
    }

    return "board/boardView";
    }
    
    @RequestMapping( method = RequestMethod.PUT)
    public String modifyBbs(HttpServletRequest request, BoardDto requestDto) throws Exception {
    	boardService.save(requestDto);
    return "redirect:/boards/list";
    }
    
    
    @RequestMapping( method = RequestMethod.DELETE)
    public String deleteByIdBbs(HttpServletRequest request,Model model, BoardDto requestDto) throws Exception {
    Long uniqueId = requestDto.getUniqueId();
    boardService.deletById(uniqueId);
  
    return "redirect:/boards/list";
    }
    
    
    
    @RequestMapping(value = "/write-bbs", method = RequestMethod.GET)
    public String writeBbs(HttpServletRequest request,Model model, BoardDto requestDto,@AuthenticationPrincipal CustomUserDetails customUserDetails) throws Exception {
    
    SessionUser user = (SessionUser) httpSession.getAttribute("user");
    if(customUserDetails != null){
        requestDto.setWriter(customUserDetails.getName());
    }
    model.addAttribute("requestDto", requestDto);
    
 
    
    return "board/writeBbs";
    }
    
    @RequestMapping(value = "/write-bbs", method = RequestMethod.POST)
    public String writeBbs(HttpServletRequest request,BoardDto requestDto) throws Exception {
    
    boardService.save(requestDto);
    
    return "redirect:/boards/list";
    }
    
    @RequestMapping(value = "/modify-bbs", method = RequestMethod.GET)
    public String modifyBbs(HttpServletRequest request,Model model, BoardDto requestDto) throws Exception {
	  Long uniqueId = requestDto.getUniqueId();
	    Optional<Board> board = boardService.getId(uniqueId);
	    if(board.isPresent()) {
	    	model.addAttribute("requestDto", board.get());
	    }
	    
	    
//    model.addAttribute("requestDto", requestDto);
    
    return "board/modifyBbs";
    }
    
  

}
