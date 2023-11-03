package com.yameokja.mc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IcompareViewDAO 
{	
	public ArrayList<StoreDetailDTO> store(List<Integer> stList);	     						// 가게이름, 좋아요 수, 별점 평균, 리뷰 수, 전화번호, 주소...
	public ArrayList<StoreOpencloseDTO> openClose(List<Integer> stList);  						// 영업시간 + 휴무
	public ArrayList<StoreBreaktimeDTO> comBreakTime(int st_num);								// 브레이크 타임
	public ArrayList<StoreCheckDTO> comStcheck(int st_num);										// 가게 체크박스
	public StoreMenuDTO menuList(int st_num);   												// 가게 메뉴, 가격, 이미지링크
	public ArrayList<StoreMenuDTO> menuAvg(List<Integer> stList);								// 메뉴 평균 가격
	public Integer comparedIsNull(@Param("user_num") String user_num, @Param("st_num") int st_num); // 비교했던 가게인지 확인
	public Integer comparedUpdate(@Param("user_num") String user_num, @Param("st_num") int st_num); // 비교한 적 있으면 update
	public int comparedInsert(@Param("user_num") String user_num, @Param("st_num") int st_num);		// 비교한 적 없으면 insert
}
