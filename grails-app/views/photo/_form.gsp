<%@ page import="com.iii.fogal.Photo" %>



<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="photo.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${photoInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'fileName', 'error')} required">
	<label for="fileName">
		<g:message code="photo.fileName.label" default="File Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fileName" required="" value="${photoInstance?.fileName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="photo.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${photoInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="photo.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${photoInstance?.location}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'photoDate', 'error')} ">
	<label for="photoDate">
		<g:message code="photo.photoDate.label" default="Photo Date" />
		
	</label>
	<g:textField name="photoDate" value="${photoInstance?.photoDate}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'gallery', 'error')} required">
	<label for="gallery">
		<g:message code="photo.gallery.label" default="Gallery" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="gallery" name="gallery.id" from="${com.iii.fogal.Gallery.list()}" optionKey="id" required="" value="${photoInstance?.gallery?.id}" class="many-to-one"/>

</div>

