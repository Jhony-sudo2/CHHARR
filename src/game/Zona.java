package game;

import java.util.Random;

public class Zona extends Terreno {
	private int Nivel =0;
	private Enemigo[] Lenemigos = new Enemigo[4];
	Random n = new Random();
		
	public Zona(int x, int y) {
		super(x, y,'+');
		this.Nivel = n.nextInt(2) + 1;
		LlenarEnemigos();
		// TODO Auto-generated constructor stub
	}

	public void LlenarEnemigos(){
		int numero = 0;
		for (int i = 0; i < Lenemigos.length; i++) {
			numero = n.nextInt(8)+1;
			Lenemigos[i] = App.enemigos[numero];
		}
		
	}

	public boolean IniciarPelea(Jugador jugador){
		Manejobatalla tmp = new Manejobatalla(jugador, Lenemigos);
		tmp.batalla();
		return true;
	}

	public int getNivel() {
		return Nivel;
	}

	public void setNivel(int nivel) {
		Nivel = nivel;
	}

	

}
