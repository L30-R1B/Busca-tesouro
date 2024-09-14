package itens.coletavel;

public class Machado extends Item{
    private static final String ASSINATURA_MACHADO = "machado";
    private int danoBase;
    
    public Machado(){
        super(ASSINATURA_MACHADO);
        this.danoBase = 80;
    }

    public int getDanoBase(){
        return this.danoBase;
    }
}
