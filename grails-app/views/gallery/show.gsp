
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
		<div id="show-gallery" class="content scaffold-show" role="main">
            <h1>${galleryInstance?.name} ${entityName}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
            <ol class="gallery-category">
               <li><g:link controller="category" action="show" id="${galleryInstance.category.id}">${galleryInstance.category.name}</li></g:link>
            </ol>

            <ol class="property-list category">
            <g:each in="${galleryInstance.photos.sort{a,b->a.galleryIdx<=>b.galleryIdx}}" var="p" status="i">
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
            
            <g:form url="[resource:galleryInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>
                    <g:link class="edit" action="edit" resource="${galleryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"  /><!-- onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" -->
                </fieldset>
            </g:form>
            </sec:ifLoggedIn>
                
		</div>
	</body>
</html>
