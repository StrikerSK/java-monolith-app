<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:url var="findUnisURL" value="/university/getUniversities" />
<c:url var="findFacultyURL" value="/university/getFaculties" />
<c:url var="findAllFacultyURL" value="/university/getAllUniversityFaculties" />


<!DOCTYPE html>
<html>

<head>
    <title>Edit student ${editedStudent.id}</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${contextPath}/jsp/css/student-registration.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/jsp/css/navigation-bar.css" type="text/css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" >

    <script src="${contextPath}/jsp/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="${contextPath}/jsp/js/functions.js" type="text/javascript"></script>
    <script src="${contextPath}/jsp/js/navigation-bar.js"></script>

    <script type="text/javascript">
        $(document).ready(
            function() {
                $.getJSON('${findUnisURL}', {
                    ajax : 'true'
                }, function(data) {
                    let html = '<option value="${universityValue}" hidden="hidden">${editedStudent.university}</option>';
                    let len = data.length;
                    for ( let i = 0; i < len; i++) {
                        html += '<option value="' + data[i].university_id + '">'
                            + data[i].universityName + '</option>';
                    }
                    html += '</option>';

                    $('#universities').html(html);
                });
            });
    </script>

    <script type="text/javascript">
        $(document).ready(
            function() {
                $.getJSON('${findAllFacultyURL}', {
                    uniId : ${universityValue},
                    ajax : 'true'
                }, function(data) {
                    let len = data.length;
                    let html = '<option value="${facultyValue}" hidden="hidden">${editedStudent.faculty}</option>';
                    for ( let i = 0; i < len; i++) {
                        html += '<option value="' + data[i].facultyId + '">'
                            + data[i].facultyName + '</option>';
                    }
                    html += '</option>';

                    $('#faculties').html(html);
                });
            });
    </script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#universities').change(
                function() {
                    $.getJSON('${findFacultyURL}', {
                        uniId : $(this).val(),
                        ajax : 'true'
                    }, function(data) {
                        let len = data.length;
                        let html = '<option value="' + data[0].facultyId + '">' + data[0].facultyName + '</option>';
                        for ( let i = 1; i < len; i++) {
                            html += '<option value="' + data[i].facultyId + '">'
                                + data[i].facultyName + '</option>';
                        }
                        html += '</option>';

                        $('#faculties').html(html);
                    });
                });
        });
    </script>

    <script type="text/javascript">
        $(document).ready(
                function() {
                    let gradeSelector = $('#grade');
                    let typeSelector = $('#typeOfStudy');
                    let html = '<option value="${editedStudent.grade}" hidden="hidden">${editedStudent.grade}</option>';
                    if(${editedStudent.typeOfStudy == null}){
                        gradeSelector.attr('disabled', 'disabled');
                        html = '<option value="" hidden="hidden">Select year</option>';
                    }
                    if (typeSelector.val() === 'Bachelor'){
                        html += '<option value="1st">1st</option>';
                        html += '<option value="2st">2nd</option>';
                        html += '<option value="3rd">3rd</option>';
                        html += '<option value="Extended">Extended</option>';
                    } else {
                        if(typeSelector.val() === 'Masters' || $(typeSelector.val() === 'PhD student')){
                            html += '<option value="1st">1st</option>';
                            html += '<option value="2st">2nd</option>';
                            html += '<option value="Extended">Extended</option>';
                        }
                    }
                    gradeSelector.html(html);
                });
    </script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#typeOfStudy').change(
                function() {
                    let html = '<option value="" hidden="hidden">Select year</option>';
                    if ($(this).val() === 'Bachelor'){
                        html += '<option value="1st">1st</option>';
                        html += '<option value="2st">2nd</option>';
                        html += '<option value="3rd">3rd</option>';
                        html += '<option value="Extended">Extended</option>';
                    } else {
                        html += '<option value="1st">1st</option>';
                        html += '<option value="2st">2nd</option>';
                        html += '<option value="Extended">Extended</option>';
                    }
                    $('#grade').html(html).removeAttr('disabled');
                })
        });
    </script>

</head>

<body>

    <div class="navbar" id="myTopnav">
        <a href="${contextPath}/" onclick="return confirm('Are you sure you want to go to main menu?')">Home</a>
        <a href="${contextPath}/student/list" onclick="return confirm('Are you sure you want to go to student list?')">Student list</a>
        <a href="${contextPath}/student/detail/${editedStudent.id}" onclick="return confirm('Are you sure you want to cancel changes?')">Student detail</a>
        <a href="javascript:void(0);" class="icon" onclick="makeResponsive()">
            <i class="fa fa-bars"></i>
        </a>
    </div>

    <div class="RegisterPage">
        <h1>Edit student ${editedStudent.firstName} ${editedStudent.lastName}</h1>

        <div class="RegisterForm">
            <form:form action="/student/postEdit" modelAttribute="editedStudent" method="post" onsubmit="return validateValues()">

                <form:input type="hidden" path="id"/>

                <div class="inputText">
                    <label for="fname">First name: </label>
                    <form:input path="firstName" id="fname" placeholder="Your name.." required="required"/>

                    <label for="lname">Last name: </label>
                    <form:input path="lastName" id="lname" placeholder="Your last name.." required="required"/>
                </div>

                <div class="countryOption">
                    <label for="country">Select your country:</label>
                    <form:select path="country" id="country" required="required">
                        <form:option value="" label="Select language" selected="true" hidden="true"/>
                        <form:options items="${countries.values()}"/>
                    </form:select>
                </div>

                <div class="select-options">
                    <label for="typeOfStudy">Select your year:</label>
                    <form:select id="typeOfStudy" path="typeOfStudy" required="true">
                        <form:option value="" hidden="hidden">Select type of study</form:option>
                        <form:option value="Bachelor">Bachelor</form:option>
                        <form:option value="Masters">Masters</form:option>
                        <form:option value="PhD student">PhD student</form:option>
                    </form:select>
                </div>

                <div class="select-options">
                    <label for="grade">Select your year:</label>
                    <form:select id="grade" path="grade" required="true"></form:select>
                </div>

                <div class="universityOptions">
                    <label for="university">Select your University:</label>
                    <form:select id="universities" path="university" required="true"></form:select>
                </div>

                <div class="facultyOption">
                    <label for="faculties">Select your Faculty:</label>
                    <form:select id="faculties" path="faculty" required="true">
                        <c:if test="${editedStudent.faculty.equals('No faculty')}">
                            <option value="">${editedStudent.faculty}</option>
                        </c:if>
                    </form:select>
                </div>

                <div class="language-option-section">
                    <p class="language-label">Select your favorite language:</p>
                    <fieldset>
                        <div class="language-options">
                            <ul class="radio-button-grid">
                                <c:forEach items="${progLang.values()}" var="language">
                                    <li>
                                        <form:radiobutton path="favoriteLanguage" cssClass="radion-input" value="${language}" required="true" onclick="chooseProgrammingLanguage()"/>
                                        <label for="${language}" class="radio-label">${language}</label>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </fieldset>
                    <output hidden="hidden" id="radio-result"></output>
                </div>

                <div class="language-option-section">
                    <p class="language-label">Select your known language:</p>
                    <fieldset>
                        <div class="language-options">
                            <ul class="radio-button-grid">
                                <c:forEach items="${progLang.values()}" var="language">
                                    <li>
                                        <form:checkbox path="knownLanguages" cssClass="radion-input" value="${language}"/>
                                        <label for="${language}" class="radio-label">${language}</label>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </fieldset>
                </div>

                <div class="language-option-section">
                    <p class="language-label">Select your spoken language:</p>
                    <fieldset>
                        <div class="language-options">
                            <ul class="radio-button-grid">
                                <c:forEach items="${languages.values()}" var="language">
                                    <li>
                                        <form:checkbox path="spokenLanguages" cssClass="radion-input" value="${language}"/>
                                        <label for="${language}" class="radio-label">${language}</label>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </fieldset>
                </div>

                <div class="submit-container">
                    <input type="submit" value="Edit student" id="submit-button"/>
                </div>
            </form:form>
        </div>
    </div>
</body>

</html>