package TPfinal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
	public FileManager() {
	}

	public String readFile(String path) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			//De llegar aquí la ejecución, significa que el archivo no existe
			return null;
		}
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));
		}
		reader.close();
		return builder.toString();
	}
}
