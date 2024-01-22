<%@page import="java.util.Set"%>
<%@ page import="java.util.List" %>
<%@ page import="kr.ant.kpa.pharmcrew.apitest.ApiMgr" %>
<%@ page import="com.bumdori.spring.apitest.ApiInfo" %>
<%@ page import="com.bumdori.spring.apitest.ParameterInfo" %>
<%@ page import="com.bumdori.spring.apitest.HistoryInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ApiMgr apiMgr = ApiMgr.getInstance();
	String key = request.getParameter("controller");
	String apiKey = request.getParameter("api");
	ApiInfo api = apiMgr.getControllers().get(key).getApis().get(apiKey);
	List<ParameterInfo> pathParams = api.getPathParameters();
	List<ParameterInfo> params = api.getParameters();
	List<String> validations = api.getValidations();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript">
	function copy(button) {
		var org = null;
		var form = button.form;
		var elements = form.elements;
		for(var i=0; elements[i] != button ; i++){
			org = elements[i];
		}
		var cloned = org.cloneNode(true);
		org.parentNode.insertBefore(cloned, button);
	}
	
	function onSubmitForm() {
		var action = "<%=request.getContextPath() + api.getUrl()%>";
<%
	if (pathParams != null) {
		for (ParameterInfo param : pathParams) {
%>
		<%-- if (document.api.<%=param.getName()%>.value == '') {
			alert("uri parameter는 필수 입니다.");
			return false;
		} --%>
		action = action.replace("{<%=param.getName()%>}", document.api.<%=param.getName()%>.value);
<%
		}
	}
%>
		document.api.action = action;
		document.api.submit();
		return true;
	}
</script>
</head>
<body>
	<H2><%=api.getName()%>[<%=api.getUrl()%>] : <%=api.getMethod() != null ? api.getMethod().name() : ""%></H2>
<%
	if (api.getError() != null) {
		out.println(api.getError());
	}
%>
<%
	if (api.getDescription() != null) {
%>
	<b><%=api.getDescription()%></b>
<%
	}
%>
<%
	if (api.getHistories().size() > 0) {
%>
	<hr>
	<h3>이력</h3>
<%
		for (HistoryInfo history: api.getHistories()) {
			out.println(history.getDate() + " : " + history.getDescription() + "<br>");
		}
	}
%>
<%-- <%
	if (api.isNeedSession()) {
		out.println("<H3>로그인 필수</H3>");
	}
%> --%>

	<form name="api" onsubmit='onSubmitForm()' <%=api.makeMethodInForm()%> target="res">
<%
	out.println(api.makeMethodInHidden());
	if (pathParams.size() > 0) {
%>
	<hr>
	<H3>path parameter</H3>
<%
		for (ParameterInfo param : pathParams) {
			/* if (pathParams.indexOf(param) == 0) {
				out.println(param.getDescription());
				out.println("<br>");
				out.println("<br>");
			} */
			out.println("<b>" + param.getName() + "</b>");
			if (param.getDescription() != null) {
				out.println(": " + param.getDescription());
			}
			out.println("<br>");
			if (param.getError() != null) {
				out.println(param.getError());
				out.println("<br>");
			}
			out.println(param.makeInputHtml());
			out.println("<br><br>");
		}
	}
	if (params != null) {
%>
	<hr>
	<H3>body parameter</H3>
<%
		for (ParameterInfo param : params) {
			out.println("<b>" + param.getName() + "</b> [" + (param.getType() != null ? param.getType().name() : "") + "]");
			/* out.println("<br>"); */
			if (param.getDescription() != null) {
				out.println(": " + param.getDescription());
			}
			out.println("<br>");
			if (param.getError() != null) {
				out.println(param.getError());
				out.println("<br>");
			}
			if (param.isRequire()) {
				out.println("<b>필수</b>");
			} else {
				out.println("옵션");
			}
			/* if (param.getType() != TYPE.FILE && param.getMaxOccured() > 1) {
				out.println(" ~ " + param.getMaxOccured() + "개");
			} */
			out.println("<br>");
			if (param.getName().equals("sessionId")) {
				out.println(param.makeSessionInputHtml((String)session.getAttribute("SESSION_ID")));
			} else {
				out.println(param.makeInputHtml());
			}
			if (param.isArray()) {
				out.println("<input type='button' value='추가' onclick='copy(this)'>");
			}
			out.println("<br><br>");
		}
	}
%>
		<input type="submit" value="전송">
	</form>
	
	<hr>
	<h3>응답 정보</h3>
	<table border="1">
		<tbody>
<%
		out.println(api.printResult());
%>
		</tbody>
	</table>
	
	<hr>
	<h3>오류 코드</h3>
<%
	for (String validation: validations) {
		out.println(validation + "<br>");
	}
%>
	
	<%-- <a href="validators.jsp?controller=<%out.println(key);%>&api=<%out.println(apiKey);%>&path=<%out.print(api.getPath()); %>" target="res"><input type="button" value="응답 코드 보기"></a> --%>

</body>
</html>