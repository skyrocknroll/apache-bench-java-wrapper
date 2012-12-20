package com.canvera.pvss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateCommandString {

	String folderForUploadFile = "/srv/source/photovault/trunk/pv_storage/pvss_test/fixture/perf/";
	String httpMethod = "POST";
	String perfTool = "ab";
	String numberOfRequests = "1";
	String concurrentRequests = "1";
	String fileToUpload = "50kb.jpg";
	String pathToTest = "http://localhost/post/perf/";
	String fileToTest = fileToUpload;
	String outputDirectory = "/srv/out/";

	public String[] createCommand() {

		if (httpMethod.equalsIgnoreCase("POST")) {
			return new String[] { perfTool, "-n", numberOfRequests, "-c", concurrentRequests, "-p",
					folderForUploadFile + fileToUpload, pathToTest + fileToTest };
		} else {
			return new String[] { perfTool, "-n", numberOfRequests, "-c", concurrentRequests, pathToTest + fileToTest };

		}

	}

	/***
	 * This method will run the ab with the parameters given and generates 
	 * the file in /srv/out with the file name as
	 * "TotalNoofRequst-conCurrentRequest-filename.tsv"
	 * 
	 * @param ccs
	 */

	public void runTest(CreateCommandString ccs) {

		BufferedWriter out;

		try {

			FileWriter fwstream = new FileWriter("/srv/out/" + ccs.getNumberOfRequests() + "-"
					+ ccs.getConcurrentRequests() + "-" + ccs.getFileToUpload() + ".tsv");
			out = new BufferedWriter(fwstream);

			String[] command = ccs.createCommand();
			Process p;
			p = Runtime.getRuntime().exec(command);
			int x = p.waitFor();
			String line;
			if (x == 0) {
				BufferedReader bis = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while (bis.ready()) {
					line = bis.readLine();
					out.write(line + "\n");

				}
			}else {
				System.err.println("Command Execution Failed. \n Error Return Code : "+ x);
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getfolderForUploadFile() {
		return folderForUploadFile;
	}

	public void setfolderForUploadFile(String pathToUploadFile) {
		this.folderForUploadFile = pathToUploadFile;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getPerfTool() {
		return perfTool;
	}

	public void setPerfTool(String perfTool) {
		this.perfTool = perfTool;
	}

	public String getNumberOfRequests() {
		return numberOfRequests;
	}

	public void setNumberOfRequests(String numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}

	public String getConcurrentRequests() {
		return concurrentRequests;
	}

	public void setConcurrentRequests(String concurrentRequests) {
		this.concurrentRequests = concurrentRequests;
	}

	public String getFileToUpload() {
		return fileToUpload;
	}

	public void setFileToUpload(String fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	public String getPathToTest() {
		return pathToTest;
	}

	public void setPathToTest(String pathToTest) {
		this.pathToTest = pathToTest;
	}

	public String getFileToTest() {
		return fileToTest;
	}

	public void setFileToTest(String fileToTest) {
		this.fileToTest = fileToTest;
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
}
