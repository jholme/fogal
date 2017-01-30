<%@ page import="com.iii.fogal.Link" %>

<div class="fieldcontain ${hasErrors(bean: linkInstance, field: 'linkText', 'error')} required">
	<label for="linkText">
		<g:message code="link.linkText.label" default="Link Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="linkText" required="" value="${linkInstance?.linkText}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: linkInstance, field: 'linkHref', 'error')} required">
	<label for="linkHref">
		<g:message code="link.linkHref.label" default="Link HREF" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="url" name="linkHref" required="" value="${linkInstance?.linkHref}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: linkInstance, field: 'linkCat', 'error')} required">
	<label for="linkCat">
		<g:message code="link.linkCat.label" default="Link Category" />
		<span class="required-indicator">*</span>
	</label>
<%--	<g:textField name="linkCat" required="" value="${linkInstance?.linkCat}"/>--%>
	<g:select name="linkCat"
          from="${Link.allCats}" 
          value="${linkInstance.linkCat}" />&nbsp;To create a <strong>Story</strong> link, select <strong>Stories</strong>
<%--          optionKey="id" />--%>

	

</div>

