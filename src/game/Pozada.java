package game;

import java.util.Scanner;

public class Pozada extends Terreno {

	public Pozada(int x, int y) {
		super(x, y, 'P');

	}

	public boolean Atender(Jugador jugador) {
		Scanner n = new Scanner(System.in);
		System.out.println("DESEA ENTRAR A LA POZADA POR 10 DE ORO ");
		System.out.println("1. SI");
		System.out.println("2. NO");
		int opcion = App.seleccion(2);

		if (opcion == 1) {
			for (Heroe heroe : jugador.getHeroes()) {
				heroe.vida = 100;
			}
			jugador.setOro(jugador.getOro() - 10);
			
		}
		return true;
	}

}
