<%@page isErrorPage="true"%>

Exception <b><%=exception.getMessage()%></b>
<br>
<%exception.printStackTrace();%>

