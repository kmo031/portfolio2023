package com.sangmin.portfolio.photoBoard.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sangmin.portfolio.dto.AttachFileDTO;
import com.sangmin.portfolio.model.SessionUser;
import com.sangmin.portfolio.photoBoard.dto.PhotoBoardDto;
import com.sangmin.portfolio.photoBoard.entity.PhotoBoard;
import com.sangmin.portfolio.photoBoard.service.PhotoBoardService;
import com.sangmin.portfolio.service.FileService;

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
	private FileService fileService;
	
	@Value("${part4.upload.path}")
	private String filePath;
	
	private final HttpSession httpSession;
	
	private static final Integer POSTS_PER_PAGE = 10;
	private static final Integer PAGES_PER_BLOCK = 5;


	
	@GetMapping("/list")
	public String list( Model model) {
		return "photoBoard/pBoardList";
	}
	
	@GetMapping("/detail")
	public String detail( Model model, @RequestParam("pno") Long pno) throws Exception {
		
		PhotoBoardDto pBoard = photoBoardService.getId(pno);
		 model.addAttribute("pboard", pBoard);
		return "photoBoard/pDetail";
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

		File file = new File(filePath+ fileName);

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
    
    @SuppressWarnings("deprecation")
	@GetMapping(value ="/getProductDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> getProductDetail(Long pno ){
		
		return new ResponseEntity<>(fileService.findByBoardId(pno),HttpStatus.OK);
	}
    


}
