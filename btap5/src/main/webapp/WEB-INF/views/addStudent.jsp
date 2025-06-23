<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Add Student</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="/css/style.css" rel="stylesheet">
        </head>

        <body>
            <div class="container">
                <h2 class="text-center mb-4">Add New Student</h2>
                <div class="form-container">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>
                    <form action="/students/add" method="post" modelAttribute="student">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="age" class="form-label">Age</label>
                            <input type="number" class="form-control" id="age" name="age" required>
                        </div>
                        <button type="submit" class="btn btn-success">Add Student</button>
                        <a href="/students" class="btn btn-secondary">Back to List</a>
                    </form>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>