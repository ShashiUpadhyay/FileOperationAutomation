package utility;

import java.io.File;

public class InputValidations {
	
	public static boolean validatingInputArguments(String[] args) {
		boolean returnvalue = false;
		try {
			if (args.length == 1) {
				if (args[0] != null && !args[0].isEmpty()) {
					if (!new File(args[0]).canRead()) {
						System.out.println("Input file name invalid or missing !!");
						System.exit(0);
					}
				}
				return true;
			} else {
				System.out.println("Input Arguments are missing !!");
				System.out.println("Arguments should be in the below mentioned format !!");
				System.out.println("java <Executable> <InputFile> !!");
				System.exit(0);
			}

		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(0);
		} finally {

		}
		return returnvalue;
	}

}
