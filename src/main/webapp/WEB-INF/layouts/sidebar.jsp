<%@page import="com.cn.template.xutil.enums.MessageCategory"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="companyProfile" value="<%=MessageCategory.COMPANY_PROFILE %>" />
<c:set var="technicalMonograph" value="<%=MessageCategory.TECHNICAL_MONOGRAPH %>" />
<c:set var="ePaper" value="<%=MessageCategory.EPAPER %>" />
<c:set var="majorProduct" value="<%=MessageCategory.MAJOR_PRODUCT %>" />
<c:set var="newsInformation" value="<%=MessageCategory.NEWS_INFORMATION %>" />

<!-- 侧边栏 -->
<div id="sidebar" class="sidebar">
	<div class="sidebar-menu nav-collapse">
		<div class="divide-20"></div>
		<!-- 搜索栏 -->
		<div id="search-bar">
			<input class="search" type="text" placeholder="Search"><i class="fa fa-search search-icon"></i>
		</div>
		<!-- /搜索栏 -->
		
		<!-- 侧边栏 菜单 -->
		<ul>
			<li id="workbench">
				<a href="${ctx}/workbench">
					<i class="fa fa-tachometer fa-fw"></i> <span class="menu-text">我的工作</span>
				</a>					
			</li>
			<li id="struct-manager" class="has-sub">
				<a href="javascript:;" class="">
				<i class="fa fa-sitemap fa-fw"></i> <span class="menu-text">架构管理</span>
				<span class="arrow"></span>
				</a>
				<ul class="sub">
					<li id="departments-list"><a class="" href="${ctx}/department"><span class="sub-menu-text">部门信息</span></a></li>
					<li id="employees-list"><a class="" href="${ctx}/employee"><span class="sub-menu-text">员工信息</span></a></li>
				</ul>
			</li>
			<li id="projects-manager" class="has-sub">
				<a href="javascript:;" class="">
				<i class="fa fa-tasks fa-fw"></i> <span class="menu-text">项目管理</span>
				<span class="arrow"></span>
				</a>
				<ul class="sub">
					<li id="projects-list"><a class="" href="${ctx}/project"><span class="sub-menu-text">项目列表</span></a></li>
					<li id="tasks-list"><a class="" href="${ctx}/task"><span class="sub-menu-text">任务列表</span></a></li>
					<li id="demos-list"><a class="" href="${ctx}/demo"><span class="sub-menu-text">例子列表</span></a></li>
				</ul>
			</li>
			<li id="weixin-manager" class="has-sub">
				<a href="javascript:;" class="">
				<i class="fa fa-comments fa-fw"></i> <span class="menu-text">微信管理</span>
				<span class="arrow"></span>
				</a>
				<ul class="sub">
					<li id="weixinMenus-list"><a class="" href="${ctx}/weixinMenu"><span class="sub-menu-text">菜单列表</span></a></li>
					<li id="wxusers-list"><a class="" href="${ctx}/wxuser"><span class="sub-menu-text">订阅者列表</span></a></li>
					<%-- <li id="newsMessages-list"><a class="" href="${ctx}/news"><span class="sub-menu-text">图文消息列表</span></a></li> --%>
					<li id="${companyProfile}"><a class="" href="${ctx}/newsCategory?messageCategory=${companyProfile}"><span class="sub-menu-text">${companyProfile.value}</span></a></li>
					<li id="${technicalMonograph}"><a class="" href="${ctx}/newsCategory?messageCategory=${technicalMonograph}"><span class="sub-menu-text">${technicalMonograph.value }</span></a></li>
					<li id="${ePaper}"><a class="" href="${ctx}/newsCategory?messageCategory=${ePaper}"><span class="sub-menu-text">${ePaper.value }</span></a></li>
					<li id="${majorProduct}"><a class="" href="${ctx}/newsCategory?messageCategory=${majorProduct}"><span class="sub-menu-text">${majorProduct.value }</span></a></li>
					<li id="${newsInformation}"><a class="" href="${ctx}/newsCategory?messageCategory=${newsInformation}"><span class="sub-menu-text">${newsInformation.value }</span></a></li>
				</ul>
			</li>
			<li id="other-page" class="has-sub">
				<a href="javascript:;" class="">
				<i class="fa fa-briefcase fa-fw"></i> <span class="menu-text">其它页面</span>
				<span class="arrow"></span>
				</a>
				<ul class="sub">
					<shiro:hasPermission name="users-list"><li id="users-list"><a class="" href="${ctx}/user"><span class="sub-menu-text">用户管理</span></a></li></shiro:hasPermission>
					<li id="roles-list"><a class="" href="${ctx}/role"><span class="sub-menu-text">角色管理</span></a></li>
					<li id="sidebars-list"><a class="" href="${ctx}/sidebar"><span class="sub-menu-text">侧边栏菜单</span></a></li>
					<li id="demo-ss"><a class="" href="${ctx}/workbench"><span class="sub-menu-text">Search Results</span></a></li>
					<li><a class="" href="blank_page.html"><span class="sub-menu-text">Blank Page</span></a></li>
				</ul>
			</li>
		</ul>
		<!-- /侧边栏 菜单 -->
	</div>
</div>
<!-- /侧边栏 -->
