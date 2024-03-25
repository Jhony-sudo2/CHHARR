package game;

public class Manejobatalla {
    private Jugador jugador;
    private Enemigo[] enemigos;
    private Enemigo enemigo;
    int tipo = 0;

    public Manejobatalla(Jugador jugador,Enemigo enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        tipo = 1;
    }

    
    public Manejobatalla(Jugador jugador,Enemigo[] enemigos) {
        this.jugador = jugador;
        this.enemigos = enemigos;
        tipo = 2;
    }


    public void batalla(){
        jugador.ordenarPorVelocidad(jugador.getHeroes());
        enemigos[0].ordenarPorVelocidad(enemigos);

        Heroe actJugador = jugador.getHeroes()[0];
        Enemigo acatEnemigo =enemigos[0];

        int opcion = 0;
        while (true) {
            System.out.println("Turno de: " + actJugador.getClass().getSimpleName());
            System.out.println("1. Atacar");
            System.out.println("2. Utilizar Magias");
            System.out.println("3. Utilizar Objeto");
            System.out.println("4. Utilizar Arma");
            System.out.println("5. Saltar Turno");
            opcion = App.entero();

            switch (opcion) {
                case 1:
                    System.out.println("A quien quieres atacar");
                    for (int i = 0; i < enemigos.length; i++) {
                        Enemigo tmpeEnemigo = enemigos[i];
                        System.out.println(i+1 +" "+ tmpeEnemigo.getNombre());
                    }
                    opcion = App.entero();
                    acatEnemigo = enemigos[opcion-1];

                    break;
                case 2:
                
                    break;
                    
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                default:
                    break;
            }
        }


    }

    


}
