package game;

public abstract class Item {
    private int Precio;
    private String Nombre;
    private int Cantidad = 0;

    public Item(int precio, String nombre) {
        Precio = precio;
        Nombre = nombre;
    }

    public int getPrecio() {
        return Precio;
    }
    public void setPrecio(int precio) {
        Precio = precio;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void info(){
        System.out.println("Nombre: " + Nombre);
    }


    public int getCantidad() {
        return Cantidad;
    }



    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    


}
