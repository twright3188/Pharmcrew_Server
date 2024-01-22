<%@ page import="java.net.InetAddress" %>
<%@ page import="com.bumdori.spring.apitest.ControllerInfo" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.bumdori.spring.apitest.ApiInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.ant.kpa.pharmcrew.apitest.ApiMgr" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>팜크루 Admin API</p>
<%
		out.println(InetAddress.getLocalHost().getHostName() + "<br>");
	/* out.println("ACCOUNT_ID: " + session.getAttribute("ACCOUNT_ID")); */
%>
	<br>
	<%-- <a href="<c:url value='/apiDoc' />" target="res">문서</a> --%>
	<a href="resCode.jsp" target="_blank">응답코드</a>
<%
		ApiMgr apiMgr = ApiMgr.getInstance();
		Map<String, ControllerInfo> controllers = apiMgr.getControllers();

		Iterator<String> itr = controllers.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			ControllerInfo controller = controllers.get(key);
%>
	<h3><%out.print(controller.getName() + "(" + controller.getClassName() + ")");%></h3>
<%
			if (controller.getError() != null) {
				out.println(controller.getError());
			}

			Map<String, ApiInfo> apis = controller.getApis();
			List<String> apiKeys = new ArrayList<String>(apis.keySet());
			/* Collections.sort(apiKeys); */
			/* Set<String> apiKeys = apis.keySet(); */
			Iterator<String> itra = apis.keySet().iterator();
			while (itra.hasNext()) {
				String apiKey = itra.next();
				ApiInfo api = apis.get(apiKey);
				String date = "-";
				if (api.getHistories() != null && api.getHistories().size() > 0) {
					date = api.getHistories().get(api.getHistories().size() - 1).getDate() + " - " + api.getHistories().get(api.getHistories().size() - 1).getDescription();
					// date = api.getHistories().get(api.getHistories().size() - 1).getDate();
				}
%>
	<a href="right.jsp?controller=<%out.print(key);%>&api=<%out.print(apiKey);%>" target="req"><%=api.getName() + " : " + date%></a><br>
<%	
			}
%>
<%
		}
%>
</body>
</html>