package game;

import java.util.Random;

public class Magia extends Item {
    private int Puntos;
    private int Tipo;
    private int Turnos;
    private boolean esAtaque = false;

    public Magia(String nombre, int precio, int tipo) {
        super(precio, nombre);
        this.Tipo = tipo;
        esAtaque = false;
    }

    @Override
    public void Usar(Heroe jugador) {
        
        Random n = new Random();
        switch (Tipo) {
            case 1:
                jugador.vida += n.nextInt(jugador.concentracion, 2 * jugador.concentracion);
                break;
            case 2:
                jugador.vida = 100;
                break;
            case 3:
                Turnos = n.nextInt(1, 3);
                Turnos += jugador.concentracion / 10;
                jugador.espiritu += jugador.concentracion * 2;
                break;
            case 4:
                Turnos = n.nextInt(1, 3);
                Turnos += jugador.concentracion / 10;
                jugador.damage += jugador.concentracion * 2;
                break;
            case 5:
                this.esAtaque = true;
                jugador.damage = n.nextInt(50, 100);
                break;

            case 6:
                this.esAtaque = true;
                jugador.damage = n.nextInt(jugador.concentracion, 2 * jugador.concentracion);
                jugador.setTipoAtaque(1);
                break;

            case 7:
                this.esAtaque = true;
                jugador.damage = n.nextInt(jugador.concentracion, 2 * jugador.concentracion);
                jugador.setTipoAtaque(2);
                break;

            case 8:
                this.esAtaque = true;
                jugador.damage = n.nextInt(jugador.concentracion, 2 * jugador.concentracion);
                jugador.setTipoAtaque(3);
                break;

            case 9:
                this.esAtaque = true;
                jugador.damage = n.nextInt(jugador.concentracion, 2 * jugador.concentracion);
                jugador.setTipoAtaque(4);
                break;
            /* PENDIENTE 10 METEORO */
            case 10:
                break;
            default:
                break;
        }
    }

    public int getPuntos() {
        return Puntos;
    }

    public void setPuntos(int puntos) {
        Puntos = puntos;
    }

    public int getTurnos() {
        return Turnos;
    }

}
