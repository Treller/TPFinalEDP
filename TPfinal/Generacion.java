package TPfinal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generacion {
	private Map<Integer, List<Gacela>> generation = new HashMap<>();
	
	public Generacion(List<Gacela> generac, int count) {
		this.generation.put(count, generac);
	}
	
}
