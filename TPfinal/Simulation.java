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
	private Map<Integer,String> secuenciasADN = new HashMap<>();
	
	
	//constructor
	public Simulation(){
		inicializarSecuenciasADN();
	}
	
	//getters
	public String getSecuenciaADN(int num) {
		return secuenciasADN.get(num);
	}
	
	public void getVivas() {
	}
	
	//setters
	
	public void addPopulation(Generacion gen) {
		this.population.add(gen);
		countGeneration++;
	}
	
	private void inicializarSecuenciasADN() {
		this.secuenciasADN.put(1,"ACGGTAAAC"); //Comida de leones
		this.secuenciasADN.put(2,"AACACGTTG"); //Comida de cocodrilos
		this.secuenciasADN.put(3,"GGCTTATGA"); //Enfermo
		this.secuenciasADN.put(4,"CTCATGTTA"); //Hambruna
		this.secuenciasADN.put(5,"ACTTTACGA"); //Alergia
		this.secuenciasADN.put(6,"CCGATATGT"); //Esteril
		this.secuenciasADN.put(7,"GGTTAAACG"); //1 Hijo
	}
}
