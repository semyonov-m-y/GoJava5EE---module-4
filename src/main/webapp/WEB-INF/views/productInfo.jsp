<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pet Shop: Product</title>
</head>
<body>
<a href="/products">back</a>
<br>
<h3>Product: ${product.name}</h3>
<table style="align-items: center">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Manufacturer</th>
        <th>Price</th>
        <th>Description</th>
        <th>Delete</th>
    </tr>
    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.manufacturer}</td>
        <td>${product.price}</td>
        <td>${product.description}</td>
        <td><a href="/products/${product.id}/delete">Delete</a></td>
    </tr>
</table>
<h3>Edit product</h3>
<form action="/products/${product.id}/update" method="post">
    <input type="hidden" name="id" value="${product.id}">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${product.name}"></td>
        </tr>
        <tr>
            <td>Manufacturer:</td>
            <td><input type="text" name="manufacturer" value="${product.manufacturer}"></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price" value="${product.price}"></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type="text" name="description" value="${product.description}"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Update">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
