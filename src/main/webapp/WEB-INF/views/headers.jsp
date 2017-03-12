<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="locale" value="${pageContext.response.locale}" />
<c:set var="languageCode" value="${locale.language}" />
<c:set var="countryCode" value="${locale.country}" />
<link rel="shortcut icon" href="<c:url value="/static/images/favicon.ico" />"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/webjars/bootstrap/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/webjars/bootstrap-table/bootstrap-table.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/webjars/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/fakemail.css"/>">
<script type="text/javascript" src="<c:url value="/webjars/jquery/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/jquery-dateFormat/jquery-dateFormat.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap-table/bootstrap-table.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap-table/locale/bootstrap-table-${languageCode}-${countryCode}.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap-notify/bootstrap-notify.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.${languageCode}.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/fakemail.js"/>"></script>
