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
	
	@Override
	public void CalcularDamage(){
		int dfuerza = this.Fuerza * 2;
		int dvelocidad = this.Velocidad * 1;
		int dpornivel = this.nivel * 3;
		this.damage = dfuerza + dvelocidad + dpornivel;
	}
	
	@Override 
	public void CalcularDamageRecibido(int recibido){
		double resta = recibido * ((double)this.Defensa/100);
		int rec = (int) (recibido - resta);
		this.damagerecibido = rec;
		this.vida = this.vida -this.damagerecibido;
	}

	public int getTipo() {
		return Tipo;
	}

	public String getNombre() {
		return Nombre;
	}

	public int getVision() {
		return vision;
	}


	public void ordenarPorVelocidad(Enemigo[] enemigos) {
        int n = enemigos.length;
        boolean intercambiado;
        do {
            intercambiado = false;
            for (int i = 0; i < n - 1; i++) {
                if (enemigos[i].Velocidad > enemigos[i + 1].Velocidad) {
                    Enemigo temp = enemigos[i];
                    enemigos[i] = enemigos[i + 1];
                    enemigos[i + 1] = temp;
                    intercambiado = true;
                }
            }
            n--;
        } while (intercambiado);
    }
}
