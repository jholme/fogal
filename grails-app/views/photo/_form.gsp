<%@ page import="com.iii.fogal.Photo" %>



<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'title', 'error')}">
	<label for="title">
		<g:message code="photo.title.label" default="Title" />
	</label>
	<g:textField name="title" value="${photoInstance?.title}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'originalFilename', 'error')}">
	<label for="originalFilename">
		<g:message code="photo.originalFilename.label" default="Original Filename" />
	</label>
	<g:textField name="originalFilename" value="${photoInstance?.originalFilename}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'thumbnailFilename', 'error')}">
	<label for="thumbnailFilename">
		<g:message code="photo.thumbnailFilename.label" default="Thumbnail Filename" />
	</label>
	<g:textField name="thumbnailFilename" value="${photoInstance?.thumbnailFilename}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="photo.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" value="${photoInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="photo.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${photoInstance?.location}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'photoDate', 'error')} ">
	<label for="photoDate">
		<g:message code="photo.photoDate.label" default="Photo Date" />
		
	</label>
	<g:textField name="photoDate" value="${photoInstance?.photoDate}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'image', 'error')} ">
	<label for="image">
		<g:message code="photo.image.label" default="Image" />
		
	</label>
	<input type="file" id="image" name="image" />

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'fileSize', 'error')}">
	<label for="fileSize">
		<g:message code="photo.fileSize.label" default="File Size" />
	</label>
	<g:field name="fileSize" type="number" value="${photoInstance.fileSize}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: photoInstance, field: 'gallery', 'error')}">
	<label for="gallery">
		<g:message code="photo.gallery.label" default="Gallery" />
	</label>
	<g:select id="gallery" name="gallery.id" from="${com.iii.fogal.Gallery.list()}" optionKey="id" value="${photoInstance?.gallery?.id}" class="many-to-one"/>

</div>

