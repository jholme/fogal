
<%@ page import="com.iii.fogal.Photo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-photo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list photo">
			
				<g:if test="${photoInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="photo.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${photoInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${photoInstance?.originalFilename}">
				<li class="fieldcontain">
					<span id="originalFilename-label" class="property-label"><g:message code="photo.originalFilename.label" default="Original Filename" /></span>
					
						<span class="property-value" aria-labelledby="originalFilename-label"><g:fieldValue bean="${photoInstance}" field="originalFilename"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${photoInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="photo.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${photoInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${photoInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="photo.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${photoInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${photoInstance?.photoDate}">
				<li class="fieldcontain">
					<span id="photoDate-label" class="property-label"><g:message code="photo.photoDate.label" default="Photo Date" /></span>
					
						<span class="property-value" aria-labelledby="photoDate-label"><g:fieldValue bean="${photoInstance}" field="photoDate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${photoInstance?.originalFilename}">
				<li class="fieldcontain">
					<span id="image-label" class="property-label"><g:message code="photo.image.label" default="Image" /></span>
					<span class="property-value" aria-labelledby="image-label"><img src="<g:createLink controller='photo' action='renderImage' id='${photoInstance.id}'/>"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${photoInstance?.fileSize}">
				<li class="fieldcontain">
					<span id="fileSize-label" class="property-label"><g:message code="photo.fileSize.label" default="File Size" /></span>
					
						<span class="property-value" aria-labelledby="fileSize-label"><g:fieldValue bean="${photoInstance}" field="fileSize"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${photoInstance?.gallery}">
				<li class="fieldcontain">
					<span id="gallery-label" class="property-label"><g:message code="photo.gallery.label" default="Gallery" /></span>
					
						<span class="property-value" aria-labelledby="gallery-label"><g:link controller="gallery" action="show" id="${photoInstance?.gallery?.id}">${photoInstance?.gallery?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:photoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${photoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
