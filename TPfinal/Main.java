package TPfinal;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		sc.useDelimiter(Pattern.compile(System.getProperty("line.separator")));

		SimulationEinterfaz Simulac = new SimulationEinterfaz();
		boolean flagPoblacInic = true; //true mientras que no se haya cargado la poblacion inicial
		boolean flagGeneral = true;

		while(flagGeneral) {
			while(flagPoblacInic) {
				String path = sc.next();
				String poblacInicial = Simulac.readFile(path);
				if(poblacInicial == null) {
					System.out.println("No se ha ingresado un path valido. Vuelva a intentarlo");
				}else{
					Simulac.createGenZero(poblacInicial);
					flagPoblacInic = false;
				}
			}

			System.out.println("\tMenú del programa:");
			System.out.println("\tIngrese 1 para Simular Nueva Generacion");
			System.out.println("\tIngrese 2 para Listar las Gacelas aun Vivas, clasificadas por Generacion");
			System.out.println("\tIngrese 3 para Listar las Gacelas muertas, clasificadas por Motivo de Muerte");
			System.out.println("\tIngrese 4 para Salir y Terminar Simulacion.");
			flagGeneral = Simulac.opcionElegida(sc.next());

		}
		System.out.println("Gracias por utilizar este programa!");
		System.out.println("Hasta Luego!");
	}

}
