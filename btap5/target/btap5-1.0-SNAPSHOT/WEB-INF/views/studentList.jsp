<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Student List</title>
            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Custom CSS -->
            <link href="../css/style.css" rel="stylesheet">
        </head>

        <body>
            <div class="container">
                <h2 class="text-center mb-4">Student List</h2>
                <a href="/students/add" class="btn btn-primary btn-custom mb-3">Add New Student</a>
                <table class="table table-striped table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.id}</td>
                                <td>${student.name}</td>
                                <td>${student.age}</td>
                                <td>
                                    <a href="/students/delete/${student.id}" class="btn btn-danger btn-sm"
                                        onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Bootstrap JS (cho các tính năng như confirm dialog) -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>