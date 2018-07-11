package TPfinal;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
        sc.useDelimiter(Pattern.compile(System.getProperty("line.separator")));
		
        SimulationEinterfaz Simulac = new SimulationEinterfaz();
		boolean flagPoblacInic = true; //true mientras que no se haya cargado la poblacion inicial
		
		while(true) {
			String path = sc.next();
			while(flagPoblacInic) {
				String poblacInicial = Simulac.readFile(path);
				if(poblacInicial == null) {
					System.out.println("No se ha ingresado un path valido. Vuelva a intentarlo");
				}else{
					Simulac.createGenZero(poblacInicial);
					flagPoblacInic = false;
				}
			}
			
			System.out.println("\tMenú del programa:");
            System.out.println("\tIngrese 1 para ...");
            System.out.println("\tIngrese 2 para ...");
            System.out.println("\tIngrese 3 para ...");
            System.out.println("\tIngrese Q para salir.");
			
		}
		
	}

}
