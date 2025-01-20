package com.phycaresolutions.mymap.encriptanddecript;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESAlgorithm {
    private static String key = "riakjluVs/@qpUh#";
    private static final String initVector = "jsKidmshatyb4jdu"; //"x1s7k8t3F9C5o3W6"
    private static String secretKey = "riakjluVs/@qpUh#";
    private static String salt = "Personnel Assessment";
    public AESAlgorithm(){

    }
    @SuppressLint("NewApi")
    public static void main(String[] args) throws Exception {
        String input = "pippo";
        System.out.println("input:" + input);
        String str="";
       //StringBuffer stringBuffer=new StringBuffer(input);
       // str=stringBuffer.reverse().toString();
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
         //key=strsalt;
        //salt=strsalt;
        //System.out.println("strsalt:" + strsalt);
        StringBuffer stringBuffer=new StringBuffer(strsalt);
        strsalt=stringBuffer.reverse().toString();
        //System.out.println("strsalt:" + strsalt);
        /*String ss="@x!r^f#d3k@y$8&*";
        String extra="l!VCX#$&NdayVqYf";
        if (input.length()>16) {
            for (int i = 0; i < 16; i++) {
                str = str + input.charAt(i) + ss.charAt(i);
            }
        }else {
            for (int i = 0; i < 16; i++) {
                str = str + extra.charAt(i) + ss.charAt(i);
            }
        }*/
        //System.out.println("strsalt:" + str);
        String encryptedString = Encrypt(input);
        System.out.println("Encrypted String - " + encryptedString);
        // String decryptedString = decrypt("C6bsDpuArwTiDUt7VhNtbg==");
        //StringBuffer stringBuffer1=new StringBuffer(decryptedString.replace("@@P","").replace("@@","").replace(",",""));
        //System.out.println("After decryption - " +"C6bsDpuArwTiDUt7VhNtbg==");
        System.out.println("After decryption - " +Decrypt(encryptedString));
        System.out.println("After Decryption - " +Decrypt("3lOQfzFoEqGCpZyokhj6Pg=="));
        String en2 = encrypt2("hianu");
        System.out.println("Encrypted en2 - " + en2);
        System.out.println("After decrypt2 - " +decrypt2("3lOQfzFoEqGCpZyokhj6Pg=="));
        String en = Encrypt("hianu");
        System.out.println("Encrypted String - " + en);
    }
    @SuppressLint("NewApi")
    public static String encrypt(String value) {
          try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
             //Key key1=new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
              @SuppressLint({"NewApi", "LocalSuppress"})
              Base64.Encoder encoder = Base64.getEncoder();
              return encoder.encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
        /*try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            //System.out.println("getBytes - " + cipher.doFinal(value.getBytes("UTF-8")));
            StringBuffer sb = new StringBuffer();
            //Converting string to character array
            char ch[] =Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes("UTF-8"))).toCharArray();
            for(int i = 0; i < ch.length; i++) {
                String hexString = Integer.toHexString(ch[i]);
                sb.append(hexString);
            }
            String result = sb.toString();
            System.out.println(result);

           *//* SecretKeyFactory kf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec keySpec = new PBEKeySpec(salt.toCharArray());
            SecretKey key = kf.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("PBKDF2WithHmacSHA256");
            cipher.init(Cipher.ENCRYPT_MODE, key);*//*
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;*/
    }
    @SuppressLint("NewApi")
    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes());
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ecipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] raw = Base64.getDecoder().decode(encrypted);
            byte[] originalBytes = ecipher.doFinal(raw);
            String original = new String(originalBytes, "UTF8");
            return original;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
         return null;
        /*try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            System.out.println(">>>>spec>>>>>>>: "+spec);
            SecretKey tmp = factory.generateSecret(spec);
            System.out.println(">>>>>>temp>>>>>>>>: "+tmp);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            System.out.println(">>>>>>secretKey>>>>>>>>: "+secretKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;*/
    }
    @SuppressLint("NewApi")
    public static String Decrypt(String text) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
        byte[] raw = Base64.getDecoder().decode(text);
        byte [] results = cipher.doFinal(raw);
        return new String(results,"UTF-8");
    }
    @SuppressLint("NewApi")
    public static String Encrypt(String text)throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);
        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(results);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt2(String encrypted) {
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
    public static String encrypt2(String value) {
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
