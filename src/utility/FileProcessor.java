package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

	private static String inputfile;
	BufferedReader buffer_reader;
	
	/**
	 * @param filename
	 */
	public FileProcessor(String filename) {
		try {
			setInputfile(filename);
			buffer_reader = new BufferedReader(new FileReader(getInputfile()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Entered input file : " + getInputfile() + " is missing.");
			System.exit(0);
		}  finally {}
	}
	
	public String readNextLine() {
		String lineJustFetched = null;

		try {
			lineJustFetched = buffer_reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {}
		return lineJustFetched;
	}
	
	@Override
    protected void finalize() throws Throwable {
        try{
            if(buffer_reader != null){
            	try {
    				buffer_reader.close();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				System.exit(0);
            } 
        }
            }catch(Throwable t){
            throw t;
        }finally{
            super.finalize();
        }
    }
	

	/**
	 * @return the inputfile
	 */
	public static String getInputfile() {
		return inputfile;
	}

	/**
	 * @param inputfile the inputfile to set
	 */
	private static void setInputfile(String inputfile) {
		FileProcessor.inputfile = inputfile;
	}

	
}
