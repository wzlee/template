<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="btn-group dropdown pull-right">
	<button class="btn  btn-default dropdown-toggle" data-toggle="dropdown">
		排序 : <span id="sortTypeValue">${sortTypes[sortType]}</span> <span class="caret"></span>
	</button>
	<input type="hidden" id="sortTypeKey" value="${sortType}"/>
	
	<ul class="dropdown-menu context">
	<c:forEach items="${sortTypes}" var="entry">
	   	<li>
	   		<a href="javascript:sort('${entry.key}','${entry.value}')">${entry.value}</a>
	   	</li>
	</c:forEach>
	</ul>
	
</div>