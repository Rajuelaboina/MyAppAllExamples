package com.phycaresolutions.mymap;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESHelper {
    private static String key = "riakjluVs/@qpUh#";
    private static final String initVector = "jsKidmshatyb4jdu"; //"x1s7k8t3F9C5o3W6"
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) throws Exception {
        String input="PersonnelAssessment";
        String strsalt = "";

        for (int i = 0; i < 15; i++)
        {
            if (i < input.length()) {
                strsalt = strsalt + "P@@" + input.substring(i, i+1) + ",";


            }
            else
                strsalt = strsalt + "P@@Z,";
            if (i == 14)
            {
                strsalt = strsalt.substring(1, strsalt.length() - 1);

            }
        }
        System.out.println("salt : " + strsalt);
        System.out.println("salt : " + strsalt.substring(0,16));
        String en2 = encrypt("hello raki");
        System.out.println("Encrypted en2 - " +en2);
        System.out.println("After decrypt2 - " +decrypt("ghqyzyzBJ4yBGKa/7IDoWuXv5weend4E+qxDJuFFcOQ="));

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            //SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            SecretKey skeySpec = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] raw = Base64.getDecoder().decode(encrypted);
            byte [] results = cipher.doFinal(raw);
            return new String(results,"UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
