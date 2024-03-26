package game;

public abstract class Entity extends java.lang.Object{
	int nivel;
	int vida;
	int concentracion;
	int espiritu;
	int experiencia;
	int Fuerza;
	int Defensa;
	int Velocidad;
	int vidaMaxima;

	int x;
	int y;
	char c;
	String color="";
	boolean overLava=false;
	boolean overAgua=false;
	boolean overArbol=false;

	
	public Entity(int nivel, int vida, int concentracion, int espiritu, int experiencia, int fuerza, int defensa,
			int velocidad,char c) {
                this.c=c;
		this.nivel = nivel;
		this.vida = vida;
		this.concentracion = concentracion;
		this.espiritu = espiritu;
		this.experiencia = experiencia;
		Fuerza = fuerza;
		Defensa = defensa;
		Velocidad = velocidad;
	}
	
	public void die() {
		if(!isDead())return;
		App.tablero.map[y][x]=null;
		if(overLava)App.tablero.map[y][x] = new Zona(x,y);
		else if(overAgua)App.tablero.map[y][x] = new Pozada(x,y);
		else if(overArbol) {
			App.tablero.map[y][x] = new Tienda(x,y,'t');
			((Entity)App.tablero.map[y][x]).x=x;
			((Entity)App.tablero.map[y][x]).y=y;
		}
		else App.tablero.map[y][x] = new Planicie(x,y);
	}
	
	public String info() {
		return color+getClass().getSimpleName()+App.ANSI_RESET+": "+vida+"/"+vidaMaxima+"["+(x+1)+","+(y+1)+"]\n";
	}

	public boolean isDead() {
		return this.vida<=0;
	}
	
	public String toString() {
		return ""+c;
	}
}
