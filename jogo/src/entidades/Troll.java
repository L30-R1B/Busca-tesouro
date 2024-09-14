package entidades;

public class Troll extends Personagem{
    private static final String ASSINATURA_TROLL = "TROLL";
    private boolean segurandoMachado;

    public Troll(){
        super(ASSINATURA_TROLL);
        segurandoMachado = false;
    }

    public void seguraMachado(){
        this.segurandoMachado = true;
    }

    public void soltaMachado(){
        this.segurandoMachado = false;
    }

    public boolean getSegurandoMachado(){
        return this.segurandoMachado;
    }

    @Override
    public void printPersonagem(){
        super.printPersonagem();
        if(this.segurandoMachado) 
            System.out.print("(Machado equipado)");
    }
}
