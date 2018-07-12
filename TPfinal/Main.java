package TPfinal;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		sc.useDelimiter(Pattern.compile(System.getProperty("line.separator")));

		SimulationEinterfaz Simulac = new SimulationEinterfaz();
		boolean flagGeneral = true;
		boolean flagPoblacInic = true; //true mientras que no se haya cargado la poblacion inicial

		while(flagGeneral) {
			
			while(flagPoblacInic) {
			flagPoblacInic = Simulac.calcularPoblacInic(sc, flagPoblacInic);
			}

			System.out.println("\tMenú del programa:");
			System.out.println("\tIngrese 1 para Simular Nueva Generacion");
			System.out.println("\tIngrese 2 para Listar las Gacelas aun Vivas, clasificadas por Generacion");
			System.out.println("\tIngrese 3 para Listar las Gacelas muertas, clasificadas por Motivo de Muerte");
			System.out.println("\tIngrese 4 para Salir y Terminar Simulacion.");
			flagGeneral = Simulac.opcionElegida(sc.next(),sc);

		}
		System.out.println("Gracias por utilizar este programa!");
		System.out.println("Hasta Luego!");
	}

}
