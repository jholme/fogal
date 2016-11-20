
<%@ page import="com.iii.fogal.Photo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.structure.min.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui.theme.css')}" type="text/css">
  	</head>
	<body>
		<a href="#show-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-photo" class="content scaffold-show" role="main">
			<h1><g:if test="${photoInstance?.title}">${photoInstance?.title}</g:if></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
            <ol class="photo-breadcrumb">
               <li><g:link controller="gallery" action="show" id="${photoInstance.gallery.id}">${photoInstance.gallery.name}</li></g:link>
               <li style="color:#fff">&gt;</li>
               <li><g:link controller="category" action="show" id="${photoInstance.gallery.category.id}">${photoInstance.gallery.category.name}</li></g:link>
            </ol>
            
            <ol class="photo-next-prev">
                <g:if test="${photoInstance.galleryIdx > 1}"><li><g:link action="show" id="${Photo.findByGalleryAndGalleryIdx(photoInstance.gallery, photoInstance.galleryIdx - 1).id}">Previous</g:link></li></g:if><g:else><li class="invis">Previous</li></g:else>
                <li id="dialog-link" style="cursor:pointer">Description</li>
                <g:if test="${photoInstance.galleryIdx < photoInstance.gallery.photos.size()}"><li><g:link action="show" id="${Photo.findByGalleryAndGalleryIdx(photoInstance.gallery, photoInstance.galleryIdx + 1).id}">Next</g:link></li></g:if>
            </ol>

            <sec:ifLoggedIn>
            <g:form url="[resource:photoInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${photoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form><br/>
            </sec:ifLoggedIn>
		</div>
 
		<div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-dialog-buttons ui-draggable ui-resizable" style="display:none" id="dialog" title="Description">
		  <p>${photoInstance.description}</p>
		</div>
            
<%--            <div style="display:block; margin-left:-350px">--%>
            <div style="display:block; margin-left:${photoInstance.aspect.equals(Photo.LANDSCAPE) ? '-115px' : '0px'}"><!-- works with dnbsalinasfieldcleaners01a.jpg
             -->
            <g:if test="${photoInstance?.originalFilename}"><br/>
                <span id="img-container">
                <img class="rendered-image" src="<g:createLink controller='photo' action='renderMainImage' id='${photoInstance.id}'/>"/><br/>
                <span class="rendered-image" style="margin-left:${photoInstance.aspect.equals(Photo.LANDSCAPE) ? '115px' : '0px'}">${photoInstance?.encodeAsHTML()}</span>
                </span>
            </g:if>
            </div>
 
        <script>
        $( "#dialog" ).dialog({
            autoOpen: false,
            width: 400,
            buttons: [
                {
                    text: "Ok",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                },
                {
                    text: "Cancel",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                }
            ]
        });

        // Link to open the dialog
        $( "#dialog-link" ).click(function( event ) {
            $( "#dialog" ).dialog( "open" );
            event.preventDefault();
        });

        </script>
    </body>
</html>
