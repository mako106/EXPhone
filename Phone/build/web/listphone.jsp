<%-- 
    Document   : listphone
    Created on : 10 Dec 2018, 9:01:04 AM
    Author     : chien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>List phone</h3>
        <jsp:useBean id="db" class="com.chien.model.DB" scope="request"/>
        <c:set var="list" value="${db.getAll()}" scope="request"/>
        <table class="table table-bordered mx-auto">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Name</th>
                    <th>Brand</th>
                    <th>Price</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.brand}</td>
                        <td>${p.price}</td>
                        <td>${p.description}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="addphone.jsp">Add more</a>
    </body>
</html>
