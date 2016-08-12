<%@ page contentType="text/html;charset=UTF-8" pageEncoding="Cp1251" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>--- users list ---</title>
</head>
<body>
<table width="500" height="49" border="0" cellpadding="0" cellspacing="5">
    <tr>
        <%--For actions menu--%>
        <td height="49" valign="top"><table width="195" height="50" border="1" cellpadding="0" cellspacing="0" bgcolor="#ffe4e1" bordercolor="#808080">
            <tr>
                <td align="center"><b>Действия</b></td>
            </tr>
            <tr>
                <td><a href="<c:url value="/add.do"/>">Добавить пользователя</a></td>
            </tr>
            <tr>
                <td><a href="<c:url value="/fill.do"/>">Заполнить список</a></td>
            </tr>
            <tr>
                <td><a href="<c:url value="/clear.do"/>">Очистить список</a></td>
            </tr>
        </table></td>
            <%--For filters adjust--%>
        <td valign="top"><table width="300" height="50" border="1" cellpadding="0" cellspacing="0" bgcolor="#e0ffff" bordercolor="#808080">
            <tr>
                <td colspan="2" align="center"><b>Фильтры</b></td>
            </tr>
            <tr>
                <td width="100">По имени:</td>
                <td>${filters.nameFilter}</td>
            </tr>
            <tr>
                <td colspan="2"><a href="<c:url value="/editFilters.do"/>">Настроить</a></td>
            </tr>
            <tr>
                <td colspan="2"><a href="<c:url value="/deleteFilters.do"/>">Снять</a></td>
            </tr>
        </table></td>
    </tr>
    <%--For users display--%>
    <tr>
        <td valign="top" colspan="2"><table width="500" border="1" cellpadding="0" cellspacing="0" bgcolor="#f5f5dc" bordercolor="#808080">
            <tr>
                <td width="30" align="center"><b>Id</b></td>
                <td width="100" align="center"><b>Ф.И.О</b></td>
                <td width="30" align="center"><b>Лет</b></td>
                <td align="center"><b>Админ</b></td>
                <td align="center"><b>Добавлен</b></td>
                <td align="center"><b>Действия</b></td>
            </tr>
            <c:forEach items="${items}" var="item" varStatus="status">
                <tr>
                    <td width="30">${item.id}</td>
                    <td width="100">${item.name}</td>
                    <td width="30">${item.age}</td>
                    <td>${item.isAdmin}</td>
                    <td>${item.created}</td>
                    <td>
                        <a href="<c:url value="/edit.do?id=${item.id}"/>">Редактировать</a>
                        <a href="<c:url value="/delete.do?id=${item.id}"/>">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
        </table></td>
    </tr>
    <%--For pagination--%>
    <tr>
        <td valign="top" colspan="2" align="center"><table border="0">
            <tr>
                <td>
                    <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPage != 1}">
                         <td><a href="users.do?page=${currentPage - 1}">Предыдущая</a></td>
                    </c:if>
                </td>
                <%--For displaying page numbers --%>
                <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="users.do?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <td>
                    <%--For displaying Next link --%>
                         <c:if test="${currentPage != numberOfPages}">
                    <td><a href="users.do?page=${currentPage + 1}">Следующая</a></td>
                </c:if>
                </td>
            </tr>
        </table></td>
    </tr>
</table>
</body>
</html>
