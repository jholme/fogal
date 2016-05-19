
<%@ page import="com.iii.fogal.Category" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'category.label', default: 'Category')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-category" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<%--		<div class="nav" role="navigation">--%>
<%--			<ul>--%>
<%--				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--%>
<%--				<sec:ifLoggedIn>--%>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
<%--				</sec:ifLoggedIn>--%>
<%--			</ul>--%>
<%--		</div>--%>
		<div id="list-category" class="content scaffold-show" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
            
            <sec:ifLoggedIn>
            <g:form url="[resource:categoryInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="create" action="create"><g:message code="default.button.new.label" default="New Category" /></g:link>
                </fieldset>
            </g:form>
            </sec:ifLoggedIn>

			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'category.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'category.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="path" title="${message(code: 'category.path.label', default: 'Path')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${categoryInstanceList.sort{it.name}}" status="i" var="categoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${categoryInstance.id}">${fieldValue(bean: categoryInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: categoryInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: categoryInstance, field: "path")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${categoryInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
