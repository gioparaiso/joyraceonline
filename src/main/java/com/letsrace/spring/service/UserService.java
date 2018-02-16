package com.letsrace.spring.service;

import java.util.List;

import com.letsrace.spring.model.User;

public abstract interface UserService {

	public abstract boolean saveUser(User paramUser);

	public abstract void updateUser(User paramUser);

	public abstract void deleteUserById(String paramString);

	public abstract User findUserByUsername(String paramString);

	public abstract List<User> list();

	public abstract void saveOrUpdate(User user);

	public abstract void delete(int userId);

	public abstract User get(int userId);

	public abstract int isUserExist(String username);

	public abstract boolean isCorrectPassword(String username, String password);

	public abstract User getUserDetails(String username);

	public abstract String[] getUserStat(String username);

	public abstract String[] getHomeStat();

	public abstract List<String> findUserByNameOrEmail(String usernameOrEmail,
			String loggedUser);

	public abstract int getPrivacy(String followUser);

	public abstract int updateUsernamePassword(String loggedUser,
			String username, String passwd);

	public abstract String[] getWorldStat(String username);

	public abstract String[] getFriendsStat(String loggedUser);

	public abstract String getEmailOfUser(String username);

	public abstract boolean savePasswordForUser(String username,
			String passwordGenerated);

	public abstract int updateUserConfig(String loggedUser, String username,
			String passwd, String privacy);

}
