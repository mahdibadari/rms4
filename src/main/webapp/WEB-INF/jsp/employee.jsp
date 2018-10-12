<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
    <title>First Web Application</title>
    </head>
    <body>
        <table style="border:2px solid black">
            <c:forEach items="${movies}" var="element"> 
            <tr>
                <td>${element.getTitle()}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
    </html>