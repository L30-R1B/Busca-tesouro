package entidades;

public class Personagem {
    private final String TIPO_PERSONAGEM;
    private boolean vivo;
    private int vidaMaxima;
    private int quantidadeVida;

    private int nivel;
    private int experienciaProximoNivel;
    private int experienciaAcumulada;
    
    private double multiplicadorDano;

    public Personagem(String tipoPersonagem){
        this.TIPO_PERSONAGEM = tipoPersonagem;
        this.vidaMaxima = 100;
        this.quantidadeVida = 100;
        this.nivel = 0;
        this.experienciaProximoNivel = 5;
        this.experienciaAcumulada = 0;
        this.multiplicadorDano = 1.0;

        vivo = true;
    }

    public int getNivel(){
        return this.nivel;
    }

    public int getVidaMaxima(){
        return this.vidaMaxima;
    }

    public int getQuantidadeVida(){
        return this.quantidadeVida;
    }

    public int getExperienciaProximoNivel(){
        return this.experienciaProximoNivel;
    }

    public int getExperienciaAcumulada(){
        return this.experienciaAcumulada;
    }

    public double getMultiplicadorDano(){
        return this.multiplicadorDano;
    }

    private void mataPersonagem(){
        this.vivo = false;
    }

    public void printPersonagem(){
        System.out.print(" [(" + this.TIPO_PERSONAGEM +")(Nivel "+ this.nivel+ ")" + "(Vida " + this.quantidadeVida + "/" + this.vidaMaxima + ")" + "] ");
    }

    private void evoluirNivel() {
        this.nivel += 1;
        this.vidaMaxima += (this.vidaMaxima * 0.2);
        this.quantidadeVida = vidaMaxima;
        this.experienciaProximoNivel *= 2;
        this.experienciaAcumulada = 0;
        this.multiplicadorDano += (vidaMaxima * 0.2/ 100.0);
    }
    

    public void reduzirVida(int dano){
        this.quantidadeVida -= dano;
        if(quantidadeVida < 1){
            mataPersonagem();
        }
    }

    public void adicionarExperiencia(int quantidadeExperiencia){
        if(quantidadeExperiencia < 0)
            return;
        this.experienciaAcumulada += quantidadeExperiencia;
        if(this.experienciaAcumulada >= this.experienciaProximoNivel){
            int experienciaExtra = this.experienciaAcumulada - this.experienciaProximoNivel;
            evoluirNivel();
            adicionarExperiencia(experienciaExtra);
        }
    }

    public boolean isVivo(){
        return this.vivo;
    }
}
