package game;

public abstract class Objeto extends Object {
	int precio;
	String nombre;
	public Objeto(String nombre,int precio) {
		this.nombre=nombre;
		this.precio=precio;
	}
	
	public abstract void usar(Heroe e);

	public String shopping() {
		return "Precio: "+precio;
	}
	
	public String info() {
		return nombre;
	}

	public String venta() {
		return nombre+" "+(int)(precio/2);
	}
}

