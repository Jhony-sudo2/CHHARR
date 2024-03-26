package game;

import java.util.Random;


public class Tablero {
	int width = 8;
        int height = 8;
        Object map[][];
        int planicie = 0;

    public Tablero(int T) {
        if(T == 7) {
            width = height = 7;
        } else if(T == 10) {
            width = height = 10;
        } else if(T == 12) {
            width = height = 12;
        }
        generar();
    }

    public Tablero(int width, int height) {
        this.width = width;
        this.height = height;
        generar();
    }
	
	
	public void generar() {
	map = new Object[height][width];
        App.tablero = this;
        Random rand = new Random();

        generarTiendas(1, rand);
        generarPosadas(1, rand);
        generarCiudades(3 + rand.nextInt(3), rand); // Entre 3 y 5 ciudades
        generarZonas(3 + rand.nextInt(3), rand); // Entre 3 y 5 zonas

        // Generar planicies en el resto del tablero
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == null) {
                    map[i][j] = new Planicie(j, i);
                    planicie++;
                }
            }
        }
    }

    private void generarTiendas(int numTiendas, Random rand) {
        for (int i = 0; i < numTiendas; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            map[y][x] = new Tienda(x, y, 'T');
        }
    }

    private void generarPosadas(int numPosadas, Random rand) {
        for (int i = 0; i < numPosadas; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            map[y][x] = new Pozada(x, y);
        }
    }

    private void generarCiudades(int numCiudades, Random rand) {
        for (int i = 0; i < numCiudades; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            map[y][x] = new Ciudad(x, y);
        }
    }

    private void generarZonas(int numZonas, Random rand) {
        for (int i = 0; i < numZonas; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            map[y][x] = new Zona(x, y);
        }
    }
	
	public String selectColor(Object o) {
		if(o instanceof Enemigo) {
			return App.ANSI_RED;
		}
		else if(o instanceof Heroe) {
			return App.ANSI_YELLOW;
		}
		else if(o instanceof Pozada) {
			return App.ANSI_BLUE;
		}
		else if(o instanceof Tienda) {
			return App.ANSI_PURPLE;
		}
                else if (o instanceof Ciudad){
                    return App.ANSI_YELLOW;
                }
                else if(o instanceof Zona){
			Zona tmp = (Zona)o;
			if (tmp.getNivel() == 1)
                return App.ANSI_GREEN;
			else
				return App.ANSI_ORANGE;
        }
		else return App.ANSI_WHITE;
	}
	
	public void generarVista() {
		System.out.print("  ");
                String color;
		for(int i=0;i<width;i++) 
			System.out.print(" "+(i+1)+" ");
		System.out.println();
		for(int i=0;i<height;i++) {
			System.out.print((i+1)+" ");
			for(int j=0;j<width;j++) {
                color=selectColor(map[i][j]);
				System.out.print(" "+color+map[i][j]+App.ANSI_RESET+" ");
			}
			System.out.println();
		}
	}
	
	public void generarMounstros(Enemigo[] listaEnemigos) {
		int can=1+1;
		Random rand = new Random();
		
		while(can>0) {
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					if(can==0)return;
					if(map[i][j] instanceof Planicie) {
						if(rand.nextInt(101)<20) {
							int t=rand.nextInt(9);
							Enemigo e=null;
							Enemigo tmpEnemigo = listaEnemigos[t];
							e = new Enemigo(tmpEnemigo.nivel, tmpEnemigo.vida, tmpEnemigo.concentracion, tmpEnemigo.espiritu, tmpEnemigo.Fuerza, tmpEnemigo.Fuerza, tmpEnemigo.Velocidad, tmpEnemigo.c, tmpEnemigo.getTipo(), tmpEnemigo.getNombre());
							e.x=j;
							e.y=i;
							map[i][j]=e;	
							//App.enemy.add(e);
							can--;
						}
					}
				}
			}
		}
	}
			
	public void ver() {
		generarVista();
	}

}
