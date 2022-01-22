<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/20/2022
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Reg</title>
</head>
<body>
<div class="container">
    <form:form method="POST" action="/createPerson" modelAttribute="person">
        <div class="form-group">
            <form:label path="firstName" for="firstNameInput">First Name(*)</form:label>
            <form:input type="text" path="firstName" class="form-control" id="firstNameInput"/>
        </div>
        <div class="form-group">
            <form:label path="lastName" for="lastNameInput">Last Name(*)</form:label>
            <form:input type="text" path="lastName" class="form-control" id="lastNameInput"/>
        </div>
        <div class="form-group">
            <form:label path="middleName" for="middleNameInput">Middle Name</form:label>
            <form:input type="text" path="middleName" class="form-control" id="middleNameInput"/>
        </div>
        <div class="form-group">
            <form:label path="email" for="emailInput">Email(*)</form:label>
            <form:input type="text" path="email" class="form-control" id="emailInput" value = "${email}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <form:label path="cellphone" for="cellphoneInput">Cellphone(*)</form:label>
            <form:input type="text" path="cellphone" class="form-control" id="cellphoneInput"/>
        </div>
        <div class="form-group">
            <form:label path="alternatePhone" for="alternatePhoneInput">Alternate Phone</form:label>
            <form:input type="text" path="alternatePhone" class="form-control" id="alternatePhoneInput"/>
        </div>
        <div class="form-group">
            <form:label path="SSN" for="SSNInput">SSN(*)</form:label>
            <form:input type="text" path="SSN" class="form-control" id="SSNInput"/>
        </div>
        <div class="form-group">
            <form:label path="DOB" for="DOBInput">Date Of Birth (* as YYYY-MM-DD)</form:label>
            <form:input type="text" path="DOB" class="form-control" id="DOBInput"/>
        </div>
        <div class="form-group">
            <form:radiobutton value="male" path="gender" id="gender" label="Male"></form:radiobutton>
            <form:radiobutton value="female" path="gender" id="gender" label="Female"></form:radiobutton>
        </div>
        <div class="form-group">
            <form:label path="userID" for="userIDInput">User ID(*)</form:label>
            <form:input type="text" path="userID" class="form-control" id="userIDInput" value = "${id}" readonly="readonly"/>
        </div>
        <button type="submit" class="btn btn-primary">Next</button>
    </form:form>
</div>
</body>
</html>
