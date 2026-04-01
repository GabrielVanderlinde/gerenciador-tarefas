public class Tarefas {

    // Atributos
    private int id;
    private String titulo;
    private String descricao;
    private String data;
    private boolean concluida;

    // Construtor
    public Tarefas(int id, String titulo, String descricao, String data) {
        // Inicializa os atributos com os valores recebidos.
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        // Por padrão, toda tarefa começa como não concluída.
        this.concluida = false;
    }

    // Método do diagrama de classes - Que e para concluir metado
    public void marcarComoConcluida() {
        this.concluida = true;
    }

    // Getters - Retornam os valores dos atributos (leitura).
    public int getId()          { return id; }
    public String getTitulo()   { return titulo; }
    public String getDescricao(){ return descricao; }
    public String getData()     { return data; }
    public boolean isConcluida(){ return concluida; }

    // Setters para edição - Permitem alterar os dados da tarefa (edição).
    public void setTitulo(String titulo)    { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setData(String data)        { this.data = data; }

    // toString - Define como a tarefa será exibida ao imprimir o objeto.
    @Override
    public String toString() {
        String status = concluida ? "[CONCLUÍDA]" : "[PENDENTE]";
        return status + " ID: " + id +
                " | " + titulo +
                " | " + descricao +
                " | Data: " + data;
    }
}