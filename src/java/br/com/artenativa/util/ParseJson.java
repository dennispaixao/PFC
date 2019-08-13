/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author Dennis
 */
public class ParseJson implements ParseJsonInterface {

    @Override
    public String parseJson(Object object) {
      String retorno="{";
        
        Class <?> classe = object.getClass();
        Field[] campos = classe.getDeclaredFields();
        
        String nomeAtributo = "";
        Object valorAtributo = null;
        
        for(Field campo : campos){
            try{
                nomeAtributo = campo.getName();
                campo.setAccessible(true);
                valorAtributo = campo.get(object);
            }catch(IllegalAccessException | IllegalArgumentException e){}
            
            retorno += '"'+nomeAtributo+'"'+":\""+valorAtributo+"\",";                          
        }
        if (!"{".equals(retorno)){
            retorno = retorno.substring(0,retorno.length()-1);
        }
        return retorno +="}";
    }

   // para cada objeto no arrayList executa metodo parseJson(Objeto) para cada objeto no arrayList
   //contatena com [] colchetes para retornar um objeto com semantica de array
    @Override
    public String parseJson(ArrayList<Object> objects) {
        //retornará um array de objetos
        String retorno ="["; 
        //apica o metodo conversor de objetos para Json em cada Objeto Java contido no ArrayList
        retorno = objects.stream().map((obj) -> parseJson(obj)+",").reduce(retorno, String::concat);
        //se retorno não tiver Vazio retira a ultima virgula do objeto 
        if(!"".equals(retorno)){
            retorno = retorno.substring(0,retorno.length()-1);
        }  
        return retorno += "]";  
    }
    
  
    
}
