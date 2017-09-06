

$(function(){
	
	
		$('#save').on('click',function(){
			var isValid = $('form').form('validate');
			if(isValid){
			$.ajax({
				url:'/EshopProject/ordercontroller.do?type=modify',
				type:'post',
				data:$('#f1').serialize(),
				success:function(data){
					if (data == "1") {
						$('#f1').form('clear');
						$('#dialog').dialog('close');
						$('#acquisitionTab').datagrid('reload');
					}
				}
			})
			}
		})
	
	
	
	$('#acquisitionTab').datagrid({
		view: detailview,//注意
		url : '/EshopProject/ordercontroller.do',
		collapsible : true,
		title : '用户信息',
		rownumbers : true,
		fitColumns : true,
		iconCls : 'icon-ok',
		idField : 'userid',
		resizeHandle : 'both',
		fitColumns:true,
		queryParams : {
			type:'order'
		},
		striped : true,
		loadMsg : '正在加载，请稍候。。。。',
		onDblClickRow: function (rowIndex, rowData){
			$('#order-detail').dialog('open');
			$('#order-detail-dg').datagrid({    
			    url:'/EshopProject/orderdetailcontroller.do?id='+rowData.orderid,   
			    columns:[ [{
                	field : 'orderdetailid',
                	title : '订单详情编号'
                }, {
                	field : 'gtitle',
                	title : '商品名'
                }, {
                	field : 'gsalprice',
                	title : '总售价'
                }, {
                	field : 'gnumber',
                	title : '总数量'
                }  , {
                	field : 'orderid',
                	title : '订单编号'
                }     
                    ] ] 
			});  
		},columns : [ [ {
        	field:'chk',checkbox:true
        }, {
			field : 'orderid',
			title : '订单编号'
		}, {
			field : 'userid',
			title : '用户编号'
		}, {
			field : 'totalprice',
			title : '总价'
		},{
			field : 'orderDateString',
			width:'200px',
			align:'center',
			title : '订单日期'
		},{
			field : 'orderAddress',
			width:'200px',
			align:'center',
			title : '订单地址'
		}] ], toolbar: [{
    		iconCls: 'icon-edit',
    		text:'修改订单',
    		handler: function(){
    			var row = $('#acquisitionTab').datagrid('getSelected');
    			if(row!=null){
    				$('#orderid').textbox('setValue',row.orderid);
					$('#userid').textbox('setValue',
							row.userid);
					$('#totalprice').textbox('setValue',
							row.totalprice);
					
					$('#orderdate').textbox('setValue',
							row.orderDateString);

					$('#orderaddress').textbox('setValue',
							row.orderAddress);
					$('#dialog').dialog('open');
    			}
    			
    		}
    	},'-',{
    		iconCls: 'icon-remove',
    		text:'删除商品',
    		handler: function(){
    			var rows = $('#acquisitionTab').datagrid('getChecked');
    			var datas = JSON.stringify(rows);

    			if (rows.length == 0) {
    				$.messager.alert('删除提示','请选择要删除的行', 'info');
    			} else {
    				$.messager.confirm("确认删除","您确认要删除数据吗",
    						function(r) {
							if (r) {
								$.ajax({
									type : 'post',
									url : '/EshopProject/ordercontroller.do',
									data : {
									type : 'remove',
									data : datas
								},
								success : function(data) {
									if (data == "1") {
											$.messager.alert('删除提示','删除成功','info');
													$('#acquisitionTab').datagrid('reload');
											}
									}
								})
							}

				})
		}
    		}
    	}], 
        detailFormatter:function(index,row){//注意2  
            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';  
        },  
        onExpandRow:function(index,row){//注意3  
            $('#ddv-'+index).datagrid({  
                url:'/EshopProject/orderdetailcontroller.do?id='+row.orderid,  
                fitColumns:true,  
                singleSelect:true,  
                height:'auto',  
                columns:[ [{
                	field : 'orderdetailid',
                	title : '订单详情编号'
                }, {
                	field : 'gtitle',
                	title : '商品名'
                }, {
                	field : 'gsalprice',
                	title : '总售价'
                }, {
                	field : 'gnumber',
                	title : '总数量'
                }  , {
                	field : 'orderid',
                	title : '订单编号'
                }     
                    ] ],  
                    onResize:function(){  
                        $('#acquisitionTab').datagrid('fixDetailRowHeight',index);  
                    },  
                    onLoadSuccess:function(){  
                        setTimeout(function(){  
                            $('#acquisitionTab').datagrid('fixDetailRowHeight',index);  
                        },0);  
                    }  
            })
            $('#acquisitionTab').datagrid('fixDetailRowHeight',index);
        }

});
})

