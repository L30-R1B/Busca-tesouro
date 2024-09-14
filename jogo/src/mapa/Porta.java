package mapa;

public class Porta {
    private final char IDENTIFICADOR;
    private final int SALA_INICIO;
    private final int SALA_DESTINO;
    private boolean aberta;

    public Porta(char identificador, int salaInicio, int salaDestino){
        this.IDENTIFICADOR = identificador;
        this.SALA_INICIO = salaInicio;
        this.SALA_DESTINO = salaDestino;
        this.aberta = true;
    }

    public void abrePorta(){
        this.aberta = true;
    }

    public void fechaPorta(){
        this.aberta = false;
    }

    public boolean isAberta(){
        return this.aberta;
    }
    
    public int getSalaInico(){
        return SALA_INICIO;
    }

    public int getSalaDestino(){
        return SALA_DESTINO;
    }

    public char getIdentificador(){
        return this.IDENTIFICADOR;
    }

    public void printaPorta(){
        System.out.print(" < " + this.IDENTIFICADOR + " : " + (this.aberta?"aberta":"fechada") + " > ");
    }

}
