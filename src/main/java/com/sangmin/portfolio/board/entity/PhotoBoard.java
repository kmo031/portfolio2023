package com.sangmin.portfolio.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sangmin.portfolio.model.BaseTimeEntity;
import com.sangmin.portfolio.model.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "tPhotoBoard", schema = "board")
@SequenceGenerator(
        name="PHOTO_BOARD_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="PHOTO_BOARD_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PhotoBoard extends BaseTimeEntity{

	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="PHOTO_BOARD_SEQ_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정        
            )
	private Long id;
	private Long pno;
	private String ptitle;
	private String pcontent;
	private String writer;

//	@OneToMany(mappedBy = "file", cascade = CascadeType.ALL)
//	private List<AttachFileDTO> attachList;
	
	@OneToMany(mappedBy = "photoBoard",fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<File> attachList  = new ArrayList<>();


	@Builder
	public PhotoBoard(Long id, String ptitle, String pcontent, String writer, List<File> attachList) {
	    this.pcontent = pcontent;
	    this.ptitle = ptitle;
	    this.id =id;
	    this.writer = writer;
	    this.attachList =attachList;
	}

	
	
	
//	@ManyToMany
//    @JoinTable(name = "file",
//            joinColumns = @JoinColumn(name = "pno"),
//            inverseJoinColumns = @JoinColumn(name = "uniqueid")
//    )
//    public  List<AttachFileDTO> getAttachList() {
//        return attachList;
//    }


	
}
