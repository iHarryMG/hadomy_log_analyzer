<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jstl/core_rt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apache LogData List</title>
<link href="/resources/menu.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<div id="Content">
		<form:form method="POST" commandName="logData" action="list.htm">
			<table>
				<tr>
					<th>Category</th>
					<th>Tab</th>					
					<th>Date</th>
					<th></th>
				</tr>
				<tr>
					<td>
						<form:select path="category">
							<form:option value="" label="Select"></form:option>
							<form:options items="${ categoryList }" itemValue="categoryId" itemLabel="categoryName" />
						</form:select>
					</td>
					<td>
						<form:select path="tab">
							<form:option value="" label="Select"></form:option>
							<form:options items="${ tabList }" itemValue="tabId" itemLabel="tabName" />
						</form:select>
					</td>
					<td>
						<form:select path="date">
							<form:option value="" label="Select"></form:option>
							<form:options items="${ dateList }" itemValue="dateId" itemLabel="dateName" />
						</form:select>
					</td>
					<td><button type="submit" >SEARCH</button></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div id="Content">
		<table>
		<caption>Analyzed Apache Log Data Statistics Result</caption>
			<thead>
				<tr>
					<th>No:</th>
					<th>CATEGORY</th>					
					<th>TAB</th>
					<th>URL</th>					
					<th>VALUE</th>
					<th>DATE</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${logs}" var="log" >
					<tr>
						<td>${log.num}</td>
						<td>${log.category}</td>
						<td>${log.tab}</td>
						<td>${log.url}</td>
						<td>${log.value}</td>
						<td>${log.date}</td>
					</tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>

</body>
</html>