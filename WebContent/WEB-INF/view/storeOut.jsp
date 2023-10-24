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
	display: flex;
    align-items: center;
    justify-content: space-between;
    width: 33vw;
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
    height: 3vw;
}
.cfb
{
	height: 2.7vw;
}

.igroup
{
	margin-left: 15vw;
	margin-top: 5vh;
	width: 55vw;
	display: flex;
}

.igroup-select
{
	margin-left: 15vw;
    margin-top: 5vh;
    width: 55vw;
    display: flex;
    align-items: center;
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
    height: 2.7vw;
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

</style>

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp %>/js/jquery-ui.js"></script>
<script type="text/javascript">
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
/*
function st_in_numcheck() 
{
	var st_in_num = document.getElementById("st_in_num").value;
	var check_num = document.getElementById("check_num").value;
	
	if (st_in_num == check_num) 
	{
		alert("인증 성공");
	}
	else
	{
		// Reset the text input
		document.getElementById("st_in_num").value = "";

		// Display the error
		document.getElementById("err1").style.display = "inline";
		return;
	}	
}
*/

$(function()
{
	$("#st_in_num").click(function()
	{
		if($("#st_list option:selected").attr('id')=="")
		{
			alert("폐업할 사업장을 선택해주세요!");
			$("#st_list").focus();
			return;
		}
	});
	
	$("#submitBtn").click(function()
	{
		// 사용자, 가게 번호
		st_num = $("#st_list option:selected").attr('id');
		alert(st_num);
		user_num = $("#user_num").val();
		alert(user_num);
		
		// 사용자가 입력한 사업장등록번호
		st_in_num = $("#st_in_num").val();
		alert(st_in_num);
		
		$.ajax(
		{
			url : "storeoutcheck.action",
			type : "POST",
			data : { "user_num" : user_num
					, "st_num" : st_num
					, "st_in_num" : st_in_num },
			dataType : "text",
			success : function(data)
			{
				if (data=="1")
				{
					alert("인증되었습니다.");
					$("#err1").text("인증되었습니다.").css("display", "inline");
					$("#check_num").val("true");
				} else
				{
					$("#err1").text("잘못된 사업자 등록 번호 입니다.").css("display", "inline");
					$("#check_num").val("false");
				}
			},
			error : function(e)
			{
				alert(e.responseText);
			}
		});
	});
});


$(function()
{
    $("#insert").click(function() 
    {
        $("#err1").css("display", "none");
		
        if ($("#st_in_num").val() == "") 
        {
            $("#err1").text("사업장 관리번호를 입력해주세요.").css("display", "inline");
            return;
        }
        
        if ($("#check_num").val() != "true")
        {
        	$("#err1").text("사업장 관리번호가 올바르지 않습니다.").css("display", "inline");
            return;
        }
        
        if ($("#oContent").val() == "")
        {
        	alert("폐업사유를 입력해주세요.");
        	$("#oContent").focus();
         	return;
        }
        
        st_num = $("#st_list option:selected").attr('id');
        $("#st_num").val(st_num);
        
        // 폼 submit 액션 처리 수행
        $("#userForm").attr("action", "storeOutinsert.action");
        $("#userForm").submit();
    });
});
</script>
</head>
<body>
<div class="mainFrame">
	
	<form action="" method="post" id="userForm">
	
		<div><c:import url="header_user.jsp"></c:import></div> <!-- header -->
		
		<div class="center">
			<div class="head">
				<h1>사업장 폐업 신청</h1>
				<hr />
			</div>
			
			<!-- 폐업 신청 -->
			<div class="out_insert">
			
				<!-- 폐업할 사업장 선택 -->
				<div class="igroup-select">
					<div class="title">
						사업장 선택
					</div>
					<div>
						<select name="st_list" id="st_list">
							<option id="" selected="selected">----폐업 신청 진행 가게 선택----</option>
							<c:forEach var="st" items="${st_list }">
								<option id="${st.st_num }">${st.st_name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<!-- 사업장 번호 확인 -->
				<div class="igroup-select">
					<div class="title">
						사업장 번호 확인
					</div>
					<div class="input_group">
					    <input type="text" id="st_in_num" name="st_in_num">
					    <input type="hidden" class="check_num" id="check_num" value="false"/>
						<button type="button" class="cfb" id="submitBtn">인증하기</button>
					</div>
					<i id="err1" style="color: red; display: none; font-size: 10pt;" >잘못된 사업자 등록 번호 입니다.</i>
				</div>
				
				
				<!-- 폐업 사유 -->
				<div class="igroup">
					<div class="title">폐업사유</div>
					<div class="content">
						<textarea rows="" cols="" class="contentform" id="oContent"
						placeholder="폐업 사유를 입력해주세요"></textarea>
					</div>
				</div> <!-- .out_content -->
				
				
				<div class="button">
					<button type="button" id="insert">신청</button>
					<button type="reset">목록으로</button><br>
				</div>
				
				
				<!-- user_num -->
				<input type="hidden" name="user_num" id="user_num" value="${user_num }">
				<input type="hidden" name="st_num" id="st_num">
			</div> <!-- .out_insert -->
			
		
		</div> <!-- .center -->
		
		<!-- footer -->
		<div><c:import url="footer.jsp"></c:import></div>
	
	</form> <!-- #outform -->
</div>	<!-- class="mainFrame" -->
</body>
</html>