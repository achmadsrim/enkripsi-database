/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proses;

import algo.RC4;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import algo.Cipher;

/**
 *
 * @author rockiericker
 */
public class Enkrip 
{

    private final RC4 rc4    = new RC4();
    private final Cipher cae = new Cipher();
    
    
    private String pesanKesalahan = "";
    private String namaFile;

    public String getNamaFile() 
    {
        return namaFile;
    }

    public String getPesanKesalahan() 
    {
        return pesanKesalahan;
    }

    public String enkripText(String plaintext, String password) 
    {
        String enkrip  = "";
        String Chipper = cae.encrypt(plaintext, 6).toString();
        String EnkripFile = Chipper + " Key : " + password;
//        String EnkripFile = plaintext + " Key : " + password;
        rc4.keyString(password);        
        char[] plaintextChar = rc4.encrypt(EnkripFile.toCharArray(), null);
        String encodeString = Base64.encode(new String(plaintextChar).getBytes());
        plaintextChar = encodeString.toCharArray();
        enkrip = new String(plaintextChar);
      //  enkrip = cae.encrypt(enkrip, 3).toString();
        return enkrip;
    }

    public String deskripText(String chipertext, String password) 
    {
        String deskrip = "";
        rc4.keyString(password);
//      chipertext = cae.decrypt(chipertext, 3).toString();
        try {
            byte[] decodeByte = Base64.decode(chipertext);
            char[] chperByte = new String(decodeByte).toCharArray();
            deskrip = new String(rc4.encrypt(chperByte, null));
            if (!deskrip.substring(deskrip.lastIndexOf("Key") + 6).equals(password)) 
            {
                pesanKesalahan = "Password Salah";
                return "";
            }
            deskrip = deskrip.substring(0, deskrip.lastIndexOf(" Key"));
            deskrip = cae.decrypt(deskrip, 6).toString();
        } 
        catch (Exception e) 
        {
            pesanKesalahan = "Password Salah";
            return "";
        }
        return deskrip;
    }
}