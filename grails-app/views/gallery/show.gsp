
<%@ page import="com.iii.fogal.Gallery" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gallery.label', default: 'Gallery')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gallery" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gallery" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gallery">
			
				<g:if test="${galleryInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="gallery.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${galleryInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${galleryInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="gallery.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${galleryInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${galleryInstance?.path}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label"><g:message code="gallery.path.label" default="Path" /></span>
					
						<span class="property-value" aria-labelledby="path-label"><g:fieldValue bean="${galleryInstance}" field="path"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${galleryInstance?.photos}">
				<li class="fieldcontain">
					<span id="photos-label" class="property-label"><g:message code="gallery.photos.label" default="Photos" /></span>
					
						<g:each in="${galleryInstance.photos}" var="p">
						<span class="property-value" aria-labelledby="photos-label">
							<g:link controller="photo" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link>
							<img src="<g:createLink controller='photo' action='renderThumbnail' id='${p.id}'/>" align="middle"/>
						</span>
						</g:each>
					
				</li>
				</g:if>
				
				<li class="fieldcontain">
					
					<g:uploadForm url="[resource:galleryInstance, action:'uploadPhoto']" enctype="multipart/form-data">
						<fieldset class="form">
							<label for="image">
								<span class="buttons"><g:submitButton name="create" class="save" value="${message(code: 'default.button.upload.label', default: 'Upload Images')}" /></span>
							</label>
							<input type="file" id="image" name="image" multiple="true" />
							<input type="hidden" name="gallery" value="${galleryInstance?.id}" />
						</fieldset>
					</g:uploadForm>					
					
				</li>
			
			</ol>
			<g:form url="[resource:galleryInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${galleryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
