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
	
	public List<Gacela> getUltimaGenerParaMutac(){
		List<Gacela> ans = new LinkedList<>();
		//countGeneration empieza desde cero por lo que comparte index de la lista
		List<Gacela> ultimaGen = this.population.get(this.countGeneration).getListaGacelas(); //apunta a la lista de Gacelas de la ultima gen
		
		int contador = (int) (ultimaGen.size()*0.5); //necesito agarrar solo el 50%
		for(int i=0; i<contador; i++) {
			ans.add(ultimaGen.remove(getRandomIntBetween(0, ultimaGen.size() - 1)));
			//sortea un elemento (se toma aleatoriamente) y lo saca para ponerlo en ans, hasta tener el 50% deseado
		}
		return ans; 
	}
	
	//setters

	public void addPopulation(Generacion gen) {
		this.population.add(gen);
		countGeneration++;
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
		
		//significados de las causas de muerte (mayor comodidad para citar las causas
		this.secuenciasADNSignificados.put(1, "Comida de leones");
		this.secuenciasADNSignificados.put(2, "Comida de cocodrilos");
		this.secuenciasADNSignificados.put(3, "Enfermo");
		this.secuenciasADNSignificados.put(4, "Hambruna");
		this.secuenciasADNSignificados.put(5, "Alergia");
	}

	public static int getRandomIntBetween(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException();
		}
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}

}
