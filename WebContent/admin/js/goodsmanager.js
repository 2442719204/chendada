$(function(){
	$('#cc').combobox({    
	    url:'/EshopProject/categorycontroller.do',    
	    valueField:'cid',    
	    textField:'cname',
	    onSelect:function(data){
	    	loadGoods(data.cid);
	    },
	    onLoadSuccess:function(){
	    	var data = $(this).combobox('getData');
	    	if(data.length>0){
	    		$(this).combobox('setValue',data[0].cid);
	    		loadGoods(data[0].cid);
	    	}
	    }
	});
	
	
	
	function loadGoods(cid){
		$('#dg').datagrid({    
		    url:'/EshopProject/goodscontroller.do',
		    queryParams:{cid:cid},
		    pagination:true,
		    columns:[[    
		        {field:'chk',checkbox:true},
		        {field:'gid',title:'编号'},
		        {field:'gtitle',title:'标题'},
		        {field:'gauthor',title:'作者'},
		        {field:'gsaleprice',title:'售价'},
		        {field:'ginprice',title:'进价'},
		        {field:'gimg',title:'图片',formatter: function (value, row, index) {  
		        	var str = "";
		        	if(value!=""||value!=null){
		        		str = "<a href='javascript:showImg(\""+value+"\") '><img style=\"height:40px;width:100px;\"  src=\"/EshopProject/images/bookcover/"+value+".jpg\"/></a>";
		        		
		        		return str;
		        	}
                    /*return "<a href='javascript:showImg(\""+value+"\") '>浏览图片</a>";*/
                }    
		        },
		        {field:'gclicks',title:'点击量'},
		        {field:'category',title:'类别',formatter:function(value,data){
		        	return value.cname;
		        }},
		        {field:'publisher',title:'出版社',formatter:function(value,data){
		        	return value.pname;
		        }}
		    ]],
		    toolbar: [{
				iconCls: 'icon-add',
				text:'添加商品',
				handler: function(){
					$('#dialog').dialog('open');
				}
			},'-',{
				iconCls: 'icon-edit',
				text:'修改商品',
				handler: function(){
					var row = $('#dg').datagrid('getSelected');
					var gclicks = ""+row.gclicks+"";
					alert(row.gid)
					if (row != null) {
						$('#gid').textbox('setValue',row.gid);
						$('#gtitle').textbox('setValue',row.gtitle);
						$('#gauthor').textbox('setValue',row.gauthor);
						$('#gsaleprice').textbox('setValue',row.gsaleprice);
						$('#ginprice').textbox('setValue',row.ginprice);
						$('#pid').textbox('setValue',row.publisher.pname);
						$('#gclicks').textbox('setValue',gclicks);
						$('#cid').textbox('setValue',row.category.cname);
						$('#gimg').textbox('setValue',row.gimg);
						$('#gdesc').textbox('setValue',row.gdesc);
						$('#dialog').dialog({
							title : '修改用户',
							closed : false,
							modal : true,
							buttons : [ {
								text : '保存修改',
								handler : function() {
								var valid = $('#f1').form('validate');
								if (valid == true) {
								$.ajax({
									type : 'post',
									url : "/EshopProject/goodscontroller.do?type=modify",
									data : $('#f1').serialize(),
									success : function(data) {
									if (data == '1') {
											$.messager.alert('提示','操作成功！','info');
											$('#f1').form('clear');
											$('#dialog').dialog('close');
											$('#dg').datagrid('reload');
									}
							   }
					});
			} else {

			}
		}
} ]
});

}

}
	},'-',{
				iconCls: 'icon-remove',
				text:'删除商品',
				handler: function(){
					var rows = $('#dg').datagrid('getChecked');
		        	var datas = JSON.stringify(rows);

			       if (rows.length == 0) {
				        $.messager.alert('删除提示',
					     	'请选择要删除的行', 'info');
			      } else {
			       	$.messager.confirm("确认删除","您确认要删除数据吗",function(r){
							 if (r) {
								$.ajax({
											type : 'post',
											url : '/EshopProject/goodscontroller.do',
											data : {
											type : 'remove',
											data : datas
											},
								success : function(data) {
										if (data == "1") {
												$.messager.alert('删除提示','删除成功','info');
															$('#dg').datagrid('reload');
														}
													}
												})
									}

								})
			}
				}
			}]

		});  

	}
	$('#save').on('click',function(){
		$.ajax({
			url:'/EshopProject/goodscontroller.do?type=add',
			type:'post',
			data:$('#f1').serialize(),
			success:function(data){
				alert(data)
				if(data=="1"){
					$('#f1').form('clear');
					$('#dialog').dialog('close');
					$('#dg').datagrid('reload');
				}
				
			}
		})
	})
})
function showImg(img) {
	alert(img)
	    $("#dlgImg").dialog('open');
	    $("#showImg").attr("src","/EshopProject/images/bookcover/"+img+".jpg");
/*	    grid.datagrid("clearSelections");*/    
	} 
