package TPfinal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Generacion {
	private List<Gacela> generation = new LinkedList<>();
	private Integer generationNumber;

	public Generacion(List<Gacela> generac, int count) {
		this.generation = generac;
		this.generationNumber = count;
	}

	//getters
	public int genNumber() { 
		return this.generationNumber;
	}

	public List<Gacela> getListaGacelas() {
		return this.generation;
	}

}
