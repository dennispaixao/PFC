/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dennis
 */
public class ParseDates {
    
    //vinda do navegador para unix
    public static Long parseUnix(String data) {
        Long retorno = 0L;

        try {
            String[] sData = data.split("-");
            int dia = Integer.parseInt(sData[2]);
            int mes = Integer.parseInt(sData[1]) - 1;//mes inicia em 0
            int ano = Integer.parseInt(sData[0]);
            //cria um date com Calendar.Builder.setDate passando os parametros
            Calendar.Builder b = new Calendar.Builder().setDate(ano, mes, dia);
            //recupera o hash unix
            Long u = b.build().getTime().getTime();
            //formata o unix time com menos 3 caracteres e converte pra string
            String unix = u.toString().substring(0, u.toString().length() - 3);
            retorno = Long.parseLong(unix);
        } catch (NumberFormatException e) {
        }

        return retorno;
    }
    //formata data em unix para exibir na tela
    public static String formatUnixToDisplay(String unix){
        Long unixSeconds = 0L;
        try{
            unixSeconds =Long.parseLong(unix);
        }catch(NumberFormatException e){}
        
        Date date = new java.util.Date(unixSeconds*1000L); 
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm"); 
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-3"));
        String dtCad = sdf.format(date);
        return dtCad;
    }
    

   //formata a data de unix para formato aceito pelo input date dos navegadores
    public static String formatUnixToBrowser(String unix){
             Long unixSeconds = 0L;
        try{
            unixSeconds =Long.parseLong(unix);
        }catch(NumberFormatException e){}
        
        Date date = new java.util.Date(unixSeconds*1000L); 
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-3"));
        String dtCad = sdf.format(date);
        return dtCad;  
    }
    
    
    public static Long getNowUnix(){ 
        String dataCad = ""+Calendar.getInstance().getTime().getTime();
        dataCad = dataCad.substring(0, dataCad.length()-3);    
        return Long.parseLong(dataCad);
    }

}
