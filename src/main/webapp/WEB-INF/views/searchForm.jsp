<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel panel-primary">
	<form id="searchForm" class="form-horizontal">
		<div class="panel-heading">Search mail</div>
		<div class="panel-body">
			<div class="form-group">
				<label for="senderId" class="control-label col-sm-2">Sender</label>
				<div class="col-sm-10">
					<select class="form-control" id="senderId" name="senderId" data-toggle="tooltip" title="Sender address"></select>
				</div>
			</div>
			<div class="form-group">
				<label for="recipient" class="control-label col-sm-2">Recipient</label>
				<div class="col-sm-10">
					<input type="text" id="recipient" name="recipient" class="form-control" data-toggle="tooltip" title="Part of recipient address"/>
				</div>
			</div>
		</div>
		<div class="panel-footer text-center">
			<div class="btn-group">
				<button type="submit" class="btn btn-default" data-toggle="tooltip" title="Search for emails"><span class="glyphicon glyphicon-search"></span> Search</button>
				<button type="reset" class="btn" data-toggle="tooltip" title="Reset the form"><span class="glyphicon glyphicon-refresh"></span> Reset</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
$(function() {
	$('#searchForm #senderId').html('<option class="results" value=""/>');
	$.ajax({
		url: '<c:url value="/api/sender"/>',
		type: 'GET',
		dataType: 'json'
	})
	.done(function(data) {
		var output = '';
		$.each(data, function(index, item) {
			output += '<option class="results" value="' + item.id + '">' + item.address + '</option>';
		});
		$('#searchForm #senderId').append(output);
	})
	.fail(function(data) {
		console.log('error');
	});
});
</script>