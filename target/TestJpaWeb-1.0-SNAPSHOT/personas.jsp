<%@page import="com.laboratorio.testjpaweb.modelo.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://xmlns.jcp.org/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Listado de personas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
        %>

        <div class="container">

            <h1>Listado de personas</h1>

            <c:if test="${mensaje != null}">
                <div class="alert alert-danger" role="alert">
                    <%= mensaje%>
                </div>
            </c:if>

            <form action="PersonasController">

                <input type="hidden" id="accion" name="accion" value="agregar">

                <div class="mb-3">

                    <table class="table">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">Código</th>
                                <th scope="col">Nombres</th>
                                <th scope="col">Apellidos</th>
                                <th scope="col">Fecha nacimiento</th>
                                <th scope="col">Experiencia</th>
                                <th scope="col">Opciones</th>
                            </tr>
                        </thead>
                        <tbody class="table-group-divider">
                            <c:forEach var="persona" items="${lista_personas}">
                                <tr>
                                    <th scope="row">${persona.codigo}</th>
                                    <td>${persona.nombres}</td>
                                    <td>${persona.apellidos}</td>
                                    <td>${persona.fechaNac}</td>
                                    <td>${persona.experiencia}</td>
                                    <td>
                                        <a href="PersonasController?accion=editar&codigo=${persona.codigo}" class="btn btn-outline-success">
                                            <span class="bi bi-arrow-left"></span>Editar
                                        </a>
                                        <a href="PersonasController?accion=eliminar&codigo=${persona.codigo}" class="btn btn-outline-warning">
                                            <span class="bi bi-arrow-left"></span>Eliminar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="col-12">
                    <button type="submit" class="btn btn-primary btn-lg">Crear persona</button>
                    <button type="submit" class="btn btn-secondary btn-lg" formaction="index.html">Volver al índice</button>
                </div>

            </form>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
