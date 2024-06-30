/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

/**
 *
 * @author Emanu
 */
package com.mycompany.servlets;

import com.mycompany.beans.Producto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/clothing_store";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gestión de cookies
        Cookie[] cookies = request.getCookies();
        String lastVisit = "Never";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastVisit")) {
                    lastVisit = cookie.getValue();
                    break;
                }
            }
        }
        
        // Actualizar la cookie de última visita
        Cookie lastVisitCookie = new Cookie("lastVisit", java.time.LocalDateTime.now().toString());
        lastVisitCookie.setMaxAge(60 * 60 * 24 * 30); // Expira en 30 días
        response.addCookie(lastVisitCookie);
        
        // Obtener productos de la base de datos
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM productos";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecio(resultSet.getDouble("precio"));
                producto.setImagen(resultSet.getString("imagen"));
                productos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request.setAttribute("productos", productos);
        request.setAttribute("lastVisit", lastVisit);
        request.getRequestDispatcher("producto.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gestión de sesiones
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        if (username != null && !username.trim().isEmpty()) {
            session.setAttribute("username", username);
        }
        response.sendRedirect("productos");
    }
}
