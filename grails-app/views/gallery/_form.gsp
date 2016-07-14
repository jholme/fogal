<%@ page import="com.iii.fogal.Gallery" %>

<div class="fieldcontain ${hasErrors(bean: galleryInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="gallery.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${galleryInstance?.name}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: galleryInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="gallery.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" value="${galleryInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: galleryInstance, field: 'path', 'error')} required">
	<label for="path">
		<g:message code="gallery.path.label" default="Path" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="path" required="" value="${galleryInstance?.path}" size="60"/>

</div>

<div class="fieldcontain ${hasErrors(bean: galleryInstance, field: 'photos', 'error')} ">
	<label for="photos">
		<g:message code="gallery.photos.label" default="Photos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${galleryInstance.photos.sort{a,b->a.galleryIdx<=>b.galleryIdx}}" var="p">
<sec:ifLoggedIn>
    <li>
	    <g:link controller="photo" action="show" id="${p?.id}">${p?.encodeAsHTML()}</g:link>&nbsp;<g:textField id="galleryIdx_${p?.id}" name="galleryIdx_${p?.id}" required="" value="${p?.galleryIdx}" size="2"/>
        <br/><img src="<g:createLink controller='photo' action='renderThumbnailImage' id='${p?.id}'/>"/>
    </li>
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
    <li><g:link controller="photo" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link>&nbsp;(${p.galleryIdx}|${p.id})</li>
</sec:ifNotLoggedIn>
</g:each>
<%--<li class="add">--%>
<%--<g:link controller="photo" action="create" params="['gallery.id': galleryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'photo.label', default: 'Photo')])}</g:link>--%>
<%--</li>--%>
</ul>


</div>

<%--<script>--%>
<%--<g:each var="pIdx" in="${galleryInstance.photos.galleryIdx}">--%>
<%--$( "#galleryIdx_${pIdx}" ).change(function() {--%>
<%--      //alert($(this).val());--%>
<%--      incrementVal(${pIdx}, $(this).val());--%>
<%--    });--%>
<%--</g:each>--%>
<%--function incrementVal(idx, val) {--%>
<%--	//alert("incrementVal: " + idx);--%>
<%--	$('input[name^="galleryIdx_"]').each(function() {--%>
<%--		var thisIdx = $(this).attr('id').substring($(this).attr('id').length-1);--%>
<%--		alert("iterating on " + thisIdx + " with value " + $(this).val());--%>
<%--	});--%>
<%--}--%>
<%--</script>--%>

