package com.sangmin.portfolio.board.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sangmin.portfolio.board.dto.PhotoBoardDto;
import com.sangmin.portfolio.board.service.CommentService;
import com.sangmin.portfolio.board.service.PhotoBoardService;
import com.sangmin.portfolio.model.SessionUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/pboards")
@RequiredArgsConstructor
@Slf4j
public class PhotoBoardController {
	
	@Autowired
	private PhotoBoardService photoBoardService;
	
	@Autowired
	private CommentService commentService;
	
	private final HttpSession httpSession;
	
	private static final Integer POSTS_PER_PAGE = 10;
	private static final Integer PAGES_PER_BLOCK = 5;


	
	@GetMapping("/list")
	public String shop( Model model) {
		log.info("shop진입");
		
//		int total = service.getTotal(cri);
//		
//		model.addAttribute("pageMaker", new PageDTO(cri, total));
		return "/photoBoard/pBoardList";
	}
    @ResponseBody
    @RequestMapping(value = "/pList", method = RequestMethod.GET)
    public ResponseEntity<Page<PhotoBoardDto>> getBoardsList(HttpServletRequest request,Model model, Pageable pageable) throws Exception {
    
	Long uniqueId =1L;
	Page<PhotoBoardDto> pBoard = photoBoardService.findByBoardList(pageable);
	SessionUser user = (SessionUser) httpSession.getAttribute("user");

    model.addAttribute("board", pBoard);
    
    if(user != null){
        model.addAttribute("userName", user.getName());
    }
    
    return  new ResponseEntity<>(photoBoardService.findByBoardList(pageable),HttpStatus.OK);
    }
    
    
    @GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {

		log.info("fileName: " + fileName);

		File file = new File("c:\\upload\\" + fileName);

		log.info("file: " + file);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
//    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String PBoardsCreate(HttpServletRequest request,Model model, Pageable pageable) throws Exception {
    
	Long uniqueId =1L;
//	Page<PhotoBoard> board = photoBoardService.findByBoardList(pageable);
	SessionUser user = (SessionUser) httpSession.getAttribute("user");

//    model.addAttribute("board", board);
    
    if(user != null){
        model.addAttribute("userName", user.getName());
    }
    
    return "photoBoard/pBoardRegister";
    }
    
    @PostMapping("/pRegister")
	public String pRegister(PhotoBoardDto photoBoard, RedirectAttributes rttr) {
		
		log.info("=========================");
		log.info("register" + photoBoard);
		
		if(photoBoard.getAttachList() != null) {
			photoBoard.getAttachList().forEach(attach -> log.info(attach.toString()));		
		}
		photoBoardService.save(photoBoard);
		/* rttr.addFlashAttribute("result",product.getPno()); */
		log.info("==========");
		
		return "redirect:/";
		
	}
    
    


}
