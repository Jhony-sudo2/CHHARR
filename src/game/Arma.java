package game;

public class Arma {
    private int Tipo;
    private int AumentoFuerza;
    private int AumentoVelocidad;
    private int AumentoVida;
    private int AumentoConcentracion;
    private int AumentoDefensa;
    public Arma(int tipo, int aumentoFuerza, int aumentoVelocidad, int aumentoVida, int aumentoConcentracion,
            int aumentoDefensa) {
        Tipo = tipo;
        AumentoFuerza = aumentoFuerza;
        AumentoVelocidad = aumentoVelocidad;
        AumentoVida = aumentoVida;
        AumentoConcentracion = aumentoConcentracion;
        AumentoDefensa = aumentoDefensa;
    }
    public int getTipo() {
        return Tipo;
    }
    public void setTipo(int tipo) {
        Tipo = tipo;
    }
    public int getAumentoFuerza() {
        return AumentoFuerza;
    }
    public void setAumentoFuerza(int aumentoFuerza) {
        AumentoFuerza = aumentoFuerza;
    }
    public int getAumentoVelocidad() {
        return AumentoVelocidad;
    }
    public void setAumentoVelocidad(int aumentoVelocidad) {
        AumentoVelocidad = aumentoVelocidad;
    }
    public int getAumentoVida() {
        return AumentoVida;
    }
    public void setAumentoVida(int aumentoVida) {
        AumentoVida = aumentoVida;
    }
    public int getAumentoConcentracion() {
        return AumentoConcentracion;
    }
    public void setAumentoConcentracion(int aumentoConcentracion) {
        AumentoConcentracion = aumentoConcentracion;
    }
    public int getAumentoDefensa() {
        return AumentoDefensa;
    }
    public void setAumentoDefensa(int aumentoDefensa) {
        AumentoDefensa = aumentoDefensa;
    }

    

    


}
