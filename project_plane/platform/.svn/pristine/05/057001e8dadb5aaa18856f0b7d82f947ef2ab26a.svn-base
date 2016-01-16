<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  	<s:iterator value="functionMenus" var="i">
  			<s:if test="#i.sfmParent == 0">
  				<h1>${i.sfmName }</h1>
  				<s:iterator value="functionMenus" var="j">
  					
		  			<s:if test="#j.sfmParent == #i.sfmId">
		  				<h2 style="margin-left: 20px;">
							${j.sfmName }
						</h2>
		  			</s:if>
		  			
		  		</s:iterator>
		  		
  			</s:if>
  			
  		</s:iterator>
  </body>
</html>
