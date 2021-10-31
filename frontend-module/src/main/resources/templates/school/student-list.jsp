<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:url var="findUsersURL" value="/REST/getUsers"/>

<!DOCTYPE html>
<html>

<head>
    <title>Student list page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="/webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <%--<link href="${contextPath}/jsp/css/multilevel-dropdown.css" rel="stylesheet"/>--%>

    <script src="/webjars/jquery/3.3.1-1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="/webjars/datatables/1.10.19/js/jquery.dataTables.js" type="text/javascript" rel="script"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script src="/webjars/datatables.net-select/js/dataTables.select.min.js" type="text/javascript" rel="script"></script>
    <script src="/webjars/popper.js/1.14.3/umd/popper.min.js" type="text/javascript" rel="script"></script>
    <script src="${contextPath}/jsp/js/datatable.js" type="text/javascript" rel="script"></script>

    <script type="text/javascript">
        $(document).ready(
            function() {
                $.getJSON('${findUsersURL}', {
                    ajax : 'true'
                }, function(data) {
                    let html = '';
                    let len = data.length;
                    for ( let i = 0; i < len; i++) {
                        html += '<li class="dropdown-item"><a href="/user/detail/' + data[i].id + '">' + data[i].username + '</a></li>';
                    }
                    html += '</li>';

                    $('#universities').html(html);
                });
            });
    </script>

</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="javascript:void(0)">Student list</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/other/BMR">BMR</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/other/bmrjava">BMR(Java)</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/customer/showForm">Customer page</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        User page
                    </a>
                    <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
                        <li class="dropdown-item"><a href="/user/list">User list</a></li>
                        <li class="dropdown-item"><a href="/user/registerUser">User registration</a></li>
                        <li class="dropdown-divider"></li>
                        <li class="dropdown-submenu">
                            <a  class="dropdown-item" tabindex="-1" href="/user/list">Open user</a>
                            <ul class="dropdown-menu" id="universities"></ul>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="javascript:void(0)">Student page</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container">
        <h1 class="h1 text-center">This is student list page of my web application!</h1>
        <br/>
        <c:choose>
            <c:when test="${students.size() == 0}">
                <h3>There is no student to show!</h3>
            </c:when>

            <c:when test="${students.size() != 0}">

                <table id="myTable" class="table-hover table display col-sm-12">
                    <thead>
                        <tr class="header">
                            <th></th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Detail</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="temp" items="${students}">
                            <tr> <!--onclick="location.href='/student/detail/${temp.id}'"-->
                                <td></td>
                                <td>${temp.firstName}</td>
                                <td>${temp.lastName}</td>
                                <td><a href="/student/detail/${temp.id}" title="This link shows student detail" target="_blank">Detail</a></td>
                                <td><a href="/student/edit/${temp.id}" title="This link edit student" target="_blank">Edit</a></td>
                                <td><a href="/student/delete/${temp.id}" title="This link deletes student" onclick="return confirm('Are you sure you want to delete student ${temp.firstName} ${temp.lastName}?');">Delete</a></td>
                            </tr>
                        </c:forEach>
                    <tbody>
                </table>

                <c:if test="${students.size() >= 1}">
                    <form action="${contextPath}/student/list" method="get">
                        <div class="form-row justify-content-center">
                            <div class="col-sm-auto">
                                <div class="input-group mb-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">Number of students</div>
                                    </div>
                                    <input type="text" class="form-control" id="count" name="count" pattern="^[0-9]+$" value="${userCount}" required="required">
                                </div>
                            </div>

                            <div class="col-auto">
                                <label class="col-form-label">Randomize: </label>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="random" id="random1" value="true" ${random == true ? 'checked="checked"' : ''} required>
                                    <label class="form-check-label" for="random1">Yes</label>
                                </div>

                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="random" id="random2" value="false" ${random == false ? 'checked="checked"' : ''} required>
                                    <label class="form-check-label" for="random2">No</label>
                                </div>
                            </div>

                            <div class="col-auto">
                                <button class="btn btn-primary" type="submit">Filter students</button>
                            </div>

                            <div class="col-auto">
                                <a class="btn btn-primary" href="/student/list" onclick="return confirm('Are you sure you want to reset table?');" ${random == null || userCount == null ? 'disabled' : ''}>Reset list</a>
                            </div>
                        </div>
                    </form>

                </c:if>
            </c:when>
        </c:choose>

        <div class="form-group row">
            <div class="col-lg-12 text-center">
                <a class="btn btn-primary col-md-2 mb-2" href="/student/registrationForm" >Register new student!</a>
                <a class="btn btn-primary col-md-2 mb-2" href="/">Go to main menu!</a>
            </div>
        </div>
    </div>

</body>

</html>
