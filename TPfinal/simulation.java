package TPfinal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class simulation {
	private Map<String,List<gacela>> deathMap = new HashMap<>();
	private Map<Integer,String> secuenciasADN = new HashMap<>();
	
	public simulation(){
	this.secuenciasADN.put(1,"ACGGTAAAC"); //Comida de leones
	this.secuenciasADN.put(2,"AACACGTTG"); //Comida de cocodrilos
	this.secuenciasADN.put(3,"GGCTTATGA"); //Enfermo
	this.secuenciasADN.put(4,"CTCATGTTA"); //Hambruna
	this.secuenciasADN.put(5,"ACTTTACGA"); //Alergia
	this.secuenciasADN.put(6,"CCGATATGT"); //Esteril
	this.secuenciasADN.put(7,"GGTTAAACG"); //1 Hijo
	}
	
	public String secuenciasaInsertar(int num) {
		return secuenciasADN.get(num);
	}
}
