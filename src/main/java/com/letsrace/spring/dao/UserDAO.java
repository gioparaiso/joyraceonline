package com.letsrace.spring.dao;

import java.util.List;

import com.letsrace.spring.model.User;

/**
 * Defines DAO operations for the user model.
 * 
 */

public interface UserDAO {

	public void saveOrUpdate(User user);

	public void delete(int userId);

	public User get(int userId);

	public List<User> list();

	public User findUserByUsername(String username);

	public int saveUser(User user) throws Exception;

	public void deleteUserById(String id);

	public int isUserExist(String username);

	public String getPassword(String username);

	public User getUserDetails(String username);

	public List<Object[]> getUserRaceStat(String username);

	public List<Object[]> getUserSoloStat(String username);

	public List<Object[]> getHomeStat();

	public void update(User user);

	public List<String> findUserByNameOrEmail(String usernameOrEmail,
			String loggedUser);

	public int getPrivacy(String followUser) throws Exception;

	public int updateUsernamePassword(String loggedUser, String username,
			String passwd) throws Exception;

	public List<Object[]> getWorldStat(String loggedUser) throws Exception;

	public List<Object[]> getBesttimeFriends(String loggedUser, String field,
			String order) throws Exception;

	public int getUserId(String username) throws Exception;

	public String getEmailOfUser(String username) throws Exception;

	public int savePasswordForUser(String username, String passwordGenerated)
			throws Exception;

	public int updateUserConfig(String loggedUser, String username,
			String passwd, String privacy) throws Exception;

}
