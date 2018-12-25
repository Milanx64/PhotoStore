<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../css/style.css">

<title>About Photo Store</title>
</head>
<body>
	<div class="overlay"></div>
	<div class="container">
            <jsp:include page="../inc/navmenu.jsp"></jsp:include>
        </div>
	<main id="signup">
	<div id="form-wrpper">
		<div class="tabs">
			<a href="signup.jsp" class="tabs-link">Sign up</a> <a
				href="login.jsp" class="tabs-link">Login</a>
		</div>

		<form action="https://localhost:8443${pageContext.request.contextPath}/AddUserToDBServlet" method="post">

			<div class="float form-content1">

				<p class="form-iteam">First Name</p>
				<input type="text" name="name" placeholder="" required class="input">

			</div>
			<div class="form-content2">

				<p class="form-iteam">last Name</p>
				<input type="text" name="lastname" placeholder="" required
					class="input">

			</div>

			<div class="form-content3">

				<p class="form-iteam">Email</p>
				<input type="email" name="email" placeholder="" required
					class="input">

			</div>

			<div class="form-content4">

				<p class="form-iteam">Password</p>
				<input type="password" name="password" placeholder="" required
					class="input">

			</div>

			<p>

				<button type="submint" value="Sign Up" id="submit-btn"
					class="form-content5">Sign Up</button>
			</p>
		</form>
	</div>
	</main>
	<script type="text/javascript" src="../js/main.js"></script>
</body>
</html>
