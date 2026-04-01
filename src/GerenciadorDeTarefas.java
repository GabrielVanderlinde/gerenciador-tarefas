import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {

    private List<Tarefas> listarTarefas = new ArrayList<>();
    // Variável que controla o próximo ID a ser atribuído.
    private int proximoId = 1;

    // UC02 - Anotar afazer
    public void adicionarTarefa(String titulo, String descricao, String data) {
        listarTarefas.add(new Tarefas(proximoId++, titulo, descricao, data));
        System.out.println("\n✔ Tarefa adicionada com sucesso!");
    }

    // UC03 - Editar afazer
    public void editarTarefa(int id, String novoTitulo, String novaDescricao, String novaData) {
        Tarefas tarefa = buscarPorId(id);// Busca a tarefa pelo ID.
        if (tarefa != null) {// Verifica se a tarefa foi encontrada.
            // Atualiza os dados da tarefa.
            tarefa.setTitulo(novoTitulo);
            tarefa.setDescricao(novaDescricao);
            tarefa.setData(novaData);
            System.out.println("\n✔ Tarefa atualizada com sucesso!"); // Mensagem de sucesso.
        } else {
            System.out.println("\n✘ ID não encontrado."); // Mensagem caso o ID não exista.
        }
    }

    // Listagem - método do diagrama.
    public void listarTarefas() {

        // Verifica se a lista está vazia.
        if (listarTarefas.isEmpty()) {
            System.out.println("\n Nenhuma tarefa cadastrada.");
            return;
        }
        System.out.println("\n===== LISTA DE TAREFAS =====");
        // Percorre a lista e imprime cada tarefa.
        for (Tarefas t : listarTarefas) {
            System.out.println(t);
        }
    }

    // UC05 - Concluir afazer
    public void concluirTarefa(int ID) {
        Tarefas tarefa = buscarPorId(ID); // Busca a tarefa pelo ID.
        if (tarefa != null) {
            tarefa.marcarComoConcluida(); // Marca a tarefa como concluída.
            System.out.println("\n✔ Tarefa concluída!");
            // Mostra a lista atualizada.
            listarTarefas();
        } else {
            System.out.println("\n✘ ID não encontrado."); // Mensagem caso o ID não exista.
        }
    }

    // UC04 - Excluir afazer
    public void removerTarefa(int ID) {
        // Verifica se existem tarefas cadastradas.
        if (listarTarefas.isEmpty()) {
            System.out.println("\n✘ Nenhuma tarefa para remover.");
            return;
        }
        // Remove a tarefa usando expressão lambda (remove pelo ID).
        boolean removida = listarTarefas.removeIf(t -> t.getId() == ID);
        // Verifica se a remoção ocorreu.
        if (removida) {
            System.out.println("\n✔ Tarefa removida.");
        } else {
            System.out.println("\n✘ ID não encontrado.");
        }
    }

    // Método auxiliar - listar apenas pendentes.
    public void listarPendentes() {
        System.out.println("\n===== TAREFAS PENDENTES =====");
        // Usa Stream para filtrar apenas tarefas não concluídas.
        listarTarefas.stream()
                .filter(t -> !t.isConcluida())// filtra pendentes.
                .forEach(System.out::println);// imprime cada uma das pendentes.
    }

    // Auxiliar interno
    private Tarefas buscarPorId(int id) {
        // Percorre a lista procurando o ID correspondente.
        for (Tarefas t : listarTarefas) {
            if (t.getId() == id) return t;// Retorna a tarefa encontrada.
        }
        // Retorna null caso não encontre.
        return null;
    }
}