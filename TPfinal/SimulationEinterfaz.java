package TPfinal;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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

	public boolean calcularPoblacInic(Scanner sc, boolean flagPoblacInic) { //no pasa copia del scanner, como es no primitivo pasa referencia
		while(flagPoblacInic) {
			String path = sc.next();
			String poblacInicial = this.readFile(path);
			if(poblacInicial == null) {
				System.out.println("No se ha ingresado un path valido. Vuelva a intentarlo");
			}else{
				this.createGenZero(poblacInicial);
				flagPoblacInic = false;
				return flagPoblacInic;
			}
		}
		return flagPoblacInic;
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
			this.muerte();
			return true;
		}else if(opcion.equalsIgnoreCase("2")) {
			for(Generacion gener : s.getGeneraciones()) {
				System.out.println("Generacion: " + gener.genNumber());
				for(Gacela g : gener.getListaGacelas()) {
					System.out.println(g.getAdn());
				}
			}
			return true;
		}else if(opcion.equalsIgnoreCase("3")) {
			return true;
		}else if(opcion.equalsIgnoreCase("4")) {
			System.out.println("Esta seguro? Recuerde que se eliminaran todos los datos generados dentro de la Simulacion");
			System.out.println("Ingrese OK si esta seguro o cualquier cosa si no lo esta");
			if(sc.next().equalsIgnoreCase("OK")) {
				return false;
			}else {
				return true;
			}
			
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
		List<Gacela> ultimaGenparaMutar = this.s.getUltimaGenerParaMutacoVejez((float) 0.5);
		for(Gacela gac : ultimaGenparaMutar) {
			System.out.println(gac.getAdn()); //imprime cada Gacela a mutar
		}

		String regex = "[CGAT]";
		Pattern p = Pattern.compile(regex);
		String BaseN1 = new String(); //se elige String antes que char para evitar conflictos por si el usuario decide escribir una frase en vez
		String BaseN2 = new String(); //de un caracter

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
			}else if(BaseN1 == BaseN2){
				System.out.println("Se eligio la misma base nitrogenada como segunda opcion.");
				System.out.println("Esto no provocaria mutacion alguna. Si esta seguro ingrese OK, o cualquier cosa si no lo esta.");
				if(sc.next().equalsIgnoreCase("OK")) {
					flagChequearBaseN2 = false;
				}else { System.out.println("Ingrese nuevamente la segunda base nitrogenada a modificar");}
			}else {
				flagChequearBaseN2 = false; //es decir, se ingresaron dos bases nitrogenadas distintas
			}
		}

		if(BaseN1 != BaseN2) {
			//se procede a efectuar la mutacion
			for(Gacela gac : ultimaGenparaMutar) {
				String chAmutar = gac.getAdn(); //caracteres a mutar
				StringBuilder newADNmutado = new StringBuilder();

				for(int n = 0; n<chAmutar.length(); n++) {//para cada caracter produzco la mutacion si es una de las Bases Nitrogenada que tengo que cambiar
					if(chAmutar.charAt(n) == BaseN1.charAt(0)) {
						newADNmutado.append(BaseN2.charAt(0)); //si es el caracter Base Nitrogenada 1 se cambia a la 2
					}else if(chAmutar.charAt(n) == BaseN2.charAt(0)) {
						newADNmutado.append(BaseN1.charAt(0)); //si es el caracter Base Nitrogenada 2 se cambia a la 1
					}else {
						newADNmutado.append(chAmutar.charAt(n)); //si no es uno de las bases buscadas No se cambia
					}
				}
				gac.setADN(newADNmutado.toString());
				//Luego de la mutacion, se agregan las gacelas mutadas devuelta a la ultima generacion
				this.s.addUltimaGener(gac);
			}
		}
	}

	private void muerte(){
		List<Gacela> GenMuertePorVejez = this.s.getTodasMenosUltimaGener((float) 0.1); //remueve 10% de las gacelas
		for(Gacela gac : GenMuertePorVejez) {
			gac.setMuertePorVejez(); //cambia a true el status de muerte por vejez
			s.addDeathMap(gac.getCausaDeMuerte(), gac); //agrega al death map
		}
		//listo vejez, ahora la ultima generacion puede morir por mutaciones
		List<Gacela> ultimaGenMuerePorMutac = this.s.getGeneraciones().get(s.getCountGeneration()-1).getListaGacelas();
		//asi obtengo la lista de gacelas de la ultima generacion
		for(Gacela gac : ultimaGenMuerePorMutac) {
			if(gac.porMorir()) { //solo si tiene gen de muerte se muere por cualquiera de los genes de muerte que llegue a contener
				s.addDeathMap( gac.getCausaDeMuerte(),gac);
				ultimaGenMuerePorMutac.remove(gac); //y se elimina de la lista
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
