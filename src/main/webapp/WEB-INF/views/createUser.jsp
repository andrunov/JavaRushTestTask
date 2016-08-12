<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="Cp1251" language="java" %>
<html>
<head>
    <title>--- new user ---</title>
</head>
<body>
<form role="form" action="<c:url value="/add.do"/>" method="post" style="width: 50%;">
    <input type="hidden" name="id" value="${user.id}">
    <table border="1" cellpadding="0" cellspacing="0" bgcolor="#ffe4e1" bordercolor="#808080">
        <tr>
            <td colspan="2" align="center"><b>Добавление пользователя</b></td>
        </tr>
        <tr>
            <td>Ф.И.О</td>
            <td>
                <input type="text" name="name" value="${user.name}">
            </td>
        </tr>
        <tr>
            <td>Лет</td>
            <td>
                <input type="text" name="age" value="${user.age}">
            </td>
        </tr>
        <tr>
            <td>Админ</td>
            <td>
                <input type="text" name="isAdmin" value="${user.isAdmin}">
            </td>
        </tr>
        <tr>
            <td>Добавлен</td>
            <td>
                <input type="date" name="created" value="${user.created}">
            </td>
        </tr>
            <td><input type="submit" align="center" value="Сохранить"/></td>
            <td>
                <a href="<c:url value="/users.do"/>">Отмена</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
