package itens.coletavel;

public class Item {
    private String nome;
    private boolean usado;

    public Item(String nome) {
        this.nome = nome;
        this.usado = false;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public boolean isUsado() {
        return usado;
    }

    public void usaItem() {
        this.usado = true;
    }
}
