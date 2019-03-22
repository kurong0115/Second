<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">

<base href="<%=basePath%>">
<TITLE>论坛</TITLE>
<Link rel="stylesheet" type="text/css" href="style/style.css" />

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
   
</HEAD>
<BODY>
	<DIV>
	<IMG src="<%=request.getContextPath() %>/image/logo.gif">
</DIV>
	<!--      用户信息、登录、注册        -->
	
	<%
		
		if(session.getAttribute("user")!=null ){
	%>
	<!--  -->
			<DIV class="h" id="loginresult" >
				欢迎您: ${user.uname} | 
				<a href="<%=request.getContextPath() %>/pages/personal.jsp">个人中心</a> | 
				<a href="<%=request.getContextPath() %>/collect?flag=myCollect&uid=${user.uid}">&nbsp;&nbsp;&nbsp;我的收藏（${collectTotal}）</a> | 
				<a href="<%=request.getContextPath() %>/bbsUser?flag=logout"> 退出 </a>
				
				<button>搜索</button>
				
			</DIV>
	<%			
		}else{
	%>
			<DIV class="h" id="loginresult">
			您尚未　<a href="<%=request.getContextPath() %>/pages/login.jsp">登录</a>
			&nbsp;| &nbsp; <A href="<%=request.getContextPath() %>/pages/reg.jsp">注册</A>
			<button>搜索</button>
			</DIV>
	<%
		}
	%>
	
	
	
	
	<BR />