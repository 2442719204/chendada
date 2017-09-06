
$(function() {
	$('#dd').dialog({    
	    title: '登录',    
	    width: 400,    
	    height: 200,
	    collapsible:true,
	    resizable:true,
	    buttons:[{
			text:'保存',
			iconCls:'icon-ok',
			handler:function(){
				var flag = $('form').form('validate');
				if(flag==true){
					$.ajax({
						type:'post',
						data:$('form').serialize(),
						url:'/EshopProject/logincontroller.do',
						success:function(data){
							alert(data)
							if(data=="1"){
								window.location='index.html';
							}else{
								$.messager.alert('我的消息','这是一个提示信息！','info');


							}
						}
					})
				}else{
					$.messager.alert('我的消息','ee！','info');

				}
			}
		},{
			text:'关闭',
			iconCls:'icon-remove',
			handler:function(){alert('cancel')}
		}]
	}); 
})
