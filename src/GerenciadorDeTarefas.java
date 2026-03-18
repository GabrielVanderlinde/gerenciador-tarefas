import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeTarefas {

    private List<Tarefas> listarTarefas = new ArrayList<>();
    private int proximoId = 1;

    // UC02 - Anotar afazer
    public void adicionarTarefa(String titulo, String descricao, String data) {
        listarTarefas.add(new Tarefas(proximoId++, titulo, descricao, data));
        System.out.println("\n✔ Tarefa adicionada com sucesso!");
    }

    // UC03 - Editar afazer
    public void editarTarefa(int id, String novoTitulo, String novaDescricao, String novaData) {
        Tarefas tarefa = buscarPorId(id);
        if (tarefa != null) {
            tarefa.setTitulo(novoTitulo);
            tarefa.setDescricao(novaDescricao);
            tarefa.setData(novaData);
            System.out.println("\n✔ Tarefa atualizada com sucesso!");
        } else {
            System.out.println("\n✘ ID não encontrado.");
        }
    }

    // Listagem - método do diagrama
    public void listarTarefas() {
        if (listarTarefas.isEmpty()) {
            System.out.println("\n Nenhuma tarefa cadastrada.");
            return;
        }
        System.out.println("\n===== LISTA DE TAREFAS =====");
        for (Tarefas t : listarTarefas) {
            System.out.println(t);
        }
    }

    // UC05 - Concluir afazer
    public void concluirTarefa(int ID) {
        Tarefas tarefa = buscarPorId(ID);
        if (tarefa != null) {
            tarefa.marcarComoConcluida();
            System.out.println("\n✔ Tarefa concluída!");
            listarTarefas();
        } else {
            System.out.println("\n✘ ID não encontrado.");
        }
    }

    // UC04 - Excluir afazer
    public void removerTarefa(int ID) {
        if (listarTarefas.isEmpty()) {
            System.out.println("\n✘ Nenhuma tarefa para remover.");
            return;
        }
        boolean removida = listarTarefas.removeIf(t -> t.getId() == ID);
        if (removida) {
            System.out.println("\n✔ Tarefa removida.");
        } else {
            System.out.println("\n✘ ID não encontrado.");
        }
    }

    // Método auxiliar - listar apenas pendentes
    public void listarPendentes() {
        System.out.println("\n===== TAREFAS PENDENTES =====");
        listarTarefas.stream()
                .filter(t -> !t.isConcluida())
                .forEach(System.out::println);
    }

    // Auxiliar interno
    private Tarefas buscarPorId(int id) {
        for (Tarefas t : listarTarefas) {
            if (t.getId() == id) return t;
        }
        return null;
    }
}