<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Student List</title>
        </head>

        <body>
            <h2>Student List</h2>
            <a href="/students/add">Add New Student</a>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.age}</td>
                        <td><a href="/students/delete/${student.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </body>

        </html>