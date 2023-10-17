/* ======================================
 	IstDetailDAO_userView.java
 	- storeDetail-userView.jsp 
 ========================================*/

package com.yameokja.mc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IstDetailDAO_userView
{
	public Integer stIsOut(int st_num);										// 폐업 여부
	public ArrayList<StoreOpencloseDTO> openClose(int st_num);  			// 영업시간 + 휴무
	public ArrayList<StoreBreaktimeDTO> breakTime(int st_num);	 			// 브레이크 타임
	public ArrayList<StoreDetailDTO> store(int st_num);	     				// 가게이름, 좋아요 수, 별점 평균, 리뷰 수, 전화번호, 주소...
	public int clikeNum(int st_num);							 			// 좋아요 수
	public ArrayList<String> stPay(int st_num);								// 가게 결제수단
	public ArrayList<StoreCheckDTO> stcheck(int st_num);					// 가게 체크박스
	public ArrayList<StoreMenuDTO> menuLists(int st_num);  					// 가게 메뉴, 가격, 이미지링크
	public ArrayList<StoreReviewDTO> reviews(int st_num);  	    			// 리뷰 + 추천/비추천
	public ArrayList<StoreKeyDTO> stKeys(int st_num);  						// 가게 키워드
	public ArrayList<Integer> stKeysStr(int st_num);  						// 가게 키워드
	public ArrayList<StoreReviewKeyDTO> reviewKeys(int st_num);				// 해당 가게의 리뷰 키워드
	public ArrayList<StoreRvPhotoDTO> rvPhoto(int st_num);					// 리뷰 사진
	public ArrayList<Integer> userRnList(@Param("st_num") int st_num, @Param("user_num") String user_num);	// 특정 유저가 가게의 리뷰에 누른 추천/비추천 내역
	public ArrayList<Integer> userNrnList(@Param("st_num") int st_num, @Param("user_num") String user_num);	// 특정 유저가 가게의 리뷰에 누른 추천/비추천 내역

	// 리뷰 신고 범례 리스트
	public ArrayList<reviewRepDTO> reviewRepLabel();
	// 리뷰 신고
	public int reviewRepInsert(@Param("rv_num") int rv_num, @Param("user_num") String user_num, @Param("rep_rs_num") int rep_rs_num);	
	// 리뷰 식별
	public String searchRec(@Param("rv_num") int rv_num, @Param("user_num") String user_num);
	// 리뷰 추천/비추천 등록
	public int reviewRecInsert(@Param("rv_num") int rv_num, @Param("user_num") String user_num, @Param("rec_nonrec_number") int rec_nonrec_number);
	// 리뷰 추천/비추천 삭제
	public int reviewRecRemove(@Param("rv_num") int rv_num, @Param("user_num") String user_num, @Param("rec_nonrec_number") int rec_nonrec_number);
	// 리뷰 추천/비추천 수정
	public int reviewRecModify(@Param("rv_num") int rv_num, @Param("user_num") String user_num, @Param("rec_nonrec_number") int rec_nonrec_number);
	// 리뷰 추천/비추천 카운트
	public int reviewRecCount(@Param("rv_num") int rv_num,@Param("rec_nonrec_number") int rec_nonrec_number);

	// st_chbox_num 찾기
	public Integer searchStChboxnum(@Param("st_num") int st_num, @Param("chbox_num") int chbox_num);
	// 가게정보수정요청이 된 st_chbox_num인지 확인
	public Integer reqIsNull(@Param("st_chbox_num") int st_chbox_num);
	// 가게정보오류수정요청 INSERT
	public Integer reqApply(@Param("user_num") String user_num, @Param("req_rs") String req_rs, @Param("st_chbox_num") int st_chbox_num);
	// 가게정보수정요청이 반려된 st_chbox_num인지 확인
	public Integer reqRej(@Param("req_apply_num") int req_apply_num);
	// 가게정보수정요청 : 이의제기 내역에서 처리가 완료된 사항인지 판단
	public Integer reqObj(@Param("req_apply_num") int req_apply_num);
	// 가게정보수정요청 : 패널티 부여 후 수정했다는 요청의 처리가 완료된 사항인지 판단
	public Integer reqRevo(@Param("req_apply_num") int req_apply_num);
	// 가게정보수정요청 : 패널티 부여 후 3일이 지났는지 판단
	public Integer reqPen(@Param("req_apply_num") int req_apply_num);
	
	// 리뷰 키워드 범례 리스트
	public ArrayList<StoreReviewKeyDTO> reviewKeywords();
	
	// 리뷰 입력
	public int reviewInsert(@Param("user_num") String user_num, @Param("st_num") int st_num, @Param("rv_content") String rv_content, @Param("star_score") int star_score);
	
	// 리뷰 입력 시 포인트 추가
	public int addPoint(@Param("user_num") String user_num, @Param("point_label_num") int point_label_num);
	
	// 리뷰 키워드 존재여부
	public Integer rKeywordSearch(@Param("st_num") int st_num, @Param("rv_key_num") int rv_key_num);
	
	// 리뷰 키워드 count update
	public int rKeywordUpdate(@Param("st_num")int st_num, @Param("rv_key_num")int rv_key_num);
	
	// 리뷰 키워드 입력
	public int rKeywordInsert(@Param("st_num")int st_num, @Param("rv_key_num")int rv_key_num);
	
	// 가게 검색 키워드 존재여부
	public Integer skeywordSearch(@Param("st_num")int st_num, @Param("search_name")String search_name);
	
	// 가게 검색 키워드 count update
	public int skeywordUpdate(@Param("st_num")int st_num, @Param("search_name")String search_name);
	
	// 가게 검색 키워드 insert
	public int sKeywordInsert(@Param("st_num")int st_num, @Param("search_name")String search_name);
	
	// rv_num 검색
	public int searchRvNum(@Param("user_num")String user_num, @Param("st_num")int st_num, @Param("rv_content")String rv_content, @Param("star_score")int star_score);
	
	// 리뷰 사진 insert
	public int rvPhotoInsert(@Param("rv_num")int rv_num, @Param("photo_link") String photo_link);
}
