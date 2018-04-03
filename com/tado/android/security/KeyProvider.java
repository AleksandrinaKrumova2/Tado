package com.tado.android.security;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore.Entry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

abstract class KeyProvider {
    public abstract void generateKey() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, UnrecoverableEntryException, NoSuchPaddingException, KeyStoreException, InvalidKeyException, IOException;

    abstract Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException;

    abstract Key getSecretKey() throws CertificateException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, IOException, IllegalStateException;

    abstract void initCipher(Cipher cipher, int i, Key key) throws InvalidKeyException, InvalidAlgorithmParameterException;

    abstract boolean isValidKey(Entry entry);

    KeyProvider() {
    }

    byte[] getDecodedOrEncodedBytesForData(int mode, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchProviderException {
        Key key = getSecretKey();
        if (key == null) {
            return data;
        }
        Cipher cipher = getCipher();
        initCipher(cipher, mode, key);
        return cipher.doFinal(data);
    }
}
