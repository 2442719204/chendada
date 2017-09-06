$(function() {
	var flag = 'null';
	$('#search').on('click', function() {

		/*
		 * $.ajax({ type:"post",
		 * url:"/EshopProject/usercontroller.do?type=search",
		 * data:{context:$('#ucontext').val()}, success:function(data){
		 */
		$('#dg').datagrid('load', {
			uloginid : $('#ucontext').val()
			
		});
		/*
		 * } })
		 */
	})
	
	$('#dg').datagrid(
					{
						url : '/EshopProject/usercontroller.do',
						collapsible : true,
						title : '用户信息',
						rownumbers : true,
						fitColumns : true,
						iconCls : 'icon-ok',
						idField : 'userid',
						resizeHandle : 'both',
						striped : true,
						queryParams : {
							flag : flag,
							subject : 'datagrid'
						},
						loadMsg : '正在加载，请稍候。。。。',
						columns : [ [ {
							field : 'chk',
							checkbox : true
						}, {
							field : 'userid',
							title : 'ID'
						}, {
							field : 'uemail',
							title : '邮箱'
						}, {
							field : 'uloginid',
							title : '帐号'
						}, {
							field : 'upassword',
							title : '密码'
						}, {
							field : 'usex',
							title : '性别'
						}, {
							field : 'uaddress',
							title : '地址'
						}, {
							field : 'utel',
							title : '手机号码'
						}, {
							field : 'ustateid',
							title : '状态'
						}, {
							field : 'uroleid',
							title : '角色'
						} ] ],
						toolbar : [
								{
									iconCls : 'icon-edit',
									text : '修改',
									handler : function() {
										
										var row = $('#dg').datagrid(
												'getSelected');
										if (row != null) {
											$('#uemail').textbox('setValue',row.uemail);
											$('#uname').textbox('setValue',
													row.uloginid);
											$('#password').textbox('setValue',
													row.upassword);
											$('#usex').textbox('setValue',
													row.usex);
											$('#uaddress').textbox('setValue',
													row.uaddress);

											$('#utel').textbox('setValue',
													row.utel);
											$('#dialog').dialog({
																title : '修改用户',
																closed : false,
																modal : true,
																buttons : [ {
																	text : '保存修改',
																	handler : function() {
																		var valid = $('#f1').form('validate');
																		alert(valid)
																		if (valid == true) {
																			$.ajax({
																						type : 'post',
																						url : "/EshopProject/usercontroller.do?type=modify",
																						data : $('#f1').serialize(),
																						success : function(data) {
																							alert(data)
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
								},
								'-',
								{
									iconCls : 'icon-remove',
									text : '删除',
									handler : function() {
										var rows = $('#dg').datagrid(
												'getChecked');
										var datas = JSON.stringify(rows);

										if (rows.length == 0) {
											$.messager.alert('删除提示',
													'请选择要删除的行', 'info');
										} else {
											$.messager.confirm("确认删除","您确认要删除数据吗",
												function(r) {
														if (r) {
															$.ajax({
																		type : 'post',
																		url : '/EshopProject/usercontroller.do',
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

										/*
										 * var row =
										 * $('#dg').datagrid('getSelected');
										 * if(row==null){
										 * $.messager.alert('删除提示','请选择要删除的行','info'); }
										 * if(row!=null){
										 * $.messager.confirm("确认删除","您确认要删除数据"+row.uloginid+"吗",function(r){
										 * if(r){ $.ajax({ type:'post',
										 * url:'/EshopProject/usercontroller.do',
										 * data:{type:'remove',userid:row.userid},
										 * success:function(data){ alert(data)
										 * if(data=="1"){
										 * $.messager.alert('删除提示','删除成功','info');
										 * $('#dg').datagrid('reload'); } } }) }
										 * });
										 *  }
										 */
									}
								}, '-', {
									iconCls : 'icon-add',
									text : '添加',
									handler : function() {
										$('#f1').form('clear');
										$('#dialog').dialog({
											title : '添加用户',
											closed : false,
											modal : true,
											buttons : [ {
												text : '保存添加',
												handler : function() {
			
													var valid = $('#f1').form('validate');
													alert(valid)
													if (valid == true) {
														$.ajax({
																	type : 'post',
																	url : "/EshopProject/usercontroller.do?type=add",
																	data : $('#f1').serialize(),
																	success : function(data) {
//																		alert(data)
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

										/*$('#dialog').dialog('open');*/
										/*$('#save').on('click', function() {
											alert(flag)
											var isValid = $('form').form('validate');
											if (isValid) {
												$.ajax({
													type : "POST",
													url : "/EshopProject/usercontroller.do?type=add",
													data : $('#f1').serialize(),
													success : function(data) {
														if (data == "1") {
															$('#f1').form('clear');
															$('#dialog').dialog('close');
															$('#dg').datagrid('reload');
														}
													}
												});
											}
										})*/
									}
								} ]

					});

})