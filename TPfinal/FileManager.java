package TPfinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	static final private String NORMALIZATED_FILE_SEPARATOR = "/";
	
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

	public void writeToFile(String path, String fileContent) throws IOException {
        String normalizatedFileName = normalizateFileName(path);                
        int slashPosition = normalizatedFileName.lastIndexOf(NORMALIZATED_FILE_SEPARATOR);
        if (slashPosition >= 0)  {
            File aFile = new File(normalizatedFileName.substring(0, slashPosition));
            if (!aFile.exists()) {
                aFile.mkdirs();
            }
        }
        
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(fileContent);
        fileWriter.close();
    }
    
    private static String normalizateFileName(String path) {
        String normalizatedFileName;
        if (File.separator.equals("\\")) {
            normalizatedFileName = path.replaceAll("\\\\",NORMALIZATED_FILE_SEPARATOR );
        } else {
            normalizatedFileName = path;
        }
        return normalizatedFileName;
    }
	
}
