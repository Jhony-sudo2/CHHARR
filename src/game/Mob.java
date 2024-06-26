package game;

import java.util.Scanner;

public abstract class Mob extends Entity{
	

	final int dx[]= {1,1,0,-1,-1,-1,0,1};
	final int dy[]= {0,1,1,1,0,-1,-1,-1};
	
	int movimientos;
	int maxMov;
	int damage; 
	int damagerecibido;
	boolean volar;
	Jugador jugador;

	public Mob(int nivel, int vida, int concentracion, int espiritu, int experiencia, int fuerza, int defensa,
			int velocidad, char c) {
		super(nivel, vida, concentracion, espiritu, experiencia, fuerza, defensa, velocidad, c);
		//TODO Auto-generated constructor stub
	}

	
	

	public void CalcularDamage(){

	}

	public void CalcularDamageRecibido(int recibido){
		double resta = recibido * ((double)this.Defensa/100);
		int rec = (int) (recibido - resta);
		this.damagerecibido = rec;
		this.vida = this.vida -this.damagerecibido;
	}


	public int getVida() {
		return vida;
	}

	public int getMovimientos() {
		return movimientos;
	}

	public int getDamage() {
		return damage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void restarMove() {
		this.movimientos=maxMov;
	}
	
	//public abstract boolean noHit();

	public int mover() {
		int CasillaActual = 0;
		Tablero tablero=App.tablero;
		int dir=0;
		Scanner cin = new Scanner(System.in);
							
		System.out.println("En que direccion deseas moverte\nW-arriba\nD-derecha\nS-abajo\nA-izquierda");
		char c=cin.next().charAt(0);
		int k=1;		
		switch(c) {
		 case 'W':dir=6;break;
		 case 'w':dir=6;break;
		 case 'D':dir=0; break;
		 case 'd':dir=0; break;
		 case 'S':dir=2; break;
		 case 's':dir=2; break;
		 case 'A':dir=4; break;
		 case 'a':dir=4; break;
		 default : return 0;
		}
		
		int x=getX()+(dx[dir]*k);
		int y=getY()+(dy[dir]*k);
		
		
		for(int i=1;i<=k;i++) {
			x=getX()+(dx[dir]*i);
			y=getY()+(dy[dir]*i);
			
			System.out.println("Actualmente estoy en "+tablero.map[y][x].getClass().getSimpleName());
			Object tmp = tablero.map[y][x];
			if(tmp instanceof Tienda) {
				Tienda tienda = (Tienda) tmp;
				tienda.Atender(jugador);	
			}else if (tmp instanceof Zona) {
				Zona zona = (Zona) tmp;
				zona.IniciarPelea(jugador);
				if(!jugador.isDerrotado())
					tablero.map[y][x] = new Planicie(x, y);
				else {
					jugador.x = 1;
					jugador.y = 1;
				}
			}else if(tmp instanceof Enemigo){
				Enemigo enemigo = (Enemigo) tmp;
				Manejobatalla tmp2 = new Manejobatalla(jugador, enemigo);
				tmp2.batalla();
				if(!jugador.isDerrotado())
					tablero.map[y][x] = new Planicie(x, y);
				else {
					jugador.x = 1;
					jugador.y = 1;
				}
			}else if(tmp instanceof Pozada){
				Pozada p = (Pozada) tmp;
				p.Atender(jugador);
			}
			
			moveTo(getX()+dx[dir]*(i-1),getY()+(dy[dir]*(i-1)));
			
		}
			
		moveTo(x,y);		
		return CasillaActual;
	}
	
	public void moveTo(int x,int y) {
		if(x==getX()&&y==getY())return;
		Tablero tablero = App.tablero;
		if(overLava)tablero.map[this.y][this.x]=new Zona(this.x,this.y);
		else if(overAgua)tablero.map[this.y][this.x]=new Pozada(this.x,this.y);
		else if(overArbol) tablero.map[this.y][this.x]=new Tienda(this.x,this.y,'T');
		else tablero.map[this.y][this.x]=new Planicie(this.x,this.y);

		overLava = tablero.map[y][x] instanceof Zona;
		overAgua = tablero.map[y][x] instanceof Pozada;
		overArbol = tablero.map[y][x] instanceof Tienda;
		tablero.map[y][x]=this;
		this.x=x;
		this.y=y;		
	}
	
	public boolean canMove(int x,int y) {
		Tablero tablero = App.tablero;
		if(x<0||x>=tablero.width||y<0||y>=tablero.height)return false;
		if(!(tablero.map[y][x] instanceof Planicie))return true;
		return true;
	}
	
}
