<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${user==null }">
	<jsp:forward page="login.jsp">
		<jsp:param value="请登录后再结账" name="msg"/>
	</jsp:forward>
</c:if>