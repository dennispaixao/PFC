/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.artenativa.util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {
     
      public static String md5(String senha){
          String sen;
          MessageDigest md = null;
          try{
              md = MessageDigest.getInstance("MD5");
          }catch(NoSuchAlgorithmException e){
          }
          BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
          sen = hash.toString(16);
          return sen;
      }

    
   
    
    
    
}
