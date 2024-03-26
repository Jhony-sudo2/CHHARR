package game;

import java.util.Random;
import java.util.Scanner;

public class Tablero {
	final int dx[]= {1,1,0,-1,-1,-1,0,1};
	final int dy[]= {0,1,1,1,0,-1,-1,-1};
	
	int width=8;
	int height=8;
	Object map[][];
	int planicie=0;
	int porPlanicie=60;
	int porTienda=25;
	int porCiudad=10;
	int porZona=5;
	//int dificultad=0;

	public Tablero(int T) {
		App.enemy.clear();
		if(T==7) 
			width=height=7;
		else if(T==10) 
			width=height=10;	
		else if(T==12) 
			width=height=12;

		porPlanicie=70;
		porTienda=3;
		porCiudad=5;
		porZona=5;

		generar();
	}

	public Tablero(int width, int height, int porPlanicie, int porArbol, int porAgua, int porLava) {
		App.enemy.clear();
		this.width = width;
		this.height = height;
		this.porPlanicie = porPlanicie;
		this.porTienda = porArbol;
		this.porCiudad = porAgua;
		this.porZona = porLava;
		generar();
	}
	
	
	public void generar() {
		map = new Object[height][width];
		App.tablero=this;
		Random rand = new Random();
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				int n=rand.nextInt(porTienda+porCiudad+porZona+porPlanicie);
				if(n<porTienda)map[i][j]=new Tienda(i,j,'T');
				else if(n>=porTienda&&n<porTienda+porCiudad) {
					if(i==0||i==height-1||j==0||j==width-1) {
						map[i][j]=new Pozada(j,i);
						continue;
					}
					boolean si=false;
					int arr[]= {0,2,4,6};
					for(int k=0;k<4;k++) {
						int di=i+dy[k];
						int dj=j+dx[k];
						if(map[di][dj] instanceof Pozada) {
							map[i][j] = new Pozada(j,i);
							si = true;
						}
						if(!si) {
							map[i][j] = new Planicie(j,i);
							planicie++;
						}
					}
				}
				else if(n>=porTienda+porCiudad&&n<porTienda+porCiudad+porZona)map[i][j]=new Zona(j,i);
				else map[i][j]=new Planicie(j,i);
				if(map[i][j] instanceof Entity) {				
					((Entity)map[i][j]).x=j;
					((Entity)map[i][j]).y=i;
				}
			}
		}
							
		
	}
	
	public String selectColor(Object o) {
		if(o instanceof Enemigo) {
			return App.ANSI_RED;
		}
		else if(o instanceof Heroe) {
			return App.ANSI_PURPLE;
		}
		else if(o instanceof Pozada) {
			return App.ANSI_BLUE;
		}
		else if(o instanceof Tienda) {
			return App.ANSI_PURPLE;
		}
        else if(o instanceof Zona){
			Zona tmp = (Zona)o;
			if (tmp.getNivel() == 1)
                return App.ANSI_GREEN;
			else
				return App.ANSI_ORANGE;
        }
		else return App.ANSI_WHITE;
	}
	
	public void generarVista() {
		System.out.print("  ");
                String color;
		for(int i=0;i<width;i++) 
			System.out.print(" "+(i+1)+" ");
		System.out.println();
		for(int i=0;i<height;i++) {
			System.out.print((i+1)+" ");
			for(int j=0;j<width;j++) {
                color=selectColor(map[i][j]);
				System.out.print(" "+color+map[i][j]+App.ANSI_RESET+" ");
			}
			System.out.println();
		}
	}
	
	public void generarMounstros(Enemigo[] listaEnemigos) {
		int can=1+1;
		Random rand = new Random();
		
		while(can>0) {
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					if(can==0)return;
					if(map[i][j] instanceof Planicie) {
						if(rand.nextInt(101)<20) {
							int t=rand.nextInt(9);
							Enemigo e=null;
							Enemigo tmpEnemigo = listaEnemigos[t];
							e = new Enemigo(tmpEnemigo.nivel, tmpEnemigo.vida, tmpEnemigo.concentracion, tmpEnemigo.espiritu, tmpEnemigo.Fuerza, tmpEnemigo.Fuerza, tmpEnemigo.Velocidad, tmpEnemigo.c, tmpEnemigo.getTipo(), tmpEnemigo.getNombre());
							e.x=j;
							e.y=i;
							map[i][j]=e;	
							App.enemy.add(e);
							can--;
						}
					}
				}
			}
		}
	}
			
	public void ver() {
		generarVista();
	}

}
