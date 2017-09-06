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
		
		$('#uname').on('input',function(){
			$.post('/EshopProject/checkname.do',{'uname':$(this).val()},
					function(data){
				
				if(data=="1"){
					$('span:first').html('已被注册');
				}
				if(data=="0"){
					$('span:first').html('恭喜，可以使用')
				}
			}) 
			/*  $.ajax({
				data:{'uname':$(this).val()},
				//dataType:'text',
				type:'GET',
				url:'/EshopProject/checkname.do',
				success:function(data,b,c,d){//1参代表服务器返回的数据，2.jquery ajax状态值，3.XMLHttpRequest对象
					if(data=="1"){
						$('span:first').html('已被注册');
					}
					if(data=="0"){
						$('span:first').html('恭喜，可以使用')
					}
				}
			}) */
		})
	})
</script>

</head>
<body>
	<input type="text" id="uname"><span></span>
	<input type="text" id="uemail"><span></span>
</body>
</html>