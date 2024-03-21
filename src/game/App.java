package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
	static Scanner cin = new Scanner(System.in);
	static String nombre;
	static ArrayList<Heroe> heroes = new ArrayList<>();
	static ArrayList<Heroe> hComprar = new ArrayList<>();
	static ArrayList<Objeto> items = new ArrayList<>();
	static ArrayList<Enemigo> enemy = new ArrayList<>();
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
		enemigos[1] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 1, "XD");
		enemigos[2] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 1, "ABC");
		enemigos[3] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 2, "DEF");
		enemigos[4] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 2, "DRAGON2");
		enemigos[5] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 2, "TIPO2");
		enemigos[6] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 3, "TIPO3");
		enemigos[7] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 3, "DRAGON3");
		enemigos[8] = new Enemigo(1, 100, 20, 10, 20, 20, 20, 'Z', 3, "HADA3");

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
	
	static class Tienda{
		
		static void abrir() {
			while(true) {
				print("Posees "+oro+" monedas de oro\n");
				print("1-Comprar Heroe\n");
				print("2-Comprar Objeto\n");
				print("0-Salir\n");
				int op=seleccion(2);
				switch(op) {
					case 1:comprarHeroe();break;
					case 2:comprarItem();break;
					case 0:	return;
				}
			}
		}
		
		static void comprarHeroe() {
			if(hComprar.isEmpty()) {
				print("Ya tienes todos los heroes\n");
				return;
			}
			if(oro<200) {
				print("No tienes suficiente oro para comprar un Heroe");
				return;
			}
			mostrarhComprar();
			print("Cada Hereo tiene un valor de 200 monedas de oro \n");
			int op=seleccion(hComprar.size());
			if(op==0)return;
			else {
				Heroe h = hComprar.get(op-1);
				heroes.add(h);
				hComprar.remove(h);
				print("Se ha comprado el heroe "+h.shopping()+"\n");
				oro-=200;
			}
		}
		
		static void comprarItem() {
			if(items.size()>10) {
				print("No puedes llevar mas objetos");
				return;
			}
			mostrarItems();
			int op=seleccion(3);
			if(op==0)return;
			Objeto i=null;
			switch(op) {
				case 1:i = new SemillaVida();break;
				case 2:i = new ElixirVerde();break;
			}
			if(oro<i.precio) {
				print("No tienes suficiente oro");
				return;
			}
			items.add(i);
			oro-=items.get(items.size()-1).precio;
			print("Ha comprado con exito\n");
		}
		
		public static void mostrarItems() {
			print("1-Semilla de la vida\t50\nDescripcion: Resucita a un Heroe caido en batalla\n\n");
			print("2-Elixir Verde\t25\nDescripcion: Restaura 50 puntos de vida sobre quien se use\n\n");
			print("3-Capa de Movilidad\t75\nDescripcion: Te permite moverte una casilla\n\n");
			print("0-Salir\n");
		}
		
		public static void mostrarhComprar() {
			for(int i=0;i<hComprar.size();i++) {
				print((i+1)+"-"+hComprar.get(i).shopping()+"\n");
			}
			print("0-Salir\n");
		}
		
	}
	
	public App() {
		print("Nombre de la partida\n");
		nombre=cin.nextLine();
		mainMenu();
	}
	
	
	public void mainMenu() {
		while(true) {
			print("1-Jugar\n");
			print("2-Inventario\n");
			print("3-Tienda\n");
			print("4-Mejoras\n");
			print("5-Generar Mapa\n");
			print("0-Salir\n");
			int op=seleccion(5);
			switch(op) {
				case 1:iniciarPartida();break;
				case 2:inventario();
				case 3:Tienda.abrir();break;
				case 4:mejoras();break;
				case 5:demoTablero();break;
				case 0:return;
			}
		}
	}

	public void demoTablero() {
		seleccionarMapa();
		tablero.generarMounstros(enemigos);
		tablero.ver();
		
	}
	
	public static void showHeroes() {
		for(Heroe i:heroes) {
			print(i.info());
		}
	}
	
	public static void showItems() {
		for(int i=0;i<items.size();i++) {
			print((i+1)+"-"+items.get(i).nombre+"\n");
		}
		print("0-Salir\n");
	}
	
	public void inventario() {
		showHeroes();
		showItems();
		print("Seleccione un objeto para venderlo o usarlo\n");
		int op=seleccion(items.size());
		if(op==0)return;
		hacerCon(items.get(op-1));
	}
	
	public void hacerCon(Objeto i) {
		print(i.nombre+"\n");
		print("1-Usar\n");
		print("2-Vender\n");
		print("0-Salir\n");
		int op=seleccion(2);
		if(op==0)return;
		else if(op==1) {
			usarItem(i);
		}
		else {
			oro+=i.precio/2;
			items.remove(i);
			print("Objeto vendido por "+i.precio/2+" monedas de oro\n");
		}
		
	}
	
	public void usarItem(Objeto i) {
		for(int j=0;j<heroes.size();j++) {
			print((j+1)+"-"+heroes.get(j).all()+"\n");
		}
		print("0-Salir\n");
		int op=seleccion(heroes.size());
		if(op==0)return;
		i.usar(heroes.get(op-1));
		items.remove(i);
	}
	
	public void iniciarPartida() {
		/*		
		while(heroes.isEmpty()) {//primera vez que se juega
			print("Para iniciar tu aventura obten un Heroe y algunos objetos\n");
			Tienda.abrir();
		}
		 */

		seleccionarMapa();
		if(tablero==null)return;
		//seleccionHeroes();
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
		GenerarEnemigos();
		tablero.generarMounstros(enemigos);
		gameStart();
	}
	
	public static void mostrarItems() {
		for(int i=0;i<items.size();i++) {
			print((i+1)+"-"+items.get(i).info()+"\n");
		}
		print("0-No\n");
	}
	
	public static void mejorarVida() {
		if(oro<80) {
			print("No tienes oro para esto\n");
		}
		if(heroes.isEmpty()) {
			print("No tienes ningun heroe\n");
			return;
		}
		for(int i=0;i<heroes.size();i++) {
			print((i+1)+"-"+heroes.get(i).info()+"\n");
		}
		print("0-Salir\n");
		int op=seleccion(heroes.size());
		if(op==0)return;
		heroes.get(op-1).vidaMaxima+=50;
		if(!heroes.get(op-1).isDead())heroes.get(op-1).vida+=50;
		oro-=80;
	}

	public static void mejorarDamage() {
		if(oro<125) {
			print("No tienes oro para esto\n");
		}
		if(heroes.isEmpty()) {
			print("No tienes ningun heroe\n");
			return;
		}
		for(int i=0;i<heroes.size();i++) {
			print((i+1)+"-"+heroes.get(i).info()+"\n");
		}
		print("0-Salir\n");
		int op=seleccion(heroes.size());
		if(op==0)return;
		heroes.get(op-1).damage+=10;
		oro-=125;
	}
	
	public static void mejorarMovilidad() {
		if(oro<500) {
			print("No tienes oro para esto\n");
		}
		if(heroes.isEmpty()) {
			print("No tienes ningun heroe\n");
			return;
		}
		for(int i=0;i<heroes.size();i++) {
			print((i+1)+"-"+heroes.get(i).info()+"\n");
		}
		print("0-Salir\n");
		int op=seleccion(heroes.size());
		if(op==0)return;
		heroes.get(op-1).maxMov++;
		oro-=500;
	}
	
	public static void mejoras() {
		if(oro<80) {
			print("No tienes dinero para ninguna mejora\n");
			return;
		}
		print("Posees "+oro+" monedas de oro\n");
		print("1-Vida\t80\nDescripcion: Le otorga al personaje 50 de vida maxima\n\n");
		print("2-Dano\t125\nDescripcion: Le otorga al personaje 10 de ataque\n\n");
		print("3-Movilidad\t500\nDescripcion: Le otorga al personaje 1 casilla adicional de movimiento\n\n");
		print("0-Salir\n");
		int op=seleccion(3);
		switch(op) {
			case 1:mejorarVida();break;
			case 2:mejorarDamage();break;
			case 3:mejorarMovilidad();break;
			case 0:return;
		}
	}
	
	public static void gameStart() {
		int turno=0;
		while(true) {
			infoTurno="Descripcion del turno:\n";
			print("TURNO: "+ turno +"\n");
			//Si el heroe en batalla murio cambiarlo
			boolean murio=false;
			if(jugador.isDead()) {
				murio=true;
				int other = (mainHero+1)%2;
				Heroe second = ingame[other];
				if(second==null||second.isDead()) {
					loss();
					return;					
				}
				else {
					cambiarHeroe();
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
			if(turno>0&&canChange()){
				print("Deseas Cambiar el Heroe\n1-Si\n2-NO\n");
				int op=seleccion(2);
				if(op==1)cambiarHeroe();
			}
			if(!items.isEmpty()&&turno>0) {
				print("Deseas usar algun objeto\n");
				mostrarItems();
				int op=seleccion(items.size());
				if(op==0);
				else {
					Objeto item = items.get(op-1);
					if(item instanceof SemillaVida) {
						Heroe hero2 = ingame[(mainHero+1)%2];
						if(hero2!=null)item.usar(hero2);
					}
					//else item.usar(hero);
					
					items.remove(item);
				}
			}
			if(jugador.isDead()) {
				loss();
				return;
			}
			//hero.restarMove();
			//print(hero.all()+"\n");
			tablero.ver();
			print("1-Atacar\n");
			print("2-Moverse\n");
			print("3-Tienda\n");
			print("4-Mejoras\n");
			print("0-Terminar\n");
			int op=seleccion(4);
			switch(op) {
				case 1:jugador.atacar();break;
				case 2:jugador.mover();break;
				case 3:Tienda.abrir();break;
				case 4:mejoras();break;
				case 0:return;
			}
			
			for(int i=0;i<enemy.size();i++) {			
				if(!enemy.get(i).atacar())enemy.get(i).mover();	
			}
			
			print(infoTurno+"\n");
			if(enemy.isEmpty()) {
				win();
				return;
			}
			turno++;
		}
	}

	public static boolean canChange() {
		int other = (mainHero+1)%2;
		if(ingame[other]==null) {
			return false;
		}
		if(ingame[other].isDead()) {
			for(int i=0;i<items.size();i++) {
				Objeto item=items.get(i);
				if(item instanceof SemillaVida) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
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
	
	public static boolean cambiarHeroe() {
		int other = (mainHero+1)%2;		
		if(ingame[other]==null) {
			print("No tienes otro heroe");
			return false;
		}
		if(ingame[other].isDead()) {
			boolean semilla=false;
			for(int i=0;i<items.size();i++) {
				Objeto item=items.get(i);
				if(item instanceof SemillaVida) {
					semilla=true;
					item.usar(ingame[other]);
					items.remove(item);
					//mainHero=other;
					break;
				}
			}
			if(!semilla) {
				print("No se puede revivir al heroe\n");
				return true;
			}
			else {
				print("Se uso una semilla vida para revivir al heroe\n");
				return true;
			}
		}
		Heroe heroe = ingame[other];
		heroe.x=ingame[mainHero].x;
		heroe.y=ingame[mainHero].y;
		tablero.map[heroe.y][heroe.x]=heroe;
		mainHero=other;
		return true;
		
		
	}
	
	public static void seleccionarMapa() {
		Random n = new Random();
		int lenght = n.nextInt(3);
		System.out.println("EL TAMA;O SERA" + lenght);
		switch(lenght) {
			case 0:tablero = new Tablero(7);break;
			case 1:tablero = new Tablero(10);break;
			case 2:tablero = new Tablero(12);break;
			default: tablero=null; break;
		}
	}
	
	public static void seleccionHeroes() {
		ingame[0]=null;
		ingame[1]=null;
		print("Ahora selecciona a tu heroe principal\n");
		for(int i=0;i<heroes.size();i++) {
			print((i+1)+"-"+heroes.get(i).shopping()+"\n");
		}
		print("0-Salir\n");
		int op=seleccion(heroes.size());
		if(op==0) return;
		ingame[0]=heroes.get(op-1);
		if(heroes.size()>1) {
			ArrayList<Heroe> temp = new ArrayList<>(heroes);
			temp.remove(ingame[0]);
			print("Ahora selecciona a tu heroe secundario\n");
			for(int i=0;i<temp.size();i++) {
				print((i+1)+"-"+temp.get(i).shopping()+"\n");
			}
			print("0-Salir\n");
			op=seleccion(temp.size());
			if(op==0) return;
			ingame[1]=temp.get(op-1);
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
	
	public static void tableroPersonalizado() {
		System.out.println("Systema de personalizacion de tableros\n");
		String ops[]= {"Alto","Ancho","Porcentaje Planicies","Porcentaje Arboles","Porcentaje Agua","Porcentaje Lava"};
		int n[]= new int[ops.length];
		for(int i=0;i<ops.length;i++) {
			n[i]=0;
			while(n[i]<1) {
				print(ops[i]);
				n[i]=seleccion(20);
				if(n[i]==0)print("valores mayores que 0\n");
			}
			
			if(n[i]<0)return;
		}
		
		tablero = new Tablero(n[0],n[1],n[2],n[3],n[4],n[5]);	
	}
	
	public static void main(String args[]) {	    
		new App();
		System.exit(0);
	}

}
