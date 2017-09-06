$(function(){
	$('#pagination').pagination({
		  pageSize: 5,
		  pageIndex:0,
		  firstBtnText:'首页',
		  lastBtnText:'尾页',
		  prevBtnText:'上一页', 
		  nextBtnText:'下一页',
		  showInfo:true,
		  noInfoText:'没有数据',
		  showJump:true,
		  showPageSizes:true,
		  pageSizeItems:[10,20,30],
		  remote:{
			  	url:'/EshopProject/listcopy.do',
			  	totalName:'totalnumber',
			  	success:function(pageinfo){
			  		$('#content').empty();
			  		var ul="<ul>";
			  		$(pageinfo.data).each(function(index,goods){
			  			ul+="<li>" + goods.gtitle+"</li>";
			  		}) 
			  		ul+="</ul>";
			  		$('#content').append(ul);
			  	}
		  }
		  
	});
	/*$('#pagination').pagination({
		pageIndex:0,
		pageSize:5,
		firstBtnText:"首页",
		lastBtnText:"末页",
		prevBtnText:"上野",
		nextBtnText:"下辅",
		showJump:true,
		showPageSizes:true,
		pageSizeItems:[10,20,15],
		jumpBtnText:"跳",
		showInfo:true,
		remote:{
			url:'/EshopProject/listcopy.do',
			success:function(pageinfo){
				$(' #content').empty();
				var ul = '<ul>';
				$(pageinfo.data).each(function(index,obj){
					ul += '<li>'+obj.gtitle+'</li>';
				})
				
				ul+='</ul>';
				console.log(ul);
				$('#content').append(ul);
			},
			totalName:'totalnumber'
		}
		
	})*/
})
