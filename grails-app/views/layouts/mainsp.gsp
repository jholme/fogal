<%@ page import="grails.plugin.springsecurity.SecurityTagLib" %>
<%@ page import="com.iii.fogal.Category" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Photographs &amp; Stories"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">--%>
<%--		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">--%>
<%--		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">--%>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
        <g:javascript library="application"/>       
        <g:javascript library="jquery-2.2.4.min"/>      
		<g:javascript library="jquery-ui.min"/>		
		<r:layoutResources />
	</head>
	<body>
		<div id="dbaconLogo" role="banner">
		    <img id="logoLink" usemap="#headMap" src="${resource(dir: 'images/dbacon', file: 'header.gif')}" title="Stories &amp; Photographs" alt="Stories &amp; Photographs"/>
			<map name="headMap">
			<area onmouseout="rollOff();window.status=''" onmouseover="window.status='go to the Story Index';rollOver('stors');return true" href="/" coords="229,0,291,23">
			<area onmouseout="rollOff();window.status=''" onmouseover="window.status='go to the Photography Index';rollOver('photos');return true" href="/" coords="183,23,311,50">
			<area onmouseout="rollOff();window.status=''" onmouseover="window.status='News about David\'s Work';rollOver('news');return true" href="/" coords="0,0,229,50">
			<area onmouseout="rollOff();window.status=''" onmouseover="window.status='News about David\'s Work';rollOver('news');return true" href="/" coords="291,0,519,50">
			</map>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo">
		    <div id="footer-list">
            <ul>
                <g:each var="cat" in="${Category.list()}" status="idx">
                <g:if test="${idx > 0}"><span style="color: #fff;">&nbsp;|&nbsp;</span></g:if>
                    <li class="controller"><g:link controller="category" action="show" id="${cat.id}">${fieldValue(bean: cat, field: "name")}</g:link></li>
                </g:each>
            </ul>
            <ul>
                <li>Special Project:&nbsp;<g:link controller="tranatworcom" action="index">Transnational Working Communities</g:link></li>
                <span style="color: #fff;">&nbsp;|&nbsp;</span><li>mainsp.gsp</li>
<%--                <li>Special Project:&nbsp;<a href="${resource(dir:'html/TWC', file:'index.htm')}">Transnational Working Communities</a></li>--%>
<%--                <span style="color: #fff;">&nbsp;|&nbsp;</span>--%>
<%--                <li><a href="http://www.google.com">stuff</a></li>--%>
<%--                <span style="color: #fff;">&nbsp;|&nbsp;</span>--%>
<%--                <li><a href="http://www.google.com">here</a></li>--%>
            </ul>
            </div>
		</div>
		<div class="footer">
            <span style="float:right; padding-bottom: 10px;">
            <sec:ifLoggedIn>
            <g:link controller='logout' action='index'>Logout</g:link>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
            <g:link controller='login' action='auth'>Login</g:link>
            </sec:ifNotLoggedIn>
            </span>
		</div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<r:layoutResources />
	</body>
</html>
