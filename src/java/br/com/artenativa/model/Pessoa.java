/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.model;

/*
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;*/
import java.util.ArrayList;


/**
 *
 * @author Dennis
 */
public class Pessoa {
    private int id;
    private String nome;
    private String sobrenome;
    private String sexo;
    private String DataCadastro;
    private int situacao;
    private String rg;
    private String cpf;
    private String cnpj;
    private String email;
    private String telefone;
    private String celular;
    private Endereco endereco;

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
       
      
    private ArrayList<Documento> documentos;
    private ArrayList<Endereco> enderecos;
    private ArrayList<Telefone> telefones;
    private ArrayList<Email> emails;
    
    
    public Pessoa(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
    
    
    public String getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(String DataCadastro) {
         this.DataCadastro = DataCadastro;
       
    }
   
   

    public Pessoa(int id) {
        this.id = id;
    }

    public Pessoa() {
    }

    public boolean addDocumento(Documento doc){
         return this.documentos.add(doc);            
    }
      public boolean addTelefone(Telefone tel){
         return this.telefones.add(tel);            
    }
    
      public boolean addEndereco(Endereco end){
         return this.enderecos.add(end);            
    }   
      public boolean addEmail(Email email){
         return this.emails.add(email);            
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(ArrayList<Documento> documentos) {
       this.documentos = documentos;
    }


    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

     public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
 

  /*  public void setDatanasc(String data) {
      DateFormat mascara = new SimpleDateFormat("dd/MM/yyyyy");
      Date dataEscolhida; 
        try {
            dataEscolhida = (Date) mascara.parse(data);
            this.datanasc = dataEscolhida;
        } catch (ParseException ex) {
         
        
    }*/
    
}
