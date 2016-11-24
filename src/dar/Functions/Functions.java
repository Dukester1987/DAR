/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import javax.swing.JFrame;

/**
 *
 * @author ldulka
 */
public class Functions {
    private String string;
    
    public static void setFrameMinimumSize(JFrame frame){
        frame.setMinimumSize(frame.getSize());
    }
    
    public String md5(String param){
        string = param;
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        //m.reset();
        m.update(string.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
          hashtext = "0"+hashtext;
        }
        return hashtext;
    }    
    
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
                 result.append("\"");
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
