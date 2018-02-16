package com.letsrace.spring.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsrace.spring.dao.UserDAO;
import com.letsrace.spring.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	ServiceUtil util = new ServiceUtil();

	public void updateUser(User user) {
	}

	public User findUserByUsername(String name) {
		return this.dao.findUserByUsername(name);
	}

	@Override
	public boolean saveUser(User user) {
		try {
			user.setPassword(util.generateStorngPasswordHash(user.getPassword()));
			int retVal = this.dao.saveUser(user);
			if (retVal > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e);
			// e.printStackTrace();
			return false;
		}
	}

	@Override
	public void deleteUserById(String id) {
		this.dao.deleteUserById(id);
	}

	@Override
	public List<User> list() {
		return this.dao.list();
	}

	@Override
	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int userId) {
		this.dao.delete(userId);
	}

	@Override
	public User get(int userId) {
		return this.dao.get(userId);
	}

	@Override
	public int isUserExist(String username) {
		return this.dao.isUserExist(username);
	}

	@Override
	public boolean isCorrectPassword(String username, String password) {
		String dbpassword = this.dao.getPassword(username);

		try {
			System.out.println("password (not encrytpted) :" + password);
			password = util.generateStorngPasswordHash(password);
			System.out.println("password :" + password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (dbpassword == null)
			return false;
		if (password.equalsIgnoreCase(dbpassword) == true)
			return true;
		else
			return false;
	}

	@Override
	public User getUserDetails(String username) {
		return this.dao.getUserDetails(username);
	}

	@Override
	public String[] getUserStat(String username) {

		String[] retVal = new String[8];

		List<Object[]> listRace = this.dao.getUserRaceStat(username);
		Object[] resultRace = listRace.get(0);

		// sql query
		// [0] sum(if(result = '1', 1, 0)) as firsts,
		// [1] sum(if(result = '2', 1, 0)) as seconds,
		// [2] sum(if(result = '3', 1, 0)) as thirds,
		// [3] min(totaltime) as bestrace,
		// [4] min(lap1),
		// [5] min(lap2),
		// [6] min(lap3),
		// [7] min(lap4),
		// [8] min(lap5)

		// best time (race)
		retVal[0] = String.valueOf(util.checkBestTime(resultRace[3]));

		// best lap (race)
		retVal[1] = util.getTheLeast(resultRace[4], resultRace[5],
				resultRace[6], resultRace[7], resultRace[8]);

		List<Object[]> listSolo = this.dao.getUserSoloStat(username);
		Object[] resultSolo = listSolo.get(0);

		// [0] sum(if(result = 's', 1, 0)) as solos,
		// [1] min(totaltime) as bestsolo,
		// [2] min(lap1),
		// [3] min(lap2),
		// [4] min(lap3),
		// [5] min(lap4),
		// [6] min(lap5)

		// best time (solo)
		retVal[2] = String.valueOf(util.checkBestTime(resultSolo[1]));

		// best lap (solo)
		retVal[3] = util.getTheLeast(resultSolo[2], resultSolo[3],
				resultSolo[4], resultSolo[5], resultSolo[6]);

		// wins
		int firsts = util.checkNullNum(resultRace[0]);
		retVal[4] = String.valueOf(firsts);

		// losses
		int seconds = util.checkNullNum(resultRace[1]);
		int thirds = util.checkNullNum(resultRace[2]);
		int losses = seconds + thirds;
		retVal[5] = String.valueOf(losses);

		// pct
		Float pct = 0.00f;
		pct = util.getPct(firsts, losses);
		retVal[6] = String.valueOf(pct);

		// career points
		firsts = firsts * 25;
		seconds = seconds * 15;
		thirds = thirds * 10;
		int solos = util.checkNullNum(resultSolo[0]);
		solos = solos * 1;
		retVal[7] = String.valueOf(firsts + seconds + thirds + solos);

		// check and update user values
		User user = this.dao.getUserDetails(username);

		Float besttimerace = Float.valueOf(retVal[0]);
		Float bestlaprace = Float.valueOf(retVal[1]);
		Float besttimesolo = Float.valueOf(retVal[2]);
		Float bestlapsolo = Float.valueOf(retVal[3]);
		Integer careerptsUser = Integer.valueOf(retVal[7]);

		if ((user.getBesttimerace() == null)
				|| (user.getBestlaprace() == null)
				|| (user.getBesttimesolo() == null)
				|| (user.getBestlapsolo() == null)
				|| (user.getPct() == null)
				|| (user.getCareerpts() == null)
				|| (user.getBesttimerace().floatValue() != besttimerace
						.floatValue())
				|| (user.getBestlaprace().floatValue() != bestlaprace
						.floatValue())
				|| (user.getBesttimesolo().floatValue() != besttimesolo
						.floatValue())
				|| (user.getBestlapsolo().floatValue() != bestlapsolo
						.floatValue())
				|| (user.getPct().floatValue() != pct.floatValue())
				|| (user.getCareerpts().intValue() != careerptsUser.intValue())) {

			// best times can be zero, so assign 999
			if (Float.parseFloat(retVal[0]) == 0) {
				retVal[0] = "999.99";
				besttimerace = new Float(999.99);
			}
			if (Float.parseFloat(retVal[1]) == 0) {
				retVal[1] = "999.99";
				bestlaprace = new Float(999.99);
			}

			if (Float.parseFloat(retVal[2]) == 0) {
				retVal[2] = "999.99";
				besttimesolo = new Float(999.99);
			}

			if (Float.parseFloat(retVal[3]) == 0) {
				retVal[3] = "999.99";
				bestlapsolo = new Float(999.99);
			}

			user.setBesttimerace(besttimerace);
			user.setBestlaprace(bestlaprace);
			user.setBesttimesolo(besttimesolo);
			user.setBestlapsolo(bestlapsolo);
			user.setPct(pct);
			user.setCareerpts(careerptsUser);

			this.dao.update(user);
		}

		return retVal;
	}

	@Override
	public String[] getHomeStat() {
		List<Object[]> list = this.dao.getHomeStat();

		Object[] result = list.get(0);
		String[] retVal = new String[6];

		retVal[0] = String.valueOf(util.checkNullFloat(result[0]));
		retVal[1] = String.valueOf(util.checkNullFloat(result[1]));
		retVal[2] = String.valueOf(util.checkNullFloat(result[2]));
		retVal[3] = String.valueOf(util.checkNullFloat(result[3]));
		retVal[4] = String.valueOf(util.checkNullFloat(result[4]));
		retVal[5] = String.valueOf(util.checkNullNum(result[5]));

		return retVal;
	}

	@Override
	public List<String> findUserByNameOrEmail(String usernameOrEmail,
			String loggedUser) {
		List<String> users = this.dao.findUserByNameOrEmail(usernameOrEmail,
				loggedUser);
		return users;
	}

	/**
	 * returns [0:private] [1:public] [2:error]
	 */
	@Override
	public int getPrivacy(String followUser) {
		try {
			return this.dao.getPrivacy(followUser);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	/**
	 * returns [0:not updated] [1:update success] [2:error]
	 */
	@Override
	public int updateUsernamePassword(String loggedUser, String username,
			String passwd) {
		try {
			if (passwd != null && (passwd.equalsIgnoreCase("") == false))
				passwd = util.generateStorngPasswordHash(passwd);
			return this.dao
					.updateUsernamePassword(loggedUser, username, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	@Override
	public String[] getWorldStat(String loggedUser) {
		try {
			List<Object[]> result = this.dao.getWorldStat(loggedUser);

			String[] retVal = new String[6];
			retVal = util.arrangeARank(result);
			retVal = util.addRankSuffix(retVal);
			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * returns ranks of logged user within followings and followiers
	 */
	@Override
	public String[] getFriendsStat(String loggedUser) {
		try {
			List<Object[]> besttimerace = this.dao.getBesttimeFriends(
					loggedUser, "besttimerace", "ASC");
			List<Object[]> bestlaprace = this.dao.getBesttimeFriends(
					loggedUser, "bestlaprace", "ASC");
			List<Object[]> besttimesolo = this.dao.getBesttimeFriends(
					loggedUser, "besttimesolo", "ASC");
			List<Object[]> bestlapsolo = this.dao.getBesttimeFriends(
					loggedUser, "bestlapsolo", "ASC");
			List<Object[]> bestpct = this.dao.getBesttimeFriends(loggedUser,
					"pct", "DESC");
			List<Object[]> bestcareerpts = this.dao.getBesttimeFriends(
					loggedUser, "careerpts", "DESC");

			String[] retVal = new String[6];
			retVal[0] = util.getRankWithFriends(besttimerace, loggedUser);
			retVal[1] = util.getRankWithFriends(bestlaprace, loggedUser);
			retVal[2] = util.getRankWithFriends(besttimesolo, loggedUser);
			retVal[3] = util.getRankWithFriends(bestlapsolo, loggedUser);
			retVal[4] = util.getRankWithFriends(bestpct, loggedUser);
			retVal[5] = util.getRankWithFriends(bestcareerpts, loggedUser);
			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			String[] retVal = new String[6];
			retVal[0] = "error";
			retVal[1] = "error";
			retVal[2] = "error";
			retVal[3] = "error";
			retVal[4] = "error";
			retVal[5] = "error";
			return retVal;
		}
	}

	@Override
	public String getEmailOfUser(String username) {
		String email = "";
		try {
			email = dao.getEmailOfUser(username);
			if (email == null)
				return "";
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return email;
	}

	@Override
	public boolean savePasswordForUser(String username, String passwordGenerated) {
		int result = 0;
		try {
			passwordGenerated = util
					.generateStorngPasswordHash(passwordGenerated);
			result = dao.savePasswordForUser(username, passwordGenerated);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
		if (result == 0)
			return false;
		else
			return true;
	}

	/**
	 * returns [0:not updated] [1:update success] [2:error]
	 */
	@Override
	public int updateUserConfig(String loggedUser, String username,
			String passwd, String privacy) {
		try {
			if (passwd != null && (passwd.equalsIgnoreCase("") == false))
				passwd = util.generateStorngPasswordHash(passwd);
			return this.dao.updateUserConfig(loggedUser, username, passwd,
					privacy);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

}
