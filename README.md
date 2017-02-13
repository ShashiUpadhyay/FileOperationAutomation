We have a process where we receive data in a file which are required to process manually. We would like to automate this through the use of a program that will automatically read the data and process it, and then display the results to us in a report.

The data is structured as follows. 
•	The first record in the file will indicate the number of data samples to read.
•	The subsequent records will provide the specific data to process
o	The first record must contain two items that identify the data group to follow – this can be considered the control record – the items are separated by spaces
♣	The first character of the control record must be a ‘*’
♣	The first number indicates the number of data records in the data group
♣	The second number indicates the number of elements in the data group
o	The next set of records will be the actual data that needs to be processed and will contain any number of elements separated by spaces.  You only need to use the applicable values.
o	If there is a discrepancy in the data counts, the entire set of data is to be rejected and an error message printed indicated which data group the error is in.
o	If there is any error, generate an error message and reject the entire data group.
o	Blank lines should not be processed

You are to process the data as follows:

If the data contains two parameters, perform the following calculations

•	Calculate the average of the two values and display with the following label “AVG = “
•	Calculate the results of the following formula and display with the following label “FORM = “

Result = pi * (first value) + psi * (1.34 + (second value)3  ) 

•	Calculate the average of the ‘Result’ calculated above from all the data points
•	The results must be displayed to 3 significant digits – nnnn.000
•	Display the total number of records reads

If the data contains three parameters
•	Calculate the average of the three values and display with the following label “AVG 3 = “
•	Calculate the results of the following formula and display with the following label “FORM 3 = “

Result = pi * (first value) + psi * (1.34 + (second value)3  )  + (zeta (lookup using the following mapping)
) / (second value)

a	 3
b	 4
c	 6

•	If a translation is not found, then use ‘a’ as the default.
•	Calculate the average of the ‘Result’ calculated above from all the data points
•	The results must be displayed to 3 significant digits – nnnn.000
•	Display the total number of records reads


The constants pi,psi, and zeta are defined as follows:

	Pi = 3.14
	Psi = 6.39485
	Zeta = 3.2


You are more than welcome to add additional data to validate other error and test conditions.  All code and output should be sent back so it can be reviewed and verified.  If there are any questions, please feel free to contact us.
