<!DOCTYPE html>
<%@ page import="com.iii.fogal.*" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Photographs &amp; Stories</title>
		<style type="text/css" media="screen">
<%--			#status {--%>
<%--				background-color: #eee;--%>
<%--				border: .2em solid #fff;--%>
<%--				margin: 2em 2em 1em;--%>
<%--				padding: 1em;--%>
<%--				width: 12em;--%>
<%--				float: left;--%>
<%--				-moz-box-shadow: 0px 0px 1.25em #ccc;--%>
<%--				-webkit-box-shadow: 0px 0px 1.25em #ccc;--%>
<%--				box-shadow: 0px 0px 1.25em #ccc;--%>
<%--				-moz-border-radius: 0.6em;--%>
<%--				-webkit-border-radius: 0.6em;--%>
<%--				border-radius: 0.6em;--%>
<%--			}--%>
<%----%>
<%--			.ie6 #status {--%>
<%--				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */--%>
<%--			}--%>
<%----%>
<%--			#status ul {--%>
<%--				font-size: 0.9em;--%>
<%--				list-style-type: none;--%>
<%--				margin-bottom: 0.6em;--%>
<%--				padding: 0;--%>
<%--			}--%>
<%----%>
<%--			#status li {--%>
<%--				line-height: 1.3;--%>
<%--			}--%>
<%----%>
<%--			#status h1 {--%>
<%--				text-transform: uppercase;--%>
<%--				font-size: 1.1em;--%>
<%--				margin: 0 0 0.3em;--%>
<%--			}--%>

			#page-body {
                margin: 2em 1em 1.25em 2em;
<%--				margin: 2em 1em 1.25em 18em;--%>
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

            #controller-list {
<%--                clear: left;--%>
<%--                position: relative;--%>
                display: block;
			    padding-top: 10px;
			    margin-left: auto;
			    margin-right: auto;
			    width: 370px;
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
<%--                clear: all;--%>
<%--                list-style-type: disc;--%>
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
			    display: block;
			    padding-top: 10px;
			    margin-left: auto;
			    margin-right: auto;
                position: relative;
                height: 500px;
                width:720px;
<%--			     border: 1px dotted white;--%>
<%--			     height: 300px;--%>
<%--			     width: 600px;--%>
			}
			.tile {
			     position: absolute;
			     margin: 0.5em;
			     border: 1px solid red;
			     padding: 2px;
			     text-align: center;
			     font-size: small;
			     font-weight: bold;
			}
			.portrait {
                height: 200px;
                width: 134px;			     
			}
            .landscape {
                height: 134px;
                width: 200px;                 
            }
		</style>
	</head>
	<body>
<%--		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--%>
<%--		<div id="status" role="complementary">--%>
<%--			<h1>Application Status</h1>--%>
<%--			<ul>--%>
<%--				<li>App version: <g:meta name="app.version"/></li>--%>
<%--				<li>Grails version: <g:meta name="app.grails.version"/></li>--%>
<%--				<li>Groovy version: ${GroovySystem.getVersion()}</li>--%>
<%--				<li>JVM version: ${System.getProperty('java.version')}</li>--%>
<%--				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>--%>
<%--				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>--%>
<%--				<li>Domains: ${grailsApplication.domainClasses.size()}</li>--%>
<%--				<li>Services: ${grailsApplication.serviceClasses.size()}</li>--%>
<%--				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>--%>
<%--			</ul>--%>
<%--			<h1>Installed Plugins</h1>--%>
<%--			<ul>--%>
<%--				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">--%>
<%--					<li>${plugin.name} - ${plugin.version}</li>--%>
<%--				</g:each>--%>
<%--			</ul>--%>
<%--		</div>--%>
		<div id="page-body" role="main">
<%--			<h1>Welcome to Grails</h1>--%>
<%--			<p>Congratulations, you have successfully started your first Grails application! At the moment--%>
<%--			   this is the default page, feel free to modify it to either redirect to a controller or display whatever--%>
<%--			   content you may choose. Below is a list of controllers that are currently deployed in this application,--%>
<%--			   click on each to execute its default action:</p>--%>
            
            <div id="tile-container">
            
            <!-- tile_01 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_01)?.id}" />
            <g:if test="${catId}">
                <div class="tile portrait" id="${HomePageTile.TILE_01.cssId}" style="top:0px; left:0px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='porThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile portrait" id="${HomePageTile.TILE_01.cssId}" style="top:0px; left:0px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_01.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile portrait" id="${HomePageTile.TILE_01.cssId}" style="top:0px; left:0px">${HomePageTile.TILE_01.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            <!-- tile_02 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_02)?.id}" />
            <g:if test="${catId}">
                <div class="tile portrait" id="${HomePageTile.TILE_02.cssId}" style="top:223px; left:0px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='porThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile portrait" id="${HomePageTile.TILE_02.cssId}" style="top:223px; left:0px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_02.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile portrait" id="${HomePageTile.TILE_02.cssId}" style="top:223px; left:0px">${HomePageTile.TILE_02.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            <!-- tile_03 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_03)?.id}" />
            <g:if test="${catId}">
                <div class="tile landscape" id="${HomePageTile.TILE_03.cssId}" style="top:0px; left:145px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='lanThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_03.cssId}" style="top:0px; left:145px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_03.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_03.cssId}" style="top:0px; left:145px">${HomePageTile.TILE_03.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            <!-- tile_04 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_04)?.id}" />
            <g:if test="${catId}">
                <div class="tile landscape" id="${HomePageTile.TILE_04.cssId}" style="top:145px; left:145px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='lanThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_04.cssId}" style="top:145px; left:145px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_04.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_04.cssId}" style="top:145px; left:145px">${HomePageTile.TILE_04.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            <!-- tile_05 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_05)?.id}" />
            <g:if test="${catId}">
                <div class="tile landscape" id="${HomePageTile.TILE_05.cssId}" style="top:290px; left:145px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='lanThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_05.cssId}" style="top:290px; left:145px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_05.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_05.cssId}" style="top:290px; left:145px">${HomePageTile.TILE_05.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
                
            <!-- tile_06 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_06)?.id}" />
            <g:if test="${catId}">
                <div class="tile portrait" id="${HomePageTile.TILE_06.cssId}" style="top:0px; left:355px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='porThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile portrait" id="${HomePageTile.TILE_06.cssId}" style="top:0px; left:355px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_06.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile portrait" id="${HomePageTile.TILE_06.cssId}" style="top:0px; left:355px">${HomePageTile.TILE_06.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            <!-- tile_07 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_07)?.id}" />
            <g:if test="${catId}">
                <div class="tile portrait" id="${HomePageTile.TILE_07.cssId}" style="top:223px; left:355px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='porThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile portrait" id="${HomePageTile.TILE_07.cssId}" style="top:223px; left:355px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_07.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile portrait" id="${HomePageTile.TILE_07.cssId}" style="top:223px; left:355px">${HomePageTile.TILE_07.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            <!-- tile_08 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_08)?.id}" />
            <g:if test="${catId}">
                <div class="tile landscape" id="${HomePageTile.TILE_08.cssId}" style="top:0px; left:500px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='lanThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_08.cssId}" style="top:0px; left:500px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_08.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_08.cssId}" style="top:0px; left:500px">${HomePageTile.TILE_08.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            <!-- tile_09 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_09)?.id}" />
            <g:if test="${catId}">
                <div class="tile landscape" id="${HomePageTile.TILE_09.cssId}" style="top:145px; left:500px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='lanThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_09.cssId}" style="top:145px; left:500px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_09.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_09.cssId}" style="top:145px; left:500px">${HomePageTile.TILE_09.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            <!-- tile_10 -->
            <g:set var="catId" value="${Category.findWhere(homePageTile:HomePageTile.TILE_10)?.id}" />
            <g:if test="${catId}">
                <div class="tile landscape" id="${HomePageTile.TILE_10.cssId}" style="top:290px; left:500px"><g:link controller="category" action="show" id="${catId}"><img src="<g:createLink controller='category' action='lanThum' id='${catId}'/>"/></g:link></div>
            </g:if><g:else>
                <sec:ifLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_10.cssId}" style="top:290px; left:500px"><g:link controller="category" action="index">pick a Category for ${HomePageTile.TILE_10.cssId}</g:link></div>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                <div class="tile landscape" id="${HomePageTile.TILE_10.cssId}" style="top:290px; left:500px">${HomePageTile.TILE_10.cssId}</div>
                </sec:ifNotLoggedIn>
            </g:else>
            
            </div>
            
            <div style="clear: both;"><br/></div>

<%--			<div id="controller-list" role="navigation">--%>
<%--				<h2>Available Controllers:</h2>--%>
<%--				<ul>--%>
<%--					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">--%>
<%--						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>--%>
<%--					</g:each>--%>
<%--				</ul>--%>
<%--			</div>--%>
            
<%--            <div id="category-list" role="navigation" style="border:5px dotted black">--%>
<%--                 <h2>Category Mix</h2>--%>
<%--                 <g:each var="cat" in="${Category.list()}" status="idx">--%>
<%--                     <div id="${cat.id}" class="imgLink">--%>
<%--                     <g:link controller="category" action="show" id="${cat.id}">--%>
<%--                        <img src="<g:createLink controller='category' action='thumbnail' id='${cat.id}'/>"/>--%>
<%--                     </g:link>--%>
<%--                     </div>--%>
<%--                 </g:each>--%>
<%--            </div>--%>
 			
<%--			<div id="category-list" role="navigation">--%>
<%--			     <h2>Categories</h2>--%>
<%--			     <ul>--%>
<%--			         <g:each var="cat" in="${Category.list()}" status="idx">--%>
<%--                     <g:if test="${idx > 0}"><span style="color: #fff;">&nbsp;|&nbsp;</span></g:if>--%>
<%--			             <li class="controller"><g:link controller="category" action="show" id="${cat.id}">${fieldValue(bean: cat, field: "name")}</g:link></li>--%>
<%--			         </g:each>--%>
<%--			     </ul>--%>
<%--			</div>--%>
			
<%--            <div id="category-list" role="navigation">--%>
<%--                 <h2>Category Images</h2>--%>
<%--                 <ul>--%>
<%--                     <g:each var="cat" in="${Category.list()}">--%>
<%--                         <li class="controller">${cat.name}<br/>--%>
<%--                         <g:link controller="category" action="show" id="${cat.id}">--%>
<%--                            <img src="<g:createLink controller='category' action='thumbnail' id='${cat.id}'/>"/>--%>
<%--                         </g:link>--%>
<%--                         </li><br/>--%>
<%--                     </g:each>--%>
<%--                 </ul>--%>
<%--            </div>--%>
           
		</div>
	</body>
</html>
