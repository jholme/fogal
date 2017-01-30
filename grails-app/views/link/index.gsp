
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
<%--						<g:sortableColumn property="linkCat" title="${message(code: 'link.linkCat.label', default: 'Group Name')}" />--%>
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
<%--						<td>${fieldValue(bean: linkInstance, field: "linkCat")}</td>--%>
<%--					--%>
<%--					</tr>--%>
<%--				</g:each>--%>
<%--				</tbody>--%>
<%--			</table>--%>
			
			<ol class="property-list link" style="list-style:none">
			<g:each in="${Link.linkCats}" var="linkCat">
			<g:if test="${linkInstanceList.any{it.linkCat.equals(linkCat)}}">
			<li style="margin-top:2em; margin-bottom:1em">${linkCat}</li>
                 <ol class="property-list link">
			     <g:each in="${linkInstanceList}" var="linkInstance">
			         <g:if test="${linkInstance.linkCat.equals(linkCat)}">
                        <li>
                        <sec:ifNotLoggedIn><a href="${linkInstance.linkHref}" target="_blank">${linkInstance.linkText}</a></sec:ifNotLoggedIn>
						<sec:ifLoggedIn>
						<g:form url="[resource:linkInstance, action:'delete']" method="DELETE">
						    <a href="${linkInstance.linkHref}">${linkInstance.linkText}</a>
						    <fieldset class="buttons">
						        <g:link class="edit" action="edit" resource="${linkInstance}"><g:message code="default.button.edit.label" default="Edit ${linkInstance?.linkHref}" /></g:link>
						        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
						    </fieldset>
						</g:form>
						</sec:ifLoggedIn>
                        </li>
			         </g:if>
			     </g:each>
                 </ol>
            </g:if>
			</g:each>
			</ol>
			
<%--			<div class="pagination">--%>
<%--				<g:paginate total="${linkInstanceCount ?: 0}" />--%>
<%--			</div>--%>
			
		</div>
	</body>
</html>
