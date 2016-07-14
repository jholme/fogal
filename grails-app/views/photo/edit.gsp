<%@ page import="com.iii.fogal.Photo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<%--		<div class="nav" role="navigation">--%>
<%--			<ul>--%>
<%--				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--%>
<%--				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--%>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
<%--			</ul>--%>
<%--		</div>--%>
		<div id="edit-photo" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${photoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${photoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:photoInstance, action:'update']" method="PUT"  enctype="multipart/form-data">
				<g:hiddenField name="version" value="${photoInstance?.version}" />
				<fieldset class="form">
                    <g:render template="form"/>
<%--					<g:render template="form" keys="${photoInstance.image}" required=""--%>
<%--					           disabled="${mode == 'edit'}"/>--%>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton class="save" name="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    <g:link class="show" action="show" resource="${photoInstance}"><g:message code="default.button.cancel.label" default="Cancel" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

<%--https://jira.grails.org/browse/GRAILS-11308--%>
<%--https://jira.grails.org/browse/GRAILS-11067--%>
<%--http://stackoverflow.com/questions/14841302/how-to-conditionally-disable-a-form-input-field--%>
<%--<g:select name="teacherType" from="${TeacherType?.values()}"--%>
<%--  keys="${TeacherType.values()*.name()}" required=""--%>
<%--  value="${teacherInstance?.teacherType?.name()}"--%>
<%--  disabled="${mode == 'edit'}"/>--%>
