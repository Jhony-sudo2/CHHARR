package game;

import java.util.Random;

public class Magia extends Item {
    private int Puntos;
    private int Tipo;
    private int Turnos;
    private boolean esAtaque = false;
      // Contadores para cada tipo de magia
    private static int[] contadoresMagia = new int[11]; // 11 tipos de magia (del 1 al 10)

    public Magia(String nombre, int precio, int tipo) {
        super(precio, nombre);
        this.Tipo = tipo;
        esAtaque = false;
    }

    @Override
    public void Usar(Heroe jugador) {
        // Incrementar contador de la magia usada
        contadoresMagia[Tipo]++;
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
     public static void mostrarContadoresMagia() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Magia tipo " + i + ": " + contadoresMagia[i] + " veces utilizada.");
        }
    }
     
    public static int[] getContadoresMagia() {
        return contadoresMagia;
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
