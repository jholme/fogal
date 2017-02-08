<%@ page import="com.iii.fogal.*" %>



<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="category.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${categoryInstance?.name}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="category.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" value="${categoryInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'path', 'error')} required">
	<label for="path">
		<g:message code="category.path.label" default="Path" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="path" required="" value="${categoryInstance?.path}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'homePageTile', 'error')} ">
    <label for="homePageTile">
        <g:message code="category.homePageTile.label" default="homePageTile" />
    </label>
    <g:select name="homePageTile" from="${HomePageTile.values()}" value="${categoryInstance.homePageTile}" />

</div>

<%--<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'galleries', 'error')} ">--%>
<%-- don't allow pre-existing galleries to be swapped between categories. on the category/edit page, provide 'add gallery' button plus list of current galleries --%>
<%--	<label for="galleries"><g:message code="category.galleries.label" default="Galleries" /></label>--%>
<%--	<span>--%>
<%--	<g:each in="${categoryInstance.galleries}" var="g"><span class="property-value" aria-labelledby="galleries-label"><g:link controller="gallery" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span></g:each>--%>
<%--	</span>--%>
<%--	<g:select name="galleries" from="${com.iii.fogal.Gallery.list()}" multiple="multiple" optionKey="id" size="5" value="${categoryInstance?.galleries*.id}" class="many-to-many"/>--%>
<g:if test="${categoryInstance?.galleries}">
	<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'galleries', 'error')} ">
	    <span id="galleries-label" class="property-label"><g:message code="category.galleries.label" default="Galleries" /></span>
	    
	        <g:each in="${categoryInstance.galleries}" var="g">
	        <span class="property-value" aria-labelledby="galleries-label"><g:link controller="gallery" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
	        </g:each>
	    
	</div>
</g:if>

	

</div>

