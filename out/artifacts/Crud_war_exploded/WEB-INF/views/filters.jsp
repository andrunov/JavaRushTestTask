<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="Cp1251" language="java" %>
<html>
<head>
    <title>--- user ---</title>
</head>
<body>

<form role="form" action="<c:url value="/editFilters.do"/>" method="post" style="width: 50%;">
    <table border="1" cellpadding="0" cellspacing="0" bgcolor="#e0ffff" bordercolor="#808080">
        <tr>
            <td colspan="2" align="center"><b>��������� �������</b></td>
        </tr>
        <tr>
            <td>������ ����� �����</td>
            <td>
                <input type="text" name="nameFilter" value="${filters.nameFilter}">
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" align="center" value="���������"/>
            </td>
            <td>
                <a href="<c:url value="/users.do"/>">������</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
