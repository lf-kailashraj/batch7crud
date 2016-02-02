<!DOCTYPE html>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="body" fragment="true" %>

<html>
    <head>
        <base href="${pageContext.request.contextPath}/">
        <title>home page</title>
        <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
        <link rel="stylesheet" type="text/css" href="resources/css/layout.css">
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <%  response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility--%>
    </head>

    <body>
        <jsp:invoke fragment="body"/>
        <script type="text/javascript" src="resources/js/index.js"></script>
    </body>
</html>