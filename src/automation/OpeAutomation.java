package automation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import utility.FileProcessor;



public class OpeAutomation {

	private static final double PI = 3.14;
	private static final double PSI = 6.39485;
	private static final double ZETA = 3.2;
	private static final String DECIMALFORMAT = "#0.000";
	private static final String DEFAULT_LOOKUP_KEY = "a";
	private static final String SPACE = " ";
	private static final String STAR = "*";

	private static String group_being_executed;

	private FileProcessor fileProcessor;
	private String file_processor_fetchedline = null;
	private Scanner scanner = null;

	private NumberFormat formatter = new DecimalFormat(DECIMALFORMAT);
	private List<String[]> data_records = new ArrayList<String[]>();
	int data_records_count, elements_in_group_count, data_samples;
	static int records_with_2_parms = 0, records_with_3_parms = 0;
	static double total_2_result = 0.0, total_3_result = 0.0;

	private static Map<String, Integer> lookup = new HashMap<String, Integer>();
	static {
		lookup.put("a", 3);
		lookup.put("b", 4);
		lookup.put("c", 6);
	};

	public boolean validateRecords() {
		if (data_records.size() != data_records_count) {
			System.out.println("Entire group of data is rejected !!!");
			System.out.println("Discrepancy in the number of data records in the data group ");
			System.out.println("The data group the error is in : " + group_being_executed);
			System.out.println("");
			System.out.println("");
			return false;
		} else {
			for (String[] row : data_records) {

				if (row.length != elements_in_group_count) {
					System.out.println("Entire group of data is rejected !!!");
					System.out.println("Discrepancy in the number of elements in the data group ");
					System.out.println("The data group the error is in : " + group_being_executed);
					System.out.print("Error in data set -> ");
					Arrays.asList(row).forEach(n -> System.out.print(" " + n + " "));
					System.out.println("");
					System.out.println("");
					return false;
				}

			}
		}

		return true;

	}

	public void performOperation() {

		if (validateRecords()) {
			
			if (elements_in_group_count == 2) {
				

				for (String[] row : data_records) {
					double sum = 0.0, result = 0.0;
					for (String value : row) {
						sum += Integer.parseInt(value);
					}
					result = PI * (Integer.parseInt(row[0])) + PSI * (1.34 + (Integer.parseInt(row[1])) * 3);
					System.out.println("AVG = " + formatter.format(sum / elements_in_group_count));
					System.out.println("FORM = " + formatter.format(result));
					
					total_2_result += result;
					++records_with_2_parms;
				}
				
				

			} else if (elements_in_group_count == 3) {
				
				int firstvalue=0, secondvalue=0, thirdvalue=0;
				
				for (String[] row : data_records) {
					double sum = 0.0, result = 0.0;
					firstvalue = Integer.parseInt(row[0]);
					secondvalue = Integer.parseInt(row[1]);

					if (lookup.containsKey(row[2])) {
						thirdvalue = lookup.get(row[2]);
					} else {
						thirdvalue = lookup.get(DEFAULT_LOOKUP_KEY);
					}

					sum = firstvalue + secondvalue + thirdvalue;
					result = PI * (firstvalue) + PSI * (1.34 + (secondvalue) * 3)
							+ (ZETA * (thirdvalue)) / (secondvalue);
					System.out.println("AVG 3 = " + formatter.format(sum / elements_in_group_count));
					System.out.println("FORM = " + formatter.format(result));
					
					total_3_result += result;
					++records_with_3_parms;
				}
			}
		}

		data_records.clear();
		data_records_count = 0;
		elements_in_group_count = 0;
	
	}

	public void parseFile(FileProcessor fileProcessorIn) {

		try {

			this.fileProcessor = fileProcessorIn;
			int index = 0;

			while ((file_processor_fetchedline = fileProcessor.readNextLine()) != null) {
				scanner = new Scanner(file_processor_fetchedline);
				if (index == 0) {
					data_samples = scanner.nextInt();
					++index;
				} else {

					if (file_processor_fetchedline.startsWith(STAR)) {
						++index;
						if (data_records != null && data_records.size() > 0) {
							performOperation();
							System.out.println("----------------------------------");
						}

						group_being_executed = file_processor_fetchedline;
						file_processor_fetchedline = file_processor_fetchedline.substring(1);
						scanner.reset();
						scanner = new Scanner(file_processor_fetchedline);
						data_records_count = scanner.nextInt();
						elements_in_group_count = scanner.nextInt();

					} else {
						String[] record_set = file_processor_fetchedline.split(SPACE);
						data_records.add(record_set);

					}
				}

			}

			if ((index - 1) == data_samples) {
				if (data_records != null && data_records.size() > 0) {
					performOperation();
					System.out.println("----------------------------------");
				}
			}
			
			System.out.println("AVERAGE of Total Result contains two parameters = " + formatter.format(total_2_result / records_with_2_parms));
			System.out.println("Total numbers of records read contains two parameters = " + records_with_2_parms);
			System.out.println();
			
			System.out.println("AVERAGE of Total Result contains three parameters = " + formatter.format(total_3_result / records_with_3_parms));
			System.out.println("Total numbers of records read contains three parameters = " + records_with_3_parms);
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
