<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ranks</title>

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
									<td align="right"><a href="home"><img
											src="img/1home2.jpg"></a></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="profile"><img
											src="img/2profile2.jpg"></a></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="search"><img
											src="img/3search2.jpg"></a></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="follow"><img
											src="img/4follow2.jpg"></a></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><img src="img/5ranks1.jpg"></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="history"><img
											src="img/6history2.jpg"></a></td>
									<td></td>
								</tr>
								<tr height="30px">
									<td colspan=2></td>
								</tr>
								<tr>
									<td align="right"><a href="config"><img
											src="img/7config2.jpg"></a></td>
									<td></td>
								</tr>
								<tr height="70px">
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td align="right"><a href="logout"><img
											src="img/9logout.jpg"></td>
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
									<td width=62px></td>
									<!-- space in the middle of 2 text-->
									<td width=8px></td>
									<!-- space align for value in the right-->
									<td width=30px></td>
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
								<!-- user name -->
								<tr height="62px">
									<td colspan=7 align=center><login>ranking</login></td>
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
									<td colspan=6 align=right><contentlabels>friends
										|</contentlabels></td>
									<td align=left><contentlabels>&nbsp;all</contentlabels></td>
								</tr>
								<tr>
									<td colspan=7 height=10px></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										time (race) :</contentlabels></td>
									<td></td>
									<td align=right><contentlabels>${frankbesttimerace}&nbsp;</contentlabels></td>
									<td><contentlabels>|</contentlabels></td>
									<td align=left><contentlabels>&nbsp;${arankbesttimerace}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										lap (race) :</contentlabels></td>
									<td></td>
									<td align=right><contentlabels>${frankbestlaprace}&nbsp;</contentlabels></td>
									<td><contentlabels>|</contentlabels></td>
									<td align=left><contentlabels>&nbsp;${arankbestlaprace}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										time (solo) :</contentlabels></td>
									<td></td>
									<td align=right><contentlabels>${frankbesttimesolo}&nbsp;</contentlabels></td>
									<td><contentlabels>|</contentlabels></td>
									<td align=left><contentlabels>&nbsp;${arankbesttimesolo}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										lap (solo) :</contentlabels></td>
									<td></td>
									<td align=right><contentlabels>${frankbestlapsolo}&nbsp;</contentlabels></td>
									<td><contentlabels>|</contentlabels></td>
									<td align=left><contentlabels>&nbsp;${arankbestlapsolo}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										pct :</contentlabels></td>
									<td></td>
									<td align=right><contentlabels>${frankbestpct}&nbsp;</contentlabels></td>
									<td><contentlabels>|</contentlabels></td>
									<td align=left><contentlabels>&nbsp;${arankbestpct}</contentlabels></td>
								</tr>
								<tr height=20px>
									<td colspan=3 align=right><contentlabels>best
										career points :</contentlabels></td>
									<td></td>
									<td align=right><contentlabels>${frankbestcareerpts}&nbsp;</contentlabels></td>
									<td><contentlabels>|</contentlabels></td>
									<td align=left><contentlabels>&nbsp;${arankbestcareerpts}</contentlabels></td>
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
