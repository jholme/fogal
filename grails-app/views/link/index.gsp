
<%@ page import="com.iii.fogal.Link" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'link.label', default: 'Link')}" />
		<title>Links</title>
	</head>
	<body>
		<a href="#list-link" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<%--		<div class="nav" role="navigation">--%>
<%--			<ul>--%>
<%--				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--%>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
<%--			</ul>--%>
<%--		</div>--%>
		<div id="list-link" class="content fieldcontain" role="main">
			<h1>Links</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
            
            <sec:ifLoggedIn>
            <g:form url="[resource:linkInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="create" action="create"><g:message code="default.button.new.label" default="New Link" /></g:link>
                </fieldset>
            </g:form>
            </sec:ifLoggedIn>

<%--			<table>--%>
<%--			<thead>--%>
<%--					<tr>--%>
<%--					--%>
<%--						<g:sortableColumn property="linkText" title="${message(code: 'link.linkText.label', default: 'Link Text')}" />--%>
<%--					--%>
<%--						<g:sortableColumn property="linkHref" title="${message(code: 'link.linkHref.label', default: 'Link HREF')}" />--%>
<%--					--%>
<%--						<g:sortableColumn property="groupName" title="${message(code: 'link.groupName.label', default: 'Group Name')}" />--%>
<%--					--%>
<%--					</tr>--%>
<%--				</thead>--%>
<%--				<tbody>--%>
<%--				<g:each in="${linkInstanceList}" status="i" var="linkInstance">--%>
<%--					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">--%>
<%--					--%>
<%--						<td><g:link action="show" id="${linkInstance.id}">${fieldValue(bean: linkInstance, field: "linkText")}</g:link></td>--%>
<%--					--%>
<%--						<td>${fieldValue(bean: linkInstance, field: "linkHref")}</td>--%>
<%--					--%>
<%--						<td>${fieldValue(bean: linkInstance, field: "groupName")}</td>--%>
<%--					--%>
<%--					</tr>--%>
<%--				</g:each>--%>
<%--				</tbody>--%>
<%--			</table>--%>
			
			<ol class="property-list link" style="list-style:none">
			<g:each in="${Link.groupNames}" var="groupName">
			<li style="margin-top:2em; margin-bottom:1em">${groupName}</li>
                 <ol class="property-list link">
			     <g:each in="${linkInstanceList}" var="linkInstance">
			         <g:if test="${linkInstance.groupName.equals(groupName)}">
			             <li>
			             <a href="${linkInstance.linkHref}">${linkInstance.linkText}</a>
			             <sec:ifLoggedIn>&nbsp;&lt;&lt;<g:link action="edit" id="${linkInstance.id}">Edit</g:link>&gt;&gt;</sec:ifLoggedIn>
			             </li>
			         </g:if>
			     </g:each>
                 </ol>
			</g:each>
			</ol>
			
<%--			<div class="pagination">--%>
<%--				<g:paginate total="${linkInstanceCount ?: 0}" />--%>
<%--			</div>--%>
			
		</div>
	</body>
</html>
