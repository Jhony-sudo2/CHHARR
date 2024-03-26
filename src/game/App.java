package game;
import java.util.Random;
import java.util.Scanner;

public class App {
	static Scanner cin = new Scanner(System.in);
	static String nombre;
	static Tablero tablero;
	static Jugador jugador = new Jugador();
	static int oro=0;
        private static int vecesTerminado = 0; // Contador de veces que se ha terminado el juego
	static Heroe ingame[] = new Heroe[2];
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\033[0;42;32m";
	public static final String ANSI_YELLOW = "\033[0;43;33m";
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
		enemigos[0] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'E', 1, "DRAGON");
		enemigos[1] = new Enemigo(2, 100, 25, 15, 25, 25, 25, 'E', 1, "Cocodrilo");
		enemigos[2] = new Enemigo(3, 100, 30, 20, 20, 25, 30, 'E', 1, "PEKKA");
		enemigos[3] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'E', 2, "Thanos");
		enemigos[4] = new Enemigo(2, 100, 25, 15, 25, 25, 25, 'E', 2, "Eclipse");
		enemigos[5] = new Enemigo(3, 100, 30, 20, 30, 30, 30, 'E', 2, "Satan");
		enemigos[6] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'E', 3, "Cell");
		enemigos[7] = new Enemigo(2, 100, 25, 15, 25, 25, 25, 'E', 3, "Madara");
		enemigos[8] = new Enemigo(3, 100, 30, 20, 30, 30, 30, 'E', 3, "Fantasmon");

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
		print("INGRESE NOMBRE: \n");
		nombre=cin.nextLine();
		jugador.setNombre(nombre);
		GenerarEnemigos();
		mainMenu();
	}
	
	
	public void mainMenu() {
		while(true) {
			print("1-Jugar\n");
                        print("2-Reportes\n");
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
            print("Nombre del jugador: "+nombre+"\n");
            MenuR();
            return;
        }
        
        public void MenuR(){
            print("  --- Elija la informacion que desea ver: ---\n");
            print(" 1 --- Estadisticas \n");
            print(" 2 --- Cantidad de ciudades reconquistadas: \n");
            print(" 3 --- Reconquistar todas las ciudades y veces que el jugador termino la partida\n");
            print(" 4 --- Cantidad de veces que se ejecutó cada magia \n");
            print(" 0 --- Regresar");
            int op=seleccion(5);
            switch(op) {
				case 1:Estadisticass();break;
                                case 2:CiudadesConquistadas();break;
                                case 3:TodasCiudades();break;
                                case 4:MagiasUtilizadas();break;
				case 0:return;
			}
        }
	public void Estadisticass(){
            // Llamar al método mostrarEstadisticas()
            //manejoBatalla.mostrarEstadisticas(); no se como llmar esta caca jajaja
        }
        
        public void CiudadesConquistadas(){
            int ciudadesReconquistadas = Ciudad.getCiudadesReconquistadas();
            System.out.println("Cantidad de ciudades reconquistadas: " + ciudadesReconquistadas);
        }
        
        public void TodasCiudades(){
          if (Ciudad.todasLasCiudadesReconquistadas()) {
              print("Todas las ciudades fueron conquistadas.\n");
            } else {
                print("Se finalizó la partida por: "+nombre+" " + vecesTerminado + " veces.\n");
            }
        }
        
        public void MagiasUtilizadas(){
            Magia.mostrarContadoresMagia();
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
        
	public static void gameStart() {
		int turno=0;
		while(true) {
			tablero.ver();
			print("1-Mover\n");
			print("0-Terminar\n");
			int op=seleccion(4);
			switch(op) {
				case 1:jugador.mover();
                                break;
				case 0:  vecesTerminado++; // Incrementar el contador cuando se termina el juego
                                    return;
			}
			turno++;
		}
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
