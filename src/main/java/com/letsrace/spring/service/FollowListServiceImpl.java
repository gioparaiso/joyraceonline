package com.letsrace.spring.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsrace.spring.dao.FollowListDAO;

@Service("followListService")
@Transactional
public class FollowListServiceImpl implements FollowListService {

	@Autowired
	private FollowListDAO dao;

	/**
	 * returns [0:not inserted] [1:successful]
	 */
	@Override
	public int followUser(String userIdLogged, String followUser) {

		try {
			int updateStat = this.dao.followUser(userIdLogged, followUser);
			if (updateStat > 0)
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * check is userIdLogged is already following followUser
	 * 
	 * returns [0:not following] [1:following] [2:requested] [4:error]
	 */
	@Override
	public int getFollowStat(String userIdLogged, String followUser) {
		try {
			return this.dao.getFollowStat(userIdLogged, followUser);
		} catch (Exception e) {
			e.printStackTrace();
			return 4;
		}
	}

	/**
	 * returns [0:not updated] [1:successful]
	 */
	@Override
	public int unfollowUser(String userIdLogged, String unfollow) {
		try {
			int updateStat = this.dao.unfollowUser(userIdLogged, unfollow);
			if (updateStat > 0)
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * returns [0:not updated] [1:successful]
	 */
	@Override
	public int requestFollowUser(String userIdLogged, String requestFollow) {
		try {
			int updateStat = this.dao.requestFollowUser(userIdLogged,
					requestFollow);
			if (updateStat > 0)
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * returns [0:not updated] [1:successful]
	 */
	@Override
	public int cancelRequest(String userIdLogged, String requestFollow) {
		try {
			int updateStat = this.dao
					.cancelRequest(userIdLogged, requestFollow);
			if (updateStat > 0)
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Integer[] getFollowStats(String loggedUser) {
		try {
			int followersCount = this.dao.getFollowersCount(loggedUser);
			int followingCount = this.dao.getFollowingCount(loggedUser);
			int requestsCount = this.dao.getRequestsCount(loggedUser);

			Integer[] retVal = new Integer[3];
			retVal[0] = followersCount;
			retVal[1] = followingCount;
			retVal[2] = requestsCount;

			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getFollowersNames(String loggedUser) {
		try {
			List<String> followers = this.dao.getFollowersNames(loggedUser);

			return followers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getFollowingNames(String loggedUser) {
		try {
			List<String> following = this.dao.getFollowingNames(loggedUser);

			return following;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getFollowrequestsNames(String loggedUser) {
		try {
			List<String> followrequests = this.dao
					.getFollowrequestsNames(loggedUser);

			return followrequests;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * returns [0:not updated] [1:successful]
	 */
	@Override
	public int approveRequest(char c, String loggedUser, String requestapprove) {
		try {
			int approveStat = 0;
			if (c == 'Y')
				approveStat = this.dao
						.followApprove(loggedUser, requestapprove);
			else
				approveStat = this.dao.followDeny(loggedUser, requestapprove);

			if (approveStat > 0)
				return 1;
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * returns [0:not updated] [1:successful]
	 */
	@Override
	public int removeFollower(String loggedUser, String removeUser) {
		try {
			return this.dao.removeFollower(loggedUser, removeUser);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * check if 100 limit of followings
	 */
	@Override
	public boolean isFollowingLimitMaxed(String loggedUser) {
		try {
			BigInteger following = this.dao.hasHundredFollowing(loggedUser);
			if (following.intValue() < 100)
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	/**
	 * check if 100 limit of followers
	 */

	@Override
	public boolean isFollowersLimitMaxed(String follow) {
		try {
			BigInteger followers = this.dao.hasHundredFollowers(follow);
			if (followers.intValue() < 100)
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

}
