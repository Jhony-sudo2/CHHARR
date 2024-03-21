package game;

import java.util.Scanner;

public abstract class Mob extends Entity{
	

	final int dx[]= {1,1,0,-1,-1,-1,0,1};
	final int dy[]= {0,1,1,1,0,-1,-1,-1};
	
	int movimientos;
	int maxMov;
	int damage; 
	boolean volar;

	public Mob(int nivel, int vida, int concentracion, int espiritu, int experiencia, int fuerza, int defensa,
			int velocidad, char c) {
		super(nivel, vida, concentracion, espiritu, experiencia, fuerza, defensa, velocidad, c);
		//TODO Auto-generated constructor stub
	}
	

	public void CalcularDamage(){

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
	
	public abstract boolean atacar();
	
	public abstract boolean noHit();
	
	public void hit(Entity m) {
		if(noHit()) {
			App.infoTurno+=info()+"ha fallado el ataque contra "+m.info()+"\n";
			return;
		}
		m.vida-=this.damage;
		App.infoTurno+=m.info()+"ha recibido "+this.damage+" de danno de "+info();
		m.die();
	}

	public int mover() {
		int CasillaActual = 0;
		Tablero tablero=App.tablero;
		int dir=0;
		Scanner cin = new Scanner(System.in);
							
		System.out.println("En que direccion deseas moverte\nU-arriba\nR-derecha\nD-abajo\nL-izquierda");
		char c=cin.next().charAt(0);
		int k=1;		
		switch(c) {
		 case 'U':dir=6;break;
		 case 'u':dir=6;break;
		 case 'R':dir=0; break;
		 case 'r':dir=0; break;
		 case 'D':dir=2; break;
		 case 'd':dir=2; break;
		 case 'L':dir=4; break;
		 case 'l':dir=4; break;
		 default : return 0;
		}
		
		int x=getX()+(dx[dir]*k);
		int y=getY()+(dy[dir]*k);
		
		
		for(int i=1;i<=k;i++) {
			x=getX()+(dx[dir]*i);
			y=getY()+(dy[dir]*i);
			
			App.print("Actualmente estoy en "+tablero.map[y][x].getClass().getSimpleName());
			Object tmp = tablero.map[y][x];
			if(tablero.map[y][x] instanceof Tienda) {
				Tienda tienda = (Tienda) tmp;
				tienda.Atender();	
			}
			else if(tablero.map[y][x] instanceof Tienda) CasillaActual = 1;

			moveTo(getX()+dx[dir]*(i-1),getY()+(dy[dir]*(i-1)));
			
		}
			
		
		moveTo(x,y);		
		return CasillaActual;
	}
	
	public void moveTo(int x,int y) {
		if(x==getX()&&y==getY())return;
		App.infoTurno+=this.info()+"se ha movida a la posicion ["+(x+1)+","+(y+1)+"]\n";
		Tablero tablero = App.tablero;
		if(overLava)tablero.map[this.y][this.x]=new Zona(this.x,this.y);
		else if(overAgua)tablero.map[this.y][this.x]=new Pozada(this.x,this.y);
		else if(overArbol) {
			tablero.map[this.y][this.x]=new Tienda(this.x,this.y,'T');
			((Entity)tablero.map[this.y][this.x]).x=this.x;
			((Entity)tablero.map[this.y][this.x]).y=this.y;
		}
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
