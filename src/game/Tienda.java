package game;

import java.util.Scanner;

public class Tienda extends Terreno {

    Jugador jugador;
    int Oro = 0;
    Scanner n = new Scanner(System.in);
    public Tienda(int x, int y, char c) {
        super(x, y, c);
    }

    public void Atender(Jugador tmpJugador) {
        this.jugador = tmpJugador;
        this.Oro = this.jugador.getOro();
        System.out.println("ATIENDO A: " + tmpJugador.getNombre());
        int Seleccion = 1;
        while (Seleccion != 0) {
            System.out.println("--------TIENDA--------");
            System.out.println("1. Comprar Objetos ");
            System.out.println("2. Comprar Armas");
            System.out.println("3. Comprar Magias");
            System.out.println("4. Vender");
            System.out.println("0. Salir");
            Seleccion = n.nextInt();
            if (Seleccion != 0)
                comprarItem(Seleccion);
        }
        
    }
    
    public void comprarItem(int seleccion) {
        System.out.println("SELECCION " + seleccion);
        switch (seleccion) {
            case 1:
                mostrarObjetos();
                break;
            case 2:
                MostrarArmas();
                break;
            case 3:
                mostrarMagias();
                break;
            case 4:
                VenderItem();
                break;
            default:
                break;
        }
        int op=App.seleccion(10);
        if(op==0)return;
        Item i = null;
        Objeto[] tmp = jugador.getObjetos();
        Arma[] tmp1 = jugador.getArmas();
        Magia[] tmp2 = jugador.getMagias();
        
        switch (seleccion) {
            case 1:   
                switch(op) {
                    case 1:i = tmp[0];;break;
                    case 2:i = tmp[1]; break;
                    case 3:i = tmp[2];break;
                    case 4:i = tmp[3];break;
                    case 5:i = tmp[4];break;
                    case 6:i = tmp[5];break;
                }
                break;
            case 2:
                switch (op) {
                    case 1:i = tmp1[0];;break;
                    case 2:i = tmp1[1]; break;
                    case 3:i = tmp1[2];break;
                    case 4:i = tmp1[3];break;
                    case 5:i = tmp1[4];break;
                    case 6:i = tmp1[5];break;
                }
                break;
            case 3:    
                switch (op) {
                    case 1:i = tmp2[0];;break;
                    case 2:i = tmp2[1]; break;
                    case 3:i = tmp2[2];break;
                    case 4:i = tmp2[3];break;
                    case 5:i = tmp2[4];break;
                    case 6:i = tmp2[5];break;
                }
                break;
            default:
                break;
        }
        
        if(Oro<i.getPrecio()) {
            System.out.print("No tienes suficiente oro");
            return;
        }
        tmp[op-1].setCantidad(tmp[op-1].getCantidad() + 1);;
        Oro-=i.getPrecio();
        System.out.println("Compra con exito");
    }


    public void mostrarMagias(){
        System.out.println("  Objeto-------------------Precio");
        System.out.println("1. Cura----------------------50");
        System.out.println("2. Revivir-------------------60");
        System.out.println("3. Coraza-------------------100");
        System.out.println("4. Escudo--------------------30");
        System.out.println("5. Divinidad-----------------40");
        System.out.println("6. Fuego---------------------40");
        System.out.println("7. Hielo---------------------40");
        System.out.println("8. Rayo----------------------40");
        System.out.println("9. Veneno--------------------40");
        System.out.println("10. Meteoro------------------40");
        System.out.println("0. Cancelar");
    }
    
        

    public void mostrarObjetos(){
        System.out.println("  Objeto-------------------Precio");
        System.out.println("1. Pocion--------------------50");
        System.out.println("2. PocionMayor---------------60");
        System.out.println("3. Pluma Fenix--------------100");
        System.out.println("4. Tienda de campana---------30");
        System.out.println("5. Velocidad-----------------40");
        System.out.println("6. Freno---------------------40");
        System.out.println("0. Cancelar");
    
    }

    public void MostrarArmas(){
        System.out.println("  Objeto-------------------Precio");
        System.out.println("1. Una mano sable-------------50");
        System.out.println("2. Dos manos hacha------------60");
        System.out.println("3. Corta Daga----------------100");
        System.out.println("4. Baculo--------------------30");
        System.out.println("5. Escudo--------------------40");
        System.out.println("0. Cancelar");
        
    }
public void VenderItem() {
       while (true) {
        Objeto[] inventario = jugador.getObjetos();
        System.out.println("Inventario:");
        for (int i = 0; i < inventario.length; i++) {
            Objeto objeto = inventario[i];
            if (objeto != null && objeto.getCantidad() > 0) {
                System.out.println((i + 1) + ". " + objeto.getNombre() + " - Cantidad: " + objeto.getCantidad());
            }
        }
        System.out.println("Seleccione el objeto a vender (0 para regresar al tablero):");
        int opcion = n.nextInt();
        if (opcion == 0) {
            // Regresar al tablero
            return;
        }
        if (opcion < 1 || opcion > inventario.length || inventario[opcion - 1] == null) {
            System.out.println("Selección inválida.");
            continue; // Continuar solicitando al jugador que seleccione un objeto válido
        }
        Objeto objetoVendido = inventario[opcion - 1];
        if (objetoVendido.getCantidad() <= 0) {
            System.out.println("No tienes este objeto para vender.");
            continue; // Continuar solicitando al jugador que seleccione un objeto válido
        }
        // Agregar el valor al oro del jugador
        Oro += 50;
        jugador.setOro(Oro);
        // Reducir la cantidad del objeto en el inventario del jugador
        objetoVendido.setCantidad(objetoVendido.getCantidad() - 1);
        System.out.println("Has vendido " + objetoVendido.getNombre());
        Atender(jugador);
        }
    }
}
