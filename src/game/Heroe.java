package game;

import java.util.Random;

public abstract class Heroe extends Mob {

	private Trabajo[] trabajo = new Trabajo[2];
	private Arma arma;
	private Magia magia;
	private Objeto objeto;
	private Trabajo trabajoAcutal;
	private int TipoAtaque = 0 ;

	public Heroe(int nivel, int vida, int concentracion, int espiritu, int experiencia, int fuerza, int defensa,
			int velocidad, char c) {
		super(nivel, vida, concentracion, espiritu, experiencia, fuerza, defensa, velocidad,c);
		this.color = App.ANSI_GREEN;
		// TODO Auto-generated constructor stub
	}
	
	
	public void setTipoAtaque(int tipo) {
		TipoAtaque = tipo;
	}


	@Override
	public void CalcularDamage() {
		int dfuerza = this.Fuerza * 2;
		int dvelocidad = this.Velocidad * 1;
		int dpornivel = this.nivel * 3;
		if (arma != null) {
			dfuerza += arma.getAumentoFuerza();
			dvelocidad += arma.getAumentoVelocidad();
			this.vida += arma.getAumentoVida();
			this.concentracion += arma.getAumentoConcentracion();
			this.Defensa += arma.getAumentoDefensa();
		}
		if (trabajoAcutal.getAumentoVida() != 0)
			this.vida += this.vida * (trabajoAcutal.getAumentoVida() / 100);

		if (trabajoAcutal.getAumentoFuerza() != 0)
			this.Fuerza += this.Fuerza * (trabajoAcutal.getAumentoFuerza() / 100);

		if (trabajoAcutal.getAumentoVelocidad() != 0)
			this.Velocidad += this.Velocidad * (trabajoAcutal.getAumentoVelocidad() / 100);

		if (trabajoAcutal.getAumentoDefensa() != 0)
			this.Defensa += this.Defensa * (trabajoAcutal.getAumentoDefensa() / 100);

		this.damage = dfuerza + dvelocidad + dpornivel;
	}

	public void CalcularDamageMagia(){
		if(magia != null){
			String nombre = magia.getNombre();
			
		}else{

		}
	}


	@Override 
	public void CalcularDamageRecibido(int recibido){
		double resta = recibido * ((double)this.Defensa/100);
		int rec = (int) (recibido - resta);
		this.damagerecibido = rec;
		this.vida = this.vida -this.damagerecibido;
	}



	public String info() {
		return App.ANSI_GREEN + "Heroe " + App.ANSI_RESET + super.info();
	}


	public String all() {
		return "|" + getClass().getSimpleName() + "|" + vida + "/" + vidaMaxima + "|" + damage + "|[" + x + "," + y
				+ "]|";
	}

	public String shopping() {
		return "Vida: " + vidaMaxima + "\tdamage: " + damage;
	}

	public Trabajo[] getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(Trabajo trabajo,int posicion) {
		this.trabajo[posicion] = trabajo;
		this.trabajoAcutal = this.trabajo[posicion];
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
