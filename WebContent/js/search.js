$(function() {
	/*document.querySelector('#search').oninput = function() {
		alert("aa")
	}*/
	$('#search').on('input',function(){
		var xmlhttp = new XMLHttpRequest();
		var val = document.querySelector("#search").value;
		var url = "search.do?key="+val;
		xmlhttp.open("GET", url, true);
		xmlhttp.setRequestHeader("Content-Type", "text/html;charset=UTF-8")
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = function() {
			if(xmlhttp.readyState==4&&xmlhttp.status==200){
				var data = xmlhttp.responseText;
				var json = JSON.parse(data);
				var options = "";
				console.log(json);
				for(var i=0;i<json.length;i++){
					options += "<option value='"+json[i].key+"'/>";
					console.log(options);
				}
				var ds = document.querySelector("#ds");
				ds.innerHTML = options;
			}
		}
	});
})