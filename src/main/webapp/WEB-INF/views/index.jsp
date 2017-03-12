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
    	method: 'POST',
    	url: '<c:url value="/api/mail"/>',
    	contentType: 'application/x-www-form-urlencoded',
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
    	pageSize: 3,
    	clickToSelect: true
    });
});

$(function () {
	$('#searchForm').submit(function(event) {
		var array = $('#searchForm').serializeArray();
		var searchParams = {};
		for (i = 0; i < array.length; i++) {
			searchParams[array[i].name] = array[i].value;
		}
		$('#searchForm').data('searchParams', searchParams);
	    $('#results').bootstrapTable('refresh');
		event.preventDefault();
	});
});
	</script>
</body>
</html>