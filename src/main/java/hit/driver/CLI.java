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

	private InputStream in;
	private OutputStream out;
	public CLI(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}

	public String[] getConfiguration() {
		// reading the command line with scanner and in
		// writing back to the command line to out
		// returning the configuration when it is finished
		
		String[] configuration = new String[2];
		boolean start = false;
		String nextWord;
		
		try (Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(in)))) {
			while(sc.hasNext()) {
				nextWord = sc.next();
				if(nextWord.equalsIgnoreCase("START")) {
					write("Please enter required algorithm and ram capacity\n");
					start = true;
				}
				else if(nextWord.equalsIgnoreCase("STOP")){
					write("Thank you\n");
					start = false;
					return null;
				}
				else if(nextWord.equalsIgnoreCase("MRU") || nextWord.equalsIgnoreCase("LRU") || nextWord.equalsIgnoreCase("RR") ) {
					if(start) {
						configuration[0] = nextWord;
						if(sc.hasNext()) {
							try {
								nextWord = sc.next();
							    int capacity = Integer.parseInt(nextWord);
							    configuration[1] = nextWord;
							    return configuration;
							} catch (NumberFormatException e) {
							    write("Please enter a valid number");
							}
						}
					}
					else {
						write("Please enter start to begin");
					}
				}
				else{
					write("Not a valid command\n");
				}
			}
		}
		
		return null;
	}

	public void write(String string) {
		// need to check if flush method is a must
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)));
		pw.write(string);
		pw.flush();
	}
}
