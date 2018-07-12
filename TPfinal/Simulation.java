package TPfinal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Simulation {
	//la principal estructura lo da la union de todas las generaciones que forman la poblacion viva
	private List<Generacion> population = new LinkedList<>();
	//Mapa de causa de muerte a las gacelas que murieron, por lo que contiene a la poblacion muerta
	private Map<String,List<Gacela>> deathMap = new HashMap<>();

	//estructuras secundarias
	private int countGeneration = 0; //cuento la correspondencia de las generaciones
	private Map<Integer,String> secuenciasADN = new HashMap<>(); //numero a secuencia de ADN que da tal propiedad
	private Map<Integer,String> secuenciasADNSignificados = new HashMap<>();//numero a significado de las causas de muerte


	//constructor
	public Simulation(){
		inicializarSecuenciasADN();
		inicializarDeathMap();
	}

	//getters
	public String getSecuenciaADN(int num) {
		return secuenciasADN.get(num);
	}
	
	public String getSecuenciaADNSignificados(int num){
		return secuenciasADNSignificados.get(num);
	}

	public int getCountGeneration() {
		return countGeneration;
	}

	public List<Gacela> getVivas() {
		List<Gacela> ans = new LinkedList<>();
		for(Generacion gen : this.population) {
			for(Gacela g : gen.getListaGacelas()) {
				ans.add(g);
			}
		}
		return ans;
	}

	public List<Gacela> getVivasForReproduction(){
		int len = this.population.size() - 1;
		List<Gacela> ans = new LinkedList<>();

		for(Gacela g : (this.population.get(len)).getListaGacelas()) {
			ans.add(g);
		}

		if(len>0) {
			for(Gacela g : (this.population.get(len - 1)).getListaGacelas()) {
				ans.add(g);
			}
		}
		return ans;
	}

	public List<Generacion> getGeneraciones(){
		return this.population;
	}
	
	
	//setters
	
	public List<Gacela> getUltimaGenerParaMutacoVejez(float porcentaje){
		List<Gacela> ans = new LinkedList<>();
		//countGeneration empieza desde uno por lo que No comparte index de la lista (es mayor +1)
		List<Gacela> ultimaGen = this.population.get(this.countGeneration-1).getListaGacelas(); //apunta a la lista de Gacelas de la ultima gen
		
		int contador = (int) (ultimaGen.size()*porcentaje); //necesito agarrar solo el 50% caso Mutac
		for(int i=0; i<contador; i++) {
			ans.add(ultimaGen.remove(getRandomIntBetween(0, ultimaGen.size() - 1)));
			//sortea un elemento (se toma aleatoriamente) y lo saca para ponerlo en ans, hasta tener el 50% deseado
		}
		return ans; 
	}
	
	public List<Gacela> getTodasMenosUltimaGener(float porcentaje){
		List<Gacela> ans = new LinkedList<>();
		for(int i=0; i<this.countGeneration-1; i++) { //itera en todas menos la ultima
			List<Gacela> Gen = this.population.get(i).getListaGacelas();
			
			int contador = (int) (Gen.size()*porcentaje); //necesito agarrar solo el 10% caso Vejez
			for(int i2=0; i2<contador; i2++) {
				ans.add(Gen.remove(getRandomIntBetween(0, Gen.size() - 1)));
				//asi el 10% al azar de cada generacion muere por causa vejez
			}
		}
		return ans;
	}
	
	public void addUltimaGener(Gacela gac){
		List<Gacela> ultimaGen = this.population.get(this.countGeneration-1).getListaGacelas(); //apunta a la lista de Gacelas de la ultima gen
		ultimaGen.add(gac);
	}

	public void addPopulation(Generacion gen) {
		this.population.add(gen);
		countGeneration++;
	}
	
	public void addDeathMap(String causaMuerte, Gacela gac){
		this.deathMap.get(causaMuerte).add(gac); //actualizo el mapa
	}
	
	private void inicializarDeathMap(){
		List<Gacela> Laux1 = new LinkedList<>();
		List<Gacela> Laux2 = new LinkedList<>();
		List<Gacela> Laux3 = new LinkedList<>();
		List<Gacela> Laux4 = new LinkedList<>();
		List<Gacela> Laux5 = new LinkedList<>();
		List<Gacela> Laux6 = new LinkedList<>();
		this.deathMap.put("Comida de leones", Laux1);
		this.deathMap.put("Comida de cocodrilos", Laux2);
		this.deathMap.put("Enfermo", Laux3);
		this.deathMap.put("Hambruna", Laux4);
		this.deathMap.put("Alergia", Laux5);
		this.deathMap.put("Vejez", Laux6);
	}

	private void inicializarSecuenciasADN() {
		//secuencias de muerte
		this.secuenciasADN.put(1,"ACGGTAAAC"); //Comida de leones
		this.secuenciasADN.put(2,"AACACGTTG"); //Comida de cocodrilos
		this.secuenciasADN.put(3,"GGCTTATGA"); //Enfermo
		this.secuenciasADN.put(4,"CTCATGTTA"); //Hambruna
		this.secuenciasADN.put(5,"ACTTTACGA"); //Alergia
		//secuencias de reproduccion
		this.secuenciasADN.put(6,"CCGATATGT"); //Esteril
		this.secuenciasADN.put(7,"GGTTAAACG"); //1 Hijo
		
		//significados de las causas de muerte (mayor comodidad para citar las causas)
		this.secuenciasADNSignificados.put(1, "Comida de leones");
		this.secuenciasADNSignificados.put(2, "Comida de cocodrilos");
		this.secuenciasADNSignificados.put(3, "Enfermo");
		this.secuenciasADNSignificados.put(4, "Hambruna");
		this.secuenciasADNSignificados.put(5, "Alergia");
		this.secuenciasADNSignificados.put(6, "Vejez"); //solo el 10% de las gacelas en cada simulac conoce este destino :(
	}

	public static int getRandomIntBetween(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException();
		}
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}

}
