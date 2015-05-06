<%@ page import="com.iii.fogal.Gallery" %>



<div class="fieldcontain ${hasErrors(bean: galleryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="gallery.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${galleryInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: galleryInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="gallery.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${galleryInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: galleryInstance, field: 'path', 'error')} required">
	<label for="path">
		<g:message code="gallery.path.label" default="Path" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="path" required="" value="${galleryInstance?.path}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: galleryInstance, field: 'photos', 'error')} ">
	<label for="photos">
		<g:message code="gallery.photos.label" default="Photos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${galleryInstance?.photos?}" var="p">
    <li><g:link controller="photo" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="photo" action="create" params="['gallery.id': galleryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'photo.label', default: 'Photo')])}</g:link>
</li>
</ul>


</div>

