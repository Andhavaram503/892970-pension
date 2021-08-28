<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Pension Management System Login</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="css/A.style.css.pagespeed.cf.EsokhafFue.css">
</head>
<style>
html {
	height: 100%;
}

body {
	background-image:
		url(https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjI5fHxmdWxsJTIwc2NyZWVuJTIwd2FsbHBhcGVyfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60);
	background-repeat: no-repeat;
	background-size: 100% 100%;
	background-attachment: fixed;
}

.formStyling {
	background-color: rgba(255, 255, 255, 0.1);
	margin: auto auto;
	padding: 40px;
	border-radius: 5px;
	box-shadow: 0 0 10px #000;
	align-items: center;
	overflow: auto;
}
</style>
<body>




	<div class="container pt-5 mt-5">
		<div class="row justify-content-md-center">
			<div class="col-md-4 col-lg-4 col-sm-8 col-xs-8">
				<f:form modelAttribute="user" method="post" action="/login">
					<div class="formStyling">
						<h2 style="text-align: center">Login</h2>
						<br>
						<br>
						<div class="form-outline mb-4">
							<f:input  id="typePasswordX" path="userName"
								class="form-control form-control-lg active"/> <label
								class="form-label" for="typePasswordX" style="margin-left: 0px;">Username</label>
							<div class="form-notch">
								<div class="form-notch-leading" style="width: 9px;"></div>
								<div class="form-notch-middle" style="width: 64.8px;"></div>
								<div class="form-notch-trailing"></div>
							</div>
						</div>

						

						<div class="form-outline mb-4">
							<f:input type="password" id="typePasswordX" path="password"
								class="form-control form-control-lg active"/> <label
								class="form-label" for="typePasswordX" style="margin-left: 0px;">Password</label>
							<div class="form-notch">
								<div class="form-notch-leading" style="width: 9px;"></div>
								<div class="form-notch-middle" style="width: 64.8px;"></div>
								<div class="form-notch-trailing"></div>
							</div>
						</div>
						<c:if test="${error!=null }">
						<p style="color:red">${error }</p>
						</c:if>
						<br>
						<div class="text-center">
							<button class="btn btn-primary" type="submit">Login</button>
						</div>


					</div>
				</f:form>
			</div>
		</div>

	</div>

	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
	<link rel='stylesheet' id='dashicons-css'
		href='https://mdbcdn.b-cdn.net/wp-includes/css/dashicons.min.css?ver=5.8'
		type='text/css' media='all' />
	<link rel='stylesheet' id='admin-bar-css'
		href='https://mdbcdn.b-cdn.net/wp-includes/css/admin-bar.min.css?ver=5.8'
		type='text/css' media='all' />
	<link rel='stylesheet' id='wp-block-library-css'
		href='https://mdbcdn.b-cdn.net/wp-includes/css/dist/block-library/style.min.css?ver=5.8'
		type='text/css' media='all' />
	<link rel='stylesheet' id='wc-blocks-vendors-style-css'
		href='https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce/packages/woocommerce-blocks/build/wc-blocks-vendors-style.css?ver=5.5.1'
		type='text/css' media='all' />
	<link rel='stylesheet' id='wc-blocks-style-css'
		href='https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce/packages/woocommerce-blocks/build/wc-blocks-style.css?ver=5.5.1'
		type='text/css' media='all' />
	<style id='woocommerce-inline-inline-css' type='text/css'>
.woocommerce form .form-row .required {
	visibility: visible;
}
</style>
	<link rel='stylesheet' id='wsl-widget-css'
		href='https://mdbcdn.b-cdn.net/wp-content/plugins/wordpress-social-login/assets/css/style.css?ver=5.8'
		type='text/css' media='all' />
	<link rel='stylesheet' id='admin.css-css'
		href='https://mdbcdn.b-cdn.net/wp-content/themes/mdbootstrap4/css/mdb5/3.9.0/compiled.min.css?ver=3.9.0-update.4'
		type='text/css' media='all' />
	<link rel='stylesheet' id='roboto-subset.css-css'
		href='https://mdbcdn.b-cdn.net/wp-content/themes/mdbootstrap4/css/mdb5/fonts/roboto-subset.css?ver=3.9.0-update.5'
		type='text/css' media='all' />
</body>
</html>

<!--

    <div class="form-outline mb-4">
                    <input type="password" id="typePasswordX" class="form-control form-control-lg">
                    <label class="form-label" for="typePasswordX" style="margin-left: 0px;">Password</label>
                  <div class="form-notch"><div class="form-notch-leading" style="width: 9px;"></div><div class="form-notch-middle" style="width: 64.8px;"></div><div class="form-notch-trailing"></div></div></div>
    
-->