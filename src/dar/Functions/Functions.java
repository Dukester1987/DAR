/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 *
 * @author ldulka
 */
public class Functions {
    
   public static String forHTML(String aText){
     final StringBuilder result = new StringBuilder();
     final StringCharacterIterator iterator = new StringCharacterIterator(aText);
     char character =  iterator.current();
     while (character != CharacterIterator.DONE ){
       if (character == '<') {
         result.append("");
       }
       else if (character == '>') {
         result.append("");
       }
       else if (character == '&') {
         result.append("");
      }
       else if (character == '\"') {
         result.append("");
       }
       else if (character == '\t') {
         addCharEntity(9, result);
       }
       else if (character == '!') {
         addCharEntity(33, result);
       }
       else if (character == '#') {
         addCharEntity(35, result);
       }
       else if (character == '$') {
         addCharEntity(36, result);
       }
       else if (character == '%') {
         addCharEntity(37, result);
       }
       else if (character == '\'') {
         addCharEntity(39, result);
       }
       else if (character == '(') {
         addCharEntity(40, result);
       }
       else if (character == ')') {
         addCharEntity(41, result);
       }
       else if (character == '*') {
         addCharEntity(42, result);
       }
       else if (character == '?') {
         addCharEntity(63, result);
       }
       else if (character == '@') {
         addCharEntity(64, result);
       }
       else if (character == '[') {
         addCharEntity(91, result);
       }
       else if (character == '\\') {
         addCharEntity(92, result);
       }
       else if (character == ']') {
         addCharEntity(93, result);
       }
       else if (character == '^') {
         addCharEntity(94, result);
       }
       else if (character == '`') {
         addCharEntity(96, result);
       }
       else if (character == '{') {
         addCharEntity(123, result);
       }
       else if (character == '|') {
         addCharEntity(124, result);
       }
       else if (character == '}') {
         addCharEntity(125, result);
       }
       else if (character == '~') {
         addCharEntity(126, result);
       }
       else {
         //the char is not a special one
         //add it to the result as is
         result.append(character);
       }
       character = iterator.next();
     }
     return result.toString();
  }    
   
  private static void addCharEntity(Integer aIdx, StringBuilder aBuilder){
    String padding = "";
    if( aIdx <= 9 ){
       padding = "00";
    }
    else if( aIdx <= 99 ){
      padding = "0";
    }
    else {
      //no prefix
    }
    String number = padding + aIdx.toString();
    aBuilder.append("");
  }   
    
}
