package com.letsrace.spring.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class ServiceUtil {

	protected String checkNull(Object param) {
		if (param == null)
			return "";
		else
			return param.toString();
	}

	protected boolean checkNullOrZero(Object object) {
		if (object == null)
			return true;
		if (object.toString().equalsIgnoreCase("0"))
			return true;
		return false;
	}

	protected String getTheLeast(Object object1, Object object2,
			Object object3, Object object4, Object object5) {
		float lap1 = 0;
		float lap2 = 0;
		float lap3 = 0;
		float lap4 = 0;
		float lap5 = 0;
		if (checkNullOrZero(object1) == true)
			lap1 = 999.99f;
		else
			lap1 = Float.parseFloat(object1.toString());
		if (checkNullOrZero(object2) == true)
			lap2 = 999.99f;
		else
			lap2 = Float.parseFloat(object2.toString());
		if (checkNullOrZero(object3) == true)
			lap3 = 999.99f;
		else
			lap3 = Float.parseFloat(object3.toString());
		if (checkNullOrZero(object4) == true)
			lap4 = 999.99f;
		else
			lap4 = Float.parseFloat(object4.toString());
		if (checkNullOrZero(object5) == true)
			lap5 = 999.99f;
		else
			lap5 = Float.parseFloat(object5.toString());

		float theLeast = 999.99f;

		if (Float.compare(lap1, lap2) > 0)
			theLeast = lap2;
		else
			theLeast = lap1;
		if (Float.compare(theLeast, lap3) > 0)
			theLeast = lap3;
		if (Float.compare(theLeast, lap4) > 0)
			theLeast = lap4;
		if (Float.compare(theLeast, lap5) > 0)
			theLeast = lap5;

		if (theLeast == 999.99)
			return "999.99";

		return String.valueOf(theLeast);
	}

	protected int checkNullNum(Object object) {
		if (object == null)
			return 0;
		if (object.toString().equalsIgnoreCase(""))
			return 0;
		return Integer.parseInt(object.toString());
	}

	protected float checkNullFloat(Object object) {
		if (object == null)
			return 0.00f;
		if (object.toString().equalsIgnoreCase(""))
			return 0.00f;
		float retVal = 0.00f;
		retVal = Float.parseFloat(object.toString());

		return retVal;
	}

	protected String checkNullSetZero(String object) {
		if (object == null)
			return "0";
		if (object.toString().equalsIgnoreCase(""))
			return "0";
		return object;
	}

	/**
	 * computes for the PCT - percentage of wins
	 * 
	 * @param firsts
	 * @param losses
	 * @return round off to 3 decimal places
	 */

	protected float getPct(int firsts, int losses) {
		if ((firsts == 0) && (losses == 0))
			return 0.00f;
		if (firsts == 0)
			return 0.00f;
		Float retVal = 0.00f;
		retVal = (float) firsts / (firsts + losses);

		double shift = Math.floor(retVal * 1000.0) / 1000.0;
		retVal = new Float(shift);

		return retVal.floatValue();
	}

	protected String checkIfZero(String string) {
		if (string.equalsIgnoreCase("") == true)
			return "0";
		return string;
	}

	protected float checkBestTime(Object object) {
		if (object == null)
			return 999.99f;
		if (object.toString().equalsIgnoreCase(""))
			return 999.99f;
		float retVal = 0.00f;
		retVal = Float.parseFloat(object.toString());
		if (retVal == 0)
			return 999.99f;

		return retVal;
	}

	protected List<String[]> arrangeRaceHistory(List<Object[]> raceHistory) {

		if (raceHistory == null || raceHistory.size() == 0)
			return null;

		List<String[]> retVal = new ArrayList<String[]>();

		String[] record;
		Object[] result;

		for (int i = 0; i < raceHistory.size(); i++) {

			result = raceHistory.get(i);

			Date racedate = (Date) result[0];
			if (result[1] == null)
				result[1] = new BigDecimal(999);
			BigDecimal totaltime = (BigDecimal) result[1];
			Character raceresult = (Character) result[2];
			BigDecimal bestlap = getLeastLap((BigDecimal) result[3],
					(BigDecimal) result[4], (BigDecimal) result[5],
					(BigDecimal) result[6], (BigDecimal) result[7]);
			if (bestlap.compareTo(new BigDecimal(999)) == 0)
				bestlap = new BigDecimal(0);

			String pattern = "MM/dd/yy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);

			record = new String[4];
			// record[0] = racedate.toString();
			record[0] = format.format(racedate).toString();
			record[1] = getResultDisplay(raceresult.toString());
			record[2] = getTimeDisplay(bestlap.toString());
			record[3] = getTimeDisplay(totaltime.toString());

			retVal.add(record);
		}

		return retVal;
	}

	private String getTimeDisplay(String string) {
		if (string == null)
			return "none";
		if (string.equalsIgnoreCase("0"))
			return "none";
		if (string.equalsIgnoreCase("999"))
			return "none";
		if (string.equalsIgnoreCase("999.99"))
			return "none";
		// return string + "s";
		return string;
	}

	private String getResultDisplay(String string) {
		if (string == null)
			return "";
		if (string.equalsIgnoreCase("1") == true)
			return "1st";
		if (string.equalsIgnoreCase("2") == true)
			return "2nd";
		if (string.equalsIgnoreCase("3") == true)
			return "3rd";
		if (string.equalsIgnoreCase("s") == true)
			return "solo";
		return string;
	}

	private BigDecimal getLeastLap(BigDecimal param1, BigDecimal param2,
			BigDecimal param3, BigDecimal param4, BigDecimal param5) {

		BigDecimal retVal = new BigDecimal(999);

		if (param1 == null)
			param1 = new BigDecimal(999);
		if (param2 == null)
			param2 = new BigDecimal(999);
		if (param3 == null)
			param3 = new BigDecimal(999);
		if (param4 == null)
			param4 = new BigDecimal(999);
		if (param5 == null)
			param5 = new BigDecimal(999);

		if (param1.compareTo(retVal) < 0)
			retVal = param1;
		if (param2.compareTo(retVal) < 0)
			retVal = param2;
		if (param3.compareTo(retVal) < 0)
			retVal = param3;
		if (param4.compareTo(retVal) < 0)
			retVal = param4;
		if (param5.compareTo(retVal) < 0)
			retVal = param5;

		return retVal;
	}

	protected String[] arrangeARank(List<Object[]> result) throws Exception {
		String[] retVal = new String[6];

		if (result == null)
			return null;

		if (result.size() != 1)
			throw new Exception("Error in SQL for ranking");

		Object[] record = result.get(0);

		retVal[0] = ((BigInteger) record[0]).toString();
		retVal[1] = ((BigInteger) record[1]).toString();
		retVal[2] = ((BigInteger) record[2]).toString();
		retVal[3] = ((BigInteger) record[3]).toString();
		retVal[4] = ((BigInteger) record[4]).toString();
		retVal[5] = ((BigInteger) record[5]).toString();

		return retVal;
	}

	protected String[] addRankSuffix(String[] retVal) {
		String[] retString = new String[6];

		retString[0] = identifyRankSuffix(retVal[0]);
		retString[1] = identifyRankSuffix(retVal[1]);
		retString[2] = identifyRankSuffix(retVal[2]);
		retString[3] = identifyRankSuffix(retVal[3]);
		retString[4] = identifyRankSuffix(retVal[4]);
		retString[5] = identifyRankSuffix(retVal[5]);

		return retString;
	}

	private String identifyRankSuffix(String param) {
		if ((param == null) || (param.equalsIgnoreCase("") == true))
			return "none";
		if (param.endsWith("11") == true)
			return param + "th";
		else if (param.endsWith("12") == true)
			return param + "th";
		else if (param.endsWith("13") == true)
			return param + "th";
		else if (param.endsWith("1") == true)
			return param + "st";
		else if (param.endsWith("2") == true)
			return param + "nd";
		else if (param.endsWith("3") == true)
			return param + "rd";
		else
			return param + "th";
	}

	protected String getRankWithFriends(List<Object[]> besttimerace,
			String loggedUser) {
		if (besttimerace == null)
			return "error";
		if (besttimerace.size() == 0)
			return "error";
		for (int i = 0; i < besttimerace.size(); i++) {
			Object[] record = besttimerace.get(i);
			if ((record[0] != null)
					&& (record[0].toString().equalsIgnoreCase(loggedUser) == true)) {
				return identifyRankSuffix(String.valueOf(i + 1));
			}
		}
		return "error";
	}

	protected String generateStorngPasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		// return iterations + ":" + toHex(salt) + ":" + toHex(hash);
		return iterations + ":" + toHex(hash);
	}

	protected byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		// sr.nextBytes(salt);
		return salt;
	}

	protected String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	protected boolean validatePassword(String originalPassword,
			String storedPassword) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt,
				iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	protected byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
					16);
		}
		return bytes;
	}

}
