<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
     <%@ taglib prefix="yc" uri="http://www.hyycinfo.com" %>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<TITLE>论坛--帖子列表</TITLE>

<Link rel="stylesheet" type="text/css" href="style/style.css" />
</head>
<body>
<DIV>
	<IMG src="image/logo.gif">
</DIV>
<!--      用户信息、登录、注册        -->

	<DIV id="logindiv" class="h">
</DIV>


<!--      主体        -->
<DIV>
<!--      导航        -->
<br/>
<DIV>
	&gt;&gt;<B><a href="index.html">论坛首页</a></B>&gt;&gt;
	<B><a href="topic.action?op=find&boardid=${toviewboard.boardid }">${toviewboard.boardname }</a></B>
</DIV>
<br/>
<!--      新帖        -->
	<DIV>
		<A href="topic.action?op=newtopic"><IMG src="image/post.gif" name="td_post" border="0" id=td_post></A>
	</DIV>
<!--         翻 页         -->
	<DIV>
		<yc:pagination url="topic.action?op=find&boardid=${param.boardid }" pagemodel="${pagemodel }" ></yc:pagination>
	</DIV>

	<DIV class="t">
		<TABLE cellSpacing="0" cellPadding="0" width="100%">		
			<TR>
				<TH class="h" style="WIDTH: 100%" colSpan="4"><SPAN>&nbsp;</SPAN></TH>
			</TR>
<!--       表 头           -->
			<TR class="tr2">
				<TD>&nbsp;</TD>
				<TD style="WIDTH: 80%" align="center">文章</TD>
				<TD style="WIDTH: 10%" align="center">作者</TD>
				<TD style="WIDTH: 10%" align="center">回复</TD>
			</TR>
<!--         主 题 列 表        -->
			
			
			
			
		<c:if test="${listtopic!=null&&listtopic.size()>0 }">	
		<c:forEach items="${listtopic }" var="item">
			<TR class="tr3">
				<TD><IMG src="image/topic.gif" border=0></TD>
				<TD style="FONT-SIZE: 15px">
					<A href="topic.action?op=detail&topicid=${item.topicid }&boardid=${toviewboard.boardid }">${item.title }</A>
				</TD>
				<TD align="center">${item.uname }</TD>
				<TD align="center">${item.total }</TD>
			</TR>
		</c:forEach>	
        </c:if>
        
        <c:if test="${listtopic==null|| listtopic.size()<=0 }">
        	<TR class="tr3">
				<TD colspan="4">此版块下暂无新贴</TD>
			</TR>
        </c:if>    
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
	
			
		</TABLE>
	</DIV>
<!--            翻 页          -->
	<DIV>
		<yc:pagination url="topic.action?op=find&boardid=${param.boardid }" pagemodel="${pagemodel }"></yc:pagination>
	</DIV>
</DIV>
<!--             声 明          -->
<BR/>
<CENTER class="gray">源辰信息</CENTER>
</body>
</html>