public class Tarefas {

    private int id;
    private String titulo;
    private String descricao;
    private String data;
    private boolean concluida;

    public Tarefas(int id, String titulo, String descricao, String data) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.concluida = false;
    }

    // Método do diagrama de classes
    public void marcarComoConcluida() {
        this.concluida = true;
    }

    // Getters
    public int getId()          { return id; }
    public String getTitulo()   { return titulo; }
    public String getDescricao(){ return descricao; }
    public String getData()     { return data; }
    public boolean isConcluida(){ return concluida; }

    // Setters para edição
    public void setTitulo(String titulo)    { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setData(String data)        { this.data = data; }

    @Override
    public String toString() {
        String status = concluida ? "[CONCLUÍDA]" : "[PENDENTE]";
        return status + " ID: " + id +
                " | " + titulo +
                " | " + descricao +
                " | Data: " + data;
    }
}