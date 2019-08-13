/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.model.mock;

/**
 *
 * @author Dennis
 */
public class OrcamentoMock {
   private int id;
   private int cliente;
   private String nomeCliente;
   private int responsavel;
   private String dataInsercao;
   private String dataInicio;
   private String dataPrevista;
   private String dataFim;
   private String relatorio;
   private double valor;
   private double totalPago;

    public OrcamentoMock() {
    }

    public OrcamentoMock(int id) {
        this.id = id;
    }
   private int estado;

    public OrcamentoMock(int id, int cliente, int responsavel, String dataInsercao, String dataInicio, String dataPrevista, String dataFim, String relatorio, double valor, double totalPago, int estado) {
        this.id = id;
        this.cliente = cliente;
        this.responsavel = responsavel;
        this.dataInsercao = dataInsercao;
        this.dataInicio = dataInicio;
        this.dataPrevista = dataPrevista;
        this.dataFim = dataFim;
        this.relatorio = relatorio;
        this.valor = valor;
        this.totalPago = totalPago;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(int responsavel) {
        this.responsavel = responsavel;
    }

    public String getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(String dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
 
    
    
    
}
