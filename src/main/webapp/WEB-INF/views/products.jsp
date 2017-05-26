<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Pet Shop: Products</title>
</head>
<body>
<a href="/">back</a>
<br>
<h3>Products</h3>
<table style="align-items: center">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Manufacturer</th>
        <th>Price</th>
        <th>Description</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="product" items="${productsList}">
        <tr>
            <td><a href="/products/${product.id}">${product.id}</a></td>
            <td>${product.name}</td>
            <td>${product.manufacturer}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td><a href="/products/${product.id}">Edit</a></td>
            <td><a href="/products/${product.id}/delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<h3>Add product</h3>
<form action="/products/add" method="post">
    <input type="hidden" name="id" value="">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Manufacturer:</td>
            <td><input type="text" name="manufacturer"></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Add">
            </td>
        </tr>
    </table>
</form>
</body>
</html>