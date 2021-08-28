<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pension Management</title>
</head>

<script>
	document.onreadystatechange = function() {
		if (document.readyState == 'loaded'|| document.readyState == 'complete')
			document.getElementById("popup").click();
	}
</script>
<style>
html {
	height: 100%;
}

body {
	background-image:
		url(https://images.pexels.com/photos/167699/pexels-photo-167699.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260);
	background-repeat: no-repeat;
	background-size: 100% 100%;
	background-attachment: fixed;
	height: 100%;
	overflow: auto;
}

.error {
	color: red;
}

.tableStyling {
	background-color: rgba(255, 255, 255, 0.5);
	margin: auto auto;
	padding: 40px;
	border-radius: 5px;
	box-shadow: 0 0 10px #000;
	overflow: auto;
}

.tableHead {
	background-color: rgba(7, 7, 0, 0.2);
}
</style>

<body>
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Pension Management System</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Pensioner Details </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item active" href="#">All</a></li>
							<li><a class="dropdown-item" href="#">Active</a></li>
							<li><a class="dropdown-item" href="#">Pending</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link" data-toggle="modal"
						data-target="#myModal">Get Pensioner</a></li>
				</ul>
				<button class="btn btn-outline-light" type="submit"  onclick="window.location.href='/logout'">Logout</button>
			</div>
		</div>



	</nav>
	
	
	<c:if test="${message!=null }">
	<a  data-toggle="modal" href="#ignismyModal" id="popup"></a>
	<div class="modal fade" id="ignismyModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label=""><span>×</span></button>
                     </div>
					
                    <div class="modal-body">
                       
						<div class="thank-you-pop">
							<img src="http://goactionstations.co.uk/wp-content/uploads/2017/03/Green-Round-Tick.png" alt="">
							<h1>Thank You!</h1>
							<p>Your submission is received and we will contact you soon</p>
							<h3 class="cupon-pop">Your Id: <span>12345</span></h3>
							
 						</div>
                         
                    </div>
					
                </div>
            </div>
        </div>
		<h1>${ message }</h1>
	</c:if>
	<!-- 
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#exampleModal">Launch demo modal</button>
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	-->

	<div class="modal fade" id="myModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="false">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title">Pensioner Details</h2>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">

					<f:form method="post" action="/pensionDetails1"
						modelAttribute="pensionerInput1">
						<div class="">
							<div class="form-group bg-transparent">
								<f:label path="aadhar">Aadhar Number</f:label>
								<f:input path="aadhar" class="form-control" />
							</div>
							<div class="form-group bg-transparent">
								<f:label path="name">Name</f:label>
								<f:input path="name" class="form-control" />
							</div>
							<div class="form-group bg-transparent">
								<f:label path="pan">Pan Number</f:label>
								<f:input path="pan" class="form-control" />
							</div>
							<div class="form-group bg-transparent">
								<f:label path="dateOfBirth">Date Of Birth</f:label>
								<f:input path="dateOfBirth" class="form-control"  placeholder="dd/mm/yyyy" />
							</div>

							<div class="form-group bg-transparent">
								<f:label path="pensionType">Pension Type</f:label>
								<f:input path="pensionType" class="form-control" />
							</div>
							<br>


						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" >Get
								Pension Details</button>
						</div>
					</f:form>
				</div>

			</div>
		</div>
	</div>

	<!-- 
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">Get Pension Details</button>
						</div>
 -->
	<div class="container pt-5 mt-5">
		<c:if test="${ error!=null }">
			<h3 class="error">${ error }</h3>
		</c:if>
		<c:if test="${ pensioners!=null }">
			<table class="table table-hover tableStyling">
				<tr class="tableHead">
					<td align="center"><b>Aadhar Number</b></td>
					<th>Name</th>
					<th>Pan Number</th>
					<th>Self Or Family Pension</th>
					<th></th>
				</tr>
				<c:forEach items="${pensioners}" var="partnerTypesMap">
					<tr>
						<td align="center"><a
							href="/pensioners/${partnerTypesMap['key']}"
							class="btn btn-outline-secondary">${partnerTypesMap['key']}</a></td>
						<td>${partnerTypesMap['value'].name}</td>
						<td>${partnerTypesMap['value'].pan}</td>
						<td>${partnerTypesMap['value'].pensionType}</td>
						<td><a class="btn btn-success"
							href="/pensionDetails?aadharNumber=${partnerTypesMap['key']}">Fetch
								Pension Details</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
		crossorigin="anonymous">
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>