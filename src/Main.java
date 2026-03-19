import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== GERENCIADOR DE TAREFAS =====");
        if (!fazerLogin()) {
            System.out.println("Encerrando o sistema.");
            return;
        }
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        exibirMenu(gerenciador);
        scanner.close();
    }

    // UC01 - Login
    static boolean fazerLogin() {
        final String EMAIL_VALIDO = "admin@empresa.com";
        final String SENHA_VALIDA = "1234";
        int tentativas = 3;
        while (tentativas > 0) {
            System.out.print("\nE-mail: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            if (email.equals(EMAIL_VALIDO) && senha.equals(SENHA_VALIDA)) {
                System.out.println("\nBem-vindo(a)! Acesso liberado.");
                return true;
            } else {
                tentativas--;
                System.out.println("Login invalido. Tentativas: " + tentativas);
            }
        }
        return false;
    }

    // Menu principal
    static void exibirMenu(GerenciadorDeTarefas gerenciador) {
        int opcao;
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
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas numeros.");
                opcao = -1;
                continue;
            }
            switch (opcao) {
                case 1: // UC02
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descricao: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Data (dd/mm/aaaa): ");
                    String data = scanner.nextLine();
                    gerenciador.adicionarTarefa(titulo, descricao, data);
                    break;
                case 2:
                    gerenciador.listarTarefas();
                    break;
                case 3: // UC03
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
                        gerenciador.editarTarefa(idE, nt, nd, ndata);
                    } catch (NumberFormatException e) {
                        System.out.println("ID invalido.");
                    }
                    break;
                case 4: // UC05
                    gerenciador.listarPendentes();
                    System.out.print("ID da tarefa concluida: ");
                    try {
                        gerenciador.concluirTarefa(
                                Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("ID invalido.");
                    }
                    break;
                case 5: // UC04
                    gerenciador.listarTarefas();
                    System.out.print("ID para remover: ");
                    try {
                        int idR = Integer.parseInt(scanner.nextLine());
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
                case 0:
                    System.out.println("Sistema encerrado. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }
}