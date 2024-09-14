package itens.coletavel;

public class Chave extends Item{
    private static final String ASSINATURA_CHAVE = "chave";
    private final char TIPO_CHAVE;

    public Chave(char tipoChave){
        super(ASSINATURA_CHAVE);
        this.TIPO_CHAVE = tipoChave;
    }

    public char getTipoChave(){
        return this.TIPO_CHAVE;
    }

    @Override
    public String getNome() {
        return super.getNome() + "(" + this.TIPO_CHAVE + ")";
    }
}
