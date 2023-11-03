<%@page import="com.yameokja.mc.RevoApplyViewDTO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	RevoApplyViewDTO dto = (RevoApplyViewDTO)request.getAttribute("revo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패널티 회수 신청서</title>

<style type="text/css">

.header
{
	position: sticky;
	top: 0;
	background-color: white;
}

.st_info_insert
{
	width: 70vw;
	margin-left: 15vw;
	padding-top: 5vh;
	padding-bottom: 5vh;
	border: 5px solid #F7F4EA;
	border-radius: 40px;
}

.top
{
	width: 70vw;
	margin-left: 15vw;
	text-align: center;
}

.igroup
{
	margin-left: 10vw;
	margin-top: 2vh;
	width: 55vw;
	display: flex;
}
.igroup2
{
	margin-top: 0vh;
	margin-left: 10vw;
	width: 55vw;
	display: flex;
}

.input
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
	height: 33px;
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

.errorMsg
{
	color: #ef6351;
	font-size: small;
	display: none;
}
.input_group {
    display: flex;
    align-items: center;
}
.title
{
	background-color: #F7F4EA;
	width: auto;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 10px 0 0 10px;
	margin-right: 1vw;
	/* border: 1px solid; */
	height: 3vh;
	padding: 0.5vh 0.5vh 0.5vh 0.5vh
}
.title1
{
   width: 7vw;
   padding: 1vh;
   background-color: #F7F4EA;
}
.tcontent1
{
   padding: 1vh;
}
.more
{
   display: flex;
   flex-direction: column;
}
.lbox
{
   display: flex;
   border: 1px solid  #F7F4EA;
}
.repot_date_list
{
   width: 72.5%;
   display: flex;
   justify-content: space-between;
   margin-left: 10vw;
}

.sendBtn
{
	display: flex;
    flex-direction: row;
    justify-content: center;
    margin-top: 1%;
}

.checkLabel
{
	display: flex;
    flex-direction: row;
    justify-content: center;
    padding-left: 40vw;
    margin-top: 1% 
}

 
input[type="radio"] 
{
  display: none;
}

.label input[type="radio"] + span 
{
	display: inline-block;
	padding: 5px 10px;
	border: 1px solid #dfdfdf;
	border-radius: 10px 10px;
	background-color: #ffffff;
	width: 3vw;
	text-align: center;
	cursor: pointer;
}


.label input[type="radio"]:checked + span 
{
        background-color:  #F7F4EA;
}

#myTextarea
{
	display: none;
}
#span
{
	padding-top: 10px;
	height: 25px;
}
#penaltyRevokeReport
{
	cursor: not-allowed;
}

</style>

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp %>/js/jquery-ui.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
	
	$(function ()
	{
		$(".check").change(function() 
		{
			if ($(this).is(":checked")) 
			{
				if ($(this).val() == "true")
					$("#myTextarea").css("display", "flex");
				else
					$("#myTextarea").css("display", "none");
			}
		});
		 
		$(".sendResult").click(function()
		{
			//alert("확인");
			
			if ($(".check:checked").length == 0)
			{
				alert("승인 / 반려 중 하나를 선택해주세요.");
				return;
			}
			else if($(".check:checked").val() == "true")
			{
				if($("#myTextarea").val().trim() == "" || $("#myTextarea").val().trim() == null)
				{
					alert("반려사유를 입력해주셔야 합니다.");
					$("#myTextarea").focus();
					return;
				}
				$("#checklabel").val("true");
			}
			
			$("#adminForm").attr("action", "revopro.action");
			$("#adminForm").submit();
			
		});
	});
</script>

</head>
<body>
<form id="adminForm" method="post">
<div class="bframe">
	
	<!-- header -->
	<div class="header"><c:import url="/WEB-INF/view/header_admin.jsp"></c:import></div>
	
	<div class="middle">
		<div class="top">
			<h1>패널티 회수 신청서</h1>
			<input type="hidden" name="revo_num" id="revo_num" value=<%=dto.getRevo_apply_num() %>/>
			<hr>
		</div>
		
		<!-- 입력 부분 -->
		<div class="st_info_insert">
			
			<div class="repot_date_list">
				<div class="more">
					<div class="lbox">
						<div class="title1">아이디</div>
						<div class="tcontent1"><input type="text" id="reporter" value=<%=dto.getUser_id() %> style="border: 0px; width: 70px;"></div>
					</div>
					<div class="lbox">
						<div class="title1">가게명</div>
						<div class="tcontent1"><input type="text" id="reportDate" value=<%=dto.getSt_name() %> style="border: 0px; width: 70px;"></div>
					</div>
				   
				</div>
		         
				<div class="more">
					<div class="lbox">
						<div class="title1">신청날짜</div>
						<div class="tcontent1"><input type="text" id="reported" value=<%=dto.getReg_date() %> style="border: 0px; width: 70px;"></div>
					</div>
				</div>
			</div>
				
			<div class="st_info_insert2">
				<div class="igroup" >
					<div class="title" style="margin-bottom: 0.5vh;">
						<span>패널티 받은 사유</span>
					</div>
				</div>
				<div class="igroup2" style="width: 100%;">
					<textarea id="penaltyRevokeReport" readonly="readonly" style="width: 72%; height: 5em; 3px; resize: none;">${revo.getReq_rs() }</textarea>
				</div>
			
				<div class="igroup" >
					<div class="title" style="margin-bottom: 0.5vh;">
						<span>패널티회수 요청 사유</span>
					</div>
				</div>
				
				<div class="igroup2" style="width: 100%;">
					<textarea id="penaltyRevokeReport" readonly="readonly" style="width: 72%; height: 5em; 3px; resize: none;">${revo.getRevo_rs() }</textarea>
				</div>
				
				<c:if test="${state ne '처리완료' }">
					<div style="width: 87%; text-align: right; margin-top: 1vh;">
						<label class="label"><input type="radio" class="check" name="res" id="approve"><span id="span" style="font-size: 9pt; padding: 0 auto;">승인</span></label>
						<label class="label"><input type="radio" class="check" name="res" id="reject" value="true"><span id="span" style="font-size: 9pt; padding: 0 auto;">반려</span></label>
					</div>
					
					<br>
					<div class="igroup2" style="width: 100%;">
						<textarea id="myTextarea" style="width: 72%; height: 10em; resize: none;" placeholder="반려사유를 입력해주세요."></textarea>
					</div>
					
					
					<br><br>
					<div class="sendBtn">
						<input type="button" class="sendResult" value="처리 하기">
						<input type="hidden" id="checklabel" name="checklabel">
					</div>
				</c:if>
				
				<c:if test="${state eq '처리완료' }">
					<div class="igroup">
						<div class="lbox">
							<c:if test="${revo.getRevo_rej_state() eq '반려' }">
								<div class="title1">
									${revo.getRevo_rej_state() }
								</div>
								<div class="tcontent1 content">
									${revo.getRevo_rej_rs() }
								</div>
							</c:if>
						</div>
					</div>
					<br><br>
					<div class="igroup" style="width: 100%; color: red; font-size: 20pt; text-align: center; width: 510px; margin: 0 auto;">
						처리가 완료된 패널티 회수 신청서 입니다.
					</div>
				</c:if>
			</div>
		</div>
	</div>
	
	
	<!-- footer -->
	<div><c:import url="/WEB-INF/view/footer.jsp"></c:import></div>
</div>
</form>


</body>
</html>