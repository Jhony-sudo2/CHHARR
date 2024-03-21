package game;
import java.util.Random;
public abstract class Heroe extends Mob {


	private Trabajo[] trabajo = new Trabajo[2];
	private Arma arma;
	private Trabajo trabajoAcutal;

	
	public Heroe(int nivel, int vida, int concentracion, int espiritu, int experiencia, int fuerza, int defensa,
			int velocidad, char c) {
		super(nivel, vida, concentracion, espiritu, experiencia, fuerza, defensa, velocidad, c);
		this.color=App.ANSI_GREEN;
		//TODO Auto-generated constructor stub
	}
	

	@Override
	public void CalcularDamage(){
		this.damage = 100;
	}

	public String info() {
		return App.ANSI_GREEN+"Heroe "+App.ANSI_RESET+super.info();
	}

	
	
	@Override
	public boolean noHit() {
		Random rand = new Random();
		int n=1+ rand.nextInt(101);
		return n>75;
	}
	
	public String all() {
		return "|"+getClass().getSimpleName()+"|"+vida+"/"+vidaMaxima+"|"+damage+"|["+x+","+y+"]|";
	}
	
	public String shopping() {
		return "Vida: "+vidaMaxima+"\tdamage: "+damage;
	}

	public Trabajo[] getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(Trabajo[] trabajo) {
		this.trabajo = trabajo;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}



	public Trabajo getTrabajoAcutal() {
		return trabajoAcutal;
	}



	public void setTrabajoAcutal(Trabajo trabajoAcutal) {
		this.trabajoAcutal = trabajoAcutal;
	}
}
