package com.tado.android.security;

import android.support.annotation.RequiresApi;
import android.util.Base64;
import com.tado.android.utils.Snitcher;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import org.jetbrains.annotations.Contract;

@RequiresApi(api = 18)
public enum TadoSecurity {
    INSTANCE;
    
    static final String ANDROID_KEY_STORE_NAME = "AndroidKeyStore";
    private static final String CHARSET_NAME = "UTF-8";
    static final String KEY_ALIAS = "keyAlias";
    private KeyProvider keyProvider;

    public synchronized void initKeys() {
        try {
            KeyStore keyStore = getKeyStore();
            if (keyStore.containsAlias(KEY_ALIAS)) {
                if (!this.keyProvider.isValidKey(keyStore.getEntry(KEY_ALIAS, null))) {
                    keyStore.deleteEntry(KEY_ALIAS);
                    this.keyProvider.generateKey();
                }
            } else {
                this.keyProvider.generateKey();
            }
        } catch (Exception e) {
            Snitcher.start().toCrashlytics().logException(e);
        }
    }

    public static KeyStore getKeyStore() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
        keyStore.load(null);
        return keyStore;
    }

    public synchronized String encryptData(String stringDataToEncrypt) {
        String encryptedBase64Encoded;
        if (stringDataToEncrypt != null) {
            if (stringDataToEncrypt.length() != 0) {
                encryptedBase64Encoded = stringDataToEncrypt;
                try {
                    encryptedBase64Encoded = Base64.encodeToString(this.keyProvider.getDecodedOrEncodedBytesForData(1, stringDataToEncrypt.getBytes("UTF-8")), 0);
                } catch (Exception e) {
                    Snitcher.start().toCrashlytics().logException(e);
                }
            }
        }
        throw new IllegalArgumentException("Data to be encrypted must be non empty");
        return encryptedBase64Encoded;
    }

    @Contract("null -> fail")
    public synchronized String decryptData(String encryptedData) {
        String decryptedData;
        if (encryptedData != null) {
            if (encryptedData.length() != 0) {
                decryptedData = encryptedData;
                try {
                    decryptedData = new String(this.keyProvider.getDecodedOrEncodedBytesForData(2, Base64.decode(encryptedData, 0)), "UTF-8");
                } catch (Exception e) {
                    Snitcher.start().toCrashlytics().logException(e);
                }
            }
        }
        throw new IllegalArgumentException("Data to be decrypted must be non empty");
        return decryptedData;
    }
}
