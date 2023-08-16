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
<title>가게 등록</title>
<style type="text/css">
.bframe {
	display: flex;
	flex-direction: column;
}

.st_info_insert {
	width: 70vw;
	margin-left: 15vw;
	padding-top: 3vh;
	padding-bottom: 5vh;
	border: 5px solid #F7F4EA;
	border-radius: 40px;
}

.top {
	width: 70vw;
	margin-left: 15vw;
	text-align: center;
}

.inputform {
	background-color: #f5f5f5;
	border: 1px solid #ccc;
	color: #555;
	cursor: not-allowed;
	width: 23.8vw;
	border-radius: 0 10px 10px 0;
	padding: 8px 12px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	margin: 0;
}

.fileDown {
	height: 50%;
	width: 23.8vw;
}

#st_in_num {
	width: 27.5v
}

.title {
	background-color: #F7F4EA;
	width: 8vw;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 10px 0 0 10px;
	margin-right: 1vw;
}

.igroup {
	margin-left: 18vw;
	margin-top: 1%;
	width: 55vw;
	display: flex;
}

#insert {
	margin-left: 2vw;
}

.input_group {
	display: flex;
	align-items: center;
}

#st_in_num1 {
	width: 6vw;
	border-radius: 10px;
	margin-right: 0.5vw;
}

#st_in_num2 {
	width: 6vw;
	border-radius: 10px;
	margin-right: 0.5vw;
	margin-left: 0.5vw;
}

#st_in_num3 {
	width: 6vw;
	border-radius: 10px;
	margin-left: 0.5vw;
	margin-right: 1vw;
}

.checkLabel {
	display: flex;
	flex-direction: row;
	justify-content: center;
	padding-left: 25.9vw;
	margin-top: 1%
}

input[type="radio"] {
	display: none;
}

.checkLabel input[type="radio"]+span {
	display: inline-block;
	padding: 5px 10px;
	border: 1px solid #dfdfdf;
	border-radius: 10px 10px;
	background-color: #ffffff;
	width: 3vw;
	text-align: center;
	cursor: pointer;
}

.checkLabel input[type="radio"]:checked+span {
	background-color: #F7F4EA;
}

.sendBtn {
	display: flex;
	flex-direction: row;
	justify-content: center;
	margin-top: 1%
}

#myTextarea {
	width: 24.6vw;
	resize: none;
	border-color: gray;
}

#myTextarea:focus {
	border-color: gray;
}

.rejText {
	display: none;
}

#in_file, #place_file {
	display: none;
}
</style>

<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp%>/js/jquery-ui.js"></script>
<script type="text/javascript">

   $(function()
   {
      $(".check").change(function() 
      {
          if ($(this).is(":checked")) 
          {
             if ($(this).val() == "true")
                $(".rejText").css("display", "flex");
             else
                $(".rejText").css("display", "none");
          }
      });
      
      $(".sendResult").click(function()
      {
         if ($(".check:checked").length == 0)
         {
            alert("승인 또는 반려를 선택해주세요.");
            return;
         }
         else if($(".check:checked").val() == "true")
         {
            if($("#myTextarea").val().trim() == "" || $("#myTextarea").val().trim() == null)
            {
               alert("반려사유를 입력해주셔야 합니다.");
               return;
            }
               
         }
         
         $("#adminForm").attr("inapplysend.action");
         $("#adminForm").submit();
      });
      
     /*  $(".download").click(function()
      {
         $path = $(this).val();
         alert($path);
         $saveFileName = $(this).attr("id");
         alert($saveFileName)
         
         $.ajax(
         {
            url : "filedownload.action",
            type : "POST",
            data :
            {
               "path" : $path,
               "saveFileName" : $saveFileName
            },
            dataType: "text",
            success : function(res)
            {
               window.location ="filedownload.action";
                  alert('다운 성공');
            },
            error : function(e)
            {
               alert(e.responseText);
            }
         });
      }); */
      
      
      /*$(".download").click(function()
		{
			var load = $(this).val();
			alert(load);
			var saveFileName = $(this).attr("id");
			alert(saveFileName)
			
			$("#adminForm").submit(function()
			{
				
				/*
				$(this).ajaxSubmit({ 
					url: "filedownload.action" 
						, contentType: "application/x-www-form-urlencoded;charset=UTF-8" 
						, xhr: function () { 
								let xhr = new XMLHttpRequest(); 
								xhr.onreadystatechange = function () { 
				                    //response 데이터를 바이너리로 처리한다. 세팅하지 않으면 default가 text 
									xhr.responseType = "blob"; 
								}; 
								return xhr; 
							} 
						, type: "post"
						, data :
							{
								"path" : $path,
								"saveFileName" : $saveFileName
							}
						, success: function (data, message, xhr) { 
							hideAjaxImage(); 
							if (xhr.readyState == 4 && xhr.status == 200) {
								// 성공했을때만 파일 다운로드 처리하고
								let disposition = xhr.getResponseHeader('Content-Disposition'); 
								let filename; 
								if (disposition && disposition.indexOf('attachment') !== -1) { 
									let filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/; 
									let matches = filenameRegex.exec(disposition); 
									if (matches != null && matches[1]) 
										filename = matches[1].replace(/['"]/g, ''); 
								} 
								let blob = new Blob([data]); 
								let link = document.createElement('a'); 
								link.href = window.URL.createObjectURL(blob); 
								link.download = filename; 
								link.click(); 
							}else{   
							    //실패했을때는 alert 메시지 출력
								alertPopup("다운로드에 실패하였습니다."); 
							} 
						} );
					*/
					
					/* $.ajax({
						url: 'filedownload.action',
		                method: 'POST',
		                loadingTarget: true,
		                data: {
		                	"load" : load,
							"saveFileName" : saveFileName
		                	},
		                dataType: 'json',
		                success: function(data) {

		                  if (data.header.success) {

		                      if(!data.body)

		                          return;

		                      var path = 'path=' + data.body;

		                      location.href = 'filedownload.action?' +  encodeURI(path , "UTF-8");

		                    }

		                }

		            }); 
			});*/
			
	/* document.getElementById('downloadButton').addEventListener('click', function() */
	$(".download").click(function()
	{
		var path = $(this).val();
		var fileName = $(this).attr("id");
		
		alert(path);
		alert(fileName);
		/* Download.jsp?saveFileName=" + req.getFilesystemName("uploadFile") */
				
		$.ajax(
		{
			url: 'filedownload.jsp',
               method: 'POST',
               data: 
               {
	               	"path" : path,
					"fileName" : fileName
               	},
               dataType: "text",
               success: function(data) 
               {
					if (data == "1")
						alert("다운로드 성공");
					else
						alert("다운로드 실패");

               },
			error : function(e)
			{
					alert(e.responseText);
			}

         }); 
				
		/* var path = $(this).val();
		alert(path);
		var saveFileName = $(this).attr("id");
		alert(saveFileName);
	    
	    // 경로와 파일명을 URL 인코딩
	    var encodedPath = encodeURIComponent(path);
	    var encodedFileName = encodeURIComponent(saveFileName);
	    
	    // 다운로드 링크 생성
	    var link = document.createElement('a');
	    link.href = encodedPath;
	    link.download = encodedFileName;
	    link.target = '_blank'; // 다운로드 창이 새 창에서 열리도록 설정 (선택사항)
	    
	    // 링크 클릭 및 제거
	    document.body.appendChild(link);
	    link.click();
	    document.body.removeChild(link); */
    });
});           
</script>


</head>
<body>
<form id="adminForm" method="post">
	<div class="bframe">
		<form action="storeregiinsert.action" method="post" id="userForm" enctype="multipart/form-data">
			<!-- header -->
			<div>
				<c:import url="header_admin.jsp"></c:import>
			</div>

			<div class="middle">
				<div class="top">
					<h1>사업자 등록 인증 요청서</h1>
					<input type="hidden" name="in_apply_num" value="${in_apply_num}">
					<hr>
				</div>

				<!-- 입력 부분 -->
				<div class="st_info_insert">

					<!-- 사업자 등록 번호 -->
					<div class="igroup">
						<div class="title">사업자 등록 번호</div>
						<div class="input_group">
							<p class="inputform" id="st_in_num1">${in.st_in_num }</p>
							-
							<p class="inputform" id="st_in_num2">${in.st_in_num }</p>
							-
							<p class="inputform" id="st_in_num3">${in.st_in_num }</p>
						</div>
					</div>

					<!-- 서류등록 -->
					<div class="igroup">
						<div class="title">사업자 서류</div>
						<div class="input_group" id="fileregi1">
							<p class="inputform">
								<button type="button" class="download" value="${in.st_in_file }"
									id="${in.in_file_name }"></button>
								<label for="${in.in_file_name }">${in.in_file_name }</label>
							</p>
						</div>
					</div>

					<!-- 사업장 관리 번호 -->
					<div class="igroup">
						<div class="title">사업장 관리 번호</div>
						<div class="input_group">
							<p class="inputform" id="st_place_num">${in.st_place_num }</p>
						</div>
					</div>

					<!-- 사업장 서류 등록 -->
					<div class="igroup">
						<div class="title">사업장 서류</div>
						<div class="input_group">
							<p class="inputform" id="st_place_num">
								<button type="button" class="download"
									value="${in.st_place_file }" id="${in.place_file_name }"></button>
								<label for="${in.place_file_name }">${in.place_file_name }</label>
							</p>
						</div>
					</div>

					<!-- 가게 명 -->
					<div class="igroup">
						<div class="title">가게 명</div>
						<div class="input_group">
							<p class="inputform" id="st_place_num">${in.st_name }</p>
						</div>
					</div>

					<!-- 가게 주소 -->
					<div class="igroup">
						<div class="title">가게 주소</div>
						<div class="input_group">
							<p class="inputform" id="st_place_num">${in.st_location }</p>
						</div>
					</div>

					<!-- 가게 상세 주소 -->
					<div class="igroup">
						<div class="title">가게 상세 주소</div>
						<div class="input_group">
							<p class="inputform" id="st_place_num">${in.st_location_dt }</p>
						</div>
					</div>

					<!-- 가게 번호 -->
					<div class="igroup">
						<div class="title">가게 번호</div>
						<div class="input_group">
							<p class="inputform" id="st_place_num">${in.st_tel }</p>
						</div>
					</div>
					
					
					<c:if test="${param.state eq '미확인' }">
						<div class="checkLabel">
							<label class="label">
							<input type="radio" class="check" name="res" id="approve"><span>승인</span></label>
							<label class="label"><input type="radio" class="check" name="res" id="reject" value="true"><span>반려</span></label>
						</div>
	
						<!-- 가게 주소 -->
						<div class="igroup rejText">
							<div class="title">반려 사유</div>
							<div class="rejReason">
								<textarea id="myTextarea" name="memo" rows="4" cols="50" placeholder="반려사유를 입력해주세요."></textarea>
							</div>
						</div>
						
						<br><br>
						<div class="sendBtn">
							<input type="button" class="sendResult" value="처리 하기">
						</div>
						
					</c:if>
					<c:if test="${param.state ne '미확인' }">
						<br><br>
						<div class="igroup" style="width: 100%; color: red; font-size: 20pt; text-align: center; width: 570px; margin: 0 auto;">
							처리가 완료된 사업자 등록 인증 요청서 입니다.
						</div>
					</c:if>
				</div>
				<!-- st_info_insert -->
			</div>



		</form>
		<!-- footer -->
		<div><c:import url="/WEB-INF/view/footer.jsp"></c:import></div>
	</div>
</form>
</body>
</html>