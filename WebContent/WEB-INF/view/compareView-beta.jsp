<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>compareView-beta.jsp</title>

<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=cp%>/css/compareBox.css">
<link rel="stylesheet" type="text/css" href="<%=cp%>/css/userMyPage.css">


<!-- 배너 CSS -->
<style type="text/css">
<!--
배너 -->.has_sub_menu {
	list-style-type: none;
}

.side-menu {
	top: 50px;
	width: 5vw;
	z-index: 10;
	background: #ef6351;
	border-right: 1px solid rgba(0, 0, 0, 0.07);
	height: 90%;
	margin-top: 5.7vh;
	position: fixed;
	align-items: center;
}

#sidebar-menu li {
	font-size: 1.5rem;
}

#sidebar-menu .fas {
	padding-top: 3vh;
	padding-left: 1.2vw
}

.left_sub_menu {
	position: fixed;
	top: 50px;
	width: 18vw;
	z-index: 10;
	left: 4.6vw;
	background: white;
	height: 90%;
	margin-top: 5.7vh;
}

.sub_menu {
	margin-top: 50px;
}

.left_sub_menu>.sub_menu li:hover {
	color: ff5858;
	background-color: #e1e1e1;
}

.left_sub_menu>.sub_menu li {
	color: black;
	font-size: 17px;
	font-weight: 600;
	padding: 20px 0px 8px 14px;
	border-bottom: 1px solid #e1e1e1;
}

.sub_menu>h2 {
	padding-bottom: 4px;
	border-bottom: 3px solid #797979;
	margin-top: 30px;
	font-size: 21px;
	font-weight: 600;
	color: #333;
	margin-left: 10px;
	margin-right: 10px;
	font-family: 'NotoKrB';
}

.fas {
	color: #ff5858;
	font-size: 20px;
	line-height: 20px;
	float: right;
	margin-top: 0.6vh;
	margin-right: 2vw;
}

.sub_menu>.big_menu>.small_menu li {
	color: black;
	font-size: 14px;
	font-weight: 600;
	border-bottom: 0px solid #e1e1e1;
}

.big_menu {
	list-style-type: none;
	cursor: pointer;
}

.samll_menu {
	position: fixed;
	list-style-type: none;
	padding-inline-start: 0px;
	list-style: none;
	transition-duration: 0.5s;
}

.small_menuA {
	color: black;
	text-decoration-line: none;
	background-color: transparent;
	margin-bottom: 1vh
}

.big_menu ul {
	margin-top: 0;
	margin-bottom: 0;
}

.has_sub {
	width: 100%;
}
<!--
배너
  
-->
</style>

<!-- 해당 페이지 CSS -->
<style type="text/css">
.mainBody {
	display: flex;
}

.compTitle {
	width: 15vw;
	height: 15vh;
	margin-left: 48vw;
}

.compTitle span {
	font-size: 3rem;
	font-weight: bold;
	text-align: center;
}

.compareMain {
	width: 100vw;
}

.compareBody {
	width: 63vw;
	margin-left: 18vw;
	display: flex;
	text-align: center;
}

.compareSpace {
	width: 16.5vw;
}

.cCategory {
	margin-top: 28.84vh;
	width: 11vw;
}

.cList, .week, .lWeek, .cOptions, .lOptions {
	border-top: 1px solid #EB8F83;
	padding-top: 1vh;
	padding-bottom: 1vh;
}

.cName {
	font-size: 1.5rem;
	font-weight: bold;
}

.cBox {
	border-left: 1px solid #EB8F83;
}

.cList, .cLo {
	height: 3vh;
	font-size: 1rem;
	font-weight: bold;
}

.lDay {
	height: 3vh;
	font-size: 1rem;
	font-weight: bold;
	padding-top: 0.5vh;
	padding-bottom: 1.5vh;
}

.lWeek {
	height: 35vh;
}

.lOptions, .cOptions {
	height: 30vh;
}

.cMenu {
	height: 18.75vh;
}

.cat {
	margin-top: 1vh;
}

.photo1 {
	margin-bottom: 1vh;
}

.phStore {
	width: 15vw;
	height: 20vh;
	padding-bottom: vh;
	padding: 1px;
}

.week {
	height: 35vh;
	padding-top: 1vh;
}

.cDay, .cOpt {
	padding-top: 1.5vh;
	height: 2vh;
	font-size: 1rem;
	font-weight: bold;
	padding-bottom: 1.5vh;
}

.phMenu {
	width: 13.5vw;
	height: 15vh;
}
</style>

<!-- 배너 스크립트 -->
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
				$('.overlay').css("margin-top", "1.011vh");
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

<!-- 해당 페이지 조정 스크립트 -->
<script type="text/javascript">
	/* 2개 비교할 경우  */
	$(document).ready(function()
	{
		var numOfStores = $(".compareBody .compareSpace").length;
		if (numOfStores == 2)
		{
			$(".compareBody").css(
			{
				"margin-left" : "26vw",
				"width" : "55vw"
			});
			$(".compTitle").css("margin-left", "47vw");
		}

	});

	/* 영업 요일이 7주일 미만일 경우  */
	$(document).ready(function()
	{

		var maxNumOfDays = 0;
		$(".week").each(function()
		{
			var numOfDays = $(this).find(".cDay").length;
			if (numOfDays > maxNumOfDays)
			{
				maxNumOfDays = numOfDays;
			}
		});

		$(".week").each(function()
		{
			/* var numOfDays = $(this).find(".cDay").length; */
			if (maxNumOfDays == 6)
			{
				$(this).css("height", "29.5vh");
				$(this).css("padding-top", "0vh");
				$(".lWeek").css("height", "28.5vh");
			} else if (maxNumOfDays == 5)
			{
				$(this).css("height", "24.5vh");
				$(this).css("padding-top", "0vh");
				$(".lWeek").css("height", "23.5vh");
			} else if (maxNumOfDays == 4)
			{
				$(this).css("height", "19.5vh");
				$(this).css("padding-top", "0vh");
				$(".cList.cMenu").css("height", "18.73vh");
				$(".lWeek").css("height", "18.5vh");
			} else if (maxNumOfDays == 3)
			{
				$(this).css("height", "14.5vh");
				$(this).css("padding-top", "0vh");
				$(".cList.cMenu").css("height", "18.77vh");
				$(".lWeek").css("height", "13.5vh");
				$(".cList.cMenu").css("height", "18.75vh");
			} else if (maxNumOfDays == 2)
			{
				$(this).css("height", "10vh");
				$(this).css("padding-top", "0vh");
				$(".lWeek").css("height", "9vh");
				$(".cList.cMenu").css("height", "18.8vh");
				$(".cList.avgP").css("height", "2.95vh");
			} else if (maxNumOfDays == 1)
			{
				$(this).css("height", "5vh");
				$(this).css("padding-top", "0vh");
				$(".lWeek").css("height", "4.9vh");
				$(".lWeek").css("padding-top", "0vh");
				$(".lDay").css("padding-top", "1.5vh");
				$(".lWeek").css("padding-bottom", "1.1vh");
				$(".cList.cMenu").css("height", "18.75vh");
			} else if (maxNumOfDays == 0)
			{
				$(this).css("display", "none");
				$(".lWeek").css("display", "none");
				$(".cList.cMenu").css("height", "18.7vh");
			}
		});

	});

	/* 체크박스 여부 표시에 따른 높이 변화 */
	$(document).ready(function()
	{
		var maxOptNum = 0;

		// 값 = O/X 개수 - 'none' 개수 
		$(".cOptions").each(function()
		{
			var optNum = $(this).find(".cOpt").length;

			var yesNos = $(this).find(".chBox").filter(function()
			{
				return $(this).val() == "-";
			}).length;

			optNum = optNum - yesNos;

			if (optNum > maxOptNum)
			{
				maxOptNum = optNum;
			}
		});

		// 값에 따른 길이 변화
		$(".cOptions").each(function()
		{
			if (maxOptNum == 5)
			{
				$(this).css("height", "25vh");
				$("lOptions").css("height", "25vh");
			} else if (maxOptNum == 4)
			{
				$(this).css("height", "20vh");
				$("lOptions").css("height", "20vh");
			} else if (maxOptNum == 3)
			{
				$(this).css("height", "15vh");
				$("lOptions").css("height", "15vh");
			} else if (maxOptNum == 2)
			{
				$(this).css("height", "10vh");
				$("lOptions").css("height", "10vh");
			} else if (maxOptNum == 1)
			{
				$(this).css("height", "5vh");
				$("lOptions").css("height", "5vh");
			} else if (maxOptNum <= 0)
			{
				$(this).css("display", "none");
				$(".cList.lOptions").css("display", "none");
			}
		});
	});

	/* 받은 값에 따른 체크박스 변화 */
	$(document).ready(function()
	{
		// value가 'none' 일 경우 표시 안 함
		$(".chBox").each(function()
		{
			var yesNo = $(this).val();

			if (yesNo == "○")
			{
				$(this).prop("checked", true);
				$(this).attr("disabled", true);
			} else if (yesNo == "Ⅹ")
			{
				$(this).prop("checked", false);
				$(this).attr("disabled", true);
			} else if (yesNo == "-")
			{
				$(this).parent(".cOpt").css("display", "none");
			}

		});
	});
</script>

</head>
<body>
	<div class="header">
		<c:import url="/WEB-INF/view/header_user.jsp"></c:import>
	</div>

	<div class="mainBody">

		<c:import url="sideBar.jsp"></c:import>


		<div class="compareMain">

			<div class="compTitle">
				<span>비교하기</span>
			</div>

			<div id="여기가 큰 비교 틀 & 포문으로 들어갈 공간" class="compareBody">

				<div class="cCategory">
					<div class="cCat">
						<div id="가게 음식 카테고리" class="cList cat">가게 음식 카테고리</div>
						<div id="가게 평균 별점" class="cList">가게 평균 별점</div>
						<div id="가게 리뷰 수" class="cList">가게 리뷰 수</div>
						<div id="가게 주" class="lWeek">
							<div id="일요일" class="lDay">영업 일/시간</div>
						</div>
						<div id="가게 대표 메뉴 이름" class="cList avgP">가게 평균 가격</div>
						<div id="가게 평균 가격" class="cList cMenu">가게 대표 메뉴 이름</div>
						<div id="가게 최대 수용 인원" class="cList">가게 최대 수용 인원</div>
						<div id="가게 최대 수용 인원" class="cList lOptions">가게 옵션 유무</div>
					</div>
					<!-- cCat -->
				</div>

				<c:set var="st_len" value="${storeLen }" />
				<!-- 비교할 가게 갯수 -->

				<c:forEach begin="1" end="${storeLen}" varStatus="st_count">
					<div id="비교하는 가게 공간" class="compareSpace">
						<c:forEach var="st1" items="${store}" varStatus="st_status">
							<c:if test="${st_status.index + 1 == st_count.index}">
								<div id="비교하는 가게 이름" class="cName">${st1.st_name}</div>
								<div id="비교하는 가게 대표 사진" class="photo1">
									<img id="가게 대표사진" class="phStore"
									src="<%=cp %>/${st1.photo_link}">
								</div>
								<div id="가게 주소" class="cLo">${st1.st_location}</div>

								<div class="cBox">
									<div id="가게 음식 카테고리" class="cList cat">${st1.food_name}</div>
									<div id="가게 평균 별점" class="cList">${st1.star_avg}</div>
									<div id="가게 리뷰 수" class="cList">${st1.rv_count}</div>
								</div>
							</c:if>
						</c:forEach>
							<!-- cBox -->
							<div class="cBox">
								<c:forEach var="op" items="${openClose}" varStatus="oc_status">
									<c:if test="${oc_status.index + 1 == st_count.index}">
										<div id="가게 주" class="week">
											<div id="days" class="cDay">${op.day_name}
												${op.operate_time} ${op.holiday}</div>
										</div>
									</c:if>
								</c:forEach>
								<c:forEach var="ml" items="${menuLists}" varStatus="ml_status">
									<c:if test="${ml_status.index + 1 == st_count.index}">
										<div id="가게 평균 가격" class="cList">${ml.price_avg}</div>
										<div id="가게 대표 메뉴 이름" class="cList">${ml.menu_name}</div>
										<div id="가게 대표 메뉴 이미지" class="photo2">
											<img id="가게 대표사진" class="phMenu"
												src="<%=cp %>/images/${ml.image_link}">
										</div>
									</c:if>
								</c:forEach>
								<c:forEach var="st" items="${store}" varStatus="st_status2">
									<c:if test="${st_status2.index + 1 == st_count.index}">
										<div id="가게 최대 수용 인원" class="cList">${st.max_customers}</div>
									</c:if>
								</c:forEach>
								
								<c:forEach var="sc" items="${stcheck}" varStatus="sc_status">
									<c:if test="${sc_status.index + 1 == st_count.index}">
										<div id="가게 옵션" class="cOptions">
											<c:forEach begin="1" end="6" varStatus="optNum">
												<div id="화장실 존재 여부" class="cOpt">
													${sc.chbox_name} : <input type="checkbox" class="chBox"
														value="${sc.yesorno}" id="${optNum.index }" />
												</div>
											</c:forEach>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<!-- cBox -->

					</div>
				</c:forEach>
			</div>
			<!-- compareBody -->

		</div>
		<!-- class = "compareMain" -->


		<c:import url="compare_box.jsp"></c:import>
	</div>
	<!-- mainBody -->

	<div class="footer">
		<c:import url="/WEB-INF/view/footer.jsp"></c:import>
	</div>
</body>
</html>