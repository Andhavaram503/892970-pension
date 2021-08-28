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

.formStyling {
	background-color: rgba(255, 255, 255, 0.1);
	margin: auto auto;
	padding: 40px;
	border-radius: 5px;
	box-shadow: 0 0 10px #000;
	overflow: auto;
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
					<li class="nav-item"><li class="nav-item"><a class="nav-link" data-toggle="modal"
						data-target="#myModal">Get Pensioner</a>
				</li></ul>
				<button class="btn btn-outline-light" type="submit">Logout</button>
			</div>
		</div>


	</nav>

	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Pensioner Details</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">

					<f:form method="post" action="/pensionDetails1" modelAttribute="pensionerInput1">
						<div class="">
							<h2 style="text-align: center">Pension Details</h2>
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
								<f:input path="dateOfBirth" class="form-control"  placeholder="dd/mm/yyyy"/>
							</div>

							<div class="form-group bg-transparent">
								<f:label path="pensionType">Pension Type</f:label>
								<f:input path="pensionType" class="form-control" />
							</div>
							<br>


						</div><div class="modal-footer">
					<button type="submit" class="btn btn-primary" >Get Pension Details</button>
				</div>
					</f:form>
				</div>

			</div>
		</div>
	</div>
	<div class="container pt-5 mt-5">
		<c:if test="${ error!=null }">
			<h3 class="error">${ error }</h3>
		</c:if>
		<c:if test="${ pensionDetails!=null }">
			<f:form modelAttribute="pensionDetails">
				<div class="formStyling">
					<h2 style="text-align: center">Pension Details</h2>
					<div class="form-group bg-transparent">
						<f:label path="name">Name</f:label>
						<f:input path="name" class="form-control"
							value="${pensionDetails.name }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="pan">Pan Number</f:label>
						<f:input path="pan" class="form-control"
							value="${pensionDetails.pan }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="dateOfBirth">Date Of Birth</f:label>
						<f:input path="dateOfBirth" class="form-control"
							value="${pensionDetails.dateOfBirth }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="pensionType">Pension Type</f:label>
						<f:input path="pensionType" class="form-control"
							value="${pensionDetails.pensionType }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="pensionAmount">Pension Amount</f:label>
						<f:input path="pensionAmount" class="form-control"
							value="${pensionDetails.pensionAmount }" disabled="true" />
					</div>
					<br>
					<div class="text-center">
						<a class="btn btn-primary"
							href="/processPension/${ aadharNumber }">Process Pension</a>
					</div>


				</div>
			</f:form>
		</c:if>
		<c:if test="${ pensioner!=null }">
			<f:form modelAttribute="pensioner">
				<div class="formStyling">
					<h2 style="text-align: center">Pensioner Details</h2>
					<div class="form-group bg-transparent">
						<f:label path="name">Name</f:label>
						<f:input path="name" class="form-control"
							value="${pensioner.name }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="dateOfBirth">Date Of Birth</f:label>
						<f:input path="dateOfBirth" class="form-control"
							value="${pensionerDateOfBirth}" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="pan">Pan Number</f:label>
						<f:input path="pan" class="form-control" value="${pensioner.pan }"
							disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="salaryEarned">Salary Earned</f:label>
						<f:input path="salaryEarned" class="form-control"
							value="${pensioner.salaryEarned }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="pensionType">Pension Type</f:label>
						<f:input path="pensionType" class="form-control"
							value="${pensioner.pensionType }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="salaryEarned">Salary Earned</f:label>
						<f:input path="salaryEarned" class="form-control"
							value="${pensioner.salaryEarned }" disabled="true" />
					</div>
					<h4>Bank Details</h4>

					<div class="form-group bg-transparent">
						<f:label path="bank.bankName">Bank Name</f:label>
						<f:input path="bank.bankName" class="form-control"
							value="${pensioner.bank.bankName }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="bank.accountNo">Bank Account Number</f:label>
						<f:input path="bank.accountNo" class="form-control"
							value="${pensioner.bank.accountNo }" disabled="true" />
					</div>
					<div class="form-group bg-transparent">
						<f:label path="bank.bankType">Bank Type</f:label>
						<f:input path="bank.bankType" class="form-control"
							value="${pensioner.bank.bankType }" disabled="true" />
					</div>
				</div>
			</f:form>
		</c:if>

	</div>


	<h3>${ pensioners}</h3>
	<br>
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
<!-- 
<!-- The 
					<div class="modal fade" id="myModal">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Modal Heading</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<div class="modal-body">Modal body..</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">Close</button>
								</div>

							</div>
						</div>
					</div>
				
 -->
</html>