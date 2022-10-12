<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<body>
${confirmation}
<h3>Request List</h3>

<c:if test="${!empty requestmodel}">
		<h2>List UserModels</h2>
		<table align="left" border="1">
			<tr>
				<th>Request Id</th>
				<th>User ID</th>
				<th>Description</th>
				<th>Comment</th>
				<th>Actions on Row</th>
			</tr>
 
			<c:forEach items="${requestmodel}" var="rtmodel">
				<tr>
					<td>${rtmodel.requestId}</td>
					<td>${rtmodel.userId}</td>
					<td>${rtmodel.description12}</td>
					<td>${rtmodel.comment12}</td>
					<c:url value="/approve" var="approving"></c:url>
					
					<td align="center"><a href="${approving}?requestId=${rtmodel.requestId}">Approve</a>
					
						<!--  <a href="${deleting}?userId=${usermodel.userId}">Reject</a></td> --> 
				</tr>
			</c:forEach>
			
			 
			
		</table>
	</c:if>

</body>
</html>