/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.util;

/**
 *
 * @author Dennis
 */
public interface IValidator {
    
  public String validaString(String string,String nomeDoCampo, int minLenght,int maxLenght, boolean canBeNum);
  public String validaCPF(String cpf);
  public String validaRg(String rg);
  public String validaCep(String cep);
  public String validaTelefone(String cpf);
  public String validaNumero(int num, int minLenght, int maxLenght);
  public String validaCharset(String character, char[] charValid, String nomeDoCampo);
}
