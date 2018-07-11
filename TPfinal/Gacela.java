package TPfinal;


public class Gacela{
	//sizes
	final private int len = 50;
	final private int sizeGen = 9;
	//main structure
	private String adn;
	private int tipoReproduccion = 2; //2 hijos, 1 hijo , o esteril (0 hijos)
	private boolean porMorir = false;

	//constructor1
	//si el boolean es true:
	//generacion aleatoria + gen de los listados en tabla1
	public Gacela(boolean flag){
		if(flag) {
			this.adn = adnAleatorio();
		}
	}
	
	//constructor2
	//de reproduccion, depende del int tipoReproduccion
	public Gacela(Gacela g1, Gacela g2) {
		if(g1.tipoReproduccion == 0 || g2.tipoReproduccion == 0) { //si alguno es esteril, no se reproducen
			//no se reproducen
		}
		else if(g1.tipoReproduccion != g2.tipoReproduccion) {// si son distintos es que uno es 1 y el otro es 2
			
		}else {
			//en este caso o son ambos 1, o son ambos 2
		}
	}

	//getters
	public String getAdn() {
		return this.adn;
	}
	public boolean porMorir() {
		return this.porMorir;
	}
	
	public int tipoReproduccion() {
		return this.tipoReproduccion;
	}

	//setters
	public void setADN(String newADN){
		if(this.adn == null) {
			this.adn = newADN;
		}
	}
	
	
	
	public static int getRandomIntBetween(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException();
		}
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}

	private String adnAleatorio() {
		
		//creacion de ADN aleatorio
		StringBuilder newADN = new StringBuilder();
		char[] subset = "CGAT".toCharArray();//genero bases nitrogenadas aleatorias

		char buf[] = new char[this.len];
		for (int i=0;i<buf.length;i++) {
			int index = getRandomIntBetween(0, subset.length-1);
			buf[i] = subset[index];
		}
		newADN.append(buf);

		//Y esto inserta un Gen de cualquiera de los de la Tabla 1 en cualquier lugar
		Simulation sim = new Simulation();
		String gen = sim.getSecuenciaADN(getRandomIntBetween(1, 7)); //asi obtengo un gen aleatorio
		int desde = getRandomIntBetween(0, this.len - this.sizeGen); //numero aletorio donde entra un gen
		//System.out.println(desde);
		int indexGen = 0;
		for(int i = desde; i < desde + this.sizeGen; i++) {
			newADN.setCharAt(i, gen.charAt(indexGen));
			indexGen++;
		}

		return newADN.toString();
	}

}
