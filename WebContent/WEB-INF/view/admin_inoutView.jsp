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
<title>admin_inoutView.jsp</title>

<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main_admin.css">

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
			<h1>가게 등록/폐업 요청 접수내역 관리</h1>
			
			<!-- 가게 등록/폐업 요청 box -->
			<div id="inout_Box" style="height: auto;">
				<div class="more" id="more">
					<h3><a href="#">가게 등록/폐업 요청</a></h3>
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
						<c:forEach var="inout" items="${inoutList }">
						<tr id="${inout.state }">
							<c:if test="${inout.cat eq '등록' }">
							<td style="background-color: green;" id="${inout.cat }">${inout.cat }</td>
							</c:if>
							<c:if test="${inout.cat eq '폐업' }">
							<td style="background-color: pink;">${inout.cat }</td>
							</c:if>
							<td class="inTd" id="${inout.report_num }">${inout.reg_date }</td>
							<td class="inTd" id="${inout.report_num }">${inout.user_id }</td>
							<td class="inTd" id="${inout.report_num }">${inout.state }</td>
							<td class="inTd" id="${inout.report_num }">${inout.final_date }</td>
							<td class="inTd" id="${inout.report_num }">${inout.admin_id }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="back" id="more">
				<h3></h3>
				<a href="mainAdminView.action" class="backBtn" style="font-size: 10pt;">뒤로가기</a>
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<c:import url="footer.jsp"></c:import>
	
</form>
</body>



</body>

</html>




