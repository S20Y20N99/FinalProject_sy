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
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Orbit&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=cp%>/css/side_bar.css">

<script type="text/javascript">
	$(function()
	{
		$(".left_sub_menu").hide();
		$(".has_sub").click(function()
		{
			$(".left_sub_menu").fadeToggle(300);
			if ($('#checkOverlay').val() == "true")
			{
				$('.overlay').css("position", "fixed");
				$('.overlay').css("width", "0%");
				$('.overlay').css("height", "0%");
				$('.overlay').css("background", "rgba(0, 0, 0, 0.7)");
				$('.overlay').css("z-index", "0");
				$('#checkOverlay').attr("value", "false");
			} else
			{
				$('.overlay').css("position", "fixed");
				$('.overlay').css("width", "100%");
				$('.overlay').css("height", "100%");
				$('.overlay').css("background", "rgba(0, 0, 0, 0.7)");
				$('.overlay').css("z-index", "3");
/* 				$('.overlay').css("margin-top", "1.011vh"); */
				$('#checkOverlay').attr("value", "true");
			}

		});
		// 왼쪽메뉴 드롭다운
		$(".sub_menu ul.small_menu").hide();
		$(".sub_menu ul.big_menu").click(function()
		{
			$("ul", this).slideToggle(300);
		});
		// 외부 클릭 시 좌측 사이드 메뉴 숨기기
		$('.overlay').on('click', function()
		{
			$('.left_sub_menu').fadeOut();
			$('.overlay').css("position", "fixed");
			$('.overlay').css("width", "0%");
			$('.overlay').css("height", "0%");
			$('.overlay').css("background", "rgba(0, 0, 0, 0.7)");
			$('.overlay').css("z-index", "0");
			$('#checkOverlay').attr("value", "false");
		});
	});
</script>

<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
</head>
<body>
<div class="sideBarContainer">
<!-- 왼쪽 사이드 바 -->
<div class="left side-menu">
	<div class="sidebar-inner">
		<div id="sidebar-menu">
			<ul class="has_sub_menu">
				<li class="has_sub"><a href="javascript:void(0);"
					class="waves-effect"> <i class="fas fa-bars"
						style="color: #fff"></i>
				</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="col-md-2" id="mleft">
	<div class="left_sub_menu">
		<div class="sub_menu">
			<h2>MENU</h2>
			<c:if test="${st_num != null }">
			<ul class="big_menu">
						
				<li>가게 리스트<i class="arrow fas fa-angle-right"></i></li>

				<ul class="small_menu">
					<li><a href="#" class="small_menuA">가게1</a></li>
					<li><a href="#" class="small_menuA">가게2</a></li>
					<li><a href="#" class="small_menuA">가게3</a></li>
					<li><a href="#" class="small_menuA">가게4</a></li>
				</ul>
			</ul>
			</c:if>
			<ul class="big_menu">
				<li>접수 내역<i class="arrow fas fa-angle-right"></i></li>
				<ul class="small_menu">
					<li><a href="user_rv_report.action" class="small_menuA">리뷰신고</a></li>
					<li><a href="user_stupdate_relist.action" class="small_menuA">가게정보수정요청</a></li>
				</ul>
			</ul>
			<ul class="big_menu">
				<li>경고 내역</li>
			</ul>
		</div>
	</div>
	<div class="overlay">
		<input type="hidden" id="checkOverlay" value="false">
	</div>
</div>
</div>

</body>
</html>