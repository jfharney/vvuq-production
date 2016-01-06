package gov.ornl.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.OutputStream;
import java.util.Random;

import gov.ornl.vvuq.matrix.DenseMatrix;

public class FigureCSVWriter {


	
	/*
	public static void main(String [] args) {
		
		String filePath = "src/main/resources/static/data/output.csv";
		
		
		String content = "date,bucket,count\n";

		
		int sMax = 5;
		
		double [] comparison_array = new double[sMax*sMax];
		
		Random rand= new Random();
		
		for(int i=0;i<comparison_array.length;i++) {

			comparison_array[i] = rand.nextDouble();
		}
		

		DenseMatrix matrix = new DenseMatrix(sMax,sMax,comparison_array);

		content += textToBeWritten(sMax,comparison_array);
		
		
		try {
			useFileOutputStream(content,filePath);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	*/
	
	
	public static String textToBeWritten(int sMax, double [] comparison_array) {

		
		String totalStr = "";
		for(int x=0;x<sMax;x++) {
			
			String xStr = "";
			if(x == 0) {
				xStr = "2012-07-20";
			} else if(x == 1) {
				xStr = "2012-07-21";
			} else if(x == 2) {
				xStr = "2012-07-22";
			} else if(x == 3) {
				xStr = "2012-07-23";
			} else {
				xStr = "2012-07-24";
			}
			
			xStr += ",";
			
			
			for(int y=0;y<sMax;y++) {
				
				String yStr = "";
				yStr += (y+1) + ",";
				
				totalStr += xStr + yStr + comparison_array[x*5+y] + "\n";
				
			}
			
		}
		return totalStr;
	}
	
	
	
	public static void useFileOutputStream(String content, String filePath) {
		
		OutputStream outputStream = null;
		
		try {
			outputStream = new FileOutputStream(new File(filePath));
			outputStream.write(content.getBytes(), 0, content.length());
		} catch(FileNotFoundException e) {
			System.err.println("Error Opening the file : ");
			e.printStackTrace();
		} catch(IOException e) {
			System.err.println("Error writing the file : ");
			e.printStackTrace();
		} finally {
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
}
