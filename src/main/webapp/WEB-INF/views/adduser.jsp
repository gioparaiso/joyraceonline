<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>

<style>
html {
	height: 100%;
}

body {
	height: 100%;
}

form {
	height: 100%;
}

login {
	font-size: 30px;
	font-family: Latha;
	font-weight: normal;
	color: #fdee06;
}

contentlabels {
	font-size: 14px;
	font-family: Latha;
	font-weight: normal;
	color: #ffffff;
}

forgotlink {
	font-style: normal;
	font-size: 14px;
	font-family: Latha;
	font-weight: normal;
	color: #2762ba;
}

.inputfile {
	color: white;
	background-color: black;
	display: inline-block;
	width: 180px;
}

errorMessage {
	font-style: normal;
	font-size: 12px;
	font-family: Latha;
	font-weight: normal;
	color: red;
}

saveSuccess {
	font-style: normal;
	font-size: 12px;
	font-family: Latha;
	font-weight: normal;
	color: white;
}
</style>

</head>

<!-- #7f7f7f light gray -->
<body bgcolor="#7f7f7f">

	<form name="addUserForm" method="post" action="adduser">


		<!-- table to hold the entire page -->
		<!-- #10A01A green -->
		<table
			style="background-color: #7f7f7f; halign: center; width: 100%; height: 100%"
			cellpadding=0 cellspacing=0>
			<tr>
				<td align="center">
					<!-- table to hold the screen in the center -->
					<table style="align: center; height: 100%; width: 590px"
						cellpadding="0" cellspacing=0>
						<tr>
							<!-- green area - menu (begin) -->
							<td style="width: 120px; background-color: #10A01A">
								<table style="width: 100%; height: 100%" cellpadding="0"
									cellspacing=0>
									<tr height="40px">
										<td></td>
										<td width="25px"></td>
									</tr>
									<tr>
										<td align="right"><a href="adminhome"><img
												src="img/1home2.jpg"></a></td>
										<td></td>
									</tr>
									<tr height="30px">
										<td colspan=2></td>
									</tr>
									<tr>
										<td align="right"><img src="img/8adduser1.jpg"></td>
										<td></td>
									</tr>
									<tr height="30px">
										<td colspan=2></td>
									</tr>
									<tr>
										<td align="right"><a href="searchadmin"><img
												src="img/3search2.jpg"></a></td>
										<td></td>
									</tr>
									<tr height="30px">
										<td colspan=2></td>
									</tr>
									<tr>
										<td align="right"><a href="configadmin"><img
												src="img/7config2.jpg"></a></td>
										<td></td>
									</tr>
									<tr height="70px">
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td align="right"><a href="logoutadmin"><img
												src="img/9logout.jpg"></a></td>
										<td></td>
									</tr>
									<tr height="100%">
										<td></td>
										<td></td>
									</tr>
								</table>
							</td>
							<!-- green area - menu (end) -->
							<td style="width: 47px">
								<!-- table to hold the left red stripes -->
								<table style="width: 100%; height: 100%" cellpadding="0"
									cellspacing=0>
									<tr>
										<td background="img/redstripes.jpg"></td>
									</tr>
								</table>
							</td>
							<td>
								<table
									style="background-color: #4f534f; width: 100%; height: 100%"
									cellpadding="0" cellspacing="0" background="img/asphalt.jpg">

									<!-- experiment on this -->
									<!-- after white bar adjust the value in between -->
									<tr height="20px">
										<td width=75px></td>
										<!-- white bar start -->
										<td width=4px></td>
										<td width=10px></td>
										<td width=8px></td>
										<td width=82px></td>
										<!-- white bar start -->
										<td width=4px></td>
										<td></td>
									</tr>
									<!-- profile picture -->
									<!-- 
									<tr height="80px">
										<td colspan=7 align=center><img
											src="${pageContext.request.contextPath}/profilepics/admin.jpg"
											style="width: 80px; height: 80px"></td>
									</tr>
									 -->
									<!-- user name -->
									<tr height="62px">
										<td colspan=7 align=center><login>add user</login></td>
									</tr>
									<tr height="5px">
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<!-- checkered line -->
									<tr height="26px">
										<td colspan=7 background="img/checkered.jpg"></td>
									</tr>
									<!-- f8f605 - yellow -->
									<tr height="5px">
										<td colspan=7 style="background-color: #f8f605"></td>
									</tr>
									<tr height="26px">
										<td colspan=7 background="img/checkered2.jpg"></td>
									</tr>
									<!-- spacer -->
									<tr height="26px">
										<td colspan=7></td>
									</tr>
									<!-- white marker -->
									<tr height="4px">
										<td></td>
										<td colspan=5 bgcolor=white></td>
										<td></td>
									</tr>
									<tr height="8px">
										<td></td>
										<td bgcolor=white></td>
										<td></td>
										<td></td>
										<td></td>
										<td bgcolor=white></td>
										<td></td>
									</tr>
									<!-- content -->
									<tr>
										<td colspan=7 height=10px></td>
									</tr>
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>
											username:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="text"
											name="username" size=20 value="${username}" /></td>
									</tr>
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>
											password:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="password"
											name="passwd" size=20 value="${passwd}" /></td>
									</tr>
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>
											confirm:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="password"
											name="passwdconfirm" size=20 value="${passwdconfirm}" /></td>
									</tr>
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>
											email:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="email" name="email"
											size=20 value="${email}" /></td>
									</tr>
									<tr height="18px">
										<td colspan=7 height=10px align=center><errorMessage>${adduserError}</errorMessage></td>
									</tr>
									<tr height="20px">
										<td colspan=7 height=10px align=center><saveSuccess>${saveSuccessMessage}</saveSuccess></td>
									</tr>
									<tr>
										<td colspan=7 height=10px align="center"><input
											type="button" value="add user" onclick="submit();"></td>
									</tr>
									<tr>
										<td colspan=7 height=20px></td>
									</tr>
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>
											passcode:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="password"
											name="passcode" size=15 maxlength="4" value="${passcode}" /></td>
									</tr>
									<tr>
										<td colspan=7 height=20px></td>
									</tr>
									<!-- white marker -->
									<tr height="8px">
										<td></td>
										<td bgcolor=white></td>
										<td></td>
										<td></td>
										<td></td>
										<td bgcolor=white></td>
										<td></td>
									</tr>
									<tr height="4px">
										<td></td>
										<td colspan=5 bgcolor=white></td>
										<td></td>
									</tr>
									<tr>
										<td colspan=7></td>
									</tr>
								</table>
							</td>
							<td style="width: 47px">
								<!-- table to hold the right red stripes -->
								<table style="width: 100%; height: 100%" cellpadding="0"
									cellspacing=0>
									<tr>
										<td background="img/redstripesright.jpg"></td>
									</tr>
								</table>
							</td>
							<td style="width: 120px; background-color: #10A01A"></td>
						</tr>
					</table>

				</td>
			</tr>
		</table>

	</form>

</body>

<script>
    document.getElementById("filePhoto").onchange = function() {
	var reader = new FileReader();

	reader.onload = function(e) {
	    // get loaded data and render thumbnail.
	    document.getElementById("picPreview").src = e.target.result;
	};

	// read the image file as a data URL.
	reader.readAsDataURL(this.files[0]);
    };
</script>

</html>
