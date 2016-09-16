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
         switch (character) {
             case '<':
                 result.append("<");
                 break;
             case '>':
                 result.append(">");
                 break;
             case '&':
                 result.append("&");
                 break;
             case '\"':
                 result.append("\'\"");
                 break;
             case '\t':
                 result.append("");
                 break;
             case '!':
                 result.append("!");
                 break;
             case '#':
                 result.append("#");
                 break;
             case '$':
                 result.append("$");
                 break;
             case '%':
                 result.append("%");
                 break;
             case '\'':
                 result.append("\'\'");
                 break;
             case '(':
                 result.append("(");
                 break;
             case ')':
                 result.append(")");
                 break;
             case '*':
                 result.append("*");
                 break;
             case '?':
                 result.append("?");
                 break;
             case '@':
                 result.append("@");
                 break;
             case '[':
                 result.append("[");
                 break;
             case '\\':
                 result.append("\\");
                 break;
             case ']':
                 result.append("]");
                 break;
             case '^':
                 result.append("^");
                 break;
             case '`':
                 result.append("`");
                 break;
             case '{':
                 result.append("{");
                 break;
             case '|':
                 result.append("|");
                 break;
             case '}':
                 result.append("}");
                 break;
             case '~':
                 result.append("~");
                 break;
             default:
                 //the char is not a special one
                 //add it to the result as is
                 result.append(character);
                 break;
         }
       character = iterator.next();
     }
     return result.toString();
  }    
    
}
