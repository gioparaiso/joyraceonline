package com.letsrace.spring.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.letsrace.spring.model.Admin;
import com.letsrace.spring.model.User;
import com.letsrace.spring.service.AdminService;
import com.letsrace.spring.service.FollowListService;
import com.letsrace.spring.service.PhotoService;
import com.letsrace.spring.service.RaceLogService;
import com.letsrace.spring.service.UserService;

/**
 * This controller routes accesses to the application to the appropriate handler
 * methods.
 */

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

	@Autowired
	RaceLogService raceLogService;

	@Autowired
	FollowListService followListService;

	@Autowired
	PhotoService photoService;

	HomeControllerUtil util = new HomeControllerUtil();

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView showLoginPageByRoot(ModelAndView model,
			HttpServletRequest request) {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		return new ModelAndView("redirect:/home");
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(ModelAndView model) throws IOException {
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) {
		// usually post after login button is clicked
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		int usernameCount = userService.isUserExist(username);

		ModelAndView model = new ModelAndView("login");

		if (usernameCount == 0) { // no user found
			model.addObject("loginError", "No username found");
			model.setViewName("login");
		} else if (usernameCount == 2) { // duplicate username, very unlikely
			model.addObject("loginError",
					"Duplicate username. Contact Help support");
			model.setViewName("login");
		} else { // one username found, check password if correct
			boolean correctPassword = userService.isCorrectPassword(username,
					password);
			if (correctPassword == false) { // incorrect password
				model.addObject("loginError", "Incorrect password");
				model.setViewName("login");
			} else { // validated, go to home page

				String[] homeStat = userService.getHomeStat();
				model.addObject("besttimerace", homeStat[0] + " s");
				model.addObject("bestlaprace", homeStat[1] + " s");
				model.addObject("besttimesolo", homeStat[2] + " s");
				model.addObject("bestlapsolo", homeStat[3] + " s");
				model.addObject("bestpct", homeStat[4]);
				model.addObject("bestcareerpoints", homeStat[5]);

				User user = userService.getUserDetails(username);
				// model.addObject("user", user);

				request.getSession().setAttribute("username",
						user.getUsername());

				model.setViewName("redirect:/home");
				// model.setViewName("home");
			}
		}

		return model;
	}

	@RequestMapping(value = "/admin")
	public ModelAndView admin(ModelAndView model) throws IOException {
		model.setViewName("admin");
		return model;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public ModelAndView admin(HttpServletRequest request) {
		// usually post after login button is clicked
		String adminname = request.getParameter("adminname");
		String password = request.getParameter("password");

		int adminnameCount = adminService.isAdminExist(adminname);

		ModelAndView model = new ModelAndView("login");

		if (adminnameCount == 0) { // no admin found
			model.addObject("loginError", "No adminname found");
			model.setViewName("admin");
		} else if (adminnameCount == 2) { // duplicate adminname, very unlikely
			model.addObject("loginError",
					"Duplicate adminname. Contact Help support");
			model.setViewName("admin");
		} else { // one adminname found, check password if correct
			boolean correctPassword = adminService.isCorrectPassword(adminname,
					password);
			if (correctPassword == false) { // incorrect password
				model.addObject("loginError", "Incorrect password");
				model.setViewName("admin");
			} else { // validated, go to admin page

				Admin admin = adminService.getAdminDetails(adminname);

				request.getSession().setAttribute("adminname",
						admin.getAdminname());

				if (admin.getIssuperuser() != null
						&& admin.getIssuperuser().equalsIgnoreCase("Y"))
					model.setViewName("redirect:/superuserhome");
				else
					model.setViewName("redirect:/adminhome");
				// model.setViewName("adminhome");
			}
		}

		return model;
	}

	@RequestMapping(value = "/adduser")
	public ModelAndView adduser(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		model.addObject("username", "");
		model.addObject("passwd", "");
		model.addObject("passwdconfirm", "");
		model.addObject("email", "");
		model.addObject("passcode", "");
		model.setViewName("adduser");
		return model;
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ModelAndView adduser(HttpServletRequest request) {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		ModelAndView model = new ModelAndView("adduser");

		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		String passwordconfirm = request.getParameter("passwdconfirm");
		String email = request.getParameter("email");
		String passcode = request.getParameter("passcode");

		model.addObject("username", username);
		model.addObject("passwd", password);
		model.addObject("passwdconfirm", passwordconfirm);
		model.addObject("email", email);
		model.addObject("passcode", "");
		model.setViewName("adduser");

		Pattern pattern = Pattern
				.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		if (email == null)
			email = "";
		Matcher mat = pattern.matcher(email);

		if ((username == null) || (username.equalsIgnoreCase("") == true)) {
			model.addObject("adduserError", "Please input a username");
			return model;
		} else if ((password == null)
				|| (password.equalsIgnoreCase("") == true)) {
			model.addObject("adduserError", "Please input a password");
			return model;
		} else if ((passwordconfirm == null)
				|| (passwordconfirm.equalsIgnoreCase("") == true)) {
			model.addObject("adduserError", "Please input a confirm password");
			return model;
		} else if ((email == null) || (email.equalsIgnoreCase("") == true)) {
			model.addObject("adduserError", "Please input a email");
			return model;
		} else if (password.equalsIgnoreCase(passwordconfirm) == false) {
			model.addObject("adduserError", "Passwords does not match");
			return model;
		} else if (mat.matches() == false) {
			model.addObject("adduserError", "Not a valid email");
			return model;
		} else if ((passcode == null)
				|| (passcode.equalsIgnoreCase("") == true)) {
			model.addObject("adduserError", "Please endter passcode");
			return model;
		}

		// check if admin's passcode is correct
		String adminname = (String) request.getSession().getAttribute(
				"adminname");

		int adminPasscode = adminService.getPasscode(adminname);

		if (adminPasscode == 0) {
			model.addObject("adduserError", "Passcode not set");
			return model;
		}
		if (util.isNumber(passcode) == 0) {
			model.addObject("adduserError", "Passcode should be a number");
			return model;
		}
		if (adminPasscode != Integer.parseInt(passcode)) {
			model.addObject("adduserError", "Incorrect passcode");
			return model;
		}

		// check if username already exist
		if (userService.isUserExist(username) > 0) {
			model.addObject("adduserError", "Username already exist.");
			return model;
		}

		// add the user
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		boolean retVal = userService.saveUser(user);
		if (retVal == false)
			model.addObject("adduserError", "User was not added.");
		else
			model.addObject("saveSuccessMessage", "User " + user.getUsername()
					+ " was successfully added.");

		return model;
	}

	@RequestMapping(value = "/addadmin")
	public ModelAndView addadmin(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		model.addObject("adminname", "");
		model.addObject("passwd", "");
		model.addObject("passwdconfirm", "");
		model.addObject("email", "");
		model.addObject("passcode", "");
		model.setViewName("superuseraddadmin");
		return model;
	}

	@RequestMapping(value = "/addadmin", method = RequestMethod.POST)
	public ModelAndView addadmin(HttpServletRequest request) {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		ModelAndView model = new ModelAndView("superuseraddadmin");

		String adminname = request.getParameter("adminname");
		String password = request.getParameter("passwd");
		String passwordconfirm = request.getParameter("passwdconfirm");
		String email = request.getParameter("email");
		String passcode = request.getParameter("passcode");

		model.addObject("adminname", adminname);
		model.addObject("passwd", password);
		model.addObject("passwdconfirm", passwordconfirm);
		model.addObject("email", email);
		model.addObject("passcode", "");

		Pattern pattern = Pattern
				.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		if (email == null)
			email = "";
		Matcher mat = pattern.matcher(email);

		if ((adminname == null) || (adminname.equalsIgnoreCase("") == true)) {
			model.addObject("addAdminError", "Please input a adminname");
			return model;
		} else if ((password == null)
				|| (password.equalsIgnoreCase("") == true)) {
			model.addObject("addAdminError", "Please input a password");
			return model;
		} else if ((passwordconfirm == null)
				|| (passwordconfirm.equalsIgnoreCase("") == true)) {
			model.addObject("addAdminError", "Please input a confirm password");
			return model;
		} else if ((email == null) || (email.equalsIgnoreCase("") == true)) {
			model.addObject("addAdminError", "Please input a email");
			return model;
		} else if (password.equalsIgnoreCase(passwordconfirm) == false) {
			model.addObject("addAdminError", "Passwords does not match");
			return model;
		} else if (mat.matches() == false) {
			model.addObject("addAdminError", "Not a valid email");
			return model;
		} else if ((passcode == null)
				|| (passcode.equalsIgnoreCase("") == true)) {
			model.addObject("addAdminError", "Please endter passcode");
			return model;
		}

		// check if admin's passcode is correct
		String adminnamelogged = (String) request.getSession().getAttribute(
				"adminname");

		int adminPasscode = adminService.getPasscode(adminnamelogged);

		if (adminPasscode == 0) {
			model.addObject("adduserError", "Passcode not set");
			return model;
		}
		if (util.isNumber(passcode) == 0) {
			model.addObject("addAdminError", "Passcode should be a number");
			return model;
		}
		if (adminPasscode != Integer.parseInt(passcode)) {
			model.addObject("addAdminError", "Incorrect passcode");
			return model;
		}

		// check if adminname already exist
		if (adminService.isAdminExist(adminname) > 0) {
			model.addObject("addAdminError", "adminname already exist.");
			return model;
		}

		// add the admin
		Admin admin = new Admin();
		admin.setAdminname(adminname);
		admin.setPassword(password);
		admin.setEmail(email);

		boolean retVal = adminService.saveAdmin(admin);
		if (retVal == false)
			model.addObject("addAdminError", "Admin was not added.");
		else
			model.addObject("saveSuccessMessage",
					"Admin " + admin.getAdminname()
							+ " was successfully added.");

		return model;
	}

	@RequestMapping(value = "/addsuperuser")
	public ModelAndView addsuperuser(ModelAndView model,
			HttpServletRequest request) throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		model.addObject("superusername", "");
		model.addObject("passwd", "");
		model.addObject("passwdconfirm", "");
		model.addObject("email", "");
		model.addObject("passcode", "");
		model.setViewName("superuseraddsuperuser");
		return model;
	}

	@RequestMapping(value = "/addsuperuser", method = RequestMethod.POST)
	public ModelAndView addsuperuser(HttpServletRequest request) {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		ModelAndView model = new ModelAndView("superuseraddsuperuser");

		String superusername = request.getParameter("superusername");
		String password = request.getParameter("passwd");
		String passwordconfirm = request.getParameter("passwdconfirm");
		String email = request.getParameter("email");
		String passcode = request.getParameter("passcode");

		model.addObject("superusername", superusername);
		model.addObject("passwd", password);
		model.addObject("passwdconfirm", passwordconfirm);
		model.addObject("email", email);
		model.addObject("passcode", "");

		Pattern pattern = Pattern
				.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		if (email == null)
			email = "";
		Matcher mat = pattern.matcher(email);

		if ((superusername == null)
				|| (superusername.equalsIgnoreCase("") == true)) {
			model.addObject("addSuperUserError", "Please input a superusername");
			return model;
		} else if ((password == null)
				|| (password.equalsIgnoreCase("") == true)) {
			model.addObject("addSuperUserError", "Please input a password");
			return model;
		} else if ((passwordconfirm == null)
				|| (passwordconfirm.equalsIgnoreCase("") == true)) {
			model.addObject("addSuperUserError",
					"Please input a confirm password");
			return model;
		} else if ((email == null) || (email.equalsIgnoreCase("") == true)) {
			model.addObject("addSuperUserError", "Please input a email");
			return model;
		} else if (password.equalsIgnoreCase(passwordconfirm) == false) {
			model.addObject("addSuperUserError", "Passwords does not match");
			return model;
		} else if (mat.matches() == false) {
			model.addObject("addSuperUserError", "Not a valid email");
			return model;
		} else if ((passcode == null)
				|| (passcode.equalsIgnoreCase("") == true)) {
			model.addObject("addSuperUserError", "Please endter passcode");
			return model;
		}

		// check if superuser's passcode is correct
		String superusernamelogged = (String) request.getSession()
				.getAttribute("adminname");

		int superuserPasscode = adminService.getPasscode(superusernamelogged);

		if (superuserPasscode == 0) {
			model.addObject("adduserError", "Passcode not set");
			return model;
		}
		if (util.isNumber(passcode) == 0) {
			model.addObject("addSuperUserError", "Passcode should be a number");
			return model;
		}
		if (superuserPasscode != Integer.parseInt(passcode)) {
			model.addObject("addSuperUserError", "Incorrect passcode");
			return model;
		}

		// check if superusername already exist
		if (adminService.isAdminExist(superusername) > 0) {
			model.addObject("addSuperUserError", "superusername already exist.");
			return model;
		}

		// add the superuser
		Admin admin = new Admin();
		admin.setAdminname(superusername);
		admin.setPassword(password);
		admin.setEmail(email);
		admin.setIssuperuser("Y");

		boolean retVal = adminService.saveAdmin(admin);
		if (retVal == false)
			model.addObject("addSuperUserError", "Super User was not added.");
		else
			model.addObject("saveSuccessMessage",
					"Admin " + admin.getAdminname()
							+ " was successfully added.");

		return model;
	}

	@RequestMapping(value = "/adminhome")
	public ModelAndView adminhome(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		String[] homeStat = userService.getHomeStat();
		model.addObject("besttimerace", homeStat[0] + " s");
		model.addObject("bestlaprace", homeStat[1] + " s");
		model.addObject("besttimesolo", homeStat[2] + " s");
		model.addObject("bestlapsolo", homeStat[3] + " s");
		model.addObject("bestpct", homeStat[4]);
		model.addObject("bestcareerpoints", homeStat[5]);

		model.setViewName("adminhome");

		return model;
	}

	@RequestMapping(value = "/superuserhome")
	public ModelAndView superuserhome(ModelAndView model,
			HttpServletRequest request) throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		String[] homeStat = userService.getHomeStat();
		model.addObject("besttimerace", homeStat[0] + " s");
		model.addObject("bestlaprace", homeStat[1] + " s");
		model.addObject("besttimesolo", homeStat[2] + " s");
		model.addObject("bestlapsolo", homeStat[3] + " s");
		model.addObject("bestpct", homeStat[4]);
		model.addObject("bestcareerpoints", homeStat[5]);

		model.setViewName("superuserhome");

		return model;
	}

	@RequestMapping(value = "/home")
	public ModelAndView home(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String[] homeStat = userService.getHomeStat();
		model.addObject("besttimerace", homeStat[0] + " s");
		model.addObject("bestlaprace", homeStat[1] + " s");
		model.addObject("besttimesolo", homeStat[2] + " s");
		model.addObject("bestlapsolo", homeStat[3] + " s");
		model.addObject("bestpct", homeStat[4]);
		model.addObject("bestcareerpoints", homeStat[5]);

		model.setViewName("home");

		return model;
	}

	@RequestMapping(value = "/profile")
	public ModelAndView profile(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String username = (String) request.getSession()
				.getAttribute("username");

		User user = userService.getUserDetails(username);
		model.addObject("user", user);

		String[] userStat = userService.getUserStat(username);
		model.addObject("besttimerace", util.check999(userStat[0]));
		model.addObject("bestlaprace", util.check999(userStat[1]));
		model.addObject("besttimesolo", util.check999(userStat[2]));
		model.addObject("bestlapsolo", util.check999(userStat[3]));
		model.addObject("wins", userStat[4]);
		model.addObject("losses", userStat[5]);
		model.addObject("pct", userStat[6]);
		model.addObject("careerpoints", userStat[7]);

		model.setViewName("profile");

		return model;
	}

	@RequestMapping(value = "/search")
	public ModelAndView search(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		model.setViewName("search");
		model.addObject("showSelected", "false");

		// user selected at the search list
		String showUser = request.getParameter("showUser");
		String userIdLogged = (String) request.getSession().getAttribute(
				"username");
		// same as showUser, this time used as parameter for actions
		String usernameSearched = (String) request.getSession().getAttribute(
				"usernameSearched");

		// parameter value and actions - start
		String follow = request.getParameter("follow");
		String unfollow = request.getParameter("unfollow");
		String requestFollow = request.getParameter("request");
		String requested = request.getParameter("requested");
		// parameter value and actions - end

		// store user stat
		String[] userStat = new String[8];

		// [0:private] [1:public]
		int privacy = 1;
		// used as action and label for button
		int followStat = 0;

		// show selected user from search
		if (showUser != null && showUser.equalsIgnoreCase("") == false) {
			userStat = userService.getUserStat(showUser);
			model.addObject("usernameSearched", showUser);

			// check if logged in user is already following (searched user)
			// [0:not following] [1:following] [2:requested] [4:error]
			followStat = followListService
					.getFollowStat(userIdLogged, showUser);

			privacy = userService.getPrivacy(showUser);

			if (followStat == 0) { // not yet followed
				if (privacy == 0) // 0=private
					model.addObject("followStat", "request");
				else
					model.addObject("followStat", "follow");
			} else if (followStat == 1) { // already following
				model.addObject("followStat", "unfollow");
				privacy = 1; // just so it will display stats, set public
			} else if (followStat == 2) { // already requested
				model.addObject("followStat", "requested");
			}

			model.addObject("showSelected", "true");
		} else if ((follow != null) && (follow.equalsIgnoreCase("") == false)) {
			// follow button clicked

			// check if you have 100 followers or you are to follow user with
			// 100
			boolean limitReached = false;
			limitReached = followListService
					.isFollowingLimitMaxed(userIdLogged);
			if (limitReached == true)
				model.addObject("errorMessage",
						"You're already following 100 people.");
			else {
				limitReached = followListService.isFollowersLimitMaxed(follow);
				if (limitReached == true)
					model.addObject("errorMessage", follow
							+ " already have 100 followers.");
			}

			userStat = userService.getUserStat(follow);
			model.addObject("usernameSearched", follow);

			// set to follow in db
			if (limitReached == false) {
				int followUserStatus = followListService.followUser(
						userIdLogged, follow);

				if (followUserStatus == 0) {
					// TODO show error message on screen
					// db error is printed on the system out
				}
				model.addObject("followStat", "unfollow");
			}
			model.addObject("showSelected", "true");
		} else if ((unfollow != null)
				&& (unfollow.equalsIgnoreCase("") == false)) { // unfollow
																// button
																// clicked
			userStat = userService.getUserStat(unfollow);
			model.addObject("usernameSearched", unfollow);

			// set to unfollow in db
			int unfollowUserStatus = followListService.unfollowUser(
					userIdLogged, unfollow);

			if (unfollowUserStatus == 0) {
				// TODO: show message on screen
				// db error already logged on system.out
			}

			// check if private
			privacy = userService.getPrivacy(unfollow);
			// 0=private
			if (privacy == 0) {
				model.addObject("followStat", "request");
			} else { // 1=public
				model.addObject("followStat", "follow");
			}
			model.addObject("showSelected", "true");
		} else if ((requestFollow != null)
				&& (requestFollow.equalsIgnoreCase("") == false)) {
			// request button clicked

			// check if you have 100 followers or you are to follow user with
			// 100
			boolean limitReached = false;
			limitReached = followListService
					.isFollowingLimitMaxed(userIdLogged);
			if (limitReached == true)
				model.addObject("errorMessage",
						"You're already following 100 people.");
			else {
				limitReached = followListService.isFollowersLimitMaxed(follow);
				if (limitReached == true)
					model.addObject("errorMessage", follow
							+ " already have 100 followers.");
			}

			userStat = userService.getUserStat(requestFollow);
			model.addObject("usernameSearched", requestFollow);

			privacy = userService.getPrivacy(requestFollow);

			// function to request follow
			if (limitReached == false) {
				int requestStatus = followListService.requestFollowUser(
						userIdLogged, requestFollow);

				if (requestStatus == 0) {
					// TODO: show message on screen
					// db error already logged on system.out
				}

				model.addObject("followStat", "requested");
			}
			model.addObject("showSelected", "true");
		} else if ((requested != null)
				&& (requested.equalsIgnoreCase("") == false)) { // requested
																// button
																// clicked
			userStat = userService.getUserStat(requested);
			model.addObject("usernameSearched", requested);

			privacy = userService.getPrivacy(requested);

			// function to cancel request to follow
			int cancelRequest = followListService.cancelRequest(userIdLogged,
					requested);

			if (cancelRequest == 0) {
				// TODO: show message on screen
				// db error already logged on system.out
			}

			model.addObject("followStat", "request");
			model.addObject("showSelected", "true");
		}

		if (privacy == 0) {
			model.addObject("besttimerace", "private");
			model.addObject("bestlaprace", "private");
			model.addObject("besttimesolo", "private");
			model.addObject("bestlapsolo", "private");
			model.addObject("wins", "private");
			model.addObject("losses", "private");
			model.addObject("pct", "private");
			model.addObject("careerpoints", "private");
		} else if ((showUser != null) || (follow != null) || (unfollow != null)
				|| (requestFollow != null) || (requested != null)) {
			model.addObject("besttimerace", util.check999(userStat[0]));
			model.addObject("bestlaprace", util.check999(userStat[1]));
			model.addObject("besttimesolo", util.check999(userStat[2]));
			model.addObject("bestlapsolo", util.check999(userStat[3]));
			model.addObject("wins", userStat[4]);
			model.addObject("losses", userStat[5]);
			model.addObject("pct", userStat[6]);
			model.addObject("careerpoints", userStat[7]);
		}

		return model;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("search");

		String loggedUser = (String) request.getSession().getAttribute(
				"username");
		String usernameOrEmail = request.getParameter("usernameOrEmail");
		List<String> users = userService.findUserByNameOrEmail(usernameOrEmail,
				loggedUser);

		if (users == null) { // no user found
			model.addObject("searchMessage", "No username found");
		} else { // one or more users found, set to list
			model.addObject("users", users);
		}

		return model;
	}

	@RequestMapping(value = "/searchadmin")
	public ModelAndView searchadmin(ModelAndView model,
			HttpServletRequest request) {

		model.setViewName("adminsearch");

		String showUser = request.getParameter("showUser");
		// store user stat
		String[] userStat = new String[8];

		// show selected user from search
		if (showUser != null && showUser.equalsIgnoreCase("") == false) {
			userStat = userService.getUserStat(showUser);
			model.addObject("besttimerace", util.check999(userStat[0]));
			model.addObject("bestlaprace", util.check999(userStat[1]));
			model.addObject("besttimesolo", util.check999(userStat[2]));
			model.addObject("bestlapsolo", util.check999(userStat[3]));
			model.addObject("wins", userStat[4]);
			model.addObject("losses", userStat[5]);
			model.addObject("pct", userStat[6]);
			model.addObject("careerpoints", userStat[7]);

			model.addObject("usernameSearched", showUser);

			model.addObject("showSelected", "true");
		}

		return model;
	}

	@RequestMapping(value = "/searchadmin", method = RequestMethod.POST)
	public ModelAndView searchadmin(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("adminsearch");

		String usernameOrEmail = request.getParameter("usernameOrEmail");
		List<String> users = userService.findUserByNameOrEmail(usernameOrEmail,
				"");

		if (users == null) { // no user found
			model.addObject("searchMessage", "No username found");
		} else { // one or more users found, set to list
			model.addObject("users", users);
		}

		return model;
	}

	@RequestMapping(value = "/searchsuperuser")
	public ModelAndView searchsuperuser(ModelAndView model,
			HttpServletRequest request) {

		model.setViewName("superusersearch");

		String showUser = request.getParameter("showUser");
		// store user stat
		String[] userStat = new String[8];

		// show selected user from search
		if (showUser != null && showUser.equalsIgnoreCase("") == false) {
			userStat = userService.getUserStat(showUser);
			model.addObject("besttimerace", util.check999(userStat[0]));
			model.addObject("bestlaprace", util.check999(userStat[1]));
			model.addObject("besttimesolo", util.check999(userStat[2]));
			model.addObject("bestlapsolo", util.check999(userStat[3]));
			model.addObject("wins", userStat[4]);
			model.addObject("losses", userStat[5]);
			model.addObject("pct", userStat[6]);
			model.addObject("careerpoints", userStat[7]);

			model.addObject("usernameSearched", showUser);

			model.addObject("showSelected", "true");
		}

		return model;
	}

	@RequestMapping(value = "/searchsuperuser", method = RequestMethod.POST)
	public ModelAndView searchsuperuser(HttpServletRequest request) {

		ModelAndView model = new ModelAndView("superusersearch");

		String usernameOrEmail = request.getParameter("usernameOrEmail");
		List<String> users = userService.findUserByNameOrEmail(usernameOrEmail,
				"");

		if (users == null) { // no user found
			model.addObject("searchMessage", "No username found");
		} else { // one or more users found, set to list
			model.addObject("users", users);
		}

		return model;
	}

	@RequestMapping(value = "/follow")
	public ModelAndView follow(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String loggedUser = (String) request.getSession().getAttribute(
				"username");

		// get count for following, following and requests
		Integer[] followStat = new Integer[3];
		followStat = followListService.getFollowStats(loggedUser);

		model.addObject("followers", String.valueOf(followStat[0]));
		model.addObject("following", String.valueOf(followStat[1]));
		model.addObject("requests", String.valueOf(followStat[2]));

		model.setViewName("follow");

		return model;
	}

	@RequestMapping(value = "/followers")
	public ModelAndView followers(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String loggedUser = (String) request.getSession().getAttribute(
				"username");

		List<String> followers = followListService
				.getFollowersNames(loggedUser);
		int followersCount = 0;
		if (followers != null)
			followersCount = followers.size();

		model.addObject("followers", String.valueOf(followersCount));
		model.addObject("followerNames", followers);
		model.addObject("showSelected", "false");

		model.setViewName("followers");

		// same as showUser, this time used as parameter for actions
		String usernameSelected = (String) request.getSession().getAttribute(
				"usernameSelected");

		// parameter value and actions - start
		String follow = request.getParameter("follow");
		String unfollow = request.getParameter("unfollow");
		String requestFollow = request.getParameter("request");
		String requested = request.getParameter("requested");
		String removeUser = request.getParameter("removeUser");
		// parameter value and actions - end

		// store user stat
		String[] userStat = new String[8];

		// [0:private] [1:public]
		int privacy = 1;
		// used as action and label for button
		int followStat = 0;

		// user selected at the follower list
		String showUser = request.getParameter("showUser");

		// show selected user from search
		if (showUser != null && showUser.equalsIgnoreCase("") == false) {
			userStat = userService.getUserStat(showUser);
			model.addObject("usernameSelected", showUser);

			// check if logged in user is already following (searched user)
			// [0:not following] [1:following] [2:requested] [4:error]
			followStat = followListService.getFollowStat(loggedUser, showUser);

			privacy = userService.getPrivacy(showUser);

			if (followStat == 0) { // not yet followed
				if (privacy == 0) // 0=private
					model.addObject("followStat", "request");
				else
					model.addObject("followStat", "follow");
			} else if (followStat == 1) { // already following
				model.addObject("followStat", "unfollow");
				privacy = 1; // just so it will display stats, set public
			} else if (followStat == 2) { // already requested
				model.addObject("followStat", "requested");
			}

			model.addObject("showSelected", "true");
		} else if ((follow != null) && (follow.equalsIgnoreCase("") == false)) {
			// follow button clicked

			// check if you have 100 followers or you are to follow user with
			// 100
			boolean limitReached = false;
			limitReached = followListService.isFollowingLimitMaxed(loggedUser);
			if (limitReached == true)
				model.addObject("errorMessage",
						"You're already following 100 people.");
			else {
				limitReached = followListService.isFollowersLimitMaxed(follow);
				if (limitReached == true)
					model.addObject("errorMessage", follow
							+ " already have 100 followers.");
			}

			userStat = userService.getUserStat(follow);
			model.addObject("usernameSelected", follow);

			// set to follow in db
			if (limitReached == false) {
				int followUserStatus = followListService.followUser(loggedUser,
						follow);

				if (followUserStatus == 0) {
					// TODO show error message on screen
					// db error is printed on the system out
				}
				model.addObject("followStat", "unfollow");
			}
			model.addObject("showSelected", "true");
		} else if ((unfollow != null)
				&& (unfollow.equalsIgnoreCase("") == false)) { // unfollow
																// button
																// clicked
			userStat = userService.getUserStat(unfollow);
			model.addObject("usernameSelected", unfollow);

			// set to unfollow in db
			int unfollowUserStatus = followListService.unfollowUser(loggedUser,
					unfollow);

			if (unfollowUserStatus == 0) {
				// TODO: show message on screen
				// db error already logged on system.out
			}

			// check if private
			privacy = userService.getPrivacy(unfollow);
			// 0=private
			if (privacy == 0) {
				model.addObject("followStat", "request");
			} else { // 1=public
				model.addObject("followStat", "follow");
			}
			model.addObject("showSelected", "true");
		} else if ((requestFollow != null)
				&& (requestFollow.equalsIgnoreCase("") == false)) {
			// request button clicked

			// check if you have 100 followers or you are to follow user with
			// 100
			boolean limitReached = false;
			limitReached = followListService.isFollowingLimitMaxed(loggedUser);
			if (limitReached == true)
				model.addObject("errorMessage",
						"You're already following 100 people.");
			else {
				limitReached = followListService.isFollowersLimitMaxed(follow);
				if (limitReached == true)
					model.addObject("errorMessage", follow
							+ " already have 100 followers.");
			}

			userStat = userService.getUserStat(requestFollow);
			model.addObject("usernameSelected", requestFollow);

			privacy = userService.getPrivacy(requestFollow);

			// function to request follow
			if (limitReached == false) {
				int requestStatus = followListService.requestFollowUser(
						loggedUser, requestFollow);

				if (requestStatus == 0) {
					// TODO: show message on screen
					// db error already logged on system.out
				}

				model.addObject("followStat", "requested");
			}
			model.addObject("showSelected", "true");
		} else if ((requested != null)
				&& (requested.equalsIgnoreCase("") == false)) { // requested
																// button
																// clicked
			userStat = userService.getUserStat(requested);
			model.addObject("usernameSelected", requested);

			privacy = userService.getPrivacy(requested);

			// function to cancel request to follow
			int cancelRequest = followListService.cancelRequest(loggedUser,
					requested);

			if (cancelRequest == 0) {
				// TODO: show message on screen
				// db error already logged on system.out
			}

			model.addObject("followStat", "request");
			model.addObject("showSelected", "true");
		} else if ((removeUser != null)
				&& (removeUser.equalsIgnoreCase("") == false)) { // remove
																	// user
																	// clicked
			// change db values, remove 'R'
			int removeStat = followListService.removeFollower(loggedUser,
					removeUser);
			// show requests list again
			model.addObject("showSelected", "false");
			model.setViewName("redirect:/followers");
		}

		if (privacy == 0) {
			model.addObject("besttimerace", "private");
			model.addObject("bestlaprace", "private");
			model.addObject("besttimesolo", "private");
			model.addObject("bestlapsolo", "private");
			model.addObject("wins", "private");
			model.addObject("losses", "private");
			model.addObject("pct", "private");
			model.addObject("careerpoints", "private");
		} else if ((showUser != null) || (follow != null) || (unfollow != null)
				|| (requestFollow != null) || (requested != null)) {
			model.addObject("besttimerace", util.check999(userStat[0]));
			model.addObject("bestlaprace", util.check999(userStat[1]));
			model.addObject("besttimesolo", util.check999(userStat[2]));
			model.addObject("bestlapsolo", util.check999(userStat[3]));
			model.addObject("wins", userStat[4]);
			model.addObject("losses", userStat[5]);
			model.addObject("pct", userStat[6]);
			model.addObject("careerpoints", userStat[7]);
		}

		return model;
	}

	@RequestMapping(value = "/following")
	public ModelAndView following(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String loggedUser = (String) request.getSession().getAttribute(
				"username");

		List<String> following = followListService
				.getFollowingNames(loggedUser);
		int followingCount = 0;
		if (following != null)
			followingCount = following.size();

		model.addObject("following", String.valueOf(followingCount));
		model.addObject("followingNames", following);
		model.addObject("showSelected", "false");

		model.setViewName("following");

		// same as showUser, this time used as parameter for actions
		String usernameSelected = (String) request.getSession().getAttribute(
				"usernameSelected");

		// parameter value and actions - start
		String follow = request.getParameter("follow");
		String unfollow = request.getParameter("unfollow");
		String requestFollow = request.getParameter("request");
		String requested = request.getParameter("requested");
		// parameter value and actions - end

		// store user stat
		String[] userStat = new String[8];

		// [0:private] [1:public]
		int privacy = 1;
		// used as action and label for button
		int followStat = 0;

		// user selected at the follower list
		String showUser = request.getParameter("showUser");

		// show selected user from search
		if (showUser != null && showUser.equalsIgnoreCase("") == false) {
			userStat = userService.getUserStat(showUser);
			model.addObject("usernameSelected", showUser);

			// check if logged in user is already following (searched user)
			// [0:not following] [1:following] [2:requested] [4:error]
			followStat = followListService.getFollowStat(loggedUser, showUser);

			privacy = userService.getPrivacy(showUser);

			if (followStat == 0) { // not yet followed
				if (privacy == 0) // 0=private
					model.addObject("followStat", "request");
				else
					model.addObject("followStat", "follow");
			} else if (followStat == 1) { // already following
				model.addObject("followStat", "unfollow");
				privacy = 1; // just so it will display stats, set public
			} else if (followStat == 2) { // already requested
				model.addObject("followStat", "requested");
			}

			model.addObject("showSelected", "true");
		} else if ((follow != null) && (follow.equalsIgnoreCase("") == false)) {
			// follow button clicked

			// check if you have 100 followers or you are to follow user with
			// 100
			boolean limitReached = false;
			limitReached = followListService.isFollowingLimitMaxed(loggedUser);
			if (limitReached == true)
				model.addObject("errorMessage",
						"You're already following 100 people.");
			else {
				limitReached = followListService.isFollowersLimitMaxed(follow);
				if (limitReached == true)
					model.addObject("errorMessage", follow
							+ " already have 100 followers.");
			}

			userStat = userService.getUserStat(follow);
			model.addObject("usernameSelected", follow);

			// set to follow in db
			if (limitReached == false) {
				int followUserStatus = followListService.followUser(loggedUser,
						follow);

				if (followUserStatus == 0) {
					// TODO show error message on screen
					// db error is printed on the system out
				}
				model.addObject("followStat", "unfollow");
			}
			model.addObject("showSelected", "true");
		} else if ((unfollow != null)
				&& (unfollow.equalsIgnoreCase("") == false)) { // unfollow
																// button
																// clicked
			userStat = userService.getUserStat(unfollow);
			model.addObject("usernameSelected", unfollow);

			// set to unfollow in db
			int unfollowUserStatus = followListService.unfollowUser(loggedUser,
					unfollow);

			if (unfollowUserStatus == 0) {
				// TODO: show message on screen
				// db error already logged on system.out
			}

			// check if private
			privacy = userService.getPrivacy(unfollow);
			// 0=private
			if (privacy == 0) {
				model.addObject("followStat", "request");
			} else { // 1=public
				model.addObject("followStat", "follow");
			}
			model.addObject("showSelected", "true");
		} else if ((requestFollow != null)
				&& (requestFollow.equalsIgnoreCase("") == false)) {
			// request button clicked

			// check if you have 100 followers or you are to follow user with
			// 100
			boolean limitReached = false;
			limitReached = followListService.isFollowingLimitMaxed(loggedUser);
			if (limitReached == true)
				model.addObject("errorMessage",
						"You're already following 100 people.");
			else {
				limitReached = followListService.isFollowersLimitMaxed(follow);
				if (limitReached == true)
					model.addObject("errorMessage", follow
							+ " already have 100 followers.");
			}

			userStat = userService.getUserStat(requestFollow);
			model.addObject("usernameSelected", requestFollow);

			privacy = userService.getPrivacy(requestFollow);

			// function to request follow
			if (limitReached == false) {
				int requestStatus = followListService.requestFollowUser(
						loggedUser, requestFollow);

				if (requestStatus == 0) {
					// TODO: show message on screen
					// db error already logged on system.out
				}

				model.addObject("followStat", "requested");
			}
			model.addObject("showSelected", "true");
		} else if ((requested != null)
				&& (requested.equalsIgnoreCase("") == false)) { // requested
																// button
																// clicked
			userStat = userService.getUserStat(requested);
			model.addObject("usernameSelected", requested);

			privacy = userService.getPrivacy(requested);

			// function to cancel request to follow
			int cancelRequest = followListService.cancelRequest(loggedUser,
					requested);

			if (cancelRequest == 0) {
				// TODO: show message on screen
				// db error already logged on system.out
			}

			model.addObject("followStat", "request");
			model.addObject("showSelected", "true");
		}

		if (privacy == 0) {
			model.addObject("besttimerace", "private");
			model.addObject("bestlaprace", "private");
			model.addObject("besttimesolo", "private");
			model.addObject("bestlapsolo", "private");
			model.addObject("wins", "private");
			model.addObject("losses", "private");
			model.addObject("pct", "private");
			model.addObject("careerpoints", "private");
		} else if ((showUser != null) || (follow != null) || (unfollow != null)
				|| (requestFollow != null) || (requested != null)) {
			model.addObject("besttimerace", util.check999(userStat[0]));
			model.addObject("bestlaprace", util.check999(userStat[1]));
			model.addObject("besttimesolo", util.check999(userStat[2]));
			model.addObject("bestlapsolo", util.check999(userStat[3]));
			model.addObject("wins", userStat[4]);
			model.addObject("losses", userStat[5]);
			model.addObject("pct", userStat[6]);
			model.addObject("careerpoints", userStat[7]);
		}

		return model;
	}

	@RequestMapping(value = "/followrequests")
	public ModelAndView followrequests(ModelAndView model,
			HttpServletRequest request) throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String loggedUser = (String) request.getSession().getAttribute(
				"username");

		List<String> followrequests = followListService
				.getFollowrequestsNames(loggedUser);
		int followrequestsCount = 0;
		if (followrequests != null)
			followrequestsCount = followrequests.size();

		model.addObject("followrequests", String.valueOf(followrequestsCount));
		model.addObject("followrequestsNames", followrequests);
		model.addObject("showSelected", "false");

		model.setViewName("followrequests");

		// same as showUser, this time used as parameter for actions
		String usernameSelected = (String) request.getSession().getAttribute(
				"usernameSelected");

		// parameter value and actions - start
		String follow = request.getParameter("follow");
		String unfollow = request.getParameter("unfollow");
		String requestFollow = request.getParameter("request");
		String requested = request.getParameter("requested");
		String requestapprove = request.getParameter("requestapprove");
		String requestdeny = request.getParameter("requestdeny");
		// parameter value and actions - end

		// store user stat
		String[] userStat = new String[8];

		// [0:private] [1:public]
		int privacy = 1;
		// used as action and label for button
		int followStat = 0;

		// user selected at the follower list
		String showUser = request.getParameter("showUser");

		// show selected user from search
		if (showUser != null && showUser.equalsIgnoreCase("") == false) {
			userStat = userService.getUserStat(showUser);
			model.addObject("usernameSelected", showUser);

			// check if logged in user is already following (searched user)
			// [0:not following] [1:following] [2:requested] [4:error]
			followStat = followListService.getFollowStat(loggedUser, showUser);

			privacy = userService.getPrivacy(showUser);

			if (followStat == 0) { // not yet followed
				if (privacy == 0) // 0=private
					model.addObject("followStat", "request");
				else
					model.addObject("followStat", "follow");
			} else if (followStat == 1) { // already following
				model.addObject("followStat", "unfollow");
				privacy = 1; // just so it will display stats, set public
			} else if (followStat == 2) { // already requested
				model.addObject("followStat", "requested");
			}

			model.addObject("showSelected", "true");
		} else if ((follow != null) && (follow.equalsIgnoreCase("") == false)) {
			// follow button clicked

			// check if you have 100 followers or you are to follow user with
			// 100
			boolean limitReached = false;
			limitReached = followListService.isFollowingLimitMaxed(loggedUser);
			if (limitReached == true)
				model.addObject("errorMessage",
						"You're already following 100 people.");
			else {
				limitReached = followListService.isFollowersLimitMaxed(follow);
				if (limitReached == true)
					model.addObject("errorMessage", follow
							+ " already have 100 followers.");
			}

			userStat = userService.getUserStat(follow);
			model.addObject("usernameSelected", follow);

			// set to follow in db
			if (limitReached == false) {
				int followUserStatus = followListService.followUser(loggedUser,
						follow);

				if (followUserStatus == 0) {
					// TODO show error message on screen
					// db error is printed on the system out
				}
				model.addObject("followStat", "unfollow");
			}
			model.addObject("showSelected", "true");
		} else if ((unfollow != null)
				&& (unfollow.equalsIgnoreCase("") == false)) { // unfollow
																// button
																// clicked
			userStat = userService.getUserStat(unfollow);
			model.addObject("usernameSelected", unfollow);

			// set to unfollow in db
			int unfollowUserStatus = followListService.unfollowUser(loggedUser,
					unfollow);

			if (unfollowUserStatus == 0) {
				// TODO: show message on screen
				// db error already logged on system.out
			}

			// check if private
			privacy = userService.getPrivacy(unfollow);
			// 0=private
			if (privacy == 0) {
				model.addObject("followStat", "request");
			} else { // 1=public
				model.addObject("followStat", "follow");
			}
			model.addObject("showSelected", "true");
		} else if ((requestFollow != null)
				&& (requestFollow.equalsIgnoreCase("") == false)) {
			// request button clicked

			// check if you have 100 followers or you are to follow user with
			// 100
			boolean limitReached = false;
			limitReached = followListService.isFollowingLimitMaxed(loggedUser);
			if (limitReached == true)
				model.addObject("errorMessage",
						"You're already following 100 people.");
			else {
				limitReached = followListService.isFollowersLimitMaxed(follow);
				if (limitReached == true)
					model.addObject("errorMessage", follow
							+ " already have 100 followers.");
			}

			userStat = userService.getUserStat(requestFollow);
			model.addObject("usernameSelected", requestFollow);

			privacy = userService.getPrivacy(requestFollow);

			// function to request follow
			if (limitReached == false) {
				int requestStatus = followListService.requestFollowUser(
						loggedUser, requestFollow);

				if (requestStatus == 0) {
					// TODO: show message on screen
					// db error already logged on system.out
				}

				model.addObject("followStat", "requested");
			}
			model.addObject("showSelected", "true");
		} else if ((requested != null)
				&& (requested.equalsIgnoreCase("") == false)) {
			// requested button clicked

			userStat = userService.getUserStat(requested);
			model.addObject("usernameSelected", requested);

			privacy = userService.getPrivacy(requested);

			// function to cancel request to follow
			int cancelRequest = followListService.cancelRequest(loggedUser,
					requested);

			if (cancelRequest == 0) {
				// TODO: show message on screen
				// db error already logged on system.out
			}

			model.addObject("followStat", "request");
			model.addObject("showSelected", "true");
		} else if ((requestapprove != null)
				&& (requestapprove.equalsIgnoreCase("") == false)) {
			// request approve clicked

			// change db values, remove 'R'
			int approveStat = followListService.approveRequest('Y', loggedUser,
					requestapprove);
			// show requests list again
			model.addObject("showSelected", "false");
			model.setViewName("redirect:/followrequests");
		} else if ((requestdeny != null)
				&& (requestdeny.equalsIgnoreCase("") == false)) {
			// request deny clicked

			// change db values, remove 'R'
			int approveStat = followListService.approveRequest('N', loggedUser,
					requestdeny);
			// show requests list again
			model.addObject("showSelected", "false");
			model.setViewName("redirect:/followrequests");
		}

		if (privacy == 0) {
			model.addObject("besttimerace", "private");
			model.addObject("bestlaprace", "private");
			model.addObject("besttimesolo", "private");
			model.addObject("bestlapsolo", "private");
			model.addObject("wins", "private");
			model.addObject("losses", "private");
			model.addObject("pct", "private");
			model.addObject("careerpoints", "private");
		} else if ((showUser != null) || (follow != null) || (unfollow != null)
				|| (requestFollow != null) || (requested != null)) {
			model.addObject("besttimerace", util.check999(userStat[0]));
			model.addObject("bestlaprace", util.check999(userStat[1]));
			model.addObject("besttimesolo", util.check999(userStat[2]));
			model.addObject("bestlapsolo", util.check999(userStat[3]));
			model.addObject("wins", userStat[4]);
			model.addObject("losses", userStat[5]);
			model.addObject("pct", userStat[6]);
			model.addObject("careerpoints", userStat[7]);
		}

		return model;
	}

	@RequestMapping(value = "/history")
	public ModelAndView history(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String loggedUser = (String) request.getSession().getAttribute(
				"username");

		List<String[]> raceHistory = raceLogService
				.getUserRaceHistory(loggedUser);

		model.addObject("racehistory", raceHistory);
		model.setViewName("history");

		return model;
	}

	@RequestMapping(value = "/config")
	public ModelAndView config(HttpServletRequest request) throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		ModelAndView model = new ModelAndView("config");
		return model;
	}

	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public ModelAndView config(ModelAndView model, HttpServletRequest request)
			throws IOException {
		// public ModelAndView config(ModelAndView model, HttpServletRequest
		// request,
		// @RequestParam("filePhoto") MultipartFile file) throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String loggedUser = (String) request.getSession().getAttribute(
				"username");

		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String passwdconfirm = request.getParameter("passwdconfirm");
		String privacy = request.getParameter("privacy");

		if (privacy == null)
			privacy = "N";
		else
			privacy = "Y";

		model.addObject("username", username);
		model.addObject("passwd", passwd);
		model.addObject("passwdconfirm", passwdconfirm);
		model.addObject("privacy", privacy);
		model.setViewName("config");

		boolean hasChangedUsernameOrPassword = false;
		if ((username != null) && (username.equalsIgnoreCase("") == false)) {
			hasChangedUsernameOrPassword = true;
			if (loggedUser.equalsIgnoreCase(username) == true) {
				model.addObject("configError",
						"*same username you currently have.");
				return model;
			}
			// check if username already exist
			if (userService.isUserExist(username) > 0) {
				model.addObject("configError", "*username not available.");
				return model;
			}
		}

		if ((passwd != null) && (passwd.equalsIgnoreCase("") == false)) {
			hasChangedUsernameOrPassword = true;
			if ((passwdconfirm == null)
					|| (passwdconfirm.equalsIgnoreCase("") == true)) {
				model.addObject("configError",
						"*please input confirm password.");
				return model;
			} else if (passwd.equalsIgnoreCase(passwdconfirm) == false) {
				model.addObject("configError", "*passwords must match.");
				return model;
			}
		}

		// save username and/or password
		// if (hasChangedUsernameOrPassword == true) {
		// int saveStatus = userService.updateUsernamePassword(loggedUser,
		// username, passwd);
		int saveStatus = userService.updateUserConfig(loggedUser, username,
				passwd, privacy);

		if (saveStatus == 1) {
			if (username != null && username.equalsIgnoreCase("") == false)
				request.getSession().setAttribute("username", username);
			model.addObject("saveSuccessMessage", "Changes successfully saved.");
		} else {
			model.addObject("saveSuccessMessage",
					"System error. Please contact support.");
		}
		// }

		// upload photo
		// if (!file.isEmpty()) {
		// try {
		//
		// byte[] photoBytes = file.getBytes();
		//
		// File serverFile = util.getPhotoFileAndDir(request);
		// BufferedOutputStream stream = new BufferedOutputStream(
		// new FileOutputStream(serverFile));
		// stream.write(photoBytes);
		// stream.close();
		//
		// // save to db
		// photoService.saveOrUpdate((String) request.getSession()
		// .getAttribute("username"), photoBytes, serverFile);
		//
		// // logger.info("Server File Location="
		// // + serverFile.getAbsolutePath());
		//
		// // return "You successfully uploaded file=" + name;
		// } catch (Exception e) {
		// e.getMessage();
		// }
		// } else {
		// // return "You failed to upload " + name
		// // + " because the file was empty.";
		// }

		model.setViewName("config");

		return model;
	}

	@RequestMapping(value = "/configadmin")
	public ModelAndView configadmin(HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		ModelAndView model = new ModelAndView("adminconfig");

		model.addObject("adminname", "");
		model.addObject("passwd", "");
		model.addObject("passwdconfirm", "");
		model.addObject("passcode", "");

		return model;
	}

	@RequestMapping(value = "/configadmin", method = RequestMethod.POST)
	public ModelAndView configadmin(ModelAndView model,
			HttpServletRequest request) throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		String loggedAdmin = (String) request.getSession().getAttribute(
				"adminname");

		String adminname = request.getParameter("adminname");
		String passwd = request.getParameter("passwd");
		String passwdconfirm = request.getParameter("passwdconfirm");
		String passcode = request.getParameter("passcode");

		model.addObject("adminname", adminname);
		model.addObject("passwd", passwd);
		model.addObject("passwdconfirm", passwdconfirm);
		model.addObject("passcode", passcode);
		model.setViewName("adminconfig");

		// will be used for update
		Admin adminForUpdate = new Admin();

		boolean hasChange = false;
		if ((adminname != null) && (adminname.equalsIgnoreCase("") == false)) {
			hasChange = true;
			if (loggedAdmin.equalsIgnoreCase(adminname) == true) {
				model.addObject("configError",
						"*same adminname you currently have.");
				return model;
			}
			// check if adminname already exist
			if (adminService.isAdminExist(adminname) > 0) {
				model.addObject("configError", "*adminname not available.");
				return model;
			}
			adminForUpdate.setAdminname(adminname);
		}

		if ((passwd != null) && (passwd.equalsIgnoreCase("") == false)) {
			hasChange = true;
			if ((passwdconfirm == null)
					|| (passwdconfirm.equalsIgnoreCase("") == true)) {
				model.addObject("configError",
						"*please input confirm password.");
				return model;
			} else if (passwd.equalsIgnoreCase(passwdconfirm) == false) {
				model.addObject("configError", "*passwords must match.");
				return model;
			}
			adminForUpdate.setPassword(passwd);
		}

		if (passcode != null && passcode.equalsIgnoreCase("") == false) {
			hasChange = true;
			int passcodeInt = util.isNumber(passcode);
			if (passcodeInt == 0) {
				model.addObject("configError", "*passcode must be numeric");
				return model;
			}
			adminForUpdate.setPasscode(passcodeInt);
		}

		// save adminname and/or password
		if (hasChange == true) {
			int saveStatus = adminService.updateAdminConfig(adminForUpdate,
					loggedAdmin);

			if (saveStatus == 1) {
				model.addObject("saveSuccessMessage",
						"Changes successfully saved.");
				if (adminname != null
						&& adminname.equalsIgnoreCase("") == false)
					request.getSession().setAttribute("adminname", adminname);
			} else {
				model.addObject("saveSuccessMessage",
						"System error. Please contact support.");
			}
		}

		model.setViewName("adminconfig");

		return model;
	}

	@RequestMapping(value = "/configsuperuser")
	public ModelAndView configsuperuser(HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		ModelAndView model = new ModelAndView("superuserconfig");

		model.addObject("superusername", "");
		model.addObject("passwd", "");
		model.addObject("passwdconfirm", "");
		model.addObject("passcode", "");

		return model;
	}

	@RequestMapping(value = "/configsuperuser", method = RequestMethod.POST)
	public ModelAndView configsuperuser(ModelAndView model,
			HttpServletRequest request) throws IOException {

		if (!util.checkIfAlreadyLoggedInAdmin(request))
			return new ModelAndView("redirect:/admin");

		String loggedAdmin = (String) request.getSession().getAttribute(
				"adminname");

		String superusername = request.getParameter("superusername");
		String passwd = request.getParameter("passwd");
		String passwdconfirm = request.getParameter("passwdconfirm");
		String passcode = request.getParameter("passcode");

		model.addObject("superusername", superusername);
		model.addObject("passwd", passwd);
		model.addObject("passwdconfirm", passwdconfirm);
		model.addObject("passcode", passcode);
		model.setViewName("superuserconfig");

		// will be used for update
		Admin adminForUpdate = new Admin();

		boolean hasChange = false;
		if ((superusername != null)
				&& (superusername.equalsIgnoreCase("") == false)) {
			hasChange = true;
			if (loggedAdmin.equalsIgnoreCase(superusername) == true) {
				model.addObject("configError",
						"*same superusername you currently have.");
				return model;
			}
			// check if superusername already exist
			if (adminService.isAdminExist(superusername) > 0) {
				model.addObject("configError", "*superusername not available.");
				return model;
			}
			adminForUpdate.setAdminname(superusername);
		}

		if ((passwd != null) && (passwd.equalsIgnoreCase("") == false)) {
			hasChange = true;
			if ((passwdconfirm == null)
					|| (passwdconfirm.equalsIgnoreCase("") == true)) {
				model.addObject("configError",
						"*please input confirm password.");
				return model;
			} else if (passwd.equalsIgnoreCase(passwdconfirm) == false) {
				model.addObject("configError", "*passwords must match.");
				return model;
			}
			adminForUpdate.setPassword(passwd);
		}

		if (passcode != null && passcode.equalsIgnoreCase("") == false) {
			hasChange = true;
			int passcodeInt = util.isNumber(passcode);
			if (passcodeInt == 0) {
				model.addObject("configError", "*passcode must be numeric");
				return model;
			}
			adminForUpdate.setPasscode(passcodeInt);
		}

		// save superusername and/or password
		if (hasChange == true) {
			int saveStatus = adminService.updateAdminConfig(adminForUpdate,
					loggedAdmin);

			if (saveStatus == 1) {
				model.addObject("saveSuccessMessage",
						"Changes successfully saved.");
				if (superusername != null
						&& superusername.equalsIgnoreCase("") == false)
					request.getSession().setAttribute("superusername",
							superusername);
			} else {
				model.addObject("saveSuccessMessage",
						"System error. Please contact support.");
			}
		}

		model.setViewName("superuserconfig");

		return model;
	}

	@RequestMapping(value = "/ranks")
	public ModelAndView ranks(ModelAndView model, HttpServletRequest request)
			throws IOException {

		if (!util.checkIfAlreadyLoggedIn(request))
			return new ModelAndView("redirect:/login");

		String loggedUser = (String) request.getSession().getAttribute(
				"username");

		String[] aRankStat = userService.getWorldStat(loggedUser);
		model.addObject("arankbesttimerace", aRankStat[0]);
		model.addObject("arankbestlaprace", aRankStat[1]);
		model.addObject("arankbesttimesolo", aRankStat[2]);
		model.addObject("arankbestlapsolo", aRankStat[3]);
		model.addObject("arankbestpct", aRankStat[4]);
		model.addObject("arankbestcareerpts", aRankStat[5]);

		String[] fRankStat = userService.getFriendsStat(loggedUser);
		model.addObject("frankbesttimerace", fRankStat[0]);
		model.addObject("frankbestlaprace", fRankStat[1]);
		model.addObject("frankbesttimesolo", fRankStat[2]);
		model.addObject("frankbestlapsolo", fRankStat[3]);
		model.addObject("frankbestpct", fRankStat[4]);
		model.addObject("frankbestcareerpts", fRankStat[5]);

		model.setViewName("ranks");

		return model;
	}

	@RequestMapping(value = "/")
	public ModelAndView listUser(ModelAndView model) throws IOException {
		List<User> listUser = userService.list();
		model.addObject("listUser", listUser);
		model.setViewName("home");

		return model;
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public ModelAndView showLoginPageByLogout(ModelAndView model,
			HttpServletRequest request) {
		User user = new User();
		model.addObject("user", user);
		model.setViewName("login");

		request.getSession().removeAttribute("username");

		return model;
	}

	@RequestMapping(value = { "/logoutadmin" }, method = RequestMethod.GET)
	public ModelAndView showLoginPageByLogoutAdmin(ModelAndView model,
			HttpServletRequest request) {
		User user = new User();
		model.addObject("user", user);
		model.setViewName("admin");

		request.getSession().removeAttribute("adminname");

		return model;
	}

	@RequestMapping(value = "/forgotpassword")
	public ModelAndView forgotpassword(HttpServletRequest request)
			throws IOException {

		ModelAndView model = new ModelAndView("forgotpassword");

		return model;
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ModelAndView forgotpassword(ModelAndView model,
			HttpServletRequest request) throws IOException {

		model.setViewName("forgotpassword");

		String username = request.getParameter("username");

		if (username == null || username.equalsIgnoreCase("") == true) {
			model.addObject("forgotpasswordError", "please input username");
			return model;
		}

		int userExist = userService.isUserExist(username);
		if (userExist == 0) {
			model.addObject("forgotpasswordError", "user not found");
			model.addObject("username", username);
			return model;
		}

		String emailOfUser = userService.getEmailOfUser(username);
		if (emailOfUser == null || emailOfUser.equalsIgnoreCase("") == true) {
			model.addObject("forgotpasswordError", "email not found");
			model.addObject("username", username);
		}

		// generate random password
		String passwordGenerated = RandomStringUtils.randomAlphanumeric(8);

		System.out.println(passwordGenerated);

		// send newly generated password to the email
		String gmailpassword = "J!mmss43#go";
		String gmailuser = "gio.paraiso";
		String title = "JoyRace Password Reset";
		String message = "Here is your new password" + passwordGenerated;

		emailOfUser = "gio_paraiso@yahoo.com";
		try {
			util.sendEmail(gmailuser, gmailpassword, emailOfUser, "", title,
					message);
		} catch (MessagingException e) {
			System.out.println(e);
			e.printStackTrace();
			model.addObject("forgotpasswordError",
					"email not sent. pls try again.");
			model.addObject("username", username);
			return model;
		}

		// save new password on db
		boolean successSave = userService.savePasswordForUser(username,
				passwordGenerated);
		if (successSave == false) {
			model.addObject("forgotpasswordError",
					"password not saved on db. pls try again.");
			model.addObject("username", username);
			return model;
		}

		return model;
	}

	@RequestMapping(value = "/adminforgotpassword")
	public ModelAndView adminforgotpassword(HttpServletRequest request)
			throws IOException {

		ModelAndView model = new ModelAndView("adminforgotpassword");

		return model;
	}

	@RequestMapping(value = "/adminforgotpassword", method = RequestMethod.POST)
	public ModelAndView adminforgotpassword(ModelAndView model,
			HttpServletRequest request) throws IOException {

		model.setViewName("adminforgotpassword");

		String adminname = request.getParameter("adminname");

		if (adminname == null || adminname.equalsIgnoreCase("") == true) {
			model.addObject("forgotpasswordError", "please input adminname");
			return model;
		}

		int adminExist = adminService.isAdminExist(adminname);
		if (adminExist == 0) {
			model.addObject("forgotpasswordError", "admin not found");
			model.addObject("adminname", adminname);
			return model;
		}

		String emailOfAdmin = adminService.getEmailOfAdmin(adminname);
		if (emailOfAdmin == null || emailOfAdmin.equalsIgnoreCase("") == true) {
			model.addObject("forgotpasswordError", "email not found");
			model.addObject("adminname", adminname);
		}

		// generate random password
		String passwordGenerated = RandomStringUtils.randomAlphanumeric(8);

		System.out.println(passwordGenerated);

		// send newly generated password to the email
		String gmailpassword = "J!mmss43#go";
		String gmailadmin = "gio.paraiso";
		String title = "JoyRace Password Reset";
		String message = "Here is your new password" + passwordGenerated;

		emailOfAdmin = "gio_paraiso@yahoo.com";
		try {
			util.sendEmail(gmailadmin, gmailpassword, emailOfAdmin, "", title,
					message);
		} catch (MessagingException e) {
			System.out.println(e);
			e.printStackTrace();
			model.addObject("forgotpasswordError",
					"email not sent. pls try again.");
			model.addObject("adminname", adminname);
			return model;
		}

		// save new password on db
		boolean successSave = adminService.savePasswordForAdmin(adminname,
				passwordGenerated);
		if (successSave == false) {
			model.addObject("forgotpasswordError",
					"password not saved on db. pls try again.");
			model.addObject("adminname", adminname);
			return model;
		}

		return model;
	}

}
