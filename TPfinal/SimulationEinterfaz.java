package TPfinal;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SimulationEinterfaz {
	private Simulation s = new Simulation();
	private FileManager f = new FileManager();
	
	//constructor
	public SimulationEinterfaz() {
		System.out.println("Welcome to the Gacelas simulation");
		System.out.println("Please, enter the first generation path to txt file");
	}
	
	public String readFile(String path) {
		try {
			return f.readFile(path);
		} catch (IOException e) {
			return null;
		}
	}
	
	public void createGenZero(String poblacInicial) {
		Scanner sc = new Scanner(poblacInicial).useDelimiter("\\n"); //separo por espacios cada token
		List<Gacela> generationZero = new LinkedList<>();
		while(sc.hasNext()) { //mientras haya gacelas
			Gacela g = new Gacela(false);
			g.setADN(sc.next());
			generationZero.add(g); //se agregan las gacelas a la lista
		}
		Generacion Gen0 = new Generacion(generationZero, 0); //se agrega la lista con su numero de generacion
		sc.close();
		s.addPopulation(Gen0);
	}

	public boolean opcionElegida(String opcion) {
		if (opcion.equalsIgnoreCase("1")) {
			return true;
		}else if(opcion.equalsIgnoreCase("2")) {
			return true;
		}else if(opcion.equalsIgnoreCase("3")) {
			return true;
		}else if(opcion.equalsIgnoreCase("4")) {
			//System.out.println("Esta seguro? Recuerde que se eliminaran todos los datos generados dentro de la Simulacion");
			return false;
		}else {
			System.out.println("No se ha ingresado una opcion valida. Por favor, vuelva a intentarlo.");
			return true;
		}
	}
}
