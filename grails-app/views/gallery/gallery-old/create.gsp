<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gallery.label', default: 'Gallery')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-gallery" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-gallery" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${galleryInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${galleryInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
            <g:if test="${params?.category}">
            <li class="fieldcontain">
                <span id="path-label" class="property-label"><g:message code="gallery.category.label" default="Category" /></span>
                <span class="property-value" aria-labelledby="category-label">
                    <g:link controller="category" action="show" id="${params.category}">
                    <g:fieldValue bean="${com.iii.fogal.Category.findById(params.category as Long)}" field="name"/>
                    </g:link>
                </span>
            </li>
            </g:if>
            
			<g:form url="[resource:galleryInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<g:hiddenField name="category" value="${params.category}" />
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                    <g:link class="show" controller="category" action="show" id="${params.category}"><g:message code="default.button.cancel.label" default="Cancel" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
