package com.canvera.pvss;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteData {

	String[] lines;
	FileWriter fw;
	BufferedWriter bfw;
	String method = "GET";
	String y;
	int[] tmpLines = null;
	int[] linesToExtract = { 14, 23,24,25,26,27,28,17 };
	int[] linesToExtractEr = { 14,24,25,26,27,28,29,17 };
	int[] linesToExtractGET = { 14,22,23,24,25,17 };
	int[] linesToExtractGETEr = { 14,23,24,25,26,17 };
	public void intialize(String filename, String outPutDirectory) {
		File file = new File(outPutDirectory + filename);

		try {
			
			fw = new FileWriter(file);
			bfw = new BufferedWriter(fw);
			if (method.equalsIgnoreCase("GET")){
				
				bfw.write("#Concurrent-Requests\tRequest/s\tTimePerRequest\tTimePR_ALL\tTotal-Speed-kb/s"+ "\n");
			}else {
				bfw.write("#Concurrent-Requests\tRequest/s\tTimePerRequest\tTimePR_ALL\tDownload-Rate-kb/s\tUpload-Rate-kb/s\tTotal-Speed-kb/s"
						+ "\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void parseAndWriteData() {
		
		boolean errorOcurred = false;
		
		//Check Error Occured or not
		Pattern p1 = Pattern.compile("[-]?[0-9]*[.]*[0-9]+");
		Matcher m1 = p1.matcher(lines[17]);
		
		while (m1.find()) {
			y = m1.group().toString().trim();
			if(Integer.parseInt(y) != 0){
				errorOcurred = true;
			}
			
		}
		
		if (method.equalsIgnoreCase("GET")){
			if(errorOcurred){
				System.out.println("Get Error Occurred" + y);
				tmpLines = 	linesToExtractGETEr;
			}else {
				tmpLines = 	linesToExtractGET;
			}
		}
		else {
			if(errorOcurred){
				System.out.println("POST Error Occurred" + y);
				tmpLines = 	linesToExtractEr;
			}else {
				tmpLines = 	linesToExtract;
			}
		}
		for (int i : tmpLines) {
			Pattern p = Pattern.compile("[-]?[0-9]*[.]*[0-9]+");
			Matcher m = p.matcher(lines[i]);
			while (m.find()) {
				try {
					String x = m.group().toString().trim() + "\t";
					System.out.println(x);
						bfw.write(x);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		try {
			bfw.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {

		try {
			bfw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getLines() {
		return lines;
	}

	public void setLines(String[] lines) {
		this.lines = lines;
	}

}
