package TPfinal;


public class Gacela{
	//sizes
	final private int len = 50;
	final private int sizeGen = 9;
	//main structure
	private String adn;
	private int tipoReproduccion = 2; //2 hijos, 1 hijo , o esteril (0 hijos)
	private boolean porMorir = false; //true si muere por un gen de muerte
	private String causaDeMuerte;
	private boolean vejez = false; //solo el 10% va a tener asignado true esta propiedad
	//estructuras de ayuda
	private Simulation sim = new Simulation();

	//constructor
	//si el boolean es true:
	//generacion aleatoria + gen de los listados en tabla1
	public Gacela(boolean flag){
		if(flag) {
			this.adn = adnAleatorio();
			this.actualizarSegunGenes();
		}
	}


	//getters
	public String getAdn() {
		return this.adn;
	}
	public boolean porMorir() {
		return this.porMorir;
	}

	public int getTipoReproduccion() {
		return this.tipoReproduccion;
	}

	public int getLen() {
		return this.len;
	}

	//setters
	public void setADN(String newADN){
		this.adn = newADN;
		this.actualizarSegunGenes();
	}

	private void actualizarSegunGenes() {
		//reviso los genes de Muerte
		boolean flagMuerte = false; //se mantiene falso si no tiene gen muerte
		for(int num = 1; num<6;num++) {
			if((this.adn.indexOf(sim.getSecuenciaADN(num)) != -1) && !flagMuerte) { //chequeo si hay gen de muerte
				flagMuerte = true;
				this.causaDeMuerte = sim.getSecuenciaADNSignificados(num); 
				//se le da la causa de muerte de la primer causa de muerte encontrada (en el caso de haber varias)
			}
		}
		if(flagMuerte) { //si es true es que hay gen de muerte
			this.porMorir = true; //entonces cambia el estado a true
		}else {
			this.porMorir = false; //caso contrario no hay gen de muerte
		}
		//reviso los genes de reproduccion
		if(this.adn.indexOf(sim.getSecuenciaADN(6)) != -1) { //primero reviso si es esteril
			this.tipoReproduccion = 0; //entonces puede tener cero hijos (es decir, no puede tener hijos)
		}
		else if(this.adn.indexOf(sim.getSecuenciaADN(7)) != -1) { //si solo puede tener un hijo Y no hay gen de muerte
			this.tipoReproduccion = 1;
		}else {
			this.tipoReproduccion = 2; //y si no hay gen esteril o un hijo, se deja por default dos hijos
		}

	}

	private void oldDeathChance(){
		if(this.getRandomIntBetween(0, 9) == 5) { //son diez posibilidades, la distribuc es uniforme, solo hay 10% de probab
			this.vejez = true;
		}
	}

	public void impPant(){
		System.out.println(this.adn);
	}

	public int getRandomIntBetween(int min, int max) {
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
