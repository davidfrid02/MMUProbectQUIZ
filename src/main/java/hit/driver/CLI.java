package hit.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI {
	private Scanner sc;
	private PrintWriter pw;

	/***
	 * ctor that gets Input file and output file
	 * @param in
	 * @param out
	 */
	public CLI(InputStream in, OutputStream out) {
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
		sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
	}

	/***
	 * used to read requested configuration from the command line and returning it to the main program
	 * @return
	 */
	public String[] getConfiguration() {
		String[] command;
		String[] commandTotal = new String[3];
		
		boolean start = false;
		String nextLine;
		String configFile = null;
		while (sc.hasNextLine()) {
			nextLine = sc.nextLine();
			// split the string by spaces
			command = nextLine.split("\\s+");
			if (command[0].equalsIgnoreCase("START")) {
				write("load configuration file\n");
				start = true;

				
				start = true;
			} 

			else if (command[0].equalsIgnoreCase("STOP")) {
				write("Thank you\n");
				start = false;
				return null;
			}			
			//==============QUIZ================
			else if (command[0].equalsIgnoreCase("LOAD")) {
				if (start) {
					configFile = command[1].toString();
					write("Please enter required algorithm and ram capacity\n");
					
				}else {
					write("Please enter start to begin\n");
				}
			}
			//==============================================================================
			else if (command[0].equalsIgnoreCase("MRU") || command[0].equalsIgnoreCase("LRU")
					|| command[0].equalsIgnoreCase("RR")) {
				if (start) {
					if (command.length == 2) {
						try {
							@SuppressWarnings("unused")
							int checkIfInt = Integer.parseInt(command[1]);
							commandTotal[0] = command[0];
							commandTotal[1] = command[1];
							commandTotal[2] = configFile;
							return commandTotal;
						} catch (NumberFormatException e) {
							write("Please enter a valid capacity\n");
						}
					} else {
						write("Please enter a valid capacity\n");
					}
				} else {
					write("Please enter start to begin\n");
				}
			} else {
				write("Not a valid command, system is exiting\n");
				return null;
			}
		}

		return null;
	}

	// used to write to the console
	public void write(String string) {
		pw.write(string);
		pw.flush();
	}
}
