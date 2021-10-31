<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>

<head>
    <title>Student confirmation form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${contextPath}/jsp/css/student-registration.css" rel="stylesheet"/>
</head>

<body>

    <div class="ConfBody">
        <h1>Student created</h1>
        <table class="ConfPanel">
            <colgroup>
                <col style="width: 35%">
                <col style="width: 65%">
            </colgroup>
            <tr>
                <td class="column1">Student name:</td>
                <td class="column2">${theStudent.firstName} ${theStudent.lastName}</td>
            </tr>
            <tr>
                <td class="column1">Favorite language:</td>
                <td class="column2">${theStudent.favoriteLanguage}</td>
            </tr>
            <tr>
                <td class="column1">Known languages:</td>
                <td class="column2">${theStudent.stringProgramming}</td>
            </tr>
            <tr>
                <td class="column1">Spoken languages:</td>
                <td class="column2">${theStudent.stringSpoken}</td>
            </tr>
            <tr>
                <td class="column1">Country:</td>
                <td class="column2">${theStudent.country}</td>
            </tr>
            <tr>
                <td class="column1">University:</td>
                <td class="column2">${theStudent.university}</td>
            </tr>
            <tr>
                <td class="column1">Faculty:</td>
                <td class="column2">${theStudent.faculty}</td>
            </tr>
            <tr>
                <td class="column1">Type of stdy:</td>
                <td class="column2">${theStudent.typeOfStudy}</td>
            </tr>
            <tr>
                <td class="column1">Year of study:</td>
                <td class="column2">${theStudent.grade}</td>
            </tr>
        </table>

        <div class="ConfLinks">
            <a href="/" class="ConfMain">Go to main menu!</a>
            <br/>
            <a href="${contextPath}/student/list" class="ConfList">Go to student list!</a>
            <br/>
            <a href="${contextPath}/student/registrationForm" onclick="return confirm('Do you want to register new student?')">New student!</a>
        </div>
    </div>

</body>
</html>