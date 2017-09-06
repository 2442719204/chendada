<%@page import="org.lanqiao.service.impl.CategoryServiceImpl"%>
<%@page import="org.lanqiao.service.CategoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/search.js"></script>
</head>
<body>
<%@ include file="cookiedata.jsp"  %>
<!-- Logo -->
<div id="divhead">
  <table cellspacing="0" class="headtable">
    <tr>
      <td><a href="${pageContext.request.contextPath }/index.do"><img src="${pageContext.request.contextPath }/images/logo.gif" width="95" height="30" border="0" /></a></td>
      <td style="text-align:right"><img src="${pageContext.request.contextPath }/images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;<a href="${pageContext.request.contextPath }/dispacher.do?type=cart">购物车<font color="red"><c:if test="${cart==null }">0</c:if><c:if test="${cart!=null }">${fn:length(cart) }</c:if></font></a>　|　<a href="#">帮助中心</a>　|　<a href="${pageContext.request.contextPath }/dispacher.do?type=loginsuccess">我的帐户</a>　|　<a href="${pageContext.request.contextPath }/dispacher.do?type=regedit">新用户注册</a></td>
    </tr>
  </table>
</div>
<%
	CategoryService cs = new CategoryServiceImpl();
	request.setAttribute("cates", cs.categoryList());
%>
<!-- Logo end -->
<!-- menu -->
<div id="divmenu">
	<c:forEach items="${cates }" var="cate">
		<a href="${pageContext.request.contextPath }/list.do?type=goods&cid=${cate.cid}">${cate.cname }</a>　　
	</c:forEach>
</div>
<!-- menu end -->

<!-- search -->
<div id="divsearch">
<table width="100%" border="0" cellspacing="0">
  <tr>
    <td style="text-align:right; padding-right:220px">Search
  <input type="text" name="textfield" class="inputtable" id="search" list="ds"/>
  <datalist id="ds"></datalist>
<!--<input name="searchbutton" type="image" src="images/serchbutton.gif" style=" margin-bottom:-4px"/>-->
<a href="search.html"><img src="${pageContext.request.contextPath }/images/serchbutton.gif" border="0" style="margin-bottom:-4px"/></a></td>
  </tr>
</table>

</div>
<!-- search end -->
</body>
</html>