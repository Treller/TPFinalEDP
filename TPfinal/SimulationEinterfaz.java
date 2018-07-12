package TPfinal;


import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimulationEinterfaz {
	private Simulation s = new Simulation();
	private FileManager f = new FileManager();

	//constructor
	public SimulationEinterfaz() {
		System.out.println("Welcome to the Gacelas simulation");
		System.out.println("Please, enter the first generation path to txt file");
	}

	public void calcularPoblacInic(Scanner sc) { //no pasa copia del scanner, como es no primitivo pasa referencia
		boolean flagPoblacInic = true; //true mientras que no se haya cargado la poblacion inicial
		while(flagPoblacInic) {
			String path = sc.next();
			String poblacInicial = this.readFile(path);
			if(poblacInicial == null) {
				System.out.println("No se ha ingresado un path valido. Vuelva a intentarlo");
			}else{
				this.createGenZero(poblacInicial);
				flagPoblacInic = false;
			}
		}
	}

	public String readFile(String path) {
		try {
			return f.readFile(path);
		} catch (IOException e) {
			return null;
		}
	}

	public void createGenZero(String poblacInicial) {
		Scanner sc = new Scanner(poblacInicial);
		sc.useDelimiter(Pattern.compile(System.getProperty("line.separator")));//separo por espacios cada token
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

	public boolean opcionElegida(String opcion, Scanner sc) {
		if (opcion.equalsIgnoreCase("1")) {
			this.reproduccion();
			this.mutacion(sc);
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

	private void reproduccion() {

		List<Gacela> aSeleccionar = s.getVivasForReproduction();
		List<Gacela> aReproducir = new LinkedList<>();

		int contador = (int) (aSeleccionar.size()*0.8); //necesito agarrar solo el 80%
		if(contador%2 == 1) { //asi me aseguro de que la pareja es par
			contador--;
		}

		for(int i=0; i<contador; i++) {
			aReproducir.add(aSeleccionar.remove(Simulation.getRandomIntBetween(0, aSeleccionar.size() - 1)));
		}
		//ya tengo el 80% aleatorios, por lo que agarro contiguos
		int i1=0;
		int i2=1;
		List<Gacela> generationNueva = new LinkedList<>(); //instanceo la nueva generacion
		
		while(i1 < aReproducir.size() && i2 < aReproducir.size()) {
			if(aReproducir.get(i1).getTipoReproduccion() != 0 && aReproducir.get(i2).getTipoReproduccion() != 0) {//si ninguno es esteril
				createGen(aReproducir.get(i1),aReproducir.get(i2),s.getCountGeneration()+1, generationNueva); //la generacion es la ultima de la actualizada
			}
			i1 = i1 + 2;
			i2 = i2 + 2;
		}
		//Ya tengo todos los nuevos hijos aniadidos a la Lista, que corresponde a la nueva generacion
		Generacion GenNueva = new Generacion(generationNueva, s.getCountGeneration()+1); //aniado con la (countgen + 1) a la ultima actualizada
		s.addPopulation(GenNueva); // y con este metodo se actualiza la poblacion, y countgen++

	}

	private void mutacion(Scanner sc) {
		System.out.println("La reproduccion se llevo a cabo satisfactoriamente.");
		System.out.println("Por favor, ingrese que bases nitrogenadas quiere cambiar de las Gacelas que se enlistan");
		List<Gacela> ultimaGenparaMutar = this.s.getUltimaGenerParaMutac();
		for(Gacela gac : ultimaGenparaMutar) {
			gac.impPant(); //imprime cada Gacela a mutar
		}
		
		String regex = "[CGAT]";
		Pattern p = Pattern.compile(regex);
		String BaseN1; //se elige String antes que char para evitar conflictos por si el usuario decide escribir una frase en vez
		String BaseN2; //de un caracter
		
		boolean flagChequearBaseN1 = true;
		while(flagChequearBaseN1) {
			BaseN1 = sc.next();
			Matcher m = p.matcher(BaseN1);
			if(!m.matches()) {//si No matchea
				System.out.println("No ha ingresado una base nitrogenada correcta. Debe ser C, G, A, o T en mayuscula. Vuelva a intentarlo.");
			}else {
				flagChequearBaseN1 = false;
				System.out.println("Elija la segunda base nitrogenada.");
			}
		}
		boolean flagChequearBaseN2 = true;
		while(flagChequearBaseN2) {
			BaseN2 = sc.next();
			Matcher m = p.matcher(BaseN2);
			if(!m.matches()) {//si No matchea
				System.out.println("No ha ingresado una base nitrogenada correcta. Debe ser C, G, A, o T en mayuscula. Vuelva a intentarlo.");
			}else {
				flagChequearBaseN2 = false;
			}
		}
		
		//se procede a efectuar la mutacion
		for(Gacela gac : ultimaGenparaMutar) {
			char[] chAmutar = gac.getAdn().toCharArray(); //caracteres a mutar
			StringBuilder newADNmutado = new StringBuilder();
			for(char ch : chAmutar) {//para cada caracter chequeo si es una de las Bases Nitrogenada que tengo que cambiar
				
			}
		}
	}
	
	public void createGen(Gacela g1, Gacela g2, int count, List<Gacela> generationNueva) {
		//se chequea externamente antes de llamar a esta funcion si alguno es esteril
		if(g1.getTipoReproduccion() != g2.getTipoReproduccion()) {// si son distintos es que uno es 1 y el otro es 2
			int mid = g1.getLen()/2;
			if(g1.getRandomIntBetween(1, 2) == 2) {
				String adn1 = g1.getAdn().substring(0, mid-1) + g2.getAdn().substring(mid, g1.getLen()-1);
				String adn2 = g2.getAdn().substring(0, mid-1) + g1.getAdn().substring(mid, g1.getLen()-1);
				g1.setADN(adn1);
				g2.setADN(adn2);
				generationNueva.add(g1);
				generationNueva.add(g2);
			}else{
				String adn1 = g1.getAdn().substring(0, mid-1) + g2.getAdn().substring(mid, g1.getLen()-1);
				g1.setADN(adn1);
				generationNueva.add(g1);
			}
			//en este caso o son ambos 1, o son ambos 2
		}else if(g1.getTipoReproduccion() == 2){
			int mid = g1.getLen()/2;
			String adn1 = g1.getAdn().substring(0, mid-1) + g2.getAdn().substring(mid, g1.getLen()-1);
			String adn2 = g2.getAdn().substring(0, mid-1) + g1.getAdn().substring(mid, g1.getLen()-1);
			g1.setADN(adn1);
			g2.setADN(adn2);
			generationNueva.add(g1);
			generationNueva.add(g2);
		}else {
			int mid = g1.getLen()/2;
			String adn1 = g1.getAdn().substring(0, mid-1) + g2.getAdn().substring(mid, g1.getLen()-1);
			g1.setADN(adn1);
			generationNueva.add(g1);
		}
	}
}
