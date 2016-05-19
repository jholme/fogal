
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
<%--		<div class="nav" role="navigation">--%>
<%--			<ul>--%>
<%--				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--%>
<%--				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--%>
<%--                <sec:ifLoggedIn>--%>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
<%--				</sec:ifLoggedIn>--%>
<%--			</ul>--%>
<%--		</div>--%>
		<div id="show-photo" class="content scaffold-show" role="main">
			<h1><g:if test="${photoInstance?.title}">${photoInstance?.title}</g:if></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
            <ol class="photo-breadcrumb">
               <li><g:link controller="gallery" action="show" id="${photoInstance.gallery.id}">${photoInstance.gallery.name}</li></g:link>
               <li style="color:#fff">&gt;</li>
               <li><g:link controller="category" action="show" id="${photoInstance.gallery.category.id}">${photoInstance.gallery.category.name}</li></g:link>
            </ol>
            
            <ol class="photo-next-prev">
                <li>Previous</li>
                <li>Description</li>
                <li>Next</li>
            </ol>
            
<%--			<ol class="property-list photo">--%>
			
<%--				<g:if test="${photoInstance?.title}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="title-label" class="property-label"><g:message code="photo.title.label" default="Title" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${photoInstance}" field="title"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
            
<%--                <g:if test="${photoInstance?.credit}">--%>
<%--                <li class="fieldcontain">--%>
<%--                    <span id="credit-label" class="property-label"><g:message code="photo.credit.label" default="Credit" /></span>--%>
<%--                    --%>
<%--                        <span class="property-value" aria-labelledby="credit-label"><g:fieldValue bean="${photoInstance}" field="credit"/></span>--%>
<%--                    --%>
<%--                </li>--%>
<%--                </g:if>--%>
            
<%--                <g:if test="${photoInstance?.copyright}">--%>
<%--                <li class="fieldcontain">--%>
<%--                    <span id="copyright-label" class="property-label"><g:message code="photo.copyright.label" default="Copyright" /></span>--%>
<%--                    --%>
<%--                        <span class="property-value" aria-labelledby="copyright-label"><g:fieldValue bean="${photoInstance}" field="copyright"/></span>--%>
<%--                    --%>
<%--                </li>--%>
<%--                </g:if>--%>
			
<%--				<g:if test="${photoInstance?.originalFilename}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="originalFilename-label" class="property-label"><g:message code="photo.originalFilename.label" default="Original Filename" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="originalFilename-label"><g:fieldValue bean="${photoInstance}" field="originalFilename"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--				<g:if test="${photoInstance?.thumbnailFilename}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="thumbnailFilename-label" class="property-label"><g:message code="photo.thumbnailFilename.label" default="Thumbnail Filename" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="thumbnailFilename-label"><g:fieldValue bean="${photoInstance}" field="thumbnailFilename"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--				<g:if test="${photoInstance?.description}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="description-label" class="property-label"><g:message code="photo.description.label" default="Description" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${photoInstance}" field="description"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--				<g:if test="${photoInstance?.location}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="location-label" class="property-label"><g:message code="photo.location.label" default="Location" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${photoInstance}" field="location"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--				<g:if test="${photoInstance?.photoDate}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="photoDate-label" class="property-label"><g:message code="photo.photoDate.label" default="Photo Date" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="photoDate-label"><g:fieldValue bean="${photoInstance}" field="photoDate"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--				<g:if test="${photoInstance?.thumbnailFilename}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="image-label" class="property-label"><g:message code="photo.image.label" default="Image" /></span>--%>
<%--					<span class="property-value" aria-labelledby="image-label"><img src="<g:createLink controller='photo' action='renderThumbnailImage' id='${photoInstance.id}'/>"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--				<g:if test="${photoInstance?.fileSize}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="fileSize-label" class="property-label"><g:message code="photo.fileSize.label" default="File Size" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="fileSize-label"><g:fieldValue bean="${photoInstance}" field="fileSize"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
            
<%--                <g:if test="${photoInstance?.imageHeight}">--%>
<%--                <li class="fieldcontain">--%>
<%--                    <span id="fileSize-label" class="property-label"><g:message code="photo.imageHeight.label" default="Image Height" /></span>--%>
<%--                    --%>
<%--                        <span class="property-value" aria-labelledby="imageHeight-label"><g:fieldValue bean="${photoInstance}" field="imageHeight"/></span>--%>
<%--                    --%>
<%--                </li>--%>
<%--                </g:if>--%>
            
<%--                <g:if test="${photoInstance?.imageWidth}">--%>
<%--                <li class="fieldcontain">--%>
<%--                    <span id="fileSize-label" class="property-label"><g:message code="photo.imageWidth.label" default="Image Width" /></span>--%>
<%--                    --%>
<%--                        <span class="property-value" aria-labelledby="imageWidth-label"><g:fieldValue bean="${photoInstance}" field="imageWidth"/></span>--%>
<%--                    --%>
<%--                </li>--%>
<%--                </g:if>--%>
            
<%--                <g:if test="${photoInstance?.aspect}">--%>
<%--                <li class="fieldcontain">--%>
<%--                    <span id="fileSize-label" class="property-label"><g:message code="photo.aspect.label" default="Aspect" /></span>--%>
<%--                    --%>
<%--                        <span class="property-value" aria-labelledby="aspect-label"><g:fieldValue bean="${photoInstance}" field="aspect"/></span>--%>
<%--                    --%>
<%--                </li>--%>
<%--                </g:if>--%>
			
<%--				<g:if test="${photoInstance?.gallery}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="gallery-label" class="property-label"><g:message code="photo.gallery.label" default="Gallery" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="gallery-label"><g:link controller="gallery" action="show" id="${photoInstance?.gallery?.id}">${photoInstance?.gallery?.encodeAsHTML()}</g:link></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--			</ol>--%>
			
			<g:if test="${photoInstance?.originalFilename}"><br/>
<%--				<span class="property-value" aria-labelledby="image-label"><img src="<g:createLink controller='photo' action='renderMainImage' id='${photoInstance.id}'/>"/></span>--%>
                <span id="img-container">
                <img class="rendered-image" src="<g:createLink controller='photo' action='renderMainImage' id='${photoInstance.id}'/>"/><br/>
                <span class="rendered-image">${photoInstance?.title }</span>
                </span>
			</g:if>
            
            <sec:ifLoggedIn>
            <g:form url="[resource:photoInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${photoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form><br/>
            </sec:ifLoggedIn>
		</div>
	</body>
</html>
