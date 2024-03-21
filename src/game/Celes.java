package game;

import java.util.Scanner;
import java.util.ArrayList;
public class Celes extends Heroe {

	
	public Celes() {
		super(1, 100, 20, 30, 10, 35, 10, 25, 'C');
		//TODO Auto-generated constructor stub
	}

	@Override
	public boolean atacar() {
		Tablero tablero = App.tablero;
		int arr[]= {0,2,4,6};
		ArrayList<Entity> attack = new ArrayList<>();
		
		for(int i=0;i<4;i++) {
			for(int j=1;j<4;j++) {
				int ax=x+(dx[arr[i]]*j);
				int ay=y+(dy[arr[i]]*j);
				if(ax<0||ax>=tablero.width||ay<0||ay>=tablero.height)continue;
				else if(tablero.map[ay][ax] instanceof Entity) {
					attack.add((Entity)tablero.map[ay][ax]);
				}
			}
		}
		for(int i=0;i<attack.size();i++) {
			System.out.println(i+" "+attack.get(i).info());
		}
		
		Scanner cin = new Scanner(System.in);
		
		int a=cin.nextInt();
		
		try {
			this.hit(attack.get(a));
			//attack.get(a).die();
		}
		catch(Exception e) {
			System.out.println("Seleccion invalida\n");
		}
		return true;
	}

	public String info() {
		return "Arquero "+super.info();
	}
	
	@Override
	public String shopping() {
		return "Arquero: "+super.shopping();
	}

}
