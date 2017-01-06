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

<div class="fieldcontain ${hasErrors(bean: linkInstance, field: 'groupName', 'error')} required">
	<label for="groupName">
		<g:message code="link.groupName.label" default="Group Name" />
		<span class="required-indicator">*</span>
	</label>
<%--	<g:textField name="groupName" required="" value="${linkInstance?.groupName}"/>--%>
	<g:select name="groupName"
          from="${Link.groupNames}" 
          value="${linkInstance.groupName}" />
<%--          optionKey="id" />--%>

	

</div>

