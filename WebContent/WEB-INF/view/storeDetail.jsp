<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>YameokJa</title>

<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=cp%>/css/compareBox.css">

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=cp%>/css/storeDetail.css">

<link rel="stylesheet" type="text/css" href="<%=cp%>/css/userMyPage.css">
<link rel="stylesheet" type="text/css" href="<%=cp%>/css/side_bar.css">

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>

<script type="text/javascript">

		/// 신고하기 버튼 눌렀을 때
		$(".repBtn").click(function()
		{
			alert($(this).val());
			$("input[name=rvNumHidden]").attr("value", $(this).val());
		});

		$("#decBtn").click(function()
		{
			$rv_num = $("input[name=rvNumHidden]").val();
			alert($rv_num);

			var reviewRep = [];

			$("input:checkbox[name=reviewRep]:checked").each(function()
			{
				reviewRep.push($(this).val());
			});

			$rep_rs_num = reviewRep[0];

			if (reviewRep.length == null || reviewRep.length == 0)
			{
				alert("신고사유를 선택해주세요.");
				return;
			}

			$("input:checkbox[name=reviewRep]:checked").each(function()
			{
				$(this).prop("checked", false);
				totalChecked = 0;
			});

			$.ajax(
			{
				url : "reviewRep.action",
				type : 'post',
				data :
				{
					"rv_num" : $rv_num,
					"rep_rs_num" : $rep_rs_num
				},
				success : function(result)
				{
					if (result == "1")
					{
						alert("신고가 완료되었습니다.");
					} else
					{
						alert("신고 과정에서 오류가 발생했습니다. 다시 시도해주세요.")
					}

				},
				error : function(e)
				{
					alert(e.responseText);
				}
			});
		});

		

		var totalChecked = 0;

		function CountChecked(field)
		{
			if (field.checked)
				totalChecked += 1;
			else
				totalChecked -= 1;

			if (totalChecked > 1)
			{
				alert("한 개만 선택 가능합니다.");
				field.checked = false;
				totalChecked -= 1;
			}

		}

		// 리뷰 작성 페이지로 이동
		$("#insertReview").click(function()
		{
			$("#userForm").attr("action", "reviewRep.action");
			$("#usesForm").submit();
		});

		// 모달--------------------------------------------------------------------------
		function popupOpen()
		{

			if (document.all.popup.style.visibility == "hidden")
			{
				document.all.popup.style.visibility = "visible";
				bgLayerOpen();

				var $layerPopupObj = $('#popup');
				var left = ($(window).scrollLeft() + ($(window).width() - $layerPopupObj
						.width()) / 2);
				var top = ($(window).scrollTop() + ($(window).height() - $layerPopupObj
						.height()) / 2);

				$layerPopupObj.css(
				{
					'left' : left,
					'top' : top,
					'position' : 'absolute'
				});
				$('body').css('position', 'relative').append($layerPopupObj);

				return false;
			} else
			{
				document.all.popup.style.visibility = "hidden";
				bgLayerClear();
				return false;
			}

		}

		function bgLayerOpen()
		{
			if (!$('.bgLayer').length)
			{
				$('<div class="bgLayer"></div>').appendTo($('body'));
			}
			var object = $(".bgLayer");
			var w = $(document).width() + 12;
			var h = $(document).height();

			object.css(
			{
				'width' : w,
				'height' : h
			});
			object.fadeIn(500); //생성되는 시간 설정
		}

		function bgLayerClear()
		{
			var object = $('.bgLayer');

			if (object.length)
			{
				object.fadeOut(500, function()
				{
					object.remove();

				});
			}
		}

		$(function()
		{
			$(window).resize(function()
			{ //화면 크기 변할 시
				$('.bgLayer').css('width', $(window).width() - 0);
				$('.bgLayer').css('height', $(window).height() - 0);
			});
		});

	});
	
	
	// 추천/비추천
	$(document).on("click",".recBtn", function()
	{
		//alert("확인");
		$rv_num = $(this).val();

		if ($(this).attr("name") == "rec")
		{
			$rec_nonrec_number = "1";
		} else
			$rec_nonrec_number = "2";

		//alert($rec_nonrec_number);

		$.ajax(
		{
			url : "recInsertDelete.action",
			type : 'post',
			data :
			{
				"rv_num" : $rv_num,
				"rec_nonrec_number" : $rec_nonrec_number
			},
			dataType : "json",
			success : function(html)
			{
				//alert(html.rv_num +"|"+ html.rec_nonrec_number + "|" + html.action);
				//alert(html.rec + "|" + html.nonrec);
				
				if(html.action=="-1") // 같은 추천을 눌렀을 경우 -> rec_nonrec_number의 스타일을 없애고, count-1
				{
					if($rec_nonrec_number=="1")
					{
						$("#rec" + html.rv_num).css("border", "none");
						$("#rec" + html.rv_num).html("추천 👍 ("+ html.rec +")");
					}
					else if($rec_nonrec_number=="2")
					{
						$("#nonrec" + html.rv_num).css("border", "none");
						$("#nonrec" + html.rv_num).html("비추천 👎 ("+ html.nonrec +")");
					}
				}
				else if(html.action=="0") // 그냥 추천/비추천 추가 -> 추가한 것에 스타일 추가, count+1
				{
					if($rec_nonrec_number=="1")
					{
						$("#rec" + html.rv_num).css("border", "2px solid #ef6351");
						$("#rec" + html.rv_num).html("추천 👍 ("+ html.rec +")");
					}
					else if($rec_nonrec_number=="2")
					{
						$("#nonrec" + html.rv_num).css("border", "2px solid #ef6351");
						$("#nonrec" + html.rv_num).html("비추천 👎 ("+ html.nonrec +")");
					}
				}
				else if(html.action=="1") // 이미 눌려있는 상태에서 다른 추천 눌렀을 경우
										  // -> 누른 추천에 스타일 추가, 이미 있는 것에 스타일 해제
										  //    누른 추천에 count+1, 이미 있는 것에 count-1
				{
					if(html.rec_nonrec_number=="1")
					{
						$("#rec" + html.rv_num).css("border", "2px solid #ef6351");
						$("#rec" + html.rv_num).html("추천 👍 ("+ html.rec +")");
						$("#nonrec" + html.rv_num).css("border", "none");
						$("#nonrec" + html.rv_num).html("비추천 👎 ("+ html.nonrec +")");
						
					}
					else if($rec_nonrec_number=="2")
					{
						$("#rec" + html.rv_num).css("border", "none");
						$("#rec" + html.rv_num).html("추천 👍 ("+ html.rec +")");
						$("#nonrec" + html.rv_num).css("border", "2px solid #ef6351");
						$("#nonrec" + html.rv_num).html("비추천 👎 ("+ html.nonrec +")");
					}
				}
			},
			error : function(e)
			{
				alert(e.responseText);
			}
		});
	});
</script>

</head>


<body>
	<form action="" id="userForm" method="post">
		<div class="header">
			<c:import url="header_user.jsp"></c:import>
		</div>

		<div id="body">

			<div id="column">

				<c:import url="sideBar.jsp"></c:import>
			</div>

			<div class="mainBody">

				<div id="container1" class="">
					<c:forEach var="s" items="${store }">
						<div class="placeholders main">

							<div class="placeholder main-left">

								<div class="background" style="font-weight: bold;">
									<div class="storeName">
										<span style="font-size: 30pt; font-weight: bold;">${s.st_name }</span>
									</div>
									<div class="revBoard">
										<div class="storeImgDiv">
											<img class="storeImg" src="<%=cp%>/images/store_img01.png">
										</div>

										<div class="info">
											<div class="likeRevC likeNum">
												찜 ❤ &nbsp; <span>${clikeNum }</span>
											</div>
											<div class="likeRevC score">
												별점 ⭐<span>${s.star_avg}</span>
											</div>

											<div class="likeRevC reviewNum">
												리뷰 수 &nbsp; <span>${s.rv_count}</span>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="placeholder main-right">
								<div class="background"
									style="font-weight: bold; margin-top: 1vh;">
									<div class="ocName">
										<span style="font-size: 18pt;">[영업 시간]</span>
									</div>
									<div id="weekList" class="openclose background">
										<div id="opencloseholiday">
											<div id='opencloseholidayName'>
												<div class="dayname">요일</div>
												<div class="time">운영시간</div>
											</div>
											<div id="opencloseholidayList">
												<c:if test="${empty openClose }">
													<div class="ochList">
														<div class="dayname">월요일</div>
														<div class="time">-</div>
														<div class="holiday">-</div>
													</div>
													<div class="ochList">
														<div class="dayname">화요일</div>
														<div class="time">-</div>
														<div class="holiday">-</div>
													</div>
													<div class="ochList">
														<div class="dayname">수요일</div>
														<div class="time">-</div>
														<div class="holiday">-</div>
													</div>
													<div class="ochList">
														<div class="dayname">목요일</div>
														<div class="time">-</div>
														<div class="holiday">-</div>
													</div>
													<div class="ochList">
														<div class="dayname">금요일</div>
														<div class="time">-</div>
														<div class="holiday">-</div>
													</div>
													<div class="ochList">
														<div class="dayname">토요일</div>
														<div class="time">-</div>
														<div class="holiday">-</div>
													</div>
													<div class="ochList">
														<div class="dayname">일요일</div>
														<div class="time">-</div>
														<div class="holiday">-</div>
													</div>
												</c:if>
												<c:forEach var="op" items="${openClose}">
													<div class="ochList">
														<div class="dayname">${op.day_name }</div>
														<c:choose>
															<c:when test="${op.holiday eq '휴무' }">
																<div class="time">휴무</div>
															</c:when>
															<c:otherwise>
																<div class="time">${op.operate_time }</div>
															</c:otherwise>
														</c:choose>

													</div>
												</c:forEach>
											</div>
										</div>
										<div id="breakTime">
											<div class="breakName">브레이크타임</div>

											<c:if test="${empty breakTime }">
												-
											</c:if>
											<c:forEach var="breakTime" items="${breakTime }">
												<div class="breakList">
													<div>[${breakTime.week_weekend }] &nbsp;&nbsp;</div>
													<div>${breakTime.start_breaktime }~
														${breakTime.end_breaktime }</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="storeKeywordsDiv">
							<c:forEach var="keyword" items="${stKeys }">
								"${keyword.st_keyword }" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
						</div>

						<div class="storeMiddle">
							<div class="storeinfo">
								<div class="storeInfoList">
									<div id="catName" class="siList1">카테고리</div>
									<div id="addrName" class="siList1">주소</div>
									<div id="telName" class="siList1">전화번호</div>
									<div id="emailName" class="siList1">e-mail</div>
									<div id="maxName" class="siList1">최대수용인원수</div>
									<div id="payName" class="siList1">결제수단</div>
									<div id="explainName" class="siList1">가게 설명</div>

								</div>

								<div class="storeInfoValue">
									<div id="cat" class="siList2">${s.food_name }</div>
									<div id="addr" class="siList2">${s.st_location }</div>
									<div id="tel" class="siList2">${s.st_tel }</div>
									<div id="email" class="siList2">${s.st_email }</div>
									<div id="max" class="siList2">${s.max_customers }</div>
									<div id="pay" class="siList2">
										<c:forEach var="stPay" items="${stPayList}">
										&nbsp;${stPay } 
									</c:forEach>
									</div>
									<div id="explain" class="siList2">${s.st_explain }</div>
								</div>
							</div>

							<div class="storeChecks">
								<c:if test="${empty stCheckList }">
									<div class="none">해당 항목이 존재하지 않습니다.</div>
								</c:if>
								<div class="storeCheck">
									<div class="stCheckListName"><가게 옵션></div>
									<div class="stCheckListYesorno"><여부></div>
								</div>
								<c:forEach var="stCheck" items="${stCheckList }">
									<div class="storeCheck">
										<div class="stCheckName">${stCheck.chbox_name }</div>
										<div class="stCheckYesorno">${stCheck.yesorno }</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:forEach>


					<div id="menuboard" class="container2">

						<!-- 메뉴 -->
						<h2>메뉴</h2>
						<div class="menuList">
							<c:if test="${empty menuLists }">
								<div class="none">"등록된 메뉴가 존재하지 않습니다."</div>
							</c:if>
							<c:forEach var="ml" items="${menuLists }">
								<div class="menu">
									<div class="menuPhoto">
										<img class="thumbnail" src="<%=cp %>/images/${ml.image_link }" />
									</div>
									<div class="menuName">"${ml.menu_name }"</div>
									<div class="menuPrice">
										<fmt:formatNumber value="${ml.price }" pattern="#,###" />
										원
									</div>
								</div>
							</c:forEach>
						</div>
					</div>

					<div class=" container3">
						<div id="reviewList">
							<h2>리뷰</h2>


							<div class="rvKeyList">
								<c:if test="${empty reviewKeys }">
									<div class="none">"등록된 리뷰 키워드가 존재하지 않습니다."</div>
								</c:if>
								<c:forEach var="rvKey" items="${reviewKeys }">
									<div class="rvKey">"${rvKey.rv_key_name }"</div>
									<div class="rvKeyCount">(${rvKey.rv_key_count })</div>
								</c:forEach>
							</div>
							<!-- class="revKeyList"  -->

							<div id="revList">
								<div class="reviewInsertBtnDiv">
									<button type="button" class="btn" id="insertReview">리뷰작성하기</button>
								</div>
								<c:if test="${empty reviews }">
									<div class="none">"작성된 리뷰가 존재하지 않습니다."</div>
								</c:if>
								<c:forEach var="rv" items="${reviews }">
									<div class="reviews">
										<div class="rvTop">
											<div class="userNickname">"${rv.user_nickname }"</div>
											<div>
												<button type="button" class="repBtn rvBtn"
													onclick="popupOpen()" value="${rv.rv_num }">신고하기</button>

											</div>
										</div>


										<div class="rvMiddle">
											<div class="starScore">
												<c:choose>
													<c:when test="${rv.star_score eq 5}">
													⭐⭐⭐⭐⭐
												</c:when>
													<c:when test="${rv.star_score eq 4}">
													⭐⭐⭐⭐
												</c:when>
													<c:when test="${rv.star_score eq 3}">
													⭐⭐⭐
												</c:when>
													<c:when test="${rv.star_score eq 2}">
													⭐⭐
												</c:when>
													<c:when test="${rv.star_score eq 1}">
													⭐
												</c:when>
													<c:otherwise>
													-
												</c:otherwise>
												</c:choose>
											</div>
											<div class="contentPhotoDiv">
												<div class="rvContentDiv">${rv.rv_content }</div>
												<div class="rvPhotoDiv">
													<c:forEach var="photos" items="${rvPhotos }">
														<c:if test="${photos.rv_num == rv.rv_num }">
															<img alt="" class="rvphoto"
																src="<%=cp %>/images/${photos.photo_link }" />
														</c:if>
													</c:forEach>
												</div>
											</div>
											<div class="regDate">${rv.reg_date }</div>
										</div>

										<div class="rvBottom">
											<div class="recNonrecBtnDiv">
												<c:set var="rv_num" value="${rv.rv_num }"/>
												<c:forEach var="userRvRec" items="${userReviewList }">
													<c:choose>
														<c:when test="${(userRvRec.rv_num eq rv_num) 
															&& (userRvRec.rec_nonrec_number eq 1) }">
															<button type="button" id="nonrec${rv_num }" name="nonrec"
																class="recBtn rvBtn" value="${rv.rv_num }">비추천 👎
																(${rv.nonrec })</button>
															<button type="button" id="rec${rv_num }" name="rec"
																class="recBtn rvBtn" value="${rv.rv_num }"
																style="border: 2px solid #ef6351">추천 👍
																(${rv.rec } )</button>
														</c:when>
														<c:when test="${(userRvRec.rv_num eq rv_num) 
															&& (userRvRec.rec_nonrec_number eq 2) }">
															<button type="button" id="nonrec${rv_num }" name="nonrec"
																class="recBtn rvBtn" value="${rv.rv_num }"
																style="border: 2px solid #ef6351">비추천 👎
																(${rv.nonrec })</button>
															<button type="button" id="rec${rv_num }" name="rec"
																class="recBtn rvBtn" value="${rv.rv_num }">추천 👍
																(${rv.rec } )</button>
														</c:when>
														<c:when test="${(userRvRec.rv_num ne rv_num)}">
															<button type="button" id="nonrec${rv_num }" name="nonrec"
																class="recBtn rvBtn" value="${rv.rv_num }">비추천 👎
																(${rv.nonrec })</button>
															<button type="button" id="rec${rv_num }" name="rec"
																class="recBtn rvBtn" value="${rv.rv_num }">추천 👍
																(${rv.rec } )</button>
														</c:when>
														
													</c:choose>
												</c:forEach>
											</div>
										</div>
									</div>
								</c:forEach>
								<input type="hidden" value="" name="rvNumHidden"> <input
									type="hidden" value="" name="rep_rs_num">
							</div>
							<!-- id="revList" -->

							<div id="popup" style="position: absolute; visibility: hidden;">
								<h4>
									<a href="#" class="close" onClick="javascript:popupOpen()">X</a>
								</h4>
								<h3>리뷰신고</h3>
								<div class="popCont">
									<div class="list">
										<label for="commercial"> <input type="checkbox"
											class="reviewRep" id="commercial" name="reviewRep" value="1"
											onclick="CountChecked(this)">원치 않는 상업적인 리뷰
										</label><br> <br> <label for="intended"> <input
											type="checkbox" id="intended" name="reviewRep"
											class="reviewRep" value="2" onclick="CountChecked(this)">악의적인
											리뷰
										</label><br> <br> <label for="wrong"> <input
											type="checkbox" id="wrong" name="reviewRep" class="reviewRep"
											value="3" onclick="CountChecked(this)">잘못된 정보
										</label><br> <br> <label for="violent"> <input
											type="checkbox" id="violent" name="reviewRep"
											class="reviewRep" value="4" onclick="CountChecked(this)">욕설,
											성적, 폭력적인 리뷰
										</label><br> <br>
									</div>
								</div>
								<div class="dec">
									<button id="decBtn" onclick="popupOpen()">신고하기</button>
								</div>
							</div>
						</div>
					</div>
					<!-- class="col-md-8  container4" -->
				</div>
				<!-- id="container1" class="col-md-8" -->
			</div>

			<!-- id="container1" class="col-md-8" -->

			<!-- main -->


			<!-- 비교함 영역 -->
			<div id="compareDiv">
				<div class="rectDiv">
					<!-- 비교함 이름 영역 -->
					<div class="comNameDiv">
						<span style="font-color: #ef6351">비교함</span>
					</div>
					<!-- 비교함 담은 가게 리스트 영역 -->
					<div class="comStoreListDiv">
						<c:choose>
							<c:when
								test="${fn:length(comList) == null or fn:length(comList) == 0 }">
								<c:forEach begin="0" end="9">
									<div class="comStoreDiv">
										<!-- 한 가게 대표사진 영역 -->
										<div class="comStoreImgDiv">
											<img class="comStNoImg" src="<%=cp%>/images/comp_img01.png">
										</div>
										<!-- 한 가게 가게이름 영역 -->
										<div class="comStoreNameDiv"></div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<!-- 한 가게 영역 -->
								<c:forEach var="com" items="${comList}" varStatus="status">
									<div class="comStoreDiv">
										<!-- 한 가게 대표사진 영역 -->
										<div class="comStoreImgDiv">
											<button type="button" value="${com.st_num}" class="comDelete">X</button>
											<label for="${com.st_num}" class="stLabel"> <input
												type="checkbox" class="comStImgCB" name="comStImgCB"
												id="${com.st_num}"> <c:set var="photo"
													value="${com.photo_link }" /> <c:choose>
													<c:when test="${empty photo}">
														<img class="stImg" src="<%=cp%>/images/logo_text.png">
													</c:when>
													<c:otherwise>
														<img class="stImg" src="<%=cp%>/${photo}">
													</c:otherwise>
												</c:choose>
											</label>
										</div>
										<!-- 한 가게 가게이름 영역 -->
										<div class="comStoreNameDiv">${com.st_name}</div>
									</div>
								</c:forEach>
								<c:forEach begin="0" end="${10 - fn:length(comList)}">
									<div class="comStoreDiv">
										<!-- 한 가게 대표사진 영역 -->
										<div class="comStoreImgDiv">
											<img class="comStNoImg" src="<%=cp%>/images/comp_img01.png">
										</div>
										<!-- 한 가게 가게이름 영역 -->
										<div class="comStoreNameDiv"></div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<input type="hidden" id="checkedCompare" name="checkedCompare">
				</div>
				<input type="hidden" name="regionChk" id="regionChk"> <input
					type="hidden" name="foodlabelChk" id="foodlabelChk"> <input
					type="hidden" name="stKeyChk" id="stKeyChk">

				<div class="comStoreBtnDiv">
					<button type="button" class="btn" id="comBtn" name="comBtn">비교하기</button>
				</div>
			</div>
		</div>
	</form>
	<div class="footer">
		<c:import url="footer.jsp"></c:import>
	</div>

</body>
</html>