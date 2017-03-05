<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<link rel="shortcut icon" href="<spring:url value="/images/favicon.ico" />"/>
	<link rel="stylesheet" type="text/css" href="<spring:url value="/static/classic/theme-classic-all.css"/>">
	<script type="text/javascript" src="<spring:url value="/static/ext-all-debug.js"/>"></script>
	<script type="text/javascript" src="<spring:url value="/static/locale/ext-locale-${pageContext.response.locale.language}.js"/>"></script>
	<script type="text/javascript" src="<spring:url value="/static/locale/Fakemail-locale-${pageContext.response.locale.language}.js"/>"></script>
	<script type="text/javascript">
Ext.Loader.setPath('Ext.ux', '<spring:url value="/static/Ext/ux"/>');

Ext.application({
	name: 'Fakemail',
	mainView: 'Application',
	appFolder: '<spring:url value="/static/Fakemail"/>',
	quickTips: true,
	models: [ 'Email' ],
	stores: [ 'Email' ],
	views: [ 'Application' ]
});
Ext.ariaWarn = Ext.emptyFn;
	</script>
</head>
<body>

</body>
</html>