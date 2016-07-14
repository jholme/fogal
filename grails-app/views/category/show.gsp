
<%@ page import="com.iii.fogal.Category" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		  <g:if test="${categoryInstance?.name}">
        <g:set var="entityName" value="${categoryInstance?.name}" />
            <title>${categoryInstance?.name}</title>
		</g:if>
		<g:else>
            <g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}" />
            <title><g:message code="default.show.label" args="[entityName]" /></title>
		</g:else>
	</head>
	<body>
		<a href="#show-category" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

    	<div id="show-category" class="content scaffold-show" role="main">
			<h1>${entityName}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
            <sec:ifLoggedIn>
                <fieldset class="buttons">
                    <g:link class="list" action="index">Category List</g:link>
                    <g:link class="create" action="create"><g:message code="default.button.new.label" default="New Category" /></g:link>
                    <g:link class="create" controller="gallery" action="create" params="[category:categoryInstance?.id]"><g:message code="default.new.label" args="['Gallery']" default="New Gallery" /></g:link>
                </fieldset>
            </sec:ifLoggedIn>

            <ol class="property-list category">
            <g:each in="${categoryInstance.galleries}" var="g">
               <li class="cat-gal">
               <span class="property-value">
               <g:link controller="gallery" action="show" id="${g?.id}"><img src="<g:createLink controller='gallery' action='galleryThum' id='${g?.id}'/>"/></g:link><br/>
               <g:link class="gallery-caption" controller="gallery" action="show" id="${g?.id}">${g?.name.encodeAsHTML()}</g:link>
               </span>
               </li>
            </g:each>
            </ol>

			<sec:ifLoggedIn>
            <g:form url="[resource:categoryInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${categoryInstance}"><g:message code="default.button.edit.label" default="Edit ${categoryInstance?.name}" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
<%--			<g:form url="[resource:categoryInstance, action:'delete']" method="DELETE">--%>
<%--				<fieldset class="buttons">--%>
<%--					<g:link class="edit" action="edit" resource="${categoryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--%>
<%--					<g:link class="create" controller="gallery" action="create" params="[category:categoryInstance?.id]"><g:message code="default.new.label" args="['Gallery']" default="New Gallery" /></g:link>--%>
<%--					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--%>
<%--				</fieldset>--%>
<%--			</g:form>--%>
            </sec:ifLoggedIn>
            
		</div>
	</body>
</html>
