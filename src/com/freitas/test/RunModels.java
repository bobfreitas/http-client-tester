package com.freitas.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

public class RunModels {
	
	public List<String> run(HttpConnector connector, String modelDir, String user, int maxTests) throws Exception {

		List<String> summaryResults = new ArrayList<String>(maxTests);
		
		// open the directory 
		File directory = new File(modelDir);
		File[] fileArray;  
		fileArray=new File[maxTests];
		fileArray=directory.listFiles();
		for (int i = 0; i < fileArray.length; i++) {
			// read each file
			File path = fileArray[i];
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String model = "";
			while (br.ready()) {
				String line = br.readLine();
				// could do some cleanup here, if needed
				model += line + "\n";
			}
			
			// parse the XML to retrieve the model name so can track it later
			XMLParser parser = new XMLParser();
			Document modelDoc = parser.parse(model);
			String modelName = parser.getModelName(modelDoc);
			
			// next submit the model
			String runCmd = "your_run_command here";
			//FIXME: need your command here
			connector.connect(runCmd);
			
			// now need to wait for the model to finish and do some 
			// process to figure out if completed successfully
			boolean alive = true;
			boolean complete = true;
			String errMsg = "";
			ModelStatus modelStatus = new ModelStatus(alive, complete, errMsg);
			//FIXME: need your processing here
			
			// save the test results
			summaryResults.add(createTestSummary(modelName, modelStatus));
			
			br.close();
			fr.close();
		}
		return summaryResults;
	}
	
	
	private String createTestSummary(String modelName, ModelStatus modelStatus) {
		StringBuilder sb = new StringBuilder(100);
		sb.append("Test Model: ");
		sb.append(modelName);
		if (modelStatus.isSuccess()){
			sb.append(" - Completed successfully");
		}
		else {
			sb.append(" - Encountered an error: ");
			sb.append(modelStatus.getErrMsg());
		}
		return sb.toString();
	}


}
