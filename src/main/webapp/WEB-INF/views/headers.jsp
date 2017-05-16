<%--
  #%L
  Fakemail server
  %%
  Copyright (C) 2017 Patrice Le Gurun
  %%
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as
  published by the Free Software Foundation, either version 3 of the
  License, or (at your option) any later version.
  
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Lesser Public License for more details.
  
  You should have received a copy of the GNU General Lesser Public
  License along with this program.  If not, see
  <http://www.gnu.org/licenses/lgpl-3.0.html>.
  #L%
  --%>

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
