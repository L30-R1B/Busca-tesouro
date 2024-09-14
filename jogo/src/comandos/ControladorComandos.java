package comandos;

import comandos.acoes.AcaoJogador;
import entidades.Jogador;
import itens.coletavel.Item;
import itens.coletavel.Ouro;
import mapa.Mapa;
import mapa.Sala;
import missoes.progresso.Missao;

public class ControladorComandos {

    private final AcaoJogador acaoJogador;

    public ControladorComandos() {
        this.acaoJogador = new AcaoJogador();
    }

    public void imprimirTelaGameOver() {
        String gameOverMessage = "GAME OVER";
        String linhaHorizontal = "═".repeat(gameOverMessage.length() + 10);
        
        System.out.println("╔" + linhaHorizontal + "╗");
        System.out.println("║" + " ".repeat(5) + gameOverMessage + " ".repeat(5) + "║");
        System.out.println("╚" + linhaHorizontal + "╝");
    }
    
    public void imprimirTelaFinalPercurso() {
        String finalMessage = "PARABÉNS! VOCÊ CHEGOU AO FINAL!";
        String linhaHorizontal = "═".repeat(finalMessage.length() + 10);
        
        System.out.println("╔" + linhaHorizontal + "╗");
        System.out.println("║" + " ".repeat(5) + finalMessage + " ".repeat(5) + "║");
        System.out.println("╚" + linhaHorizontal + "╝");
    }
    
    

    public void processarComando(String comando) {
        String[] partes = comando.split(" ");
        String acao = partes[0].toLowerCase();
    
        switch (acao) {
            case "view" -> {
                System.out.println("_____________________________________________________________________");
                acaoJogador.verSala();
                Jogador.getInstancia().printaInfoJogador();
                System.out.println("_____________________________________________________________________");
            }
            case "moveto" -> processarMoveto(partes);
            case "exit" -> processarExit(partes);
            case "pickup" -> processarPickUp(partes);
            case "drop" -> processarDrop(partes);
            case "throwaxe" -> {
                if (!acaoJogador.arremessarMachado()) {
                    System.out.println("Não foi possível arremessar o machado.");
                }
            }
            case "startmission" -> processaStartMission();
            case "delivermission" -> processaDeliverMission();
            case "abandonmission" -> processaAbandonMission();
            case "closedoor" -> processarFecharPorta(partes);
            case "opendoor" -> processarAbrirPorta(partes);
            case "help" -> exibirComandos();
            default -> System.out.println("Comando desconhecido. Use 'help' para ajuda.");
        }
    }
    

    private void processarMoveto(String[] partes) {
        if (partes.length > 1) {
            String destino = partes[1];
            if (destino.length() == 1) {
                char identificadorPorta = destino.charAt(0);
                acaoJogador.moveParaPorta(identificadorPorta);
            } else {
                acaoJogador.moverParaItem(destino);
            }
        } else {
            System.out.println("==== Comando de movimento inválido. Use 'moveto <item/porta>'. ====");
        }
    }

    private void processarExit(String[] partes) {
        if (partes.length > 1) {
            char identificadorPorta = partes[1].charAt(0);
            acaoJogador.atravessarPorta(identificadorPorta);
        } else {
            System.out.println("Comando 'exit' inválido. Use 'exit <porta>'.");
        }
    }

    private void processarPickUp(String[] partes) {
        if (partes.length > 1) {
            String itemNome = partes[1];
            
            Missao missaoAtual = Jogador.getInstancia().getMissaoAtual();
            if(missaoAtual != null){
                Item supostoItemMissao = missaoAtual.retornaItemMissaoCorrespondente(itemNome);
                if(supostoItemMissao != null){
                    acaoJogador.pegarItemMissao(supostoItemMissao);
                    return;
                }
            }
            if (itemNome.equalsIgnoreCase(new Ouro().getNome())) {
                acaoJogador.pegarOuro();
            } else {
                acaoJogador.pegarItem(itemNome);
            }
        } else {
            System.out.println("Comando 'pickup' inválido. Use 'pickup <item/ouro>'.");
        }
    }

    private void processarDrop(String[] partes) {
        if (partes.length > 1) {
            String itemNome = partes[1];
            if (itemNome.equalsIgnoreCase(new Ouro().getNome())) {
                acaoJogador.largarOuro();
                return;
            } 
            Item item = Jogador.getInstancia().getMissaoAtual().retornaItemMissaoCorrespondente(itemNome);
            if(item != null) {
                acaoJogador.largarItemMissao(item);
            }else {
                acaoJogador.largarItem(itemNome);
            }
        } else {
            System.out.println("Comando 'drop' inválido. Use 'drop <item>'.");
        }
    }

    private void processaStartMission(){
        Jogador jogador = Jogador.getInstancia();
        Sala sala = Mapa.getInstancia().SALAS[jogador.getSalaJogador()];
        if(sala.getMissaoDisponivel() != null){
            Jogador.getInstancia().pegarMissao(sala.getMissaoDisponivel(), jogador.getSalaMissao());
            System.out.println("==== Missão '" + sala.getMissaoDisponivel().getObjetivo() + "' aceita ====" );
        }
    }

    private void processaDeliverMission(){
        Jogador.getInstancia().cumprirMissao();
    }

    private void processaAbandonMission(){
        Jogador.getInstancia().desistirMissao();
    }

    private void processarFecharPorta(String[] partes) {
        if (partes.length > 1) {
            char identificadorPorta = partes[1].charAt(0);
            acaoJogador.fecharPorta(identificadorPorta);
        } else {
            System.out.println("Comando 'fechaporta' inválido. Use 'fechaporta <porta>'.");
        }
    }

    private void processarAbrirPorta(String[] partes) {
        if (partes.length > 1) {
            char identificadorPorta = partes[1].charAt(0);
            acaoJogador.abrirPorta(identificadorPorta);
        } else {
            System.out.println("Comando 'opendoor' inválido. Use 'opendoor <porta>'.");
        }
    }

    private void exibirComandos() {
        System.out.println("_____________________________________________________________________");
        System.out.println("Comandos disponíveis:");
        System.out.println("\tview - Mostra a descrição da sala atual.");
        System.out.println("\tmoveto <item/porta> - Move o jogador para um item ou porta específico.");
        System.out.println("\texit <porta> - Atravessa uma porta com o identificador especificado.");
        System.out.println("\tpickup <item/gold> - Pega um item ou ouro se estiver próximo.");
        System.out.println("\tdrop <item> - Larga um item se estiver carregando.");
        System.out.println("\tstartmission - Inicia missão caso tenha uma missão disponivel na sala.");
        System.out.println("\tdelivermission - Entrega a missão e recebe as recompensas.");
        System.out.println("\tabandonmission - Abandona/desiste da missão atual.");
        System.out.println("\tthrowaxe - Arremessa o machado se estiver carregando.");
        System.out.println("\tclosedoor <porta> - Fecha uma porta se estiver carregando uma poção.");
        System.out.println("\topendoor <porta> - Abre uma porta se estiver carregando uma chave.");
        System.out.println("\thelp - Mostra esta ajuda.");
        System.out.println("\tquit - Fecha jogo.");
        System.out.println("_____________________________________________________________________");
    }
}
