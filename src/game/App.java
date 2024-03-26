package game;
import java.util.Random;
import java.util.Scanner;

public class App {
	static Scanner cin = new Scanner(System.in);
	static String nombre;
	static Tablero tablero;
	static Jugador jugador = new Jugador();
	static int oro=0;
	static Heroe ingame[] = new Heroe[2];
	static int mainHero=0;
	static String infoTurno="";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\033[0;42;32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BROWN = "\033[0;42;33m";
	public static final String ANSI_ORANGE = "\033[0;41;33m";
    
	/*GENERANDO LOS ENEMIGOS */
	public static final Enemigo[] enemigos = new Enemigo[9];
	

	public void GenerarEnemigos(){
		enemigos[0] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 1, "DRAGON");
		enemigos[1] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 1, "Cocodrilo");
		enemigos[2] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 1, "PEKKA");
		enemigos[3] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 2, "Thanos");
		enemigos[4] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 2, "Eclipse");
		enemigos[5] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 2, "Satan");
		enemigos[6] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 3, "Cell");
		enemigos[7] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 3, "Madara");
		enemigos[8] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 3, "Fantasmon");

	}

	public static int seleccion(int n) {
		while(true) {
			try {
				int op=entero();
				if(op<0||op>n)throw new Exception();
				return op;
			}
			catch(Exception e) {
				print("Opcion no valida\n");
			}
		}
	}
	
	public App() {
		print("Nombre de la partida\n");
		nombre=cin.nextLine();
		jugador.setNombre(nombre);
		GenerarEnemigos();
		mainMenu();
	}
	
	
	public void mainMenu() {
		while(true) {
			print("1-Jugar\n");
                        print ("2-Reportes");
			print("0-Salir\n");
			int op=seleccion(3);
			switch(op) {
				case 1:iniciarPartida();break;
                                case 2:Reportes();break;
				case 0:return;
			}
		}
	}

	public void demoTablero() {
		seleccionarMapa();
		tablero.generarMounstros(enemigos);
		tablero.ver();
		
	}
        public void Reportes(){
            
        }
	
	public void iniciarPartida() {
		seleccionarMapa();
		if(tablero==null)return;
		print("Todo listo, empezamos\n");
		print("Seleccione unas coordenadas para posicionar el heroe\n");
		print("0-Salir\n");
		print("X: ");
		int X=seleccion(tablero.width);
		print("Y: ");
		int Y=seleccion(tablero.height);
		if(X<1||Y<1) {
			print("Estas posiciones son invaliads\nPara la proxima use posiciones a partir de 1 y menores que el tamanno del mapa");
		}
                
		jugador.x=X-1;
		jugador.y=Y-1;
		tablero.map[Y-1][X-1]=ingame[0];
		
		tablero.generarMounstros(enemigos);
		gameStart();
	}
	// usas esto o la averga ??
	public static void gameStart() {
		int turno=0;
		while(true) {
			infoTurno="Descripcion del turno:\n";
			print("TURNO: "+ turno +"\n");
			//Si el heroe en batalla murio cambiarlo
			if(jugador.isDead()) {
				int other = (mainHero+1)%2;
				Heroe second = ingame[other];
					if(second==null||second.isDead()) {
					loss();
					return;					
				}
				else {
					print("Tu heroe a muerto, se a cambiado a tu otro jugador\n");
					int X=0,Y=0;
					while(true) {
						print("X: ");
						X=seleccion(tablero.width);
						print("Y: ");
						Y=seleccion(tablero.height);
						if(X<1||Y<1) {
							print("Estas posiciones son invaliads\nPara la proxima use posiciones a partir de 1 y menores que el tamanno del mapa");
						}
						else break;
					}
					((Entity)tablero.map[ingame[mainHero].y][ingame[mainHero].x]).die();
					tablero.map[Y-1][X-1]=ingame[mainHero];
					ingame[mainHero].x=X-1;
					ingame[mainHero].y=Y-1;
				}
			}
			if(jugador.isDead()) {
				loss();
				return;
			}
			//hero.restarMove();
			//print(hero.all()+"\n");
			tablero.ver();
			print("1-Mover\n");
			print("0-Terminar\n");
			int op=seleccion(4);
			switch(op) {
				case 1:jugador.mover();break;
				case 0:return;
			}
			turno++;
		}
	}

	//hasta aca
	public static void loss() {
		System.out.println(App.ANSI_RED+"__________________________"+App.ANSI_RESET);
		System.out.println(App.ANSI_RED+"\\Has perdido esta partida/"+App.ANSI_RESET);
		System.out.println(App.ANSI_RED+" \\______________________/"+App.ANSI_RESET);
	}
	
	public static void win() {
		oro+=250;
		System.out.println(App.ANSI_GREEN+"_________________________"+App.ANSI_RESET);
		System.out.println(App.ANSI_GREEN+"\\Has GANADO esta partida/"+App.ANSI_RESET);
		System.out.println(App.ANSI_GREEN+" \\_____________________/"+App.ANSI_RESET);
	}
	
	public static void seleccionarMapa() {
		Random n = new Random();
		int lenght = n.nextInt(3);
		switch(lenght) {
			case 0:tablero = new Tablero(7);break;
			case 1:tablero = new Tablero(10);break;
			case 2:tablero = new Tablero(12);break;
			default: tablero=null; break;
		}
	}

	public static int entero() {
		while(true) {
			try {
				int n=Integer.parseInt(cin.next());
				return n;
			}
			catch(Exception e) {
				print("Opcion no valida\n");
			}
		}
	}
 
	public static void print(String txt) {
		System.out.print(txt);
	}
	
	public static void main(String args[]) {	    
		new App();
		System.exit(0);
	}

}
