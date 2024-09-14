package mapa;

import entidades.Troll;
import itens.coletavel.Item;
import java.util.ArrayList;
import java.util.List;
import missoes.progresso.Missao;

public class Sala {
    private static final int MAX_OBJETOS_SALA = 20;
    private final List<Troll> TROLLS;
    private final List<Porta> PORTAS;
    private final List<Item> ITENS_DISPONIVEIS;
    private Missao missaoDisponivel;

    public Sala() {
        this.TROLLS = new ArrayList<>();
        this.PORTAS = new ArrayList<>();
        this.ITENS_DISPONIVEIS = new ArrayList<>();
        this.missaoDisponivel = null;
    }

    public Porta getPorta(char identificadorPorta) {
        for (Porta porta : PORTAS) {
            if (porta.getIdentificador() == identificadorPorta) {
                return porta;
            }
        }
        return null;
    }

    public boolean adicionaTrollSala() {
        if (TROLLS.size() < MAX_OBJETOS_SALA) {
            return TROLLS.add(new Troll());
        }
        return false;
    }

    public boolean removeTrollSala() {
        if(TROLLS.isEmpty()){
            return false;
        }
        TROLLS.remove(0);
        return true;
    }

    public List<Troll> getTrolls() {
        return new ArrayList<>(TROLLS);
    }

    public Troll getTroll() {
        if (!TROLLS.isEmpty()) {
            return TROLLS.get(0);
        }
        return null;
    }

    public boolean adicionaPortaSala(Porta porta) {
        if (PORTAS.size() < MAX_OBJETOS_SALA) {
            return PORTAS.add(porta);
        }
        return false;
    }

    public boolean adicionaItemSala(Item item) {
        if (ITENS_DISPONIVEIS.size() < MAX_OBJETOS_SALA) {
            return ITENS_DISPONIVEIS.add(item);
        }
        return false;
    }

    public boolean removeItemSala(String nome) {
        for (Item item : ITENS_DISPONIVEIS) {
            if (item.getNome().equals(nome)) {
                return ITENS_DISPONIVEIS.remove(item);
            }
        }
        return false;
    }

    public boolean verificaExistenciaPorta(char identificadorPorta) {
        for (Porta porta : PORTAS) {
            if (porta.getIdentificador() == identificadorPorta) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaExistenciaItem(String nomeItem) {
        for (Item item : ITENS_DISPONIVEIS) {
            if (item.getNome().equals(nomeItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaExistenciaTroll() {
        return !TROLLS.isEmpty();
    }

    private void printaListaObjetos(String titulo, List<?> lista) {
        System.out.print(titulo + " : ");
        for (Object obj : lista) {
            if (obj instanceof Item) {
                System.out.print(" [" + ((Item) obj).getNome() + "] ");
            } else if (obj instanceof Troll) {
                ((Troll) obj).printPersonagem();
            } else if (obj instanceof Porta) {
                ((Porta) obj).printaPorta();
            }
        }
        System.out.println();
    }

    public void definirMissao(Missao missao) {
        this.missaoDisponivel = missao;
    }

    public void removeMissao(){
        this.missaoDisponivel = null;
    }

    public Missao getMissaoDisponivel(){
        return this.missaoDisponivel;
    }

    public void printaInfoSala() {
        System.out.println("-----------------------------\n");
        if(missaoDisponivel != null)
            System.out.println("Missão disponível : " + missaoDisponivel.getObjetivo());
        printaListaObjetos("Itens disponíveis na sala", ITENS_DISPONIVEIS);
        printaListaObjetos("Criaturas presentes na sala", TROLLS);
        printaListaObjetos("Portas presentes na sala", PORTAS);
        System.out.println("-----------------------------\n");
    }
}
