package com.yameokja.mc;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.glassfish.gmbal.ParameterNames;

public interface IStoreMainDAO
{
	public ArrayList<StoreDTO> searchStoreInfo(String user_num);
	public Integer searchRepStore(String user_num);
	public ArrayList<HashMap<String, String>> star_transition(int st_num);
	public ArrayList<HashMap<String, String>> rv_key_sum(int st_num);
	public ArrayList<ReviewDTO> rv_list(int st_num);

	//public int reviewReply(@RequestParam("rv_num") int rv_num, @RequestParam("reply_content") String reply_content);
	// 리뷰댓글(insert)
	public int reviewReply(@Param("rv_num") int rv_num,@Param("reply_content") String reply_content);
	// 댓글내용
	public ArrayList<ReviewDTO> reply_content(int rv_num); 
	
	
	public ArrayList<FoodLabelDTO> foodLabel();
	public ArrayList<PayLabelDTO> payLabel();
	public ArrayList<StoreOpencloseDTO> weekDayLabel();
	public ArrayList<weekWeekendDayLabel> weekWeekendDayLabel();
	public ArrayList<ChboxDTO> chBoxLabel();
	
	// 업데이트 하기 위한 삭제 과정
	public int stOCdelete(int st_num);
	public int bOCdelete(int st_num);
	public int holidaydelete(int st_num);
	public int paysdelete(int st_num);
	public int chBoxdelete(int st_num);
	public int stKeysdelete(int st_num);
	public int foodCatdelete(int st_num);
	public int menudelete(int st_num);
	public int stsearchKeydelete(int st_num);
	
	// 가게 첫 로그인
	public int checkfirstlogin(int st_num);
	public int findfirstlogin(int st_num);
	public int deletelognum(int log_num);
	
	
	// 업데이트 후 삽입
	public int stOCinsert(@Param("st_num")int st_num, @Param("day_num")int day_num
			, @Param("start_time")String start_time, @Param("end_time")String end_time);
	public int bOCinsert(@Param("st_num")int st_num, @Param("week_weekend_num")int week_weekend_num
			, @Param("start_breaktime")String start_time, @Param("end_breaktime")String end_breaktime);
	public int holidayinsert(@Param("day_num")int day_num, @Param("st_num")int st_num);
	public int paysinsert(@Param("pay_num")int pay_num, @Param("st_num")int st_num);
	public int chBoxinsert(@Param("st_num")int st_num, @Param("chbox_num")int chbox_num, @Param("yn_num")int yn_num);
	public int chBoxselect(@Param("st_num")int st_num, @Param("chbox_num")int chbox_num);
	public int chBoxupdate(@Param("yn_num")int yn_num, @Param("st_num")int st_num, @Param("chbox_num")int chbox_num);
	public int stKeysinsert(@Param("st_num")int st_num, @Param("st_key_num")int st_key_num);
	public int foodCatinsert(@Param("food_num")int food_num, @Param("st_num")int st_num);
	public int menuinsert(@Param("st_num")int st_num, @Param("menu_name")String menu_name
			, @Param("price")int price, @Param("image_link")String image_link);
	/*
	 * public int chBoxdeleteone(@Param("st_num")int st_num, @Param("chbox_num")int
	 * chbox_num);
	 */
	
	public ArrayList<Integer> rvReplyNumList(int st_num);
	
	// 리뷰 답글 가져오기
	public ArrayList<StoreRvReplyDTO> rvReply(int st_num);

	public int searchKeyinsert(@Param("st_num")int st_num, @Param("search_name")String search_name);
	public int stsearchKeyUpdate(@Param("st_num")int st_num, @Param("search_name")String search_name);
	public Integer searchKeyselect(@Param("st_num")int st_num, @Param("search_name")String search_name);
	public int stsearchKeyinsert(@Param("st_num")int st_num, @Param("search_num")int search_num);
	public int stsearchKeyselect(@Param("st_num")int st_num, @Param("search_num")int search_num);
	
	// 이의제기요청내역
	public StAppealRequestDTO StAppealRequest(int st_num);
	// 페널티 회수요청 내역
	public StAppealRequestDTO StPenaltyre(int st_num);

}
