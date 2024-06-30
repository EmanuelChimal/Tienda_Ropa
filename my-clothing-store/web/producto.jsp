<%-- 
    Document   : producto
    Created on : 29/06/2024, 08:08:37 PM
    Author     : Emanu
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Productos</h1>
    <c:if test="${not empty sessionScope.username}">
        <h2>Bienvenido, ${sessionScope.username}!</h2>
    </c:if>
    <p>última visita: ${lastVisit}</p>
    <c:forEach var="producto" items="${productos}">
        <div>
            <h2>${producto.nombre}</h2>
            <p>${producto.descripcion}</p>
            <p>Price: ${producto.precio}</p>
            <img src="${producto.imagen}" alt="${producto.nombre}">
        </div>
    </c:forEach>
    <a href="index.jsp">Volver al Home</a>
</body>
</html>
