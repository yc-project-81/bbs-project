<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
     <%@ taglib prefix="yc" uri="http://www.hyycinfo.com" %>      
    
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<TITLE>论坛--看贴</TITLE>
<Link rel="stylesheet" type="text/css" href="style/style.css" />


<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</HEAD>

<BODY>
<DIV>
	<IMG src="image/logo.gif">
</DIV>

<!--      用户信息、登录、注册        -->

	<DIV id="logindiv" class="h">
	</DIV>

<!--      主体        -->
<DIV><br/>
	<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.html">论坛首页</a></B>&gt;&gt;
	<B><a href="topic.action?op=find&boardid=${toviewboard.boardid }">${toviewboard.boardname }</a></B>
</DIV>
	<br/>
	<!--      回复、新帖        -->
	<DIV>
		<A href="userback/reply.action?op=newreply&topicid=${requesttopicwithreplylist.topicid }"><IMG src="image/reply.gif"  border="0" id=td_post></A> 
		<A href="topic.action?op=newtopic"><IMG src="image/post.gif"   border="0" id=td_post></A>
	</DIV>
	<!--         翻 页         -->
	<DIV>
		<yc:pagination url="topic.action?op=detail&topicid=${requesttopicwithreplylist.topicid }&boardid=${toviewboard.boardid }" pagemodel="${pagemodel }" ></yc:pagination>
	</DIV>
	<!--      本页主题的标题        -->
	<DIV>
		<TABLE cellSpacing="0" cellPadding="0" width="100%">
			<TR>
				<TH class="h">本页主题: ${requesttopicwithreplylist.title }</TH>
			</TR>
			<TR class="tr2">
				<TD>&nbsp;</TD>
			</TR>
		</TABLE>
	</DIV>
	
	<!--      主题        -->
	
	<DIV class="t">
	    主题信息如下: <br />
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B>${requesttopicwithreplylist.uname }</B><BR/>
					<image src="image/head/${requesttopicwithreplylist.head }"/><BR/>
					注册:${requesttopicwithreplylist.regtime }<BR/>
				</TH>
				<TH>
					<H4>${requesttopicwithreplylist.title }</H4>
					<DIV>${requesttopicwithreplylist.content }</DIV>
					<DIV class="tipad gray">
						发表：[${requesttopicwithreplylist.publishtime }] &nbsp;
						最后修改:[${requesttopicwithreplylist.modifytime }]
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	
	<!--      回复        -->
	 当前主题下所有的回复:<br />
	 
	 <c:if test="${requesttopicwithreplylist.listReply.size()<=0 }">
	 	<DIV class="t">
	 		本主题暂无回贴，抢沙发...
	 	</DIV>
	 </c:if>
	 
	 
<c:forEach items="${requesttopicwithreplylist.listReply }" var="item">
	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B>${item.uname }</B><BR/><BR/>
					<image src="image/head/${item.head }"/><BR/>
					注册:${item.regtime }<BR/>
				</TH>
				<TH>
					<H4>${item.title }</H4>
					<DIV>${item.content }</DIV>
					<DIV class="tipad gray">
						发表：[${item.publishtime }] &nbsp;
						最后修改:[${item.modifytime }]
						<A href="">[删除]</A>
						<A href="">[修改]</A>
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	
	
	</c:forEach>
	
	
	
</DIV>

<!--      声明        -->
<BR>
<CENTER class="gray">源辰信息</CENTER>
</BODY>
</HTML>

