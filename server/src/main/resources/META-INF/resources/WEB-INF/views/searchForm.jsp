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
			<div class="form-group">
				<label for="sentSince" class="control-label col-sm-2">Sent date</label>
				<span class="control-label col-sm-1">from</span>
				<input type="text" id="sentSince" name="sentSince" class="col-sm-2" data-toggle="tooltip" title="Begin sent date range"/>
				<div class="input-append date">
					<span class="add-on"><i class="icon-remove"></i></span>
					<span class="add-on"><i class="icon-th"></i></span>
				</div>
				<span class="control-label col-sm-1">to</span>
				<div class="input-append date">
					<input type="text" id="sentBefore" name="sentBefore" class="col-sm-2" data-toggle="tooltip" title="End sent date range"/>
					<span class="add-on"><i class="icon-remove"></i></span>
					<span class="add-on"><i class="icon-th"></i></span>
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
		type: 'get',
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
	$('#searchForm #sentSince').datetimepicker({
		language: '${pageContext.response.locale.language}',
		format: 'dd/mm/yyyy hh:ii',
		autoclose: true,
		clearBtn: true,
		todayBtn: true,
		keyboardNavigation: false
	});
	$('#searchForm #sentBefore').datetimepicker({
		language: '${pageContext.response.locale.language}',
		format: 'dd/mm/yyyy hh:ii',
		autoclose: true,
		clearBtn: true,
		todayBtn: true,
		keyboardNavigation: false
	});
});
</script>
