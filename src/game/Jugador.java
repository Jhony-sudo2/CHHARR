package game;

public class Jugador extends Mob{
    private String Nombre;
    private int Oro;
    private Heroe[] heroes = new Heroe[4];
    private Arma[] Armas = new Arma[5];
    private Objeto[] objetos = new Objeto[6];
    private Magia[] Magias = new Magia[10];

    public Jugador() {
        super(100, 100, 20, 20, 10, 20, 20, 15, '@');
        Oro = 1000;
        heroes[0] = new Vaan();
        heroes[1] = new Celes();
        heroes[2] = new Rydia();
        heroes[3] = new Locke();
        this.jugador = this;
        Llenar();
    }
    
    public void Llenar(){
        objetos[0] = new Objeto("Posion", 50,1);
        objetos[1] = new Objeto("Posion Mayor", 60,2);
        objetos[2] = new Objeto("Pluma fenix", 100,3);
        objetos[3] = new Objeto("Tienda de Campana", 30,4);
        objetos[4] = new Objeto("Velocidad", 50,5);
        objetos[5] = new Objeto("Freno", 50,6);
        
        Armas[0] = new Arma("Sable", 1, 100, -10, 20, 10, 20,100);
        Armas[1] = new Arma("Hacha", 2, 100, 10, 20, 10, 20,50);
        Armas[2] = new Arma("Daga", 3, 100, 10, 20, 10, 20,75);
        Armas[3] = new Arma("Baculo", 1, 100, 10, 20, 10, 20,80);
        Armas[4] = new Arma("Escudo", 4, 100, 10, 20, 10, 20,90);
        
        Magias[0] = new Magia("Cura", 50,1);
        Magias[1] = new Magia("Revivir", 60,2);
        Magias[2] = new Magia("Coraza", 100,3);
        Magias[3] = new Magia("Escudo", 30,4);
        Magias[4] = new Magia("Divinidad", 40,5);
        Magias[5] = new Magia("Fuego", 40,6);
        Magias[6] = new Magia("Hielo", 30,7);
        Magias[7] = new Magia("Rayo", 30,8);
        Magias[8] = new Magia("Veneno", 60,9);
        Magias[9] = new Magia("Meteoro", 90,10);
    }

    public boolean isDead(){

        return false;
    }

    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }

    public void setOro(int oro) {
        Oro = oro;
    }


    public String getNombre() {
        return Nombre;
    }

    public int getOro() {
        return Oro;
    }

    public Heroe[] getHeroes() {
        return heroes;
    }

    public Arma[] getArmas() {
        return Armas;
    }

    public Objeto[] getObjetos() {
        return objetos;
    }

    public Magia[] getMagias() {
        return Magias;
    }

    public void ordenarPorVelocidad(Heroe[] heroes) {
        int n = heroes.length;
        boolean intercambiado;
        do {
            intercambiado = false;
            for (int i = 0; i < n - 1; i++) {
                if (heroes[i].Velocidad > heroes[i + 1].Velocidad) {
                    Heroe temp = heroes[i];
                    heroes[i] = heroes[i + 1];
                    heroes[i + 1] = temp;
                    intercambiado = true;
                }
            }
            n--;
        } while (intercambiado);
    }

    @Override
    public boolean atacar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atacar'");
    }

    @Override
    public boolean noHit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'noHit'");
    }

    
    


}
