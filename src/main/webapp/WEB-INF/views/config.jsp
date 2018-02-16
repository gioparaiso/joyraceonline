<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Config</title>

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
<body bgcolor="#7f7f7f" onload="setPrivacy(${privacy};">

	<!-- 
	<form id="form1" action="config" runat="server" method="post"
		enctype="multipart/form-data">
		 -->

	<form name="configForm" method="post" action="config">


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
										<td></td>
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
										<td align="right"><img src="img/7config1.jpg"></td>
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
										<!-- white bar start -->
										<td width=4px></td>
										<td width=50px></td>
										<td width=8px></td>
										<td width=42px></td>
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
										<td colspan=7 align=center><login>config</login></td>
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
										<td colspan=3 align=right><contentlabels>change
											username:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="text" id="username"
											name="username" size=15 value="${username}" /></td>
									</tr>
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>change
											password:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="password"
											name="passwd" size=15 value="${passwd}" /></td>
									</tr>
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>confirm
											password:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="password"
											name="passwdconfirm" size=15 value="${passwdconfirm}" /></td>
									</tr>
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>private:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left><input type="checkbox"
											name="privacy" /></td>
									</tr>
									<tr height="18px">
										<td colspan=7 height=10px align=center><errorMessage>${configError}</errorMessage></td>
									</tr>
									<!-- 
									<tr height=30px>
										<td colspan=3 align=right><contentlabels>change
											profile pic:</contentlabels></td>
										<td></td>
										<td colspan=3 align=left></td>
									</tr>
									<tr height=30px>
										<td colspan=7 height=10px align="center"><input
											type="file" name="filePhoto" id="filePhoto" accept="image/*"
											class="inputfile"></td>
									</tr>
									<tr height="80px">
										<td colspan=7 align=center><table border=1
												style="height: 81px; width: 81px; border: 1px">
												<tr>
													<td align=center><img src="#" id="picPreview" alt=""
														style="width: 80px; height: 80px"></td>
												</tr>
											</table></td>
									</tr>
									<tr height="20px">
										<td colspan=7 height=10px align=center><errorMessage>${profilepicError}</errorMessage></td>
									</tr>
									 -->
									<tr height="20px">
										<td colspan=7 height=10px align=center><saveSuccess>${saveSuccessMessage}</saveSuccess></td>
									</tr>
									<tr>
										<td colspan=7 height=10px align="center"><input
											type="button" value="save changes" onclick="submit();"></td>
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

//    document.getElementById("filePhoto").onchange = function() {
//	var reader = new FileReader();

//	reader.onload = function(e) {
// get loaded data and render thumbnail.
//	    document.getElementById("picPreview").src = e.target.result;
//	};

// // read the image file as a data URL.
//	reader.readAsDataURL(this.files[0]);
//    };
    
    function setPrivacy(showVal) {
		if (showVal == 'Y') {
	    	document.getElementById('privacy').style.checked = 'checked';
		} else {
	    	document.getElementById('privacy').style.checked = 'unchecked';
	    }
    };
</script>

</html>
