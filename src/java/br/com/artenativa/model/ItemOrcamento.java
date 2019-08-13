
package br.com.artenativa.model;

public class ItemOrcamento {
    
        private int id;
        private Orcamento orcamento;
        private String dataInicio;
        private String dataFim;
        private Float quantidade;
        private int status;
        private Produto produto;

    public ItemOrcamento() {
    }

    public ItemOrcamento(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento( Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    
}
