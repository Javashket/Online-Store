<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true"%>
<%@attribute name="content" fragment="true"%>
<%@attribute name="appscript" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<title>${title }</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
</head>

<body id="body">
	<header class="site-header">
		<div class="header-content">
			<h2>
				<a href="/">WebTechnology Demo</a>
			</h2>
		</div>
	</header>
	<div class="content">
		<jsp:invoke fragment="content"></jsp:invoke>
	</div>

	<jsp:invoke fragment="appscript"></jsp:invoke>
</body>

</html>
