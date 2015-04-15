package haw.gkaprojects.duc.robert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Robert
 *
 */
public class FileReader
{
	private final List<List<String>> _listRows;
	
	
	public FileReader()
	{
		_listRows = new ArrayList<>();
	}


	public void read(String path) throws IOException
	{
		File file = new File(path);
		try(Scanner scanner = new Scanner(file))
		{
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				if (readline(line) != null)
					_listRows.add(readline(line));
			}
		}
		catch (FileNotFoundException e)
		{
			throw e;
		}

	}

	private ArrayList<String> readline(String aLine)
	{
		if (!aLine.isEmpty())
		{
			Scanner scanner = new Scanner(aLine.replaceAll("[#:, ]", " "));
			ArrayList<String> listSpalten = new ArrayList<>();

			while (scanner.hasNext())
			{
				listSpalten.add(scanner.next());
			}
			scanner.close();
			return listSpalten;
		}
		else
		{
			return null;
		}
	}

	public List<List<String>> getRowList()
	{
		return _listRows;
	}

}
