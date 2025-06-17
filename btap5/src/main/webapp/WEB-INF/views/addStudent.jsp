<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Add Student</title>
    </head>

    <body>
        <h2>Add New Student</h2>
        <form action="/students/add" method="post">
            <label>ID:</label><input type="number" name="id" required><br>
            <label>Name:</label><input type="text" name="name" required><br>
            <label>Age:</label><input type="number" name="age" required><br>
            <input type="submit" value="Add Student">
        </form>
        <a href="/students">Back to List</a>
    </body>

    </html>