
<%@ page import="com.iii.fogal.Photo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-photo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'photo.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="originalFilename" title="${message(code: 'photo.originalFilename.label', default: 'Original Filename')}" />
					
						<g:sortableColumn property="thumbnailFilename" title="${message(code: 'photo.thumbnailFilename.label', default: 'Thumbnail Filename')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'photo.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'photo.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="photoDate" title="${message(code: 'photo.photoDate.label', default: 'Photo Date')}" />
					
						<g:sortableColumn property="image" title="${message(code: 'photo.image.label', default: 'Image')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${photoInstanceList}" status="i" var="photoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${photoInstance.id}">${fieldValue(bean: photoInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: photoInstance, field: "originalFilename")}</td>
					
						<td>${fieldValue(bean: photoInstance, field: "thumbnailFilename")}</td>
					
						<td>${fieldValue(bean: photoInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: photoInstance, field: "location")}</td>
					
						<td>${fieldValue(bean: photoInstance, field: "photoDate")}</td>
					
						<td><img src="<g:createLink controller='photo' action='renderThumbnailImage' id='${photoInstance.id}'/>"/></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${photoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
