<%@page import="com.yameokja.mc.RvApplyViewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	RvApplyViewDTO dto = (RvApplyViewDTO)request.getAttribute("review");
	//System.out.print(dto.getUser_id());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reviewReportForm.jsp</title>
<style type="text/css">

#title
{
	text-align: center;
}
#more
{
	
	/* display: flex; */
    width: 100%;
    justify-content: space-between;
    align-items: center;
}
#body div
{
	float: left;
}
#leftSide, #rightSide
{
	width: 10%;
	height: 500px;
}
#center
{
	width: 80%;
}

textarea {
	text-align: left;
}



</style>

</head>
<body>

<!-- header -->
<c:import url="/WEB-INF/view/header_admin.jsp"></c:import>
<!-- 사이드바 -->
<div id="leftSideBar">
</div>


<!-- body -->
<div id="title" style="padding: 10px;">
<h1>리뷰신고서</h1><br>
</div>

<div id="body">
	<div id="leftSide"></div>
	
	<div id="center">
		<hr>
		<br>
		<div id="more">
			<div style="width: 50%;">
				신고자 : <input type="text" id="reporter" value=<%=dto.getUser_id() %> style="border: 0px; width: 70px;">
			</div>
			<div style="width: 50%; text-align: right;">
				신고일 : <input type="text" id="reportDate" value=<%=dto.getReg_date() %> style="border: 0px; text-align: right; width: 70px;">
			</div>
		</div>
		
		<div id="more">
			<div style="width: 50%;">
				피신고자 : <input type="text" id="reported" value=<%=dto.getAccu_num() %> style="border: 0px; width: 70px;">
			</div>
			<div style="width: 50%; text-align: right;">
				작성일 : <input type="text" id="createDate" value=<%=dto.getWrite_date() %> style="border: 0px; text-align: right; width: 70px;">
			</div>
		</div>
		
		<div style="width: 100%;">
			<textarea id="reviewReport" rows="10px;" readonly="readonly" style="width: 100%;">리뷰내용이 일치하지 않음.</textarea>
		</div>
		
		<div id="more" style="margin-bottom: 2vh;">
			<div style="width: 50%;">
				리뷰신고카테고리명 : ${review.getRep_rs_name()}
			</div>
			<div style="width: 50%; text-align: right;">
				<button id="stInfoBtn">해당가게정보보기</button>
			</div>
		</div>
		
		<div style="width: 100%; text-align: right; margin-bottom: 3vh;">
			<input type="submit" style="width: 120px; border-radius: 20px;" value="승인">
		</div>
		
		<div style="width: 100%;">
			<textarea id="reviewReject" rows="6px;" style="width: 100%; border-radius: 10px;" placeholder="반려사유기재"></textarea>
		</div>
		
		<div style="width: 100%; text-align: right;">
			<button>중재</button>
			<button>반려</button>
		</div>
		
	</div>
	
	<div id="rightSide"></div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/view/footer.jsp"></c:import>

</body>
</html>