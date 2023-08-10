<%@ page contentType="text/html; charset=UTF-8"%>
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
<title>userMyPage</title>


<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>



<link rel="stylesheet" type="text/css" href="<%=cp%>/css/user_main.css">

<link rel="stylesheet" type="text/css" href="<%=cp%>/css/userMyPage.css">




</head>
<body>
<form action="search.action" id="userForm" method="post">
	<div class="header">
		<c:import url="header_user.jsp"></c:import>
	</div>

	<div class="container">

		<div class="middle">

			
			<c:import url="sideBar.jsp"></c:import>
			
			<!-- 가운데 마이 페이지 영역 -->
			<div class="col-md-8">
				<div class="mypage">마이 페이지</div>
				
				<!-- 개인 정보 영역 -->
				<div class="id">
					
						<div class="id1">
							<div class="title9">닉네임(아이디)</div>
							<div class="sub9">${user.user_nickname }(${user.user_id })</div>
						</div>
						<div class="id1">
							<div class="title9">등급</div>
							<div class="sub9">${user.user_grade }</div>
						</div>
						<div class="id1">
							<div class="title9">포인트</div>
							<div class="sub9">${user.point_sum}pt</div>
						</div>
						<div class="id1">
							<div class="title9">키워드</div>
							<div class="sub9">
								<c:choose>
						            <c:when test="${empty usermp_ukeyword}">
						                키워드가 없습니다.
						            </c:when>
						            <c:otherwise>
						                <c:forEach var="keyword" items="${usermp_ukeyword}">
						                    ${keyword.ukeyword}
						                </c:forEach>
						            </c:otherwise>
						        </c:choose>
							</div>
						</div>
				</div>

				<!-- 개인이 찜한 목록 -->
				<div class="jjimlist">
					<div class="ta">
						<div class="title">찜한가게 목록</div>
						<div class="ado" id="ado1">
							<a href="" class="adoi">더보기+</a>
						</div>
					</div>

					<div class="jimlist">
						<c:choose>
					        <c:when test="${empty likelist}">
					            <div class="jimstore">찜한 가게가 없습니다.</div>
					        </c:when>
					        <c:otherwise>
					            <div class="jimlist">
					                <c:forEach var="store" items="${likelist}">
					                    <div class="jimstore">
					                        <div class="jimstoreImg">
					                            <img src="<%=cp %>/${store.photo_link }" class="jimstImg" alt="...">
					                        </div>
					                        <div class="jimstoreName">
					                            ${store.st_name }
					                        </div>
					                    </div>
					                </c:forEach>
					            </div><!-- .jimlist end -->
					        </c:otherwise>
					    </c:choose>
						
					</div><!-- .jimlist end -->
				</div><!-- .jjimlist end -->


				<!-- 개인이 비교했던 가게목록 -->
				<div class="jjimlist">
					<div class="ta">
						<div class="title">비교했던 가게 목록</div>
						<div class="ado" id="ado2">
							<a href="" class="adoi">더보기+</a>
						</div>
					</div>

					<div class="jimlist">
						<c:choose>
				            <c:when test="${empty comparedlist}">
				                <div class="jimstore">비교한 가게가 없습니다.</div>
				            </c:when>
				            <c:otherwise>
				                <c:forEach var="store" items="${comparedlist}">
				                    <div class="jimstore">
				                        <div class="jimstoreImg">
				                            <img src="<%=cp %>/${store.photo_link }" class="jimstImg" alt="...">
				                        </div>
				                        <div class="jimstoreName">
				                            ${store.st_name }
				                        </div>
				                    </div>
				                </c:forEach>
				            </c:otherwise>
				        </c:choose>
						
					</div><!-- .jimlist end -->
				</div><!-- .jjimlist end -->
				
				
				
				<!-- 개인이 작성한 리뷰목록 -->
				<div class="stalist">
					<div class="ta">
						<div class="title">작성한 리뷰목록</div>
						<div class="ado">
							<a href="" class="adoi">더보기+</a>
						</div>
					</div>

					<div class="list0" id="list0">
						<div class="list1">Num</div>
						<div class="list1">가게명</div>
						<div class="list1">내용</div>
						<div class="list1">리뷰 작성 날짜</div>
					</div>
					<c:choose>
				        <c:when test="${empty rvlist}">
				            <div class="list0">작성한 리뷰가 없습니다.</div>
				        </c:when>
				        <c:otherwise>
				            <c:forEach var="review" items="${rvlist}">
				                <div class="list0">
				                    <div class="list1">${review.rv_num }</div>
				                    <div class="list1">${review.st_num }</div>
				                    <div class="list1">${review.rv_content }</div>
				                    <div class="list1">${review.reg_date }</div>
				                </div>
				            </c:forEach>
				        </c:otherwise>
				    </c:choose>
				</div><!-- .stalist end -->
			</div><!-- .col-md-8 end -->
			
			
			<!-- 오른쪽 비교함 영역 -->
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
								test="${fn:length(comList) == 0 or fn:length(comList) == null}">
								<c:forEach var="i" begin="0" end="9">
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
								<c:forEach var="store" items="${comparingbox }" varStatus="status">
									<div class="comStoreDiv">
										<!-- 한 가게 대표사진 영역 -->
										<div class="comStoreImgDiv">
											<button type="button" value="${store.st_num}" class="comDelete">X</button>
											<label for="${store.st_num }" class="stLabel"> <input type="checkbox"
												class="comStImgCB" id="${com.st_num }">
												<c:set var = "photo" value="${com.photo_link }"/>
												<c:choose>
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
										<div class="comStoreNameDiv">${store.st_name }</div>
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
						</div><!-- .comStoreListDiv end -->
					</div>
				
					<!-- 비교하기 버튼 영역 -->
					<div class="comStoreBtnDiv">
						<button type="button" class="btn" id="comBtn" name="comBtn">비교하기</button>
					</div>
				
			</div><!-- .compareDiv end -->
		
		
		
		</div><!-- .middle end -->
	</div><!-- container end -->

	<div class="footer">
		<c:import url="footer.jsp"></c:import>
	</div>


</form>
</body>
</html>