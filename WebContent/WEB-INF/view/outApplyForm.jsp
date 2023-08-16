<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<% 
	String userNum = request.getParameter("user_num");
	String stNum = request.getParameter("st_num");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 폐업</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
.mainFrame
{
	display: flex;
	flex-direction: column;
}


.out_insert
{
	width: 70vw;
	margin-left: 15vw;
	padding-top: 10vh;
	padding-bottom: 10vh;
	border: 5px solid #F7F4EA;
	border-radius: 40px;
}
.head
{
	width: 70vw;
	margin-left: 15vw;
	text-align: center;
}

.input_group
{
	/* border: 1px solid gray; */
	border-radius: 5px;
	display: flex;
}

.inputform
{
	width: 30vw;
	border-radius: 0 10px 10px 0;
	border: 1px solid #ccc;
	padding: 8px 12px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.title
{
	background-color: #F7F4EA;
	width: 8vw;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 10px 0 0 10px;
	margin-right: 1vw;
}
.cfb
{
	height: 3.3vh;
}

.igroup
{
	margin-left: 15vw;
	margin-top: 5vh;
	width: 55vw;
	display: flex;
}


.button
{
	margin-top: 5vh;
	margin-bottom: 3vh;
	text-align: center;
}


button
{
	display: inline-block;
	outline: none;
	cursor: pointer;
	font-weight: 500;
	border-radius: 3px;
	padding: 0 16px;
	border-radius: 4px;
	background: #F7F4EA;
	line-height: 1.15;
	font-size: 14px;
	height: 36px;
	word-spacing: 0px;
	letter-spacing: .0892857143em;
	text-decoration: none;
	text-transform: uppercase;
	min-width: 64px;
	border: none;
	text-align: center;
	box-shadow: 0px 3px 1px -2px rgb(0 0 0 / 20%), 0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%);
	transition: box-shadow 280ms cubic-bezier(0.4, 0, 0.2, 1);
	:hover 
	{
	    background: rgb(98, 0, 238);
	    box-shadow: 0px 2px 4px -1px rgb(0 0 0 / 20%), 0px 4px 5px 0px rgb(0 0 0 / 14%), 0px 1px 10px 0px rgb(0 0 0 / 12%);
	}
                
}

.input_group 
{
    display: flex;
    align-items: center;
}

.file
{
	position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}

.contentform
{
	width: 30vw;
	height: 15vh;
	border-radius: 0 10px 10px 0;
	border: 1px solid #ccc;
	padding: 8px 12px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

#st_in_num
{
	width: 26vw;
	border-radius: 0 10px 10px 0;
	border: 1px solid #ccc;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.upload-name
{
	width: 14.3vw;
	border-radius: 0 10px 10px 0;
	border: 1px solid #ccc;
	padding: 8px 12px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
#upload-name01
{
	border-radius: 0;
}
#upload-name02
{
	border-radius: 0;
}

#insert
{
	margin-left: 2vw;
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

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp %>/js/jquery-ui.js"></script>
<script type="text/javascript">
	
	$(function()
	{
		function displayAndSetFileName() 
		{
		    var fileInput = document.getElementById("file");
		    var uploadNameInput = document.getElementById("upload-name");
		
		    if (fileInput.files.length > 0) {
		        var uploadedFileName = fileInput.files[0].name;
		        uploadNameInput.value = uploadedFileName;
		    }
		    else {
		    	uploadNameInput.value = null;
		    }
		}
		
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
		
		$(".sendBtn").click(function()
		{
			//alert("확인");
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
			
			
		});
	});
	
	
	
   
</script>
</head>
<body>
<div class="mainFrame">
	
	<form action="storeOutinsert.action" method="post" id="outForm">
	
	<div><c:import url="header_user.jsp"></c:import></div> <!-- header -->
	
	<div class="center">
		<div class="head">
			<h1>사업장 폐업 신청 요청서</h1>
			<hr />
		</div> <!-- .head -->
		
		<!-- 폐업 신청 -->
		<div class="out_insert">
			
			<!-- 사업장 번호 확인 -->
			<div class="igroup">
				<div class="title">
					사업장 번호
				</div>
				<div class="input_group">
				    <input type="text" id="st_in_num" name="st_in_num" value="${out.st_place_num }" disabled="disabled">
				    <%-- <input type="hidden" class="check_num" id="check_num" value="${placeNum }"/> --%>
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
				<div class="igroup" style="width: 100%; color: red; font-size: 20pt; text-align: center; width: 450px; margin: 0 auto;">
					처리가 완료된 사업장 폐업 요청서 입니다.
				</div>
			</c:if>
			
			
			<!-- user_num -->
			<input type="hidden" name="user_num" value="<%= userNum %>">
			<input type="hidden" name="st_num" value="<%= stNum %>">

		</div> <!-- .out_insert -->
		
	
	</div> <!-- .center -->
	
	<!-- footer -->
	<div><c:import url="footer.jsp"></c:import></div>
	
	</form> <!-- #outform -->
</div>	<!-- class="mainFrame" -->
</body>
</html>