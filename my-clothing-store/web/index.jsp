<%-- 
    Document   : index
    Created on : 29/06/2024, 08:07:34 PM
    Author     : Emanu
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mi Tienda de Ropa</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Bienvenid@ a mi tienda de ropa</h1>
    <form method="post" action="productos">
        <label for="username">Introduce tu nombre:</label>
        <input type="text" id="username" name="username">
        <button type="submit">Enviar</button>
    </form>
    <a href="productos">Ver Productos</a>
</body>
</html>
