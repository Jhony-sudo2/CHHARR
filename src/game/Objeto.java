package game;

import java.util.Random;

public class Objeto extends Item {

	private int Tipo;

	public Objeto(String nombre, int precio, int tipo) {
		super(precio, nombre);
		this.Tipo = tipo;
	}

	@Override
	public void Usar(Heroe jugador) {
		Random n = new Random();
		switch (Tipo) {
			case 1:
				jugador.vida += n.nextInt(10, 30);
				break;
			case 2:
				jugador.vida += n.nextInt(20, 60);
				break;

			case 3:
				jugador.vida += n.nextInt(10, 25);
				break;

			/*DESPUES */
			case 4:
				int aumento = n.nextInt(10, 20);
				jugador.Velocidad += aumento;
				break;

			case 5:
				jugador.Velocidad += n.nextInt(10, 20);
				break;

			case 6:
				jugador.Velocidad -= n.nextInt(10, 20);
				break;

			default:
				break;
		}
	}

}
