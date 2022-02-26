package application;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *@author
 */
public class CSVReader {
	public static String[] readFile (String filename) {
		try {
			String buffer = "";
			Scanner scan = new Scanner(new File(filename));
			while(scan.hasNextLine()) {
				buffer += scan.nextLine();
			}
			return buffer.split(",");
		} catch (IOException e) {
			System.out.println("IOException! " + e.getMessage());
return null;
		} catch (Exception e) {
			System.out.println("Exception! " + e.getMessage());
			return null;
		}
	}
}