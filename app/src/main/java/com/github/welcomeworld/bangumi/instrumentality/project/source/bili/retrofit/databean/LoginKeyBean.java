package com.github.welcomeworld.bangumi.instrumentality.project.source.bili.retrofit.databean;

import android.util.Base64;

import com.google.gson.annotations.SerializedName;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

@SuppressWarnings("unused")
public class LoginKeyBean {

    @SerializedName("ts")
    private long ts;
    @SerializedName("code")
    private long code;
    @SerializedName("data")
    private AuthKey data;

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public AuthKey getData() {
        return data;
    }

    public void setData(AuthKey data) {
        this.data = data;
    }

    public static class AuthKey {
        @SerializedName("hash")
        private String hash;
        @SerializedName("key")
        private String key;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String encypt(String password,String RSA){
            StringBuilder rsaBuilder = new StringBuilder();
            for(String temp : RSA.split("\n")){
                if(!temp.startsWith("-")){
                    rsaBuilder.append(temp);
                }
            }
            PublicKey publicKey;
            try {
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(rsaBuilder.toString().getBytes(),Base64.DEFAULT));
                publicKey = KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
            } catch (Exception e) {
                return "";
            }

            //加密密码
            String cipheredPassword;
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                cipheredPassword = new String(
                        Base64.encode(
                                cipher.doFinal(password.getBytes()),Base64.DEFAULT
                        )
                );
            } catch (Exception e) {
                return "";
            }
            return cipheredPassword;
        }
    }
}

