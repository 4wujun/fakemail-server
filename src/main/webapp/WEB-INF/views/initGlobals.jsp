<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="loading-indicator" class="spinner"></div>
<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
    $(document).ajaxStart(function(event, request, settings) {
    	$('#loading-indicator').fadeIn();
    });
    $(document).ajaxStop(function(event, request, settings) {
    	$('#loading-indicator').fadeOut();
    });
});
</script>