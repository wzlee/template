<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="btn-group dropdown pull-right">
	<button class="btn  btn-default dropdown-toggle" data-toggle="dropdown">
		排序 : ${sortTypes[sortType]} <span class="caret"></span>
	</button>
	<ul class="dropdown-menu context">
	<c:forEach items="${sortTypes}" var="entry">
	   	<li>
	   		<a href="?sortType=${entry.key}&${searchParams}">${entry.value}</a>
	   	</li>
	</c:forEach>
	</ul>
</div>