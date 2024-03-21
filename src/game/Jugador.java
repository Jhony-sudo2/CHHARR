package game;

public class Jugador extends Mob{
    private String Nombre;
    private int Oro;
    private Heroe[] heroes = new Heroe[4];
    private Arma[] Armas = new Arma[5];
    private Objeto[] objetos = new Objeto[6];
    private Magia[] Magias = new Magia[10];

    public Jugador() {
        super(1, 100, 100, 100, 100, 10, 10, 10, 'J');
        Oro = 1000;
        heroes[0] = new Vaan();
        heroes[1] = new Celes();
        heroes[2] = new Rydia();
        heroes[3] = new Locke();
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
