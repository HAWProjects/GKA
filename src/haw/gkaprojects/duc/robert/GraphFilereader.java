package haw.gkaprojects.duc.robert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class offers a static method to read .graph file
 * @author DucNguyenMinh
 *
 */
public class GraphFilereader {
	
	public static final int DIRECTED = 1;
	public static final int ATTRIBUTED = 3;
	public static final int WEIGHTED = 5;
	
	/**
	 * This method reads .graph file from the given path 
	 * then returns an array of String which contains content of the .graph file and the type of the graph 
	 * @param path path to .graph file
	 * @return an array which array[0] contains the content of .graph file and array[1] contains the type of the graph
	 */
	public static String[] readFile(String path) {
		String[] strData_with_graphtype = new String[2];
		String strData = "";
		int graphType = 0;
		// Read Text File
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			// read header
			sb.append(line);
			String header = sb.toString().trim();
			graphType = chooseGraphType(header);

			// read graph
			sb = new StringBuilder();
			
				//If there is no header
			if (graphType == 0)  {
				sb.append(line);
				sb.append(System.lineSeparator());
			}

			line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
				// sb.append(line);
			}
			strData = sb.toString();

		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Cannot find text data!!!" + path);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		strData_with_graphtype[0] = strData;
		strData_with_graphtype[1] = graphType+"";
		return strData_with_graphtype;
	}
	
	/**
	 * This method checks the type of the graph
	 * @param header header of file, in which the graph is stored
	 * @return int - type of the graph 
	 */
	private static int chooseGraphType(String header) {

		int graphType = 0;
		if (header == "" || header.charAt(0) != '#')
			return 0;

		String trimedheader = header.replaceAll(" ", "");
		String[] headerArr = trimedheader.split("#");

		for (String string : headerArr) {
			if (string.equals("directed")) {
				graphType += DIRECTED;
			} else if (string.equals("attributed")) {
				graphType += ATTRIBUTED;
			} else if (string.equals("weighted")) {
				graphType += WEIGHTED;
			}
		}

		return graphType;
	}

}
