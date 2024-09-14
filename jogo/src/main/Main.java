package main;

import comandos.ControladorComandos;
import comandos.acoes.AcaoTroll;
import entidades.Jogador;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ControladorComandos controlador = new ControladorComandos();
        Jogador jogador = Jogador.getInstancia();

        try (Scanner scanner = new Scanner(System.in)) {
            String comando;
            System.out.println("Digite 'help' para obter a lista de comandos.");
            System.out.println("Digite 'quit' para sair do jogo.");

            while (true) {
                System.out.println("\n\n\n\n\n\n\n\n");
                if (!Jogador.getInstancia().isVivo()) {
                    controlador.imprimirTelaGameOver();
                    return;
                } else if (jogador.getSalaJogador() == 20) {
                    controlador.imprimirTelaFinalPercurso();
                    return;
                }
                
                System.out.print("Player> ");
                comando = scanner.nextLine().trim();

                if (comando.contains("exit")) {
                    AcaoTroll acaoTroll = new AcaoTroll();
                    acaoTroll.tentarMovimentarTroll();
                    for (int i = 0; i < 20; i++) {
                        acaoTroll.tentarPegarMachado();
                    }
                    acaoTroll.tentarLancarMachado();
                }

                if (comando.equalsIgnoreCase("quit")) {
                    System.out.println("Saindo do jogo...");
                    break;
                }

                controlador.processarComando(comando);
            }
        }
    }
}
