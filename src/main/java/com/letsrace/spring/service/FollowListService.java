package com.letsrace.spring.service;

import java.util.List;

public abstract interface FollowListService {

	int followUser(String userIdLogged, String followUser);

	int getFollowStat(String userIdLogged, String followUser);

	int unfollowUser(String userIdLogged, String unfollow);

	int requestFollowUser(String userIdLogged, String requestFollow);

	int cancelRequest(String userIdLogged, String requestFollow);

	Integer[] getFollowStats(String loggedUser);

	List<String> getFollowersNames(String loggedUser);

	List<String> getFollowingNames(String loggedUser);

	List<String> getFollowrequestsNames(String loggedUser);

	int approveRequest(char c, String loggedUser, String requestapprove);

	int removeFollower(String loggedUser, String removeUser);

	boolean isFollowingLimitMaxed(String loggedUser);

	boolean isFollowersLimitMaxed(String follow);

}
