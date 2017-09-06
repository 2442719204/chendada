window.onload = function(){
	document.querySelector('#img').onclick=function(){
		//codeServlet中采用的是图片缓存，不使用一个新的id，内存会判断是同一个，不会更新图片。
		this.src="/EshopProject/code.do?id="+Math.random();
		
	}
	document.querySelector("#check_user").onclick = function() {
		var uname = document.querySelector('#uname').value;
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET", "checkname.do?uname="+uname, true);
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = function() {
			if(xmlhttp.readyState==4){
				if(xmlhttp.status==200){
					var data = xmlhttp.responseText;
					var sp = document.querySelector("#sp");
					if(data=="1"){
						sp.innerHTML = '帐号已被注册';
						sp.style.color='red';
					}else{
						sp.innerHTML='恭喜，可以使用';
						sp.style.color='green';
					}
				}
			}
		}
	}
	document.querySelector('#check_email').onclick = function() {
		var email = document.querySelector('#uemail').value;
		//对象
		var xmlhttp = new XMLHttpRequest();
		//创建一个http请求
		xmlhttp.open("POST","checkmail.do",true);
		//发送请求
		var data = "uemail="+email+"&name="+"abc";
		xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send(data);
		xmlhttp.onreadystatechange = function() {
			//数据传输完毕
			if(xmlhttp.readyState==4){
				//成功在客户端接收数据
				if(xmlhttp.status==200){
					
					var data = xmlhttp.responseText;
					var sp = document.querySelector("#spemail");
					if(data=="1"){
						sp.innerHTML = '邮箱已被注册';
						sp.style.color='red';
					}else{
						sp.innerHTML='恭喜，可以使用';
						sp.style.color='green';
					}
				}
			}
		}
	}
}



