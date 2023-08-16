<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Admin Page</title>

<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main_admin.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/side_bar.css">

<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>

<style type="text/css">
	.continer{
		position: static;
	}
</style>


<script type="text/javascript">
	$(function()
	{
		$(".reviewTd").click(function()
		{
			var reportNum = $(this).attr("id");
			//alert(reportNum);
			var state = $(this).parent().attr('id');
			$(location).attr("href","reviewreportform.action?rep_apply_num="+reportNum+"&state="+state);
			
		});
		
		$(".errTd").click(function()
		{
			var reportNum = $(this).siblings("[id]").attr("id");
			//alert(reportNum);
			var state = $(this).parent().attr('id');
			$(location).attr("href","errreportform.action?req_apply_num="+reportNum+"&state="+state);
			
		});

		$(".objTd").click(function()
		{
			var reportNum = $(this).siblings("[id]").attr("id");
			//alert(reportNum);
			var state = $(this).parent().attr('id');
			$(location).attr("href","objreportform.action?obj_apply_num="+reportNum+"&state="+state);
			
		});

		$(".revoTd").click(function()
		{
			var reportNum = $(this).siblings("[id]").attr("id");
			//alert(reportNum);
			var state = $(this).parent().attr('id');
			$(location).attr("href","penaltyrevokeform.action?revo_apply_num="+reportNum+"&state="+state);
			
		});
		
		$(".inTd").click(function()
		{
			var idNum = $(this).attr("id");
			/* alert(idNum); */
			var state = $(this).parent().attr('id');
			var catValue = $(this).siblings("td:first-child").text();
			//alert(catValue);
			
			if(catValue=="등록")
				$(location).attr("href","inapplyform.action?in_apply_num="+idNum+"&state="+state);
			else
				$(location).attr("href","outapplyform.action?out_apply_num="+idNum+"&state="+state);
		});
		
	});

	
</script>


<body>
<form id="adminForm" method="post">
	<!-- header -->
	<header><c:import url="header_admin.jsp"></c:import></header>
		
		
		<div id="mainDiv">
			
			<!-- 접수내역관리 -->
			<div id="receive">
				<h1>접수내역 관리</h1>
				
				<!-- 리뷰신고 box -->
				<div id="reviewBox" style="height: auto;">
	
					<div class="more" id="more">
						<h3><a href="#">리뷰신고</a></h3>
						<a href="reviewListView.action" class="moreBtn">더보기+</a>
					</div>
				
					<table id="review_list" style="margin-bottom: 20px;">
						<thead>
							<tr>	
								<th>신고일자</th>
								<th>신고자 ID</th>
								<th>가게 이름</th>
								<th>피신고자 ID</th>
								<th>처리상태</th>
								<th>처리일자</th>
								<th>처리한 관리자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="review" items="${rvList }" begin="0" end="4">
							<tr id="${review.state }">
								<td class="reviewTd" id="${review.report_num }">${review.reg_date }</td>
								<td class="reviewTd" id="${review.report_num }">${review.user_id }</td>
								<td class="reviewTd" id="${review.report_num }">${review.st_name }</td>
								<td class="reviewTd" id="${review.report_num }">${review.accu_num }</td>
								<td class="reviewTd" id="${review.report_num }">${review.state }</td>
								<td class="reviewTd" id="${review.report_num }">${review.final_date }</td>
								<td class="reviewTd" id="${review.report_num }">${review.admin_id }</td>
							</tr>                               
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<br>
				<!-- 가게정보수정요청 box -->
				<div id="storeErrBox" style="height: auto;">
					<div class="more" id="more">
						<h3><a href="#">가게정보수정요청</a></h3>
						<a href="storeErrView.action" class="moreBtn">더보기+</a>
					</div>
				
					<table id="storeErr_list" style="margin-bottom: 20px;">
						<thead>
							<tr>	
								<th>요청일자</th>
								<th>요청자 ID</th>
								<th>가게 이름</th>
								<th>처리상태</th>
								<th>처리일자</th>
								<th>처리한 관리자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="err" items="${reqList }" begin="0" end="4">
							<tr id="${err.state }">
								<td class="errTd" id="${err.report_num }">${err.reg_date }</td>
								<td class="errTd" id="${err.report_num }">${err.user_id }</td>
								<td class="errTd" id="${err.report_num }">${err.st_name }</td>
								<td class="errTd" id="${err.report_num }">${err.state }</td>
								<td class="errTd" id="${err.report_num }">${err.final_date }</td>
								<td class="errTd" id="${err.report_num }">${err.admin_id }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<br>
				<!-- 이의제기 box -->
				<div id="objectionBox" style="height: auto;">
					<div class="more" id="more">
						<h3><a href="#">이의제기</a></h3>
						<a href="objectionView.action" class="moreBtn">더보기+</a>
					</div>
				
					<table id="objection_list" style="margin-bottom: 20px;">
						<thead>
							<tr>	
								<th>신청일자</th>
								<th>가게 이름</th>
								<th>처리상태</th>
								<th>처리일자</th>
								<th>처리한 관리자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="obj" items="${objList }" begin="0" end="4">
							<tr id="${obj.state }">
								<td class="objTd" id="${obj.report_num }">${obj.reg_date }</td>
								<td class="objTd" id="${obj.report_num }">${obj.st_name }</td>
								<td class="objTd" id="${obj.report_num }">${obj.state }</td>
								<td class="objTd" id="${obj.report_num }">${obj.final_date }</td>
								<td class="objTd" id="${obj.report_num }">${obj.admin_id }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				
				<br>
				<!-- 패널티회수 box -->
				<div id="penaltyBox" style="height: auto;">
					<div class="more" id="more">
						<h3><a href="#">패널티회수</a></h3>
						<a href="penaltyView.action" class="moreBtn">더보기+</a>
					</div>
				
					<table id="penalty_list" style="margin-bottom: 20px;">
						<thead>
							<tr>	
								<th>신청일자</th>
								<th>가게 이름</th>
								<th>처리상태</th>
								<th>처리일자</th>
								<th>처리한 관리자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="revo" items="${revoList }" begin="0" end="4">
							<tr id="${revo.state }">
								<td class="revoTd" id="${revo.report_num }">${revo.reg_date }</td>
								<td class="revoTd" id="${revo.report_num }">${revo.st_name }</td>
								<td class="revoTd" id="${revo.report_num }">${revo.state }</td>
								<td class="revoTd" id="${revo.report_num }">${revo.final_date }</td>
								<td class="revoTd" id="${revo.report_num }">${revo.admin_id }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<br>
				<!-- 가게 등록/폐업 요청 box -->
				<div id="inout_Box" style="height: auto;">
					<div class="more" id="more">
						<h3><a href="#">가게 등록/폐업 요청</a></h3>
						<a href="inoutView.action" class="moreBtn">더보기+</a>
					</div>
				
					<table id="inout_list" style="margin-bottom: 20px;">
						<thead>
							<tr>
								<th>구분</th>	
								<th>신청일자</th>
								<th>신청 ID</th>
								<th>처리상태</th>
								<th>처리일자</th>
								<th>처리한 관리자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="inout" items="${inoutList }" begin="0" end="4">
							<tr id="${inout.state }">
								<c:if test="${inout.cat eq '등록' }">
								<td style="background-color: green;" id="${inout.report_num }">${inout.cat }</td>
								<td class="inTd" id="${inout.report_num }">${inout.reg_date }</td>
								<td class="inTd" id="${inout.report_num }">${inout.user_id }</td>
								<td class="inTd" id="${inout.report_num }">${inout.state }</td>
								<td class="inTd" id="${inout.report_num }">${inout.final_date }</td>
								<td class="inTd" id="${inout.report_num }">${inout.admin_id }</td>
								</c:if>
								<c:if test="${inout.cat eq '폐업' }">
								<td class="outTd" style="background-color: pink;" id="${inout.report_num }">${inout.cat }</td>
								<td class="outTd" id="${inout.report_num }">${inout.reg_date }</td>
								<td class="outTd" id="${inout.report_num }">${inout.user_id }</td>
								<td class="outTd" id="${inout.report_num }">${inout.state }</td>
								<td class="outTd" id="${inout.report_num }">${inout.final_date }</td>
								<td class="outTd" id="${inout.report_num }">${inout.admin_id }</td>
								</c:if>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<c:import url="footer.jsp"></c:import>


</form>
</body>



</body>

</html>