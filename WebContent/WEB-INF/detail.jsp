<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>

<body class="main">
<%@include file="header.jsp" %>


<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="product_list.html">&nbsp;${goods.category.cname }</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${goods.gtitle }</div>
        <table cellspacing="0" class="infocontent">
        <tr>
          <td><img src="${pageContext.request.contextPath }/images/ad/page_ad.jpg" width="645" height="84" />
		  
		    <table width="100%%" border="0" cellspacing="0">
              <tr>
                <td width="30%">
				
				<div class="divbookcover"><p><img src="${pageContext.request.contextPath }/images/bookcover/${goods.gimg }.jpg" /></p></div>
				
				<div style="text-align:center; margin-top:25px"><a href="cart.html"><img src="images/buybutton.gif" border="0" /></a></div>
				</td>
                <td style="padding:20px 5px 5px 5px"><img src="images/miniicon3.gif" width="16" height="13" /><font class="bookname">&nbsp;&nbsp;${goods.gtitle }</font><hr/>作者：${goods.gauthor } 著<hr/>售价：<font color="#FF0000">￥${goods.gsaleprice }</font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>￥${goods.ginprice }</s><hr/>出版社：${goods.publisher.pname }
<hr/><p><b>内容简介：</b></p>　　${goods.gdesc }
              </tr>
            </table>		  </td>
        </tr>
      </table>
      
      </td>
    </tr>
  </table>
</div>



<%@include file="footer.jsp" %>


</body>
</html>
