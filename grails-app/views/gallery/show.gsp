
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
<%--		<div class="nav" role="navigation">--%>
<%--			<ul>--%>
<%--				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--%>
<%--				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--%>
<%--<!-- don't create new gallery on gallery page - add new gallery from category page -->--%>
<%--<!--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li> -->--%>
<%--			</ul>--%>
<%--		</div>--%>
		<div id="show-gallery" class="content scaffold-show" role="main">
            <h1>${galleryInstance?.name} ${entityName}</h1>
<%--			<h1><g:message code="default.show.label" args="[entityName]" /></h1>--%>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
            <ol class="gallery-category">
               <li><g:link controller="category" action="show" id="${galleryInstance.category.id}">${galleryInstance.category.name}</li></g:link>
            </ol>
<%--            <ol class="gallery-description">--%>
<%--               <li>Description</li>--%>
<%--            </ol>--%>
			
<%--			<ol class="property-list-gallery">--%>

<%-- old way --%>
<%--			<div id="gallery-photo-container">--%>
<%--                <g:if test="${galleryInstance?.photos}">--%>
<%--                        <g:each in="${galleryInstance.photos}" var="p">--%>
<%--                        <span class="photo-in-gallery">--%>
<%--                            <g:link class="photo-in-gallery-caption" controller="photo" action="show" id="${p?.id}"><img src="<g:createLink controller='photo' action='renderThumbnailImage' id='${p?.id}'/>" align="middle"/></g:link>--%>
<%--                            <g:link class="photo-in-gallery-caption" controller="photo" action="show" id="${p?.id}">${p?.encodeAsHTML()}</g:link>--%>
<%--                        </span>--%>
<%--                        </g:each>--%>
<%--                </g:if>--%>
<%--            </div>--%>
			
<%--				<g:if test="${galleryInstance?.name}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="name-label" class="property-label"><g:message code="gallery.name.label" default="Name" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${galleryInstance}" field="name"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--				<g:if test="${galleryInstance?.description}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="description-label" class="property-label"><g:message code="gallery.description.label" default="Description" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${galleryInstance}" field="description"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
<%--				<g:if test="${galleryInstance?.path}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="path-label" class="property-label"><g:message code="gallery.path.label" default="Path" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="path-label"><g:fieldValue bean="${galleryInstance}" field="path"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
            
<%--                <g:if test="${galleryInstance?.category}">--%>
<%--                <li class="fieldcontain">--%>
<%--                    <span id="path-label" class="property-label"><g:message code="gallery.category.label" default="Category" /></span>--%>
<%--                    --%>
<%--                        <span class="property-value" aria-labelledby="category-label">--%>
<%--                            <g:link controller="category" action="show" id="${galleryInstance.category.id}">--%>
<%--                            <g:fieldValue bean="${galleryInstance.category}" field="name"/>--%>
<%--                            </g:link>--%>
<%--                        </span>--%>
<%--                    --%>
<%--                </li>--%>
<%--                </g:if>--%>
<%--			--%>
<%--				<g:if test="${galleryInstance?.photos}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="photos-label" class="property-label"><g:message code="gallery.photos.label" default="Photos" /></span>--%>
<%--					--%>
<%--						<g:each in="${galleryInstance.photos}" var="p">--%>
<%--						<span class="photo-in-gallery">--%>
<%--							<g:link class="photo-in-gallery-caption" controller="photo" action="show" id="${p?.id}"><img src="<g:createLink controller='photo' action='renderThumbnailImage' id='${p?.id}'/>" align="middle"/></g:link>--%>
<%--                            <g:link class="photo-in-gallery-caption" controller="photo" action="show" id="${p?.id}">${p?.encodeAsHTML()}</g:link>--%>
<%--						</span>--%>
<%--						</g:each>--%>
<%--				</li>--%>
<%--				</g:if>--%>
<%--				</div>--%>
<%--            </ol>--%>

<%-- new way --%>
            <ol class="property-list category">
            <g:each in="${galleryInstance.photos}" var="p">
               <li class="cat-gal">
               <span class="property-value">
               <g:link controller="photo" action="show" id="${p?.id}"><img src="<g:createLink controller='photo' action='renderThumbnailImage' id='${p?.id}'/>"/></g:link><br/>
               <g:link class="gallery-caption" controller="photo" action="show" id="${p?.id}">${p?.encodeAsHTML()}</g:link>
               </span>
               </li>
            </g:each>
            </ol>
				
            
			<sec:ifLoggedIn>
            <ol class="property-list">
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
<%--            </sec:ifLoggedIn>--%>
<%--            <sec:ifLoggedIn>--%>
            <g:form url="[resource:galleryInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>
                    <g:link class="edit" action="edit" resource="${galleryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"  /><!-- onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" -->
                </fieldset>
            </g:form>
            </sec:ifLoggedIn>
                
<%--            <sec:ifLoggedIn>--%>
<%--			<g:form url="[resource:galleryInstance, action:'delete']" method="DELETE">--%>
<%--				<fieldset class="buttons">--%>
<%--                    <g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>--%>
<%--					<g:link class="edit" action="edit" resource="${galleryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>--%>
<%--					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"  /><!-- onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" -->--%>
<%--				</fieldset>--%>
<%--			</g:form>--%>
<%--            </sec:ifLoggedIn>--%>
		</div>
	</body>
</html>
