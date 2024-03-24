package game;

public class Magia extends Item{
    private int Puntos;
    public Magia(String nombre, int precio) {
        super(precio, nombre);
    }
    
    public int getPuntos() {
        return Puntos;
    }
    public void setPuntos(int puntos) {
        Puntos = puntos;
    }
}

    


