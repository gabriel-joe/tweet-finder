<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Tweets finder</title>

<spring:url value="/resources/core/css/main.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Tweets finder</a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<br />
		<p>Please, enter the hashtag to be searched for and select the limit of tweets</p>
		<form action="/tweet-finder/finder" METHOD="POST">
			<div class="form-group row">
				<div class="col-xs-2">
					<input class="form-control" id="hashtag" type="text" name="hashtag">
				</div>
				<div class="col-xs-2">
					<select name="optionValue" class="form-control">
						<c:forEach var="element" items="${listOptions}">
							<option value="${element.count}">${element.title}</option>
						</c:forEach>
					</select>
				</div>
				<input type="submit" class="btn btn-default" value="Search">
			</div>
		</form>
		<br>
		<div class="progress">
			<div class="progress-bar ${progressBarType}" role="progressbar"
				role="progressbar" aria-valuenow="${countTweets}" aria-valuemin="0"
				aria-valuemax="${progressTotal}" style="width: ${barStyle}">
				${barText}</div>
		</div>
		<br> <br>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>User name</th>
					<th>User page</th>
					<th>User Followers</th>
					<th>Tweet</th>
					<th>Source</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${listTweets}">
					<tr>
						<td>${row.user.name}</td>
						<td>${row.user.URL}</td>
						<td>${row.user.followersCount}</td>
						<td>${row.text}</td>
						<td>${row.source}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<spring:url value="/resources/core/js/main.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>