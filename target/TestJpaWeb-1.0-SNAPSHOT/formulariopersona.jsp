<%@page import="com.laboratorio.testjpaweb.modelo.PersonaRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://xmlns.jcp.org/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Formulario de persona</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <%
            PersonaRequest persona = (PersonaRequest)request.getAttribute("persona");
            String errores = (String)request.getAttribute("errores");
        %>
        <div class="container">

            <h1>Datos de la persona</h1>
            
            <c:if test="${errores != null}">
                <div class="alert alert-danger" role="alert">
                    <%= errores %>
                </div>
            </c:if>

            <form class="row g-3">
                
                <input type="hidden" id="accion" name="accion" value="guardar">
                
                <div class="col-md-6">
                    <label for="codigo" class="form-label">Código</label>
                    <input type="number" class="form-control" id="codigo" name="codigo" readonly="true"
                           value="<%= persona.getCodigo() %>">
                </div>
                <div class="col-12">
                    <label for="nombres" class="form-label">Nombres</label>
                    <input type="text" class="form-control" id="nombres" name="nombres"
                           value="<%= persona.getNombres()%>">
                </div>
                <div class="col-12">
                    <label for="apellidos" class="form-label">Apellidos</label>
                    <input type="text" class="form-control" id="apellidos" name="apellidos"
                           value="<%= persona.getApellidos()%>">
                </div>
                <div class="col-md-6">
                    <label for="fechaNac" class="form-label">Fecha de nacimiento</label>
                    <input type="date" class="form-control" id="fechaNac" name="fechaNac"
                           value="<%= persona.getFechaNac() %>">
                </div>
                <div class="col-md-6">
                    <label for="experiencia" class="form-label">Años de experiencia</label>
                    <input type="number" class="form-control" id="experiencia" name="experiencia"
                           value="<%= persona.getExperiencia() %>">
                </div>
                
                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                    <a href="PersonasController" class="btn btn-secondary">
                        <span class="bi bi-arrow-left"></span>Regresar
                    </a>
                </div>
                
            </form>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
