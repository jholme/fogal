
<%@ page import="com.iii.fogal.Link" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityName" value="${linkInstance.linkCat.equals(Link.STOS)?"Story":"Link"}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-link" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<%--		<div class="nav" role="navigation">--%>
<%--			<ul>--%>
<%--				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--%>
<%--				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--%>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
<%--			</ul>--%>
<%--		</div>--%>
		<div id="show-link" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            
            <sec:ifLoggedIn>
                <fieldset class="buttons">
                    <g:set var="hrefAction" value="${linkInstance.linkCat.equals(Link.STOS)?"stories":"index"}"></g:set>
                    <g:link class="list" action="${hrefAction}">${entityName} List</g:link>
                    <g:link class="create" action="create"><g:message code="default.button.new.label" default="New ${entityName}" /></g:link>
                </fieldset>
            </sec:ifLoggedIn>

			<ol class="property-list link">
			
				<g:if test="${linkInstance?.linkText}">
				<li class="fieldcontain">
					<span id="linkText-label" class="property-label"><g:message code="link.linkText.label" default="Link Text" /></span>
					
						<span class="property-value" aria-labelledby="linkText-label"><g:fieldValue bean="${linkInstance}" field="linkText"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${linkInstance?.linkHref}">
				<li class="fieldcontain">
					<span id="linkHref-label" class="property-label"><g:message code="link.linkHref.label" default="Link HREF" /></span>
					
						<span class="property-value" aria-labelledby="linkHref-label"><g:fieldValue bean="${linkInstance}" field="linkHref"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${linkInstance?.linkCat}">
				<li class="fieldcontain">
					<span id="linkCat-label" class="property-label"><g:message code="link.linkCat.label" default="Link Category" /></span>
					
						<span class="property-value" aria-labelledby="linkCat-label"><g:fieldValue bean="${linkInstance}" field="linkCat"/></span>
					
				</li>
				</g:if>
			
			</ol>
            <sec:ifLoggedIn>
            <g:form url="[resource:linkInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${linkInstance}"><g:message code="default.button.edit.label" default="Edit ${linkInstance?.linkHref}" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
            </sec:ifLoggedIn>
<%--			<g:form url="[resource:linkInstance, action:'delete']" method="DELETE">--%>
<%--				<fieldset class="buttons">--%>
<%--					<g:link class="edit" action="edit" resource="${linkInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--%>
<%--					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--%>
<%--				</fieldset>--%>
<%--			</g:form>--%>
		</div>
	</body>
</html>
