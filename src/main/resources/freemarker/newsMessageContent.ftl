<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<title>${message.title}</title>
<link href="${contextPath!""}/static/js/slideby/styles/style.css" rel="stylesheet" type="text/css">
<link href="${contextPath!""}/static/js/slideby/styles/framework.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${contextPath!""}/static/js/slideby/scripts/jquery.js"></script>
<script type="text/javascript" src="${contextPath!""}/static/js/slideby/scripts/framework.launcher.js"></script>
<style type="text/css">
	#content .container img{
		max-width: 100%!important;
	}
</style>  
</head>
<body>

<div id="preloader">
	<div id="status">
    	<p class="center-text">
			等待连接...
            <em>请保持您的网络链接通畅!</em>
        </p>
    </div>
</div>


<div class="all-elements">
    <div id="content" class="page-content">
        <div class="content">
        	<div class="decoration"></div>

            <div class="container no-bottom">
            	<div class="section-title">
                	<h1>${message.title}</h1>
                </div>
            </div>
            
            <div class="decoration"></div>

            <div class="container no-bottom">
                ${message.content}
            </div>
            
            <div class="decoration"></div>
                 
            <div class="content-footer">
                <a href="#" class="go-up-footer"></a>
                <div class="clear"></div>
            </div>
              
        </div>                
    </div>  
</div>

</body>
</html>
