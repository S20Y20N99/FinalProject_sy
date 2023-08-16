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
	// 리뷰 등록 승인
	public int rvreportaccess(int rep_apply_num, String admin_num);
	// 가게 등록 처리
	public int inprocess(@Param("in_apply_num")int in_apply_num, @Param("admin_num")int admin_num);
}
