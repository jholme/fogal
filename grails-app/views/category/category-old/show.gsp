
<%@ page import="com.iii.fogal.Category" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-category" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<sec:ifLoggedIn>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				</sec:ifLoggedIn>
			</ul>
		</div>
		<div id="show-category" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list category">
			
				<g:if test="${categoryInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="category.name.label" default="Name" /></span>
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${categoryInstance}" field="name"/></span>
				</li>
				</g:if>
			
				<g:if test="${categoryInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="category.description.label" default="Description" /></span>
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${categoryInstance}" field="description"/></span>
				</li>
				</g:if>
			
				<g:if test="${categoryInstance?.path}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label"><g:message code="category.path.label" default="Path" /></span>
					<span class="property-value" aria-labelledby="path-label"><g:fieldValue bean="${categoryInstance}" field="path"/></span>
				</li>
				</g:if>
			
				<g:if test="${categoryInstance?.galleries}">
				<li class="fieldcontain">
					<span id="galleries-label" class="property-label"><g:message code="category.galleries.label" default="Galleries" /></span>
					<g:each in="${categoryInstance.galleries}" var="g">
					   <span class="property-value" aria-labelledby="galleries-label"><g:link controller="gallery" action="show" id="${g?.id}">${g?.encodeAsHTML()}</g:link></span>
					</g:each>
				</li>
				</g:if>
			
			</ol>
			<sec:ifLoggedIn>
			<g:form url="[resource:categoryInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${categoryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="create" controller="gallery" action="create" params="[category:categoryInstance?.id]"><g:message code="default.new.label" args="['Gallery']" default="New Gallery" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
            </sec:ifLoggedIn>
		</div>
	</body>
</html>