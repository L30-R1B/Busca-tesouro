package missoes.progresso;

import entidades.Jogador;
import itens.coletavel.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mapa.Mapa;
import mapa.Sala;
import missoes.coletaveis.Diamante;
import missoes.coletaveis.Esmeralda;
import missoes.coletaveis.Rubi;

public class ColetarPedrasPreciosas extends Missao{
    private static final String OBJETIVO = "Colete pedras preciosas (diamante, esmeralda, rubi)";
    private static final int RECOMPENSA_EXPERIENCIA = 100;
    private static final int RECOMPENSA_OURO = 10;
    private static final int NUMERO_PEDRAS_PRECIOSAS = 5;

    public ColetarPedrasPreciosas() {
        super(NUMERO_PEDRAS_PRECIOSAS, OBJETIVO, RECOMPENSA_EXPERIENCIA, RECOMPENSA_OURO);
    }

    private List<Item> inicializaPedrasPreciosasExistentes(){
        List<Item> pedrasPreciosas = new ArrayList<>();
        pedrasPreciosas.add(new Diamante());
        pedrasPreciosas.add(new Esmeralda());
        pedrasPreciosas.add(new Rubi());
        
        return pedrasPreciosas;
    }

    @Override
    public void adaptarMissaoMapa() {
        List<Item> pedrasPreciosas = inicializaPedrasPreciosasExistentes();
        Random rand = new Random();
        for(int i = 0; i < 20; i ++){
            int indiceSala = rand.nextInt(Mapa.getInstancia().getNumSalas());
            while(indiceSala == Jogador.getInstancia().getSalaJogador()){
                indiceSala = rand.nextInt(Mapa.getInstancia().getNumSalas());
            }
            int indicePedraPreciosa = rand.nextInt(pedrasPreciosas.size());
            Mapa.getInstancia().SALAS[indiceSala].adicionaItemSala(pedrasPreciosas.get(indicePedraPreciosa));
        }
    }

    @Override
    public void limparMissaoMapa(){
        List<Item> pedrasPreciosas = inicializaPedrasPreciosasExistentes();
        for(Sala salaAtual : Mapa.getInstancia().SALAS){
            while(true){
                if(salaAtual.removeItemSala(pedrasPreciosas.get(0).getNome())){
                    continue;
                }
                if(salaAtual.removeItemSala(pedrasPreciosas.get(1).getNome())){
                    continue;
                }
                if(salaAtual.removeItemSala(pedrasPreciosas.get(2).getNome())){
                    continue;
                }
                break;
            }
        }
    }
}
