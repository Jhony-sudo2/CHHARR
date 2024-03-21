package game;

public class Terreno extends Object{
	int x,y;
	char c;
	public Terreno(int x,int y,char c) {
		this.x=x;
		this.y=y;
		this.c=c;
		//App.tablero.map[y][x]=this;
	}

	
	public String toString() {
		return c+"";
	}
}
