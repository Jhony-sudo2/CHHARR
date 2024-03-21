package game;

import java.util.Random;

public class Enemigo extends Mob {
	private int Tipo;
	private String Nombre;

	public Enemigo(int nivel, int vida, int concentracion, int espiritu, int fuerza, int defensa,
			int velocidad, char c,int tipo,String Nombre) {
		super(nivel, vida, concentracion, espiritu,0, fuerza, defensa, velocidad, c);
		this.Tipo = tipo;
		this.Nombre = Nombre;
		this.color=App.ANSI_RED;
		//TODO Auto-generated constructor stub
	}

	int vision;
	
	//NO SIRVE
	@Override
	public int mover() {
		
		if(maxMov==0)return 0;
		Tablero tablero=App.tablero;
		int dir=0;
		int arr[]= {0,2,4,6};
		Random rand = new Random();
		dir = arr[rand.nextInt(4)];
		int k=rand.nextInt((maxMov+1));
		int x=getX()+(dx[dir]*k);
		int y=getY()+(dy[dir]*k);
		
		if(!canMove(x,y))return 0;
		moveTo(x,y);
		
		return 1;
	}
	
	@Override
	public boolean noHit() {
		Random rand = new Random();
		int n=1+ rand.nextInt(100);
		return n>60;
	}


	//NO SIRVE ALV
	public void perseguir() {
		if(!inRange())return;
		int rx=x,ry=y;
		int dmin=App.tablero.width*App.tablero.height;
		int arr[]= {0,2,4,6};
		for(int i=0;i<4;i++) {
			int dir=arr[i];
			for(int j=1;j<=maxMov;j++) {
				int x=this.x+(dx[dir]*j);
				int y=this.y+(dy[dir]*j);
				int d=distanciaManhatanToHero(x,y);
				if(!canMove(x,y))continue;				
				if(d==0)continue;
				if(d<dmin) {
					rx=x;
					ry=y;
					dmin=d;
				}
			}
		}
		moveTo(rx,ry);
	}
	

	//NO SIRVE
	public int distanciaManhatanToHero(int x,int y) {
		Heroe e = App.ingame[App.mainHero];
		return Math.abs(e.x-x)+Math.abs(e.y-y);
	}
	
	//NO SIRVE
	public boolean inRange() {		
		return distanciaManhatanToHero(x,y)<=vision;
	}

	@Override
	public boolean atacar() {
		System.out.println("ESTOY ATACANDO ");
		return true;
	}
	
}