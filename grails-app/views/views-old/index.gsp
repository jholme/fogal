<!DOCTYPE html>
<%@ page import="com.iii.fogal.Category" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Grails</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

            #category-list ul {
                list-style-position: inside;
                //list-style-type: disc;
            }

            #category-list li {
                line-height: 1.3;
                list-style-position: inside;
                margin: 0.25em 0;
                display: inline;
                //list-style-type: disc;
            }

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
			
			.imgLink {
			     //position: absolute;
			     //padding: 5;
			     //border: 5px solid red;
			     margin: 0.5em;
			     //float: left; // divs are below container;
			     display: inline-block; // divs are inside container
			}
			#tile-container {
                 position: relative;
			     border: 2px dotted green;
			     height: 300px;
			     width: 600px;
			}
			.tile {
			     position: absolute;
			     margin: 0.5em;
			     border: 1px solid red;
			     padding: 2px;
			}
			.portrait {
                height: 100px;
                width: 67px;			     
			}
            .landscape {
                height: 67px;
                width: 100px;                 
            }
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="status" role="complementary">
			<h1>Application Status</h1>
			<ul>
				<li>App version: <g:meta name="app.version"/></li>
				<li>Grails version: <g:meta name="app.grails.version"/></li>
				<li>Groovy version: ${GroovySystem.getVersion()}</li>
				<li>JVM version: ${System.getProperty('java.version')}</li>
				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li>Domains: ${grailsApplication.domainClasses.size()}</li>
				<li>Services: ${grailsApplication.serviceClasses.size()}</li>
				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
			</ul>
<%--			<h1>Installed Plugins</h1>--%>
<%--			<ul>--%>
<%--				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">--%>
<%--					<li>${plugin.name} - ${plugin.version}</li>--%>
<%--				</g:each>--%>
<%--			</ul>--%>
		</div>
		<div id="page-body" role="main">
<%--			<h1>Welcome to Grails</h1>--%>
<%--			<p>Congratulations, you have successfully started your first Grails application! At the moment--%>
<%--			   this is the default page, feel free to modify it to either redirect to a controller or display whatever--%>
<%--			   content you may choose. Below is a list of controllers that are currently deployed in this application,--%>
<%--			   click on each to execute its default action:</p>--%>

			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
            
            <div id="category-list" role="navigation" style="border:5px dotted black">
                 <h2>Category Mix</h2>
                 <g:each var="cat" in="${Category.list()}" status="idx">
                     <div id="${cat.id}" class="imgLink">
                     <g:link controller="category" action="show" id="${cat.id}">
                        <img src="<g:createLink controller='category' action='thumbnail' id='${cat.id}'/>"/>
                     </g:link>
                     </div>
                 </g:each>
            </div>
 			
			<div id="category-list" role="navigation">
			     <h2>Categories</h2>
			     <ul>
			         <g:each var="cat" in="${Category.list()}" status="idx">
                     <g:if test="${idx > 0}"><span style="color: #48802c;">&nbsp;|&nbsp;</span></g:if>
			             <li class="controller"><g:link controller="category" action="show" id="${cat.id}">${fieldValue(bean: cat, field: "name")}</g:link></li>
			         </g:each>
			     </ul>
			</div>
			
            <div id="category-list" role="navigation">
                 <h2>Category Images</h2>
                 <ul>
                     <g:each var="cat" in="${Category.list()}">
                         <li class="controller">${cat.name}<br/>
                         <g:link controller="category" action="show" id="${cat.id}">
                            <img src="<g:createLink controller='category' action='thumbnail' id='${cat.id}'/>"/>
                         </g:link>
                         </li>
                     </g:each>
                 </ul>
            </div>
            
            <div id="tile-container">
                <div class="tile portrait" id="tile_0" style="top:0px; left:0px"><img src="<g:createLink controller='category' action='thumbnail' id='1'/>"/></div>
                <div class="tile portrait" id="tile_1" style="top:110px; left:0px">tile 1</div>
                <div class="tile landscape" id="tile_2" style="top:0px; left:75px">tile 2</div>
                <div class="tile landscape" id="tile_3" style="top:75px; left:75px">tile 3</div>
                <div class="tile landscape" id="tile_4" style="top:150px; left:75px">tile 4</div>
                
                <div class="tile portrait" id="tile_5" style="top:0px; left:185px">tile 5</div>
                <div class="tile portrait" id="tile_6" style="top:110px; left:185px">tile 6</div>
                <div class="tile landscape" id="tile_7" style="top:0px; left:260px">tile 7</div>
                <div class="tile landscape" id="tile_8" style="top:75px; left:260px">tile 8</div>
                <div class="tile landscape" id="tile_9" style="top:150px; left:260px">tile 9</div>
            </div>
           
		</div>
	</body>
</html>
