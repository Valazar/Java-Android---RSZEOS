/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consoleserver;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.*;
import java.util.ArrayList;

public class CryptoSeril {

    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";
    private static final String KEY_FILE = "key.ser"; // Datoteka za čuvanje ključa
    private static final String IV_FILE = "iv.ser";   // Datoteka za čuvanje inicijalizacionog vektora

    public static SecretKey getSecretKey() throws Exception {
        SecretKey secretKey;
        if (new File(KEY_FILE).exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(KEY_FILE))) {
                secretKey = (SecretKey) ois.readObject();
            }
        } else {
            secretKey = createAESKey();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(KEY_FILE))) {
                oos.writeObject(secretKey);
            }
        }
        return secretKey;
    }

    public static byte[] getInitializationVector() throws Exception {
        byte[] initializationVector;
        if (new File(IV_FILE).exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(IV_FILE))) {
                initializationVector = (byte[]) ois.readObject();
            }
        } else {
            initializationVector = createInitializationVector();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(IV_FILE))) {
                oos.writeObject(initializationVector);
            }
        }
        return initializationVector;
    }

    public static SecretKey createAESKey() throws Exception {
        SecureRandom securerandom = new SecureRandom("RSZEOS2024".getBytes());
        KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
        keygenerator.init(128, securerandom);
        return keygenerator.generateKey();
    }

    public static byte[] createInitializationVector() {
        byte[] initializationVector = new byte[16];
        for (int i = 0; i < 16; i++) {
            initializationVector[i] = (byte) (i + 1);
        }
        return initializationVector;
    }

    public static byte[] do_AESEncryption(String plainText, SecretKey secretKey, byte[] initializationVector) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(plainText.getBytes());
    }

    public static byte[] do_AESDecryption(byte[] cipherText, SecretKey secretKey, byte[] initializationVector) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(cipherText);
    }

    public static void encryptUsersToFile(ArrayList<User> users, String fileName) throws Exception {
        SecretKey key = getSecretKey();
        byte[] iv = getInitializationVector();
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        try (CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(fileName), cipher);
             ObjectOutputStream oos = new ObjectOutputStream(cos)) {

            oos.writeObject(users); // Serijalizujemo ceo ArrayList<User>
        }
    }

    public static ArrayList<User> decryptAllUsersFromFile(String fileName) throws Exception {
        ArrayList<User> users = null;
        SecretKey key = getSecretKey();
        byte[] iv = getInitializationVector();

        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

        try (CipherInputStream cis = new CipherInputStream(new FileInputStream(fileName), cipher);
             ObjectInputStream ois = new ObjectInputStream(cis)) {

            users = (ArrayList<User>) ois.readObject(); // De-serijalizujemo ceo ArrayList<User>
        } catch (EOFException e) {
            // Do nothing, end of file reached
        }

        return users;
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}






   