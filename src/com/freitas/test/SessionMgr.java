package com.freitas.test;

import java.util.List;

public class SessionMgr {
	
	
	public boolean login(HttpConnector connector, String user, String passwd) throws Exception{
		
		StringBuilder sb = new StringBuilder(1024);
		//FIXME: create the login XML payload here
		List<String> results = connector.connect(sb.toString());
		for (String line : results){
			if (line.contains("<status>OK</status>")){
				return true;
			}
		}
		return false;
	}
	
	public boolean logout(HttpConnector connector, String user) throws Exception{
		
		StringBuilder sb = new StringBuilder(1024);
		//FIXME: create the logout XML payload here
		List<String> results = connector.connect(sb.toString());
		for (String line : results){
			if (line.contains("<status>OK</status>")){
				return true;
			}
		}
		return false;
	}

}
