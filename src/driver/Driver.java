package driver;

import automation.OpeAutomation;
import utility.FileProcessor;
import utility.InputValidations;


public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args != null && InputValidations.validatingInputArguments(args)){
			FileProcessor read_processing;
			OpeAutomation opeautomation;
			String inputFile = args[0];
			read_processing = new FileProcessor(inputFile);
			opeautomation = new OpeAutomation();
			System.out.println("================ Generating Report =====================");
			opeautomation.parseFile(read_processing);
			
		}else{
			System.out.println("Few Input Arguments are missing !!");
		}

	}

}
