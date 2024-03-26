package game;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class Manejobatalla {
    private Jugador jugador;
    private Enemigo[] enemigos;
    private Enemigo enemigo;
    
   // Variables para llevar el registro de daño y muertes por héroe
    private int[] danoHechoPorHeroe;
    private int[] vecesMuertoPorHeroe;
    private Map<String, Integer> enemigosDerrotadosPorTipo;

    public Manejobatalla(Jugador jugador, Enemigo enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.enemigos = new Enemigo[1];
        this.enemigos[0] = enemigo;
        inicializarContadores();
    }

    public Manejobatalla(Jugador jugador, Enemigo[] enemigos) {
        this.jugador = jugador;
        this.enemigos = enemigos;
        inicializarContadores();
    }
    // Método para inicializar los contadores para llevar el registro de daño y muertes por héroe
    private void inicializarContadores() {
        danoHechoPorHeroe = new int[jugador.getHeroes().length];
        vecesMuertoPorHeroe = new int[jugador.getHeroes().length];
        enemigosDerrotadosPorTipo = new HashMap<>();
    }

    public void batalla() {
        jugador.ordenarPorVelocidad(jugador.getHeroes());
        enemigos[0].ordenarPorVelocidad(enemigos);
        Heroe actJugador = jugador.getHeroes()[0];
        int x = 0;
        Enemigo acatEnemigo = enemigos[x];
        int i = 0;
        int opcion = 0;
        while (true) {
            MostrarDatos(jugador.getHeroes(), enemigos);
            System.out.println("Turno de: " + actJugador.getClass().getSimpleName());
            System.out.println("1. Atacar");
            System.out.println("2. Utilizar Magias");
            System.out.println("3. Utilizar Objeto");
            System.out.println("4. Utilizar Arma");
            System.out.println("5. Saltar Turno");
            opcion = App.seleccion(5);
            int seleccionobjeto = 0;
            switch (opcion) {
                case 1:
                    System.out.println("A quien quieres atacar");
                    mostrarEnemigos();
                    opcion = App.entero();
                    acatEnemigo = enemigos[opcion - 1];
                    actJugador.CalcularDamage();
                    acatEnemigo.CalcularDamageRecibido(actJugador.damage);
                    System.out.println("El enemigo: " + acatEnemigo.getNombre() + " recibio un dano de: "
                            + acatEnemigo.damagerecibido);
                    if (acatEnemigo.vida <= 0) {
                        System.out.println(acatEnemigo.getNombre() + " ha muerto");
                        acatEnemigo.vida = 0;
                    }
                    break;
                case 2:
                    mostrarMagia();
                    seleccionobjeto = App.entero();
                    Magia tmp = jugador.getMagias()[seleccionobjeto-1];
                    tmp.Usar(actJugador);
                    break;

                case 3:
                    mostrarObjetos();
                    seleccionobjeto = App.entero();
                    Objeto tmp2 = jugador.getObjetos()[seleccionobjeto-1];
                    tmp2.Usar(actJugador);
                    break;
                case 4:
                    mostrarArmas();
                    seleccionobjeto = App.entero();
                    actJugador.setArma(jugador.getArmas()[seleccionobjeto-1]);
                    break;
                case 5:

                    break;
                default:
                    System.out.println("OPCION INVALIDA");
                    break;
            }
            
            if (terminar())return;
            acatEnemigo = enemigos[i];
            RecibirDano(acatEnemigo);
            if(perdiste()) return;
            do {
                i = (i< enemigos.length-1)?i+1:0;
            } while (enemigos[i].vida <= 0);
            do {
                x = (x<3)?x+1:0;
                actJugador = jugador.getHeroes()[x];
            } while (actJugador.vida <= 0);
        }

    }

    public void mostrarEnemigos(){
        if(enemigo != null){
            System.out.println("1. " + enemigo.getNombre());
        }else{
            for (int i = 0; i < enemigos.length; i++) {
                Enemigo tmpeEnemigo = enemigos[i];
                if(tmpeEnemigo.vida > 0)
                    System.out.println(i+1 +" "+ tmpeEnemigo.getNombre());
            }
        }
        
    }

    public void RecibirDano(Enemigo enemigo){
        Random random = new Random();
        Heroe heroe;
        int i = 0;
        do {
            i = random.nextInt(4);
            heroe = jugador.getHeroes()[i];
        } while (heroe.vida <= 0);
        enemigo.CalcularDamage();
        heroe.CalcularDamageRecibido(enemigo.damage);
        System.out.println(enemigo.getNombre() + " lanzo un ataque de: " + enemigo.damage);
        System.out.println(heroe.getClass().getSimpleName() + " ha recibido " + heroe.damagerecibido  + " de dano");
        
        if(heroe.vida <= 0) {
            System.out.println("------------------------------------------------------------");
            System.out.println("Tu heroe: " + heroe.getClass().getSimpleName() + " ha muerto");
            heroe.vida = 0;
        }
    }

    public void mostrarArmas() {
        for (int i = 0; i < jugador.getArmas().length; i++) {
            Arma tmp = jugador.getArmas()[i];
            if (tmp.getCantidad() != 0) {
                System.out.println(i+1 + " " + tmp.getNombre());
            }
        }
    }

    public void mostrarObjetos() {
        for (int i = 0; i < jugador.getObjetos().length; i++) {
            Objeto tmp = jugador.getObjetos()[i];
            if (tmp.getCantidad() != 0) {
                System.out.println(i+1 + " " + tmp.getNombre());
            }
        }
    }

    public void mostrarMagia() {
        for (int i = 0; i < jugador.getMagias().length; i++) {
            Magia tmp = jugador.getMagias()[i];
            if (tmp.getCantidad() != 0) {
                System.out.println(i+1 + " " + tmp.getNombre());
            }
        }
    }

    public void MostrarDatos(Heroe[] heroes, Enemigo[] enemigos) {
        System.out.println("----------ENEMIGOS------------");
        for (int i = 0; i < enemigos.length; i++) {
            Enemigo tmp = enemigos[i];
            MostrarEstadistica(tmp, tmp.getNombre(),1);
        }

        System.out.println("----------ALIADOS------------");
        for (int i = 0; i < heroes.length; i++) {
            Heroe tmp = heroes[i];
            MostrarEstadistica(tmp, tmp.getClass().getSimpleName(),2);
        }
    }

    public void MostrarEstadistica(Mob tmpMob, String Nombre,int tipo) {
        if(tipo == 1)
            System.out.println("Nombre: " + Nombre + "|| Vida: " + tmpMob.vida + "||Fuerza: " + tmpMob.Fuerza +"||Velocidad: " + tmpMob.Velocidad);
        else{
            Heroe tmp2 = (Heroe)tmpMob;
            System.out.println("Nombre: " + Nombre + "||Vida: " + tmpMob.vida + "||Fuerza: " + tmpMob.Fuerza + "||Defensa: " + tmpMob.Defensa +"||Velocidad: " + tmpMob.Velocidad + "||Trabajo Actual: " +tmp2.getTrabajoAcutal().getNombre() +
             "||Arma Actual: " + (tmp2.getArma() != null ? tmp2.getArma().getNombre() : "null") );
        }
    }

    public boolean terminar() {
        boolean tmp = true;
        for (Enemigo enemigo : enemigos) {
            if (enemigo.vida > 0)
                tmp = false;
        }
        if(tmp ){
            System.out.println("--------GANASTE------------/n/n");
            jugador.setOro(jugador.getOro() + 30);
            jugador.nivel += 10;
        }
        return tmp;

    }

    public boolean perdiste(){
        for(Heroe heroet: jugador.getHeroes()){
            if(heroet.vida > 0) return false;
        }
        for(Heroe heroet: jugador.getHeroes()){
            heroet.vida = 1;
        }
        System.out.println("------------PERDISTE --------------");
        return true;
    }
    
     private void actualizarDanoHechoPorHeroe(int indiceHeroe, int dano) {
        danoHechoPorHeroe[indiceHeroe] += dano;
    }

    // Método para actualizar el registro de muertes por héroe
    private void actualizarVecesMuertoPorHeroe(int indiceHeroe) {
        vecesMuertoPorHeroe[indiceHeroe]++;
    }

    // Método para actualizar la cantidad de enemigos derrotados por tipo
    private void actualizarEnemigosDerrotadosPorTipo(String tipoEnemigo) {
        enemigosDerrotadosPorTipo.put(tipoEnemigo, enemigosDerrotadosPorTipo.getOrDefault(tipoEnemigo, 0) + 1);
    }

    // Método para mostrar todas las estadísticas
    public void mostrarEstadisticas() {
        // Estadísticas de daño y muertes por héroe
        System.out.println("Estadísticas de la batalla:");
        for (int i = 0; i < jugador.getHeroes().length; i++) {
            Heroe heroe = jugador.getHeroes()[i];
            System.out.println("Heroe: " + heroe.getClass().getSimpleName());
            System.out.println("Daño hecho: " + danoHechoPorHeroe[i]);
            System.out.println("Veces muerto: " + vecesMuertoPorHeroe[i]);
            System.out.println("--------------------------");
        }

        // Estadísticas de enemigos derrotados por tipo
        System.out.println("Enemigos derrotados por tipo:");
        for (Map.Entry<String, Integer> entry : enemigosDerrotadosPorTipo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
