<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </head>
    <body>
        <div class="col-4" style="margin: 10% 30%">
            <h3>Введите начало и конец отрезка:</h3>
            <form action="${pageContext.request.contextPath}" method="GET">
                <label for="L" class="form-label"> L: </label> <input class="form-control" id="L" type="number"
                    min="0" name="L" required> <br>
                <label for="R" class="form-label"> R: </label> <input class="form-control" id="R" type="number"
                    min="0" name="R" required> <br>
                <button class="btn btn-primary" type="submit" name="submit">Submit</button>
            </form>
            <c:if test="${not empty param.L and not empty param.R}">
                Найдены числа:
                <ul class="list-group">
                    <c:forEach var="num" items="${answer}">
                        <li class="list-group-item"><c:out value="${num}"/></li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </body>
</html>