package game;

public class Trabajo {
    private String Nombre;
    private int AumentoFuerza;
    private int AumentoVelocidad;
    private int AumentoDefensa;
    private int Especialidad;
    private int AumentoVida;
    private int CampoAumento;
    private int Valor;
    public Trabajo(int aumentoFuerza, int aumentoVelocidad, int aumentoDefensa, int especialidad, int campoAumento,
            int valor,int aumentoVida,String Nombre) {
        AumentoFuerza = aumentoFuerza;
        AumentoVelocidad = aumentoVelocidad;
        AumentoDefensa = aumentoDefensa;
        Especialidad = especialidad;
        CampoAumento = campoAumento;
        Valor = valor;
        AumentoVida = aumentoVida;
        this.Nombre = Nombre;
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
    public int getAumentoDefensa() {
        return AumentoDefensa;
    }
    public void setAumentoDefensa(int aumentoDefensa) {
        AumentoDefensa = aumentoDefensa;
    }
    public int getEspecialidad() {
        return Especialidad;
    }
    public void setEspecialidad(int especialidad) {
        Especialidad = especialidad;
    }
    public int getCampoAumento() {
        return CampoAumento;
    }
    public void setCampoAumento(int campoAumento) {
        CampoAumento = campoAumento;
    }
    public int getValor() {
        return Valor;
    }
    public void setValor(int valor) {
        Valor = valor;
    }



    public int getAumentoVida() {
        return AumentoVida;
    }



    public void setAumentoVida(int aumentoVida) {
        AumentoVida = aumentoVida;
    }



    public String getNombre() {
        return Nombre;
    }

    
    






}
