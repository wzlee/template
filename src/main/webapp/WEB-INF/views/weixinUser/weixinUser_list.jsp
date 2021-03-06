<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>微信订阅者管理</title>
</head>
<body>

	<div id="main-content">
		<div class="container">
			<div class="row">
				<!-- 页面内容-->
				<div id="content" class="col-lg-12">
			
					<!-- PAGE HEADER-->
					<div class="row">
						<div class="col-sm-12">
							<div class="page-header">
								<!-- BREADCRUMBS -->
								<ul class="breadcrumb">
									<li>
										<i class="fa fa-home"></i>
										<a href="index.html">主页</a>
									</li>
									<li>
										<a href="#">微信订阅者管理</a>
									</li>
									<li>微信订阅者列表</li>
								</ul>
								
							</div>
						</div>
					</div>
					<!-- /PAGE HEADER -->
					
					<!-- Alerts Message -->
					<c:if test="${not empty message}">
					<div class="alert alert-block alert-info fade in">
						<a class="close" data-dismiss="alert" href="#" aria-hidden="true">&times;</a>
						<h4 style="margin: 0;"><i class="fa fa-check-square-o"></i> ${message}</h4>
					</div>
					</c:if>
					<!-- /Alerts Message -->

					<!-- EXPORT TABLES -->
					<div class="row">
						<div class="col-md-12">
							<!-- BOX -->
							<div class="box border primary">
								<div class="box-title">
									<h4><i class="fa fa-table"></i>微信订阅者列表</h4>
									<div class="tools hidden-xs">
										<a href="javascript:;" class="collapse">
											<i class="fa fa-chevron-up"></i>
										</a>
										<a href="javascript:;" class="remove">
											<i class="fa fa-times"></i>
										</a>
									</div>
								</div>
								<div class="box-body">
									<form class="form-inline" action="#">
										<div class="form-group">
											<input type="text" name="search_LIKE_nickname" class="form-control" value="${param.search_LIKE_nickname}" placeholder="名称" />
										</div>
										<button type="submit" class="btn btn-inverse" id="search_btn"> 查 询 </button>
										<tags:sort/>
									</form>
									
									<br/>
									
									<table  class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>头像</th>
												<th>微信订阅者</th>
												<th>性别</th>
												<th>标识</th>
												<th>语言</th>
												<th>国家</th>
												<th>省份</th>
												<th>城市</th>
												<th>是否订阅</th>
												<th>订阅时间</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${weixinUsers.content}" var="weixinUser">
											<tr>
												<td style="width: 30px;"><img alt="" style="border-radius:5px;" width="30" height="30" src="${weixinUser.headimgurl}" /></td>
												<td style="line-height: 30px;">${weixinUser.nickname}</td>
												<td style="line-height: 30px;">${weixinUser.sexValue}</td>
												<td style="line-height: 30px;">${weixinUser.openid}</td>
												<td style="line-height: 30px;">${weixinUser.language}</td>
												<td style="line-height: 30px;">${weixinUser.country}</td>
												<td style="line-height: 30px;">${weixinUser.province}</td>
												<td style="line-height: 30px;">${weixinUser.city}</td>
												<td style="line-height: 30px;">${weixinUser.subscribeValue}</td>
												<td style="line-height: 30px;">${weixinUser.subscribe_time}</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
									<div class="row">
										<div class="col-sm-12">
											<div class="pull-right">
												<tags:pagination page="${weixinUsers}" paginationSize="5"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- /BOX -->
						</div>
					</div>
					<!-- /EXPORT TABLES -->
					
					<div class="footer-tools">
						<span class="go-top">
							<i class="fa fa-chevron-up"></i> Top
						</span>
					</div>
				
				</div>
				<!-- 页面内容-->
			</div>
		</div>
	</div>

	<!-- JAVASCRIPTS -->
	<!-- 引入公共JS脚本 -->
	<%@ include file="/WEB-INF/layouts/include_script.jsp"%>
	
	<!-- 自定义JS脚本 -->
	<script src="${ctx}/static/js/script.js"></script>
	<script>
		jQuery(document).ready(function() {
			//如果页面无需设置效果，可以不设置 App.setPage ，如设置 App.setPage 而页面缺少对应的元素，会导致JS错误.
			//App.setPage("widgets_box");  //设置当前启动的页面
			
			App.setHasSub("weixin-manager");//设置一级微信订阅者目录ID
			App.setSubMenu("wxusers-list");//设置二级微信订阅者目录ID
			App.setPath("${ctx}/static");  //设置项目路径
			App.init(); //初始化元素以及插件
		});
	</script>
	<!-- /JAVASCRIPTS -->
</body>
</html>