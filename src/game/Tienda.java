package game;

import java.util.Scanner;

public class Tienda extends Terreno {
    
    private int OroActual;
    public Tienda(int x, int y, char c) {
        super(x, y, c);
    }
    

    public void Atender(){
        Scanner n = new Scanner(System.in);
        System.out.println("USTED ESTA COMPRANDO MANO");
        System.out.println("--------TIENDA--------");
        int Seleccion = n.nextInt();
    }
}
