<%--
  Copyright (C) 2017 Patrice Le Gurun

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<meta charset="UTF-8">
	<%@ include file="headers.jsp" %>
</head>
<body>
	<div class="container">
		<%@ include file="searchForm.jsp" %>

		<table id="results"></table>
	</div>

	<%@ include file="initGlobals.jsp" %>

	<script type="text/javascript">

function queryParams(params) {
	var searchParams = $('#searchForm').data('searchParams');
	if (searchParams === undefined) {
		return false;
	}
	params = $.extend(params, searchParams);
	return params;
}

$(document).ready(function(){
    $('#results').bootstrapTable({
    	method: 'post',
    	url: '<c:url value="/api/mail"/>',
    	queryParams: queryParams,
    	columns: [{
    		checkbox: true,
    		sortable: false
    	}, {
    		field: 'sender',
    		title: 'Sender'
    	}, {
    		field: 'recipient',
    		title: 'Recipient'
    	}, {
    		field: 'sentDate',
    		title: 'Sent date',
    		formatter: function(value, row, index) {
    			return $.format.date(value, "dd/MM/yyyy HH:mm:ss")
    		}
    	}, {
    		field: 'subject',
    		title: 'Subject'
    	}],
    	pagination: true,
    	pageSize: 10,
    	clickToSelect: true
    });
});

$(function () {
	$('#searchForm').submit(function(event) {
		var array = $('#searchForm').serializeArray();
		var searchParams = { };
		for (i = 0; i < array.length; i++) {
			if (array[i].value.length != 0) {
				searchParams[array[i].name] = array[i].value;
			}
		}
		$('#searchForm').data('searchParams', searchParams);
	    $('#results').bootstrapTable('refresh');
		event.preventDefault();
	});
});
	</script>
</body>
</html>
