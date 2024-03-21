package game;

public class Pozada extends Terreno {

	public Pozada(int x, int y) {
		super(x, y, 'P');
		
	}

	public boolean Atender(int Oro,Heroe[] heroes){
		for (Heroe heroe : heroes) {
			heroe.vida = 100;
		}
		Oro = Oro - 10;
		return true;
	}

}
