package com.canvera.pvss;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {

	String fileToUpload;
	String concurrentRequests;
	String numberOfRequests;
	String pathToOutputFiles;
	StringBuffer sb = null;
	BufferedReader bfd;
	String [] lines;
	public String[] extractusefulInfo() {
		try {
			sb = new StringBuffer();
			String path = pathToOutputFiles+numberOfRequests+"-"+concurrentRequests+"-"+fileToUpload+".tsv";
			FileReader fr = new FileReader(path);
			System.out.println("Reading the file : " + path);
			bfd = new BufferedReader(fr);
			while(bfd.ready()){
				sb.append(bfd.readLine()+"\n");
			}
			lines = sb.toString().split("\n");
			
			
			
			
			//System.out.println(lines[22]);
			bfd.close();
			sb =null;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
			
		

	}

	public String getPathToOutputFiles() {
		return pathToOutputFiles;
	}

	public void setPathToOutputFiles(String pathToOutputFiles) {
		this.pathToOutputFiles = pathToOutputFiles;
	}

	public String getFileToUpload() {
		return fileToUpload;
	}

	public void setFileToUpload(String fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	public String getConcurrentRequests() {
		return concurrentRequests;
	}

	public void setConcurrentRequests(String concurrentRequests) {
		this.concurrentRequests = concurrentRequests;
	}

	public String getNumberOfRequests() {
		return numberOfRequests;
	}

	public void setNumberOfRequests(String numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}

}
