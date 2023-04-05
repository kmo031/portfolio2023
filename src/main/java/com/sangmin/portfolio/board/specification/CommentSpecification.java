package com.sangmin.portfolio.board.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sangmin.portfolio.board.dto.CommentDto;
import com.sangmin.portfolio.board.entity.Comment;

public class CommentSpecification {
	
	 public static Specification<Comment> searchComment(Map<String, Object> filter) {
		    return (root, query, criteriaBuilder) -> {
		    	 // Predicate 는 criteria 에서 제공하는 클래스이다. 이상한 클래스 임포트 받지 말자.
		        List<Predicate> predicates = new ArrayList();
		      
		        filter.forEach((key, value) -> {
		          String likeValue = "%" + value + "%";

		          switch (key) {
		            case "idx":
		              predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
		              break;

		          }
		        });

		        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

		    };
	 }
	 
	 @SuppressWarnings("unused")
	public static Specification<Comment> getSingleSpec(CommentDto commentDTO){
		    return new Specification<Comment>() {
		        private static final long serialVersionUID = 1L;
			
		        @Override
		        public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		            Predicate p = cb.conjunction();
		            if(commentDTO.getIdx() >0) p = cb.and(cb.equal(root.get("idx"), +commentDTO.getIdx())); /*TEXT BOX 조회*/
//		            if(map.get("name") != null) p = cb.and(cb.like(root.get("name"), "%"+(String) map.get("name")+"%"));
//		            if(map.get("useYn") != null) p = cb.and(cb.equal(root.get("useYn"), (String) map.get("useYn"))); /*COMBO BOX 조회*/
//		            if(map.get("regDtm") != null) p = cb.and(
//		                cb.between(root.get("regDtm"), (String) map.get("regDtmSt"), (String) map.get("regDtmEd"))); /*기간조회*/
		            //if(map.get("regDtm") != null) p = cb.and(cb.equal(root.get("regDtm"), (String) map.get("regDtm"))); /*해당 날짜만 조회*/
		            return p;
		        }
			};
		}
		
		@SuppressWarnings({"unused", "unchecked"})
		public static Specification<Comment> getMultiSpec(Map<String, Object> map){
		    return new Specification<Comment>() {
		        private static final long serialVersionUID = 1L;
				
		        @Override
		        public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		            Predicate p = cb.conjunction();
		            List<Predicate> pList = new ArrayList<Predicate>();
		            /*TEXT BOX 조회*/
//		            if(map.get("id") != null) {
//		                List<String> values = (ArrayList<String>) map.get("id");
//		                p = cb.and(p, cb.like(root.get("id"), "%"+values.get(0)+"%"));
//		                for(int i=1, n=values.size(); i<n; i++) {
//		                    p = cb.or(p, cb.like(root.get("id"), "%"+values.get(i)+"%"));
//		                }
//		            }
//		            if(map.get("name") != null) {
//		                List<String> values = (ArrayList<String>) map.get("name");
//		                p = cb.and(p, cb.like(root.get("name"), "%"+values.get(0)+"%"));
//		                for(int i=1, n=values.size(); i<n; i++) {
//		                    p = cb.or(p, cb.like(root.get("name"), "%"+values.get(i)+"%"));
//		                }
//		            }
//		            /*COMBO BOX 조회*/
//		            if(map.get("useYn") != null) {
//		                List<String> values = (ArrayList<String>) map.get("useYn");
//		                List<String> list = new ArrayList<String>();
//		                for(String value : values) {
//		                    list.add(value);
//		                }
//		                p = cb.and(root.get("useYn").in(list));
//		            }
//		            /*날짜타입 조회*/
//		            if(map.get("regDtm") != null) {
//		                if(map.get("range") != null && (boolean) map.get("range")) {
//		                    int[][] range = null;
//		                    String format = "", func = "DATE_FORMAT"; /*ORACLE : TO_CHAR*/
//		                    /*기간 조회의 경우*/
//		                    List<String> values = (ArrayList<String>) map.get("regDtm");
//		                    String dtm = values.get(0);
//		                    if(dtm.length() == 16) { /*type date의 경우*/
//		                        range = new int[][]{{0,8},{8,16}};
//		                        format = "%Y%m%d";
//		                    }else if(dtm.length() == 24) { /*type datetime의 경우*/
//		                        range = new int[][]{{0,12},{12,24}};
//		                        format = "%Y%m%d%H%i%s";
//		                    }
//		                    p = cb.and(p, cb.between(cb.function(func, String.class, root.get("regDtm"), cb.literal(format))
//		                        , dtm.substring(range[0][0], range[0][1])+((dtm.length()==24)?"00":"")
//		                        , dtm.substring(range[1][0], range[1][1])+((dtm.length()==24)?"59":"")));
//		                    for(int i=1; i<values.length; i++) {
//		                        p = cb.or(p, cb.between(cb.function(func, String.class, root.get("regDtm"), cb.literal(format))
//		                            , values.get(i).substring(range[0][0], range[0][1])+((dtm.length()==24)?"00":"")
//		                            , values.get(i).substring(range[1][0], range[1][1])+((dtm.length()==24)?"59":"")));
//		                    }
//		                }else {
//		                    /*해당 날짜들로만 검색할 경우*/
//		                    List<String> values = (ArrayList<String>) map.get("regDtm");
//		                    List<String> list = new ArrayList<String>();
//		                    for(String value : values) {
//		                        list.add(value);
//		                    }
//		                    p = cb.and(cb.function("DATE_FORMAT", String.class, root.get("regDtm"), cb.literal("%Y%m%d")).in(list)); /*mariadb*/
//		                    //p = cb.and(cb.function("TO_CHAR", String.class, root.get("regDtm"), cb.literal("yyyyMMdd")).in(list)); /*oracle*/
//		                }
//		            }
		            return p;
		        }
		    };
		}

}
