package game;

public class ElixirVerde extends Objeto {

	public ElixirVerde() {
		super("Elixir Verde",25);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void usar(Heroe e) {
		if(e.isDead()) {
			App.print("No tendra efecto");
			return;
		}
		int ahora=e.vida;
		e.vida+=50;
		e.vida=Math.min(e.vida, e.vidaMaxima);
		if(e.vida==ahora)System.out.println("No ha tenido nigun efecto");
		else System.out.println("El heroe a recuperado "+(e.vida-ahora)+" puntos de salud");
	}

}
