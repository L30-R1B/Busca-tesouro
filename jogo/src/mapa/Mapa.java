package mapa;

import itens.coletavel.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import missoes.progresso.ColetarPedrasPreciosas;
import missoes.progresso.ColetarTesouro;
import missoes.progresso.EliminarTrolls;
import missoes.progresso.Missao;

public class Mapa {
    private static Mapa instancia;
    private static final int NUMERO_SALAS = 21;
    private static final int QTD_OURO = 30;
    private static final int QTD_TROLLS = 0;
    private static final int QTD_ITENS = 80;
    public final Sala[] SALAS;

    private Mapa() {
        this.SALAS = new Sala[NUMERO_SALAS];
        inicializaSalas();
        preencherSalas();
        inicializaPortasMapa();
        fechaAlgumasPortas();
    }

    public int getNumSalas(){
        return NUMERO_SALAS;
    }

    public static Mapa getInstancia() {
        if (instancia == null) {
            instancia = new Mapa();
        }
        return instancia;
    }

    private void inicializaSalas() {
        for (int salaAtual = 0; salaAtual < NUMERO_SALAS; salaAtual++) {
            this.SALAS[salaAtual] = new Sala();
        }
    }

    private void preencherSalas() {
        List<Item> itens = inicializaItensExistentes();
        distribuirItens(itens);
        distribuirOuro();
        distribuirTrolls();
        distribuirMissoes();
    }

    private List<Item> inicializaItensExistentes() {
        List<Item> itens = new ArrayList<>();
        itens.add(new Machado());
        itens.add(new Pocao());
        itens.add(new Chave('A'));
        itens.add(new Chave('B'));
        itens.add(new Chave('C'));
        return itens;
    }

    private List<Missao> inicializaMissoesExistentes(){
        List<Missao> missoes = new ArrayList<>();
        missoes.add(new ColetarPedrasPreciosas());
        missoes.add(new EliminarTrolls());
        missoes.add(new ColetarTesouro());

        return missoes;
    }

    private static Item gerarItemAleatorio(List<Item> itens) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(itens.size());
        return itens.get(indiceAleatorio);
    }

    private void distribuirItens(List<Item> itens) {
        Random rand = new Random();
        for (int i = 0; i < QTD_ITENS; i++) {
            Item itemParaInserir = gerarItemAleatorio(itens);
            int indiceSala = rand.nextInt(NUMERO_SALAS);
            this.SALAS[indiceSala].adicionaItemSala(itemParaInserir);
        }
    }

    private void distribuirOuro() {
        Random rand = new Random();
        for (int i = 0; i < QTD_OURO; i++) {
            int indiceSala = rand.nextInt(NUMERO_SALAS);
            this.SALAS[indiceSala].adicionaItemSala(new Ouro());
        }
    }

    private void distribuirTrolls() {
        Random rand = new Random();
        for (int i = 0; i < QTD_TROLLS; i++) {
            int indiceSala = rand.nextInt(NUMERO_SALAS);
            this.SALAS[indiceSala].adicionaTrollSala();
        }
    }

    private void distribuirMissoes(){
        Random rand = new Random();
        List<Missao> missoes = inicializaMissoesExistentes();
        for(int i = 0; i < 20; i ++){
            int indiceSala = rand.nextInt(NUMERO_SALAS);
            while(this.SALAS[indiceSala].getMissaoDisponivel() != null){
                indiceSala = rand.nextInt(NUMERO_SALAS);
            }
            int indiceMissao = rand.nextInt(missoes.size());
            this.SALAS[indiceSala].definirMissao(missoes.get(indiceMissao));
        }
    }

    private void inicializaPortasMapa() {
        adicionarPortaBidirecional(0, 'A', 1);
        adicionarPortaBidirecional(0, 'B', 5);
        adicionarPortaBidirecional(1, 'B', 2);
        adicionarPortaBidirecional(2, 'A', 3);
        adicionarPortaBidirecional(3, 'B', 8);
        adicionarPortaBidirecional(4, 'A', 9);
        adicionarPortaBidirecional(5, 'A', 6);
        adicionarPortaBidirecional(6, 'B', 7);
        adicionarPortaBidirecional(6, 'C', 11);
        adicionarPortaBidirecional(7, 'A', 12);
        adicionarPortaBidirecional(8, 'C', 9);
        adicionarPortaBidirecional(9, 'B', 14);
        adicionarPortaBidirecional(10, 'A', 11);
        adicionarPortaBidirecional(10, 'B', 15);
        adicionarPortaBidirecional(12, 'A', 17);
        adicionarPortaBidirecional(13, 'A', 14);
        adicionarPortaBidirecional(14, 'C', 19);
        adicionarPortaBidirecional(15, 'A', 16);
        adicionarPortaBidirecional(16, 'B', 17);
        adicionarPortaBidirecional(18, 'A', 19);
        adicionarPortaBidirecional(18, 'B', 20);
    }

    private void fechaAlgumasPortas() {
        Random rand = new Random();
        char[] identificadoresPortas = {'A', 'B', 'C'};
    
        for (int i = 0; i < NUMERO_SALAS; i ++) {
            List<Porta> portasSala = new ArrayList<>();
    
            for (char identificador : identificadoresPortas) {
                Porta porta = SALAS[i].getPorta(identificador);
                if (porta != null) {
                    portasSala.add(porta);
                }
            }
    
            int portasParaFechar = rand.nextInt(Math.max(1, portasSala.size() / 2 + 1));
    
            for (int j = 0; j < portasParaFechar; j++) {
                int indicePorta = rand.nextInt(portasSala.size());
                Porta portaSelecionada = portasSala.get(indicePorta);
                fecharPortaBidirecional(i, portaSelecionada.getIdentificador(), portaSelecionada.getSalaDestino());
            }
        }
    }

    public boolean fecharPortaBidirecional(int indiceSalaUm, char identificadorPorta, int indiceSalaDois){
        this.SALAS[indiceSalaUm].getPorta(identificadorPorta).fechaPorta();
        this.SALAS[indiceSalaDois].getPorta(identificadorPorta).fechaPorta();
        return true;
    }

    public boolean abrirPortaBidirecional(int indiceSalaUm, char identificadorPorta, int indiceSalaDois){
        this.SALAS[indiceSalaUm].getPorta(identificadorPorta).abrePorta();
        this.SALAS[indiceSalaDois].getPorta(identificadorPorta).abrePorta();
        return true;
    }

    public Sala getSala(int numSala){
        return this.SALAS[numSala];
    }

    private void adicionarPortaBidirecional(int sala1, char identificador, int sala2) {
        this.SALAS[sala1].adicionaPortaSala(new Porta(identificador, sala1, sala2));
        this.SALAS[sala2].adicionaPortaSala(new Porta(identificador, sala2, sala1));
    }

    public void printaMapa() {
        int numSala = 0;
        for (Sala salaAtual : this.SALAS) {
            System.out.println("SALA NUM : " + numSala);
            if (salaAtual != null) {
                salaAtual.printaInfoSala();
            }
            numSala++;
        }
    }
}
