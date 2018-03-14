package com.example.a13251.suoping;


import android.util.Base64;
import android.util.Log;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by 13251 on 2018/3/12.
 */

public class Encrypt {
    private String modulusString="93510190618404480112456739142683947685791350996039849678517366015080163828283405155424588176883145348843573201615106423089973665581659426816833547797523442618687757080539327278020807455600664612345042310302559375391808991419933663227866659016228806380539601877601845271672806579915036360111363980696331519027";
    private String publicExponentString="65537";
    public String encrypt(String text){
        try {
            Log.d("text",text);
            Cipher cipher=Cipher.getInstance("RSA/ECB/NoPadding");
            PublicKey publicKey=getPublickey();
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] temp=cipher.doFinal(text.getBytes());
            return Base64encode(temp);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return  null;
    }
    private PublicKey getPublickey(){
        try {
            BigInteger modulus=new BigInteger(modulusString);
            BigInteger publicE=new BigInteger(publicExponentString);
            RSAPublicKeySpec publicKeySpec=new RSAPublicKeySpec(modulus,publicE);
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String Base64encode(byte[] con){
        return new String(Base64.encode(con,Base64.NO_WRAP));
    }
}
