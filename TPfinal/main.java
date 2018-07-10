package TPfinal;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {
	public static void main(String[] args) {
		SimulationEinterfaz simulacion = new SimulationEinterfaz();
		
		Scanner sc=new Scanner(System.in);

        sc.useDelimiter(Pattern.compile(System.getProperty("line.separator")));
        
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
	
	  public static void option1(List<String> inputs) {
	    }
	    
	    public static void option2(String input) {
	    }
	    
	    public static void option3() {
	    }

	    public static void option4() {
	    }
}
