<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>

<style>
login {
	font-size: 30px;
	font-family: Latha;
	font-weight: normal;
	color: #fdee06;
}

loginlabels {
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

errorMessage {
	font-style: normal;
	font-size: 12px;
	font-family: Latha;
	font-weight: normal;
	color: red;
}
</style>

</head>

<!-- #7f7f7f light gray -->
<body bgcolor="#7f7f7f" height="100%">

	<form name="AdminForm" method="post" action="admin">

		<!-- table to hold the entire page -->
		<!-- #10A01A green -->
		<table
			style="background-color: #7f7f7f; halign: center; width: 100%; height: 100%"
			cellpadding=0 cellspacing=0>
			<tr>
				<td align="center">
					<!-- table to hold the screen in the center -->
					<table style="align: center; height: 100%; width: 590px"
						cellpadding="0" cellspacing="0">
						<tr>
							<td style="width: 120px; background-color: #10A01A"></td>
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
								<!-- center content --> <!-- background-color: #4f534f; - dark grey -->
								<!-- background="img/drock051.gif" -->
								<table style="width: 100%; height: 100%" cellpadding="0"
									cellspacing="0" background="img/asphalt.jpg">
									<tr height="20px">
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr height="100px">
										<td colspan=5 align=center><login>Admin Login</login></td>
									</tr>
									<tr height="1px">
										<td width="30px"></td>
										<td></td>
										<td width="15px"></td>
										<td></td>
										<td width="30px"></td>
									</tr>
									<!-- error message -->
									<tr height="20px">
										<td colspan="5" align=center><errorMessage>${loginError}</errorMessage></td>
									</tr>
									<tr height="20px">
										<td colspan="5"></td>
									</tr>
									<tr height="30px">
										<td></td>
										<td><loginlabels>adminname</loginlabels></td>
										<td></td>
										<td><input type="text" name="adminname" size="18" /></td>
										<td></td>
									</tr>
									<tr height="30px">
										<td></td>
										<td><loginlabels>password</loginlabels></td>
										<td></td>
										<td><input type="password" name="password" size="18" /></td>
										<td></td>
									</tr>
									<tr height="40px">
										<!-- insert here -->
										<td colspan=4 align=right valign=bottom><input
											type="submit" value=" Go! " /></td>
										<td></td>
									</tr>
									<tr height="100px">
										<td colspan=5 align=right><a href="adminforgotpassword"><forgotlink>forgot
												adminname / password ?</forgotlink></a>&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td colspan=5></td>
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
</html>
