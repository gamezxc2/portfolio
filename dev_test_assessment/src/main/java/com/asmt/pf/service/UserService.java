package com.asmt.pf.service;

import java.util.HashMap;

public interface UserService {
	public int join(HashMap<String,String> s);
	
	public int search(String id);
	
	public int delete(int idx);
	
}
