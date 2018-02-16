package com.letsrace.spring.dao;

import java.math.BigInteger;
import java.util.List;

import com.letsrace.spring.model.Followlist;

/**
 * Defines DAO operations for the followlist model.
 * 
 */
public interface FollowListDAO {

	public void saveOrUpdate(Followlist followlist);

	public void delete(int followlistId);

	public Followlist get(int followlistId);

	public List<Followlist> list();

	public int followUser(String userIdLogged, String followUser)
			throws Exception;

	public int getFollowStat(String userIdLogged, String followUser)
			throws Exception;

	public int unfollowUser(String userIdLogged, String unfollowUser)
			throws Exception;

	public int requestFollowUser(String userIdLogged, String requestFollow)
			throws Exception;

	public int cancelRequest(String userIdLogged, String requestFollow)
			throws Exception;

	public int getFollowersCount(String loggedUser) throws Exception;

	public int getFollowingCount(String loggedUser) throws Exception;

	public int getRequestsCount(String loggedUser) throws Exception;

	public List<String> getFollowersNames(String loggedUser) throws Exception;

	public List<String> getFollowingNames(String loggedUser) throws Exception;

	public List<String> getFollowrequestsNames(String loggedUser)
			throws Exception;

	public int followApprove(String loggedUser, String requestapprove)
			throws Exception;

	public int followDeny(String loggedUser, String requestapprove)
			throws Exception;

	public int removeFollower(String loggedUser, String removeUser)
			throws Exception;

	public BigInteger hasHundredFollowing(String loggedUser) throws Exception;

	public BigInteger hasHundredFollowers(String follow) throws Exception;

}