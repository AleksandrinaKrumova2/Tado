package com.tado.android.security;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.security.KeyPairGeneratorSpec;
import android.security.KeyPairGeneratorSpec.Builder;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Base64;
import com.tado.android.app.TadoApplication;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

@RequiresApi(api = 18)
class LollipopLegacyKeyProvider extends KeyProvider {
    private static final String AES_MODE_LESS_THAN_M = "AES/ECB/PKCS7Padding";
    private static final String CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_AES = "BC";
    private static final String CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA = "AndroidOpenSSL";
    private static final String ENCRYPTED_KEY_NAME = "EncryptedKeysKeyName";
    private static final String RSA_ALGORITHM_NAME = "RSA";
    private static final String RSA_MODE = "RSA/ECB/PKCS1Padding";
    private static final String SHARED_PREFERENCE_NAME = "KeysSharedPreferences";

    LollipopLegacyKeyProvider() {
    }

    boolean isValidKey(Entry keyStoreEntry) {
        return (keyStoreEntry instanceof PrivateKeyEntry) && isKeyStored();
    }

    private String getStoredKey() {
        return getSharedPreferences().getString(ENCRYPTED_KEY_NAME, null);
    }

    private boolean isKeyStored() {
        return !TextUtils.isEmpty(getStoredKey());
    }

    void initCipher(Cipher cipher, int mode, Key key) throws InvalidKeyException {
        cipher.init(mode, key);
    }

    public void generateKey() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, UnrecoverableEntryException, NoSuchPaddingException, KeyStoreException, InvalidKeyException, IOException, IllegalStateException {
        removeSavedSharedPreferences();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(1, 30);
        KeyPairGeneratorSpec spec = new Builder(TadoApplication.getTadoAppContext()).setAlias("keyAlias").setSubject(new X500Principal("CN=keyAlias")).setSerialNumber(BigInteger.TEN).setStartDate(start.getTime()).setEndDate(end.getTime()).build();
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM_NAME, "AndroidKeyStore");
        kpg.initialize(spec);
        kpg.generateKeyPair();
        saveEncryptedKey();
    }

    public Key getSecretKey() throws CertificateException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, IOException {
        if (isKeyStored()) {
            return new SecretKeySpec(rsaDecryptKey(Base64.decode(getStoredKey(), 0)), "AES");
        }
        try {
            generateKey();
            return getSecretKey();
        } catch (Exception e) {
            return null;
        }
    }

    Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        return Cipher.getInstance(AES_MODE_LESS_THAN_M, CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_AES);
    }

    private void saveEncryptedKey() throws CertificateException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableEntryException, IOException {
        SharedPreferences pref = getSharedPreferences();
        if (getStoredKey() == null) {
            byte[] key = new byte[16];
            new SecureRandom().nextBytes(key);
            String encryptedKeyBase64encoded = Base64.encodeToString(rsaEncryptKey(key), 0);
            Editor edit = pref.edit();
            edit.putString(ENCRYPTED_KEY_NAME, encryptedKeyBase64encoded);
            edit.apply();
        }
    }

    private byte[] rsaEncryptKey(byte[] secret) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, NoSuchProviderException, NoSuchPaddingException, UnrecoverableEntryException, InvalidKeyException {
        PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) TadoSecurity.getKeyStore().getEntry("keyAlias", null);
        Cipher inputCipher = Cipher.getInstance(RSA_MODE, CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA);
        inputCipher.init(1, privateKeyEntry.getCertificate().getPublicKey());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, inputCipher);
        cipherOutputStream.write(secret);
        cipherOutputStream.close();
        return outputStream.toByteArray();
    }

    private byte[] rsaDecryptKey(byte[] encrypted) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableEntryException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException {
        PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) TadoSecurity.getKeyStore().getEntry("keyAlias", null);
        Cipher output = Cipher.getInstance(RSA_MODE, CIPHER_PROVIDER_NAME_ENCRYPTION_DECRYPTION_RSA);
        output.init(2, privateKeyEntry.getPrivateKey());
        CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(encrypted), output);
        ArrayList<Byte> values = new ArrayList();
        while (true) {
            int nextByte = cipherInputStream.read();
            if (nextByte == -1) {
                break;
            }
            values.add(Byte.valueOf((byte) nextByte));
        }
        byte[] decryptedKeyAsBytes = new byte[values.size()];
        for (int i = 0; i < decryptedKeyAsBytes.length; i++) {
            decryptedKeyAsBytes[i] = ((Byte) values.get(i)).byteValue();
        }
        return decryptedKeyAsBytes;
    }

    private SharedPreferences getSharedPreferences() {
        return TadoApplication.getTadoAppContext().getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
    }

    private void removeSavedSharedPreferences() {
        getSharedPreferences().edit().clear().apply();
    }
}
