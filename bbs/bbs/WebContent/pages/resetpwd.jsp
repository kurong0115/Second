<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ include file="header.jsp"%>
<%@ include file="../adminPages/links.jsp"%>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">

	function sendcode(){
		if( $("#email").val() != null ){				
			$.ajax({
				url:"/bbs/bbsUser?flag=sendcode",
				method:"post",
				dataType:"text",
				data:{"email":$("#email").val()},
				async:true,
				success:function(data){
					if(data!=null){
						alert("发送成功");
					}		
				},
				error:function(){
					alert("服务器异常！！！");
				}
			})
			}else{
				alert("请输入您的邮箱地址！！");
			}
	}


function checkUserInfo() {
		if (document.getElementById("samePwd").innerHTML == "密码不一致") {
			alert("密码不一致");
			return false;
		}
	}
	
	function isSamePwd() {
		if(document.pwdForm.upass.value!=document.pwdForm.upass1.value){
			document.getElementById("samePwd").innerHTML="密码不一致";
		}
		if(document.pwdForm.upass.value==document.pwdForm.upass1.value){
			document.getElementById("samePwd").innerHTML="";
		}
	}
</script>

<DIV>
	&gt;&gt;<B><a href="<%=request.getContextPath() %>/index.jsp">论坛首页</a></B>
</DIV>

<div class="t" id="pwdchange" style="MARGIN-TOP: 15px">
	<div class="container" style="margin-left: 40%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<FORM onSubmit="return checkUserInfo()" name="pwdForm"
					action="<%=request.getContextPath()%>/bbsUser" method="post">
					<input type="hidden" name="flag" value="resetpwd"> 
					<span>&nbsp;用&nbsp;户&nbsp;名：</span>
					<input type="text" name="uname" id="uname" class="input"></br> 
					<span>&nbsp;新&nbsp;密&nbsp;码：</span>
					<input type="password" name="upass" class="input"></br> 
					<span>重复密码：</span>
					<input type="password" name="upass1" onblur="isSamePwd()" class="input"><font style="color: red;" id="samePwd"></font></br>
					<span>&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;箱：&nbsp;&nbsp;</span>
					<input type="email" name="email" id="email" class="input">
					<a href="javascript:void(0)" onclick="sendcode()">获取验证码</a></br>
					<span>&nbsp;验&nbsp;证&nbsp;码：&nbsp;&nbsp;</span><input type="text" name="mycode" class="input"></br>
					
					<font style="color: red;">${msg}</font>
					<INPUT style="width: 200px;height: 25px;" class="easyui-linkbutton" tabIndex="4" type="submit" value="重置">
				</FORM>
			</div>
		</div>
	</div>
</div>
<%@ include file="bottom.jsp"%>
