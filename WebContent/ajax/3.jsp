<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('input[type=button]').click(function(){
			console.log($('form').serialize());
			console.log($('form').serializeArray())
		
		$.ajax({
				url:'/EshopProject/search.do',
				type:'post',
				dataType:'json',
				data:$('form').serializeArray(),
				success:function(data){
					
				}
			})
		})
	})
</script>
</head>
<body>
<form action="">
	<input type="text" id="uname" name ="uname">
	<input type="text" id="upassword" name ="upassword">
	<input type="text" id="utel" name ="utel">
	<input type="text" id="uaddress" name ="uaddress">
	<input type="button" value="测试序列化">
</form>

</body>
</html>