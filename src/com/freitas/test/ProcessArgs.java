package com.freitas.test;

import java.util.HashMap;
import java.util.Map;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class ProcessArgs {
	
	public static String USER_OPT = "user";
	public static String PASSWD_OPT = "passwd";
	public static String MODELDIR_OPT = "modeldir";
	
	public Map<String,String> process(String[] args) {
		
		if (args.length != 6){
			help();
			System.exit(1);
		}
		
		OptionParser parser = new OptionParser();
		parser.accepts(USER_OPT).withRequiredArg();
		parser.accepts(PASSWD_OPT).withRequiredArg();
		parser.accepts(MODELDIR_OPT).withRequiredArg();
		OptionSet options = parser.parse(args);
		
		if (!options.hasArgument(USER_OPT) || !options.hasArgument(PASSWD_OPT) || !options.hasArgument(MODELDIR_OPT)){
			help();
		}
		
		Map<String,String> map = new HashMap<String, String>();
		map.put(USER_OPT, (String)options.valueOf(USER_OPT));
		map.put(PASSWD_OPT, (String)options.valueOf(PASSWD_OPT));
		map.put(MODELDIR_OPT, (String)options.valueOf(MODELDIR_OPT));
		return map;
	}
	
	
	
	private void help() {
		
		System.out.println("Invalid options submitted");
		System.out.println("    --user <name of user to run models as>");
		System.out.println("    --passwd <password for user>");
		System.out.println("    --modeldir <directory where models are stored>");
		
	}
	

}