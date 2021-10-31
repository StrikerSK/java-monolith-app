<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Student ${studentDetail.id}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${contextPath}/jsp/css/student-detail.css">
</head>

<body>
    <div class="student-details">
        <h1>Student number ${studentDetail.id} details</h1>
        <div class="student-text">
            <p>The first name is <strong>${studentDetail.firstName}</strong> and last lame is <strong>${studentDetail.lastName}</strong>.</p>
            <p>Student is from <strong>${studentDetail.country}</strong>.</p>
            <p>Student's favorite language is <strong>${studentDetail.favoriteLanguage}</strong>.</p>
            <c:choose>
                <c:when test="${studentDetail.typeOfStudy == null && studentDetail.grade == null}">
                    <p>Student has no further study information!<p>
                </c:when>
                <c:otherwise>
                    <p>Student is studying <strong>${studentDetail.grade}</strong> year of <strong>${studentDetail.typeOfStudy}</strong>.</p>
                </c:otherwise>
            </c:choose>
            <p>Student's is studying on <strong>${studentDetail.university}</strong> faculty of <strong>${studentDetail.faculty}</strong>.</p>
            <div class="known-languages">
                <c:choose>
                    <c:when test="${studentDetail.knownLanguages.size() == 0}">
                        <p>Student does not know any programming language!</p>
                    </c:when>
                    <c:otherwise>
                        <p>Student knows following languages: <strong>${studentDetail.stringProgramming}</strong></p>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="known-languages">
                <c:choose>
                    <c:when test="${studentDetail.spokenLanguages.size() == 0}">
                        <p>Student does not know any language!</p>
                    </c:when>
                    <c:otherwise>
                        <p>Student speaks in following languages: <strong>${studentDetail.stringSpoken}</strong></p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <div class="link">
        <a href="/student/edit/${studentDetail.id}" onclick="return confirm('Are you sure you want to edit student?')">Edit student!</a>
    </div>
</body>

</html>
