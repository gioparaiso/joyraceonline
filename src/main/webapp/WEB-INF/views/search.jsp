<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>

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

div {
	border: 1px solid #000;
	font-family: arial;
	height: 190px;
	width: 200px;
	background: white;
}

ul {
	list-style: none;
	height: 190px;
	margin: 0;
	overflow: auto;
	padding: 0;
	text-indent: 10px;
}

li {
	line-height: 25px;
}

li:nth-child(even) {
	background: #ccc;
}

errorMessage {
	font-style: normal;
	font-size: 12px;
	font-family: Latha;
	font-weight: normal;
	color: red;
}

#searchContent {
	background: transparent;
	border: 0;
}

#searchedUser {
	background: transparent;
	border: 0;
}
</style>

</head>

<!-- #7f7f7f light gray -->
<body bgcolor="#7f7f7f" onload="showSearch('${showSelected}');">

	<form name="searchForm" method="post" action="search">

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
										<td align="right"><img src="img/3search1.jpg"></td>
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
										<td align="right"><a href="ranks"><img
												src="img/5ranks2.jpg"></a></td>
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
										<td width=75px></td>
										<td width=4px></td>
										<td width=62px></td>
										<td width=8px></td>
										<td width=30px></td>
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
										<td colspan=7 align=center><login>search</login></td>
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
									<!-- white marker - end -->

									<!-- content -->
									<tr>
										<td colspan="7" align="center" valign="top" height="290px">
											<div id="searchContent">
												<table background="img/asphalt.jpg">
													<tr>
														<td align=center><contentlabels>username
															or email</contentlabels></td>
													</tr>
													<tr>
														<td align="center"><input type="text"
															name="usernameOrEmail" /></td>
													</tr>
													<tr>
														<td align="center"><input type="submit"
															value=" Search " /></td>
													</tr>
													<tr>
														<td>
															<div>
																<ul>
																	<c:forEach items="${users}" var="users">
																		<li><center>
																				<a href="search?showUser=${users}">${users}</a>
																			</center></li>
																	</c:forEach>
																</ul>
															</div>
														</td>
													</tr>
												</table>
											</div>

											<div id="searchedUser">
												<table height="80px" cellpadding="0" cellspacing="0">

													<tr height="1px">
														<td width=141px></td>
														<td width=8px></td>
														<td width=64px></td>
													</tr>
													<!-- 
													<tr>
														<td colspan=3 align=center><img src="img/vettel.jpg"
															width=40px height="40px"></td>
													</tr>
													 -->
													<tr>
														<td colspan=3 align=center><contentlabels>${usernameSearched}</contentlabels></td>
													</tr>
													<tr height=10px>
														<td align=right><contentlabels>best
															time (race) :</contentlabels></td>
														<td></td>
														<td align=left><contentlabels>${besttimerace}</contentlabels></td>
													</tr>
													<tr height=10px>
														<td align=right><contentlabels>best
															lap (race) :</contentlabels></td>
														<td></td>
														<td align=left><contentlabels>${bestlaprace}</contentlabels></td>
													</tr>
													<tr height=10px>
														<td align=right><contentlabels>best
															time (solo) :</contentlabels></td>
														<td></td>
														<td align=left><contentlabels>${besttimesolo}</contentlabels></td>
													</tr>
													<tr height=10px>
														<td align=right><contentlabels>best
															lap (solo) :</contentlabels></td>
														<td></td>
														<td align=left><contentlabels>${bestlapsolo}</contentlabels></td>
													</tr>
													<tr height=10px>
														<td align=right><contentlabels>wins :</contentlabels></td>
														<td></td>
														<td align=left><contentlabels>${wins}</contentlabels></td>
													</tr>
													<tr height=10px>
														<td align=right><contentlabels>losses
															:</contentlabels></td>
														<td></td>
														<td align=left><contentlabels>${losses}</contentlabels></td>
													</tr>
													<tr height=10px>
														<td align=right><contentlabels>pct :</contentlabels></td>
														<td></td>
														<td align=left><contentlabels>${pct}</contentlabels></td>
													</tr>
													<tr height=10px>
														<td align=right><contentlabels>career
															points :</contentlabels></td>
														<td></td>
														<td align=left><contentlabels>${careerpoints}</contentlabels></td>
													</tr>
													<tr>
														<td colspan=3 align=center><input type="button"
															value="${followStat}"
															onclick="window.location.href='search?${followStat}=${usernameSearched}'">&nbsp;&nbsp;<input
															type="button" value="back to search"
															onclick="toggleSearch();"></td>
													</tr>
													<tr height=10px>
														<td colspan=3><errorMessage>${errorMessage}</errorMessage></td>
													</tr>

												</table>
											</div>
										</td>
									</tr>

									<!-- content end -->

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
									<!-- white marker - end -->
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
    function showSearch(showVal) {
	if (showVal == 'true') {
	    document.getElementById('searchContent').style.display = 'none'; //Will hide search input
	    document.getElementById('searchedUser').style.display = 'block'; //Will show
	} else {
	    document.getElementById('searchContent').style.display = 'block'; //Will show
	    document.getElementById('searchedUser').style.display = 'none'; //Will hide search input
	}
    }

    function toggleSearch() {
	document.getElementById('searchContent').style.display = 'block'; //Will show
	document.getElementById('searchedUser').style.display = 'none'; //Will hide search input
    }
</script>

</html>
