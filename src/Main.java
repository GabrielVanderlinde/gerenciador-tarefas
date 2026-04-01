import java.util.Scanner;

public class Main {

    // Objeto Scanner para leitura de dados do usuário.
    static Scanner scanner = new Scanner(System.in);

    // Método principal (ponto de entrada do programa)
    public static void main(String[] args) {
        System.out.println("===== GERENCIADOR DE TAREFAS =====");

        // Chama a função de login. Se falhar, encerra o programa.
        if (!fazerLogin()) {
            System.out.println("Encerrando o sistema.");
            return;
        }
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();// Cria uma instância do gerenciador de tarefas.
        exibirMenu(gerenciador);// Exibe o menu principal.
        scanner.close();// Fecha o scanner ao final do programa.
    }

    // UC01 - Login
    static boolean fazerLogin() {
        // Credenciais fixas para acesso ao sistema.
        final String EMAIL_VALIDO = "admin@empresa.com";
        final String SENHA_VALIDA = "1234";
        int tentativas = 3;// Número máximo de tentativas.

        // Loop enquanto ainda houver tentativas.
        while (tentativas > 0) {
            System.out.print("\nE-mail: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            // Verifica se os dados estão corretos.
            if (email.equals(EMAIL_VALIDO) && senha.equals(SENHA_VALIDA)) {
                System.out.println("\nBem-vindo(a)! Acesso liberado.");
                return true;
            } else {
                tentativas--;
                System.out.println("Login invalido. Tentativas: " + tentativas);
            }
        }

        return false; // Retorna falso se esgotar as tentativas.
    }

    // Menu principal do sistema.
    static void exibirMenu(GerenciadorDeTarefas gerenciador) {
        int opcao; // Variável para armazenar a opção escolhida.

        // Loop do menu (executa até o usuário escolher sair).
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Editar tarefa");
            System.out.println("4 - Concluir tarefa");
            System.out.println("5 - Remover tarefa");
            System.out.println("0 - Encerrar programa");
            System.out.print("Opcao: ");
            try {
                // Converte a entrada do usuário para número.
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Trata erro caso o usuário digite algo inválido.
                System.out.println("Digite apenas numeros.");
                opcao = -1;
                continue;
            }
            // Estrutura de decisão baseada na opção escolhida.
            switch (opcao) {
                case 1: // UC02 - adicionar tarefas.
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descricao: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Data (dd/mm/aaaa): ");
                    String data = scanner.nextLine();

                    // Chama o método para adicionar tarefa.
                    gerenciador.adicionarTarefa(titulo, descricao, data);
                    break;
                case 2: // listar tarefas.
                    gerenciador.listarTarefas();// Mostra tarefas antes de editar.
                    break;
                case 3: // UC03 - Editar tarefa.
                    gerenciador.listarTarefas();
                    System.out.print("ID para editar: ");
                    try {
                        int idE = Integer.parseInt(scanner.nextLine());
                        System.out.print("Novo titulo: ");
                        String nt = scanner.nextLine();
                        System.out.print("Nova descricao: ");
                        String nd = scanner.nextLine();
                        System.out.print("Nova data: ");
                        String ndata = scanner.nextLine();
                        // Chama o método para editar a tarefa.
                        gerenciador.editarTarefa(idE, nt, nd, ndata);
                    } catch (NumberFormatException e) {
                        System.out.println("ID invalido.");
                    }
                    break;
                case 4: // UC05 - Concluir tarefa.
                    gerenciador.listarPendentes();// Mostra apenas tarefas pendentes.

                    System.out.print("ID da tarefa concluida: ");
                    try {
                        // Marca a tarefa como concluída.
                        gerenciador.concluirTarefa(
                                Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("ID invalido.");
                    }
                    break;
                case 5: // UC04 - Remover tarefa.
                    gerenciador.listarTarefas();
                    System.out.print("ID para remover: ");
                    try {
                        int idR = Integer.parseInt(scanner.nextLine());
                        // Confirmação antes de remover.
                        System.out.print("Tem certeza? (s/n): ");
                        if (scanner.nextLine().equalsIgnoreCase("s")) {
                            gerenciador.removerTarefa(idR);
                        } else {
                            System.out.println("Remocao cancelada.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID invalido.");
                    }
                    break;
                case 0: // Encerrar o programa.
                    System.out.println("Sistema encerrado. Ate logo!");
                    break;
                default:
                    // Caso o usuário digite uma opção invalida.
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0); // Continua até escolher sair
    }
}