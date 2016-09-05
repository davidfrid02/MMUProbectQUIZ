package hit.driver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI {

	// private InputStream in;
	// private OutputStream out;
	private Scanner sc;
	private PrintWriter pw;

	public CLI(InputStream in, OutputStream out) {
		// this.in = in;
		// this.out = out;
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
		sc = new Scanner(new BufferedReader(new InputStreamReader(in)));
	}

	public String[] getConfiguration() {
		// reading the command line with scanner and in
		// writing back to the command line to out
		// returning the configuration when it is finished

		String[] command = null;
		boolean start = false;
		String nextLine;

		while (sc.hasNextLine()) {
			nextLine = sc.nextLine();
			command = nextLine.split("\\s+");
			if (command[0].equalsIgnoreCase("START")) {
				write("Please enter required algorithm and ram capacity\n");
				start = true;
			} else if (command[0].equalsIgnoreCase("STOP")) {
				write("Thank you\n");
				start = false;
				return null;
			} else if (command[0].equalsIgnoreCase("MRU") || command[0].equalsIgnoreCase("LRU")
					|| command[0].equalsIgnoreCase("RR")) {
				if (start) {
					if (command.length == 2) {
						try {
							@SuppressWarnings("unused")
							int checkIfInt = Integer.parseInt(command[1]);
							return command;
						} catch (NumberFormatException e) {
							write("Please enter a valid number\n");
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

	public void write(String string) {
		pw.write(string);
		pw.flush();
	}
}
