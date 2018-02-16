package com.letsrace.spring.service;

import java.io.File;

public abstract interface PhotoService {

	void saveOrUpdate(String username, byte[] photoBytes, File serverFile);

}
