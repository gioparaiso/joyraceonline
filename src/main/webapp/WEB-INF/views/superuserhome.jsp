<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Super User Home</title>

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
</style>

</head>

<!-- #7f7f7f light gray -->
<body bgcolor="#7f7f7f">


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
									<td align="right"><img src="img/1home1.jpg"></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="addadmin"><img
											src="img/8addadmin2.jpg"></a></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="addsuperuser"><img
											src="img/8addsuperuser2.jpg"></a></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="searchsuperuser"><img
											src="img/3search2.jpg"></a></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="configsuperuser"><img
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
								<tr height="20px">
									<!-- white marker start -->
									<td width=75px></td>
									<!-- white marker width -->
									<td width=4px></td>
									<!-- space alignment for lest text label -->
									<td width=77px></td>
									<!-- space in the middle of 2 text-->
									<td width=8px></td>
									<!-- space align for value in the right-->
									<td width=15px></td>
									<!-- white marker width -->
									<td width=4px></td>
									<td></td>
								</tr>
								<!-- profile picture -->
								<!-- 
								<tr height="80px">
									<td colspan=7 align=center><img src="img/hamilton.jpg"></td>
								</tr>
								 -->
								<tr height="62px">
									<td colspan=7 align=center><login>all-time bests</login></td>
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
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										time (race) :</contentlabels></td>
									<td></td>
									<td colspan=3 align=left><contentlabels>${besttimerace}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										lap (race) :</contentlabels></td>
									<td></td>
									<td colspan=3 align=left><contentlabels>${bestlaprace}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										time (solo) :</contentlabels></td>
									<td></td>
									<td colspan=3 align=left><contentlabels>${besttimesolo}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										lap (solo) :</contentlabels></td>
									<td></td>
									<td colspan=3 align=left><contentlabels>${bestlapsolo}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										pct :</contentlabels></td>
									<td></td>
									<td colspan=3 align=left><contentlabels>${bestpct}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										career points :</contentlabels></td>
									<td></td>
									<td colspan=3 align=left><contentlabels>${bestcareerpoints}</contentlabels></td>
								</tr>
								<tr>
									<td colspan=7 height=10px></td>
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

</body>
</html>
