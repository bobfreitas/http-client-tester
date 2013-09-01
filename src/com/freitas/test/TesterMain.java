package com.freitas.test;

import java.util.List;
import java.util.Map;

public class TesterMain {

	public static void main(String[] args) {
		
		HttpConnector connector = new HttpConnector();
		
		// verify the command line options are okay
		ProcessArgs processArgs = new ProcessArgs();
		Map<String,String> argsMap = processArgs.process(args);
		
		System.out.println("Executing all models in " + 
				argsMap.get(ProcessArgs.MODELDIR_OPT) + 
				" with the user " + argsMap.get(ProcessArgs.USER_OPT));
		
		// login to server
		SessionMgr session = new SessionMgr();
		boolean loggedIn = false;
		try {
			loggedIn = session.login(connector, argsMap.get(ProcessArgs.USER_OPT), 
					argsMap.get(ProcessArgs.PASSWD_OPT));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		if (!loggedIn){
			System.out.println("Unable to login with " + argsMap.get(ProcessArgs.USER_OPT) + 
					" " + argsMap.get(ProcessArgs.PASSWD_OPT));
			System.exit(1);
		}
		
		// run the set of models in the directory
		RunModels runModels = new RunModels();
		List<String> testReport = null;
		try {
			testReport = runModels.run(connector, argsMap.get(ProcessArgs.MODELDIR_OPT), 
					argsMap.get(ProcessArgs.USER_OPT), 256);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// display the results
		System.out.println("Test Results");
		for (String line : testReport){
			System.out.println("\t" + line);
		}
		System.out.println();
		
		// finally log out
		try {
			session.logout(connector, argsMap.get(ProcessArgs.USER_OPT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}