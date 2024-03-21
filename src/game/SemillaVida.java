package game;

public class SemillaVida extends Objeto {

	public SemillaVida() {
		super("Semilla de la Vida",50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void usar(Heroe e) {
		if(e.vida<=0) {
			e.vida=e.vidaMaxima;
			System.out.println("Se ha revivido al heroe");
		}
		
		else System.out.println("No ha tenido ningun efecto");
	}

}
