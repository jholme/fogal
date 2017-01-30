<%@ page import="com.iii.fogal.Category" %>
<%@ page import="com.iii.fogal.Gallery" %>
<%@ page import="com.iii.fogal.Photo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'story.label', default: 'Photos')}" />
		<title>Photos</title>
	</head>
	<body>
		<a href="#list-link" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="list-link" class="content fieldcontain" role="main">
			<h1>Photos</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
            
<%--            <sec:ifLoggedIn>--%>
<%--            <g:form url="[resource:linkInstance, action:'delete']" method="DELETE">--%>
<%--                <fieldset class="buttons">--%>
<%--                    <g:link class="create" action="create"><g:message code="default.button.new.label" default="New Story" /></g:link>--%>
<%--                </fieldset>--%>
<%--            </g:form>--%>
<%--            </sec:ifLoggedIn>--%>

			<ol class="photo">
			     <g:each in="${Category.list()}" var="category">
			         <h2><g:link controller="category" action="show" resource="${category}">${category.name}</g:link></h2>
			         <g:each in="${category.galleries}" var="gallery">
			             <h3><g:link controller="gallery" action="show" resource="${gallery}">${gallery.name}</g:link></h3>
			                <g:each in="${gallery.photos.sort{a,b-> a.galleryIdx.compareTo(b.galleryIdx)}}" var="photoInstance">
			                <li class="property-list photo">
			                    ${photoInstance.galleryIdx}&nbsp;<g:link class="edit" action="show" resource="${photoInstance}">${photoInstance.title}</g:link>
			                </li>
			                </g:each>
			         </g:each>
			     </g:each>
			</ol>
			
		</div>
	</body>
</html>
