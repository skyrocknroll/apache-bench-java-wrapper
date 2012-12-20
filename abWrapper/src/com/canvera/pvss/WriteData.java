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
	int[] linesToExtract = { 14, 22,23,24,25,26,27 };

	public void intialize(String filename, String outPutDirectory) {
		File file = new File(outPutDirectory + filename);

		try {
			fw = new FileWriter(file);
			bfw = new BufferedWriter(fw);
			bfw.write("#Concurrent-Requests\tRequest/s\tTimePerRequest\tTimePR_ALL\tDownload-Rate-kb/s\tUpload-Rate-kb/s\tTotal-Speed-kb/s"
					+ "\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void parseAndWriteData() {
		for (int i : linesToExtract) {
			Pattern p = Pattern.compile("[-]?[0-9]*[.]*[0-9]+");
			Matcher m = p.matcher(lines[i]);
			while (m.find()) {
				try {
					bfw.write(m.group().toString().trim() + "\t");
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
