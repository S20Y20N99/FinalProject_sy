package com.yameokja.mc;

import org.apache.ibatis.annotations.Param;

public interface IAdminFindDAO
{
	// rep_apply_num으로 요청내용 찾기
	public RvApplyViewDTO rvSearch(int rep_apply_num);
	// req_apply_num으로 요청내용 찾기
	public ReqApplyViewDTO accuSearch(int req_apply_num);
	// obj_apply_num으로 요청내용 찾기
	public ObjApplyViewDTO objSearch(int obj_apply_num);
	// 패널티회수 요청한 가게로 요청내용 찾기
	public RevoApplyViewDTO revoSearch(int revo_apply_num);
	// 가게 등폐업 요청한 가게로 등록 요청내용 찾기
	public StoreregiDTO inSearch(int in_apply_num);
	// 가게 등폐업 요청한 가게로 폐업 요청내용 찾기(사업장번호, 폐업사유)
	public StoreregiDTO outSearch(int out_apply_num);
	// 가게 
	public int rvupdate(int rep_apply_num);
	// 가게 정보 오류 수정 요청 관리자 처리내역 업데이트
	public int stinfoupdate(int req_apply_num);
	// 이의 제기 관리자 처리내역 업데이트
	public int objupdate(int obj_apply_num);
	// 패널티 회수 처리내역 업데이트
	public int revoupdate(int revo_apply_num);
	// 가게등록 처리내역 업데이트
	public int inapplyupdate(int in_apply_num);
	// 리뷰 신고 처리 및 승인
	public int rvreportAccess(@Param("rep_apply_num") int rep_apply_num, @Param("admin_num") int admin_num);
	// rep_process_num 찾기
	public int searchRepProNum(@Param("rep_apply_num") int rep_apply_num, @Param("admin_num") int admin_num);
	// 리뷰 신고 중재
	public int rvreportHalf(@Param("mid_rs")String mid_rs, @Param("rep_process_num")int rep_process_num);
	// 리뷰 신고 반려
	public int rvreportRej(@Param("rej_rs")String rej_rs, @Param("rep_process_num")int rep_process_num);
	// 리뷰 중재/반려 사유
	public MidRejRsDTO rvreportMidRejRs(@Param("rep_process_num") int rep_process_num);
	// 가게정보오류수정요청 처리 및 승인
	public int reqProcess(@Param("req_apply_num") int req_apply_num, @Param("admin_num") int admin_num);
	// REQ_PROCESS_NUM 찾기
	public int getReqProNum(@Param("req_apply_num") int req_apply_num, @Param("admin_num") int admin_num);
	// 가게정보오류수정요청 승인시 패널티 테이블 insert
	public int setPenalty(@Param("req_process_num") int req_process_num, @Param("pen_type_num") int pen_type_num);
	// 가게정보오류수정요청 승인시 1번 패널티 수 카운트
	public int countPenalty(@Param("req_process_num") int req_process_num);
	// 가게정보오류수정요청 반려
	public int reqRej(@Param("req_process_num") int req_process_num, @Param("rej_rs") String rej_rs);
	//이의제기 승인
	public int objProcess(@Param("obj_apply_num")int obj_apply_num, @Param("admin_num")int admin_num);
	//이의제기 승인 시 패널티 삭제
	public int delPenalty(@Param("obj_process_num")int obj_process_num);
	//OBJ_PROCESS_NUM 찾기
	public int searchOpNum(int obj_apply_num);
	//이의제기 반려
	public int objRej(@Param("rej_rs")String rej_rs, @Param("obj_process_num")int obj_process_num);
	//처리 결과 변경
	public int reverseRs(@Param("reverse_rs")String reverse_rs, @Param("obj_process_num")int obj_processs_num);
	//패널티회수요청 승인
	public int revoProcess(@Param("revo_apply_num")int revo_apply_num, @Param("admin_num")int admin_num);
	//REVO_PROCESS_NUM 찾기
	public int searchRpNum(int revo_apply_num);
	//패널티회수 요청 반려
	public int revoRej(@Param("rej_rs")String rej_rs, @Param("revo_process_num")int revo_process_num);
	
	
	
	// 가게 등록 처리
	public int inprocess(@Param("in_apply_num")int in_apply_num, @Param("admin_num")int admin_num);
	public int getinprocessnum(int in_apply_num);
	public int stlistinsert(@Param("in_process_num")int in_process_num);
	
	public int inrej(@Param("rej_rs")String rej_rs, @Param("in_process_num")int in_process_num);
	
	public int outapplyupdate(int out_apply_num);
	
	public int outprocess(@Param("out_apply_num")int out_apply_num, @Param("admin_num")int admin_num);
	public int getoutprocessnum(int out_apply_num);
	
	public int outrej(@Param("rej_rs")String rej_rs, @Param("out_process_num")int out_process_num);
}
