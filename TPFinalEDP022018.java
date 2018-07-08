import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class TPFinalEDP022018 {
    
    static final private String NORMALIZATED_FILE_SEPARATOR = "/";

    /*
     * Este método toma una ruta absoluta (Path absoluto) al archivo que se desea leer
     * Devuelve un String compuesto por todas las líneas del archivo, separados por 
     * \n o \r\n (newline de mac/linux y windows respectivamente por lo general)
     */
    public static String readFile(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            //De llegar aquí la ejecución, significa que el archivo no existe
        }
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        reader.close();
        return builder.toString();
    }
    
    /*
     * Este método toma una ruta absoluta (Path absoluto) al archivo que se desea escribir, el contenido, 
     * y escribe dicho archivo en la ubicación deseada..
     */
    public static void writeToFile(String path, String fileContent) throws IOException {
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
    
    /*
     * Este método toma dos números enteros, min y max, 
     * y genera un número entero random comprendido entre ambos.
     */
    public static int getRandomIntBetween(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException();
        }
        return (int) Math.floor(Math.random()*(max-min+1)+min);
    }

    
    public static void option1(List<String> inputs) {
    }
    
    public static void option2(String input) {
    }
    
    public static void option3() {
    }

    public static void option4() {
    }

    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in); //Scanner para obtener el input del usuario

        sc.useDelimiter(Pattern.compile(System.getProperty("line.separator")));
        
        System.out.println("Bienvenidos al TP Final de EDyP!!"); //Mensaje de bienvenida de ejemplo

        while(true) { //Ciclo principal del programa
            //Imprimimos el menú
            System.out.println("\tMenú del programa:");
            System.out.println("\tIngrese 1 para ...");
            System.out.println("\tIngrese 2 para ...");
            System.out.println("\tIngrese 3 para ...");
            System.out.println("\tIngrese Q para salir.");

            String opcion = sc.next(); //Lee una linea de input del usuario
            
            if (opcion.equalsIgnoreCase("1")) { //elijió la opción 1
                //Si quisiéramos pedirle al usuario un listado de cosas, se haría algo así
                List<String> inputs = new LinkedList<>();
                while (true) {
                    String sequence = sc.next(); //Lee una linea de input del usuario
                    if (sequence.equalsIgnoreCase("q")) { //si ingresa una 'q' sola, deja de tomar inputs y procesa
                        option1(inputs);
                        break;
                    } else {
                        inputs.add(sequence); //no era 'q', asi que agrego el input para procesarlo luego
                    }
                }   
            } else if (opcion.equalsIgnoreCase("2")) {
                //Si quisiéramos pedirle al usuario solo un elemento, podría hacerse asi:
                String sequence = sc.next(); //Lee una linea de input del usuario
                option2(sequence);
            } else if (opcion.equalsIgnoreCase("3")) {
                System.out.println(getRandomIntBetween(1, 17));
                option3();
            } else if (opcion.equalsIgnoreCase("4")) {
                option4();
            } else if (opcion.equalsIgnoreCase("5")) {
                break; //sale del while(true)
            } else {
                System.out.println("No ingresó una opción correcta");
            }
        }
        
        System.out.println("Gracias por utilizar este programa!");
        System.out.println("Hasta Luego!");
        
        sc.close();
        
    }
}
