package game;


import java.util.Random;

public class Ciudad extends Terreno{
        private int Nivel =0;
	private Enemigo[] Lenemigos = new Enemigo[4];
	Random n = new Random();
		
	public Ciudad(int x, int y) {
		super(x, y,'C');
		this.Nivel = n.nextInt(2) + 1;
		LlenarEnemigos();
		// TODO Auto-generated constructor stub
	}

	public void LlenarEnemigos(){
		int numero = 0;
		for (int i = 0; i < Lenemigos.length; i++) {
			numero = n.nextInt(8)+1;
			Enemigo tmp  = App.enemigos[numero];
			Lenemigos[i] = new Enemigo(tmp.nivel, tmp.vida, tmp.concentracion, tmp.espiritu, tmp.Fuerza, tmp.Fuerza, tmp.Velocidad, tmp.c, tmp.getTipo(), tmp.getNombre());
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
