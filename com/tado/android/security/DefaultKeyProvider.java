package com.tado.android.security;

import android.security.keystore.KeyGenParameterSpec.Builder;
import android.support.annotation.RequiresApi;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore.Entry;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;

@RequiresApi(api = 23)
public class DefaultKeyProvider extends KeyProvider {
    private static final String AES_MODE_M_OR_GREATER = "AES/GCM/NoPadding";
    private static final String FIXED_IV = "OWLFPWLX-PW2";

    boolean isValidKey(Entry keyStoreEntry) {
        return keyStoreEntry instanceof SecretKeyEntry;
    }

    void initCipher(Cipher cipher, int mode, Key key) throws InvalidKeyException, InvalidAlgorithmParameterException {
        cipher.init(mode, key, new GCMParameterSpec(128, FIXED_IV.getBytes()));
    }

    public void generateKey() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, UnrecoverableEntryException, NoSuchPaddingException, KeyStoreException, InvalidKeyException, IOException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(new Builder("keyAlias", 3).setBlockModes(new String[]{"GCM"}).setEncryptionPaddings(new String[]{"NoPadding"}).setRandomizedEncryptionRequired(false).build());
        keyGenerator.generateKey();
    }

    public Key getSecretKey() throws CertificateException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, IOException, IllegalStateException {
        return TadoSecurity.getKeyStore().getKey("keyAlias", null);
    }

    Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        return Cipher.getInstance(AES_MODE_M_OR_GREATER);
    }
}
