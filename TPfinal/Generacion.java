package TPfinal;


import java.util.LinkedList;
import java.util.List;

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
