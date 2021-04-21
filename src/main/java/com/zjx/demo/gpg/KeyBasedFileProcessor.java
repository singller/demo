package com.zjx.demo.gpg;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.jcajce.JcaPGPObjectFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.util.io.Streams;

import java.io.*;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;

public class KeyBasedFileProcessor {

    public static void decryptFile(String inputFileName, String keyFileName, char[] passwd, String defaultFileName) throws IOException, NoSuchProviderException {

        InputStream in = new BufferedInputStream(new FileInputStream(inputFileName));
        InputStream keyIn = new BufferedInputStream(new FileInputStream(keyFileName));
        decryptFile(in, keyIn, passwd, defaultFileName);
        keyIn.close();
        in.close();
    }

    /**
     * decrypt the passed in message stream
     */

    private static void decryptFile(InputStream in, InputStream keyIn, char[] passwd, String defaultFileName) throws IOException, NoSuchProviderException {
        in = PGPUtil.getDecoderStream(in);
        try {
            JcaPGPObjectFactory pgpF = new JcaPGPObjectFactory(in);
            PGPEncryptedDataList enc;
            Object o = pgpF.nextObject();
            // the first object might be a PGP marker packet.
            if (o instanceof PGPEncryptedDataList) {
                enc = (PGPEncryptedDataList) o;
            } else {
                enc = (PGPEncryptedDataList) pgpF.nextObject();
            }
            // find the secret key
            Iterator it = enc.getEncryptedDataObjects();
            PGPPrivateKey sKey = null;
            PGPPublicKeyEncryptedData pbe = null;
            PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
                    PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());
            while (sKey == null && it.hasNext()) {
                pbe = (PGPPublicKeyEncryptedData) it.next();
                sKey = PGPExampleUtil.findSecretKey(pgpSec, pbe.getKeyID(), passwd);
            }

            if (sKey == null) {
                throw new IllegalArgumentException("secret key for message not found.");
            }
            InputStream clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));
            JcaPGPObjectFactory plainFact = new JcaPGPObjectFactory(clear);
            Object message = plainFact.nextObject();
            if (message instanceof PGPCompressedData) {
                PGPCompressedData cData = (PGPCompressedData) message;
                JcaPGPObjectFactory pgpFact = new JcaPGPObjectFactory(cData.getDataStream());
                message = pgpFact.nextObject();
            }

            if (message instanceof PGPLiteralData) {
                PGPLiteralData ld = (PGPLiteralData) message;
                String outFileName = ld.getFileName();
                if (outFileName.length() == 0) {
                    outFileName = defaultFileName;
                } else {
                    outFileName = defaultFileName;
                }
                InputStream unc = ld.getInputStream();
                OutputStream fOut = new BufferedOutputStream(new FileOutputStream(outFileName));
                Streams.pipeAll(unc, fOut);
                fOut.close();
            } else if (message instanceof PGPOnePassSignatureList) {
                throw new PGPException("encrypted message contains a signed message - not literal data.");
            } else {
                throw new PGPException("message is not a simple encrypted file - type unknown.");
            }
            if (pbe.isIntegrityProtected()) {
                if (!pbe.verify()) {
                    System.err.println("message failed integrity check");
                } else {
                    System.err.println("message integrity check passed");
                }
            } else {
                System.err.println("no message integrity check");
            }
        } catch (PGPException e) {
            System.err.println(e);
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }
        }

    }

    public static void encryptFile(String outputFileName, String inputFileName, String encKeyFileName, boolean armor, boolean withIntegrityCheck)
            throws IOException, NoSuchProviderException, PGPException {

        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName));
        PGPPublicKey encKey = PGPExampleUtil.readPublicKey(encKeyFileName);
        encryptFile(out, inputFileName, encKey, armor, withIntegrityCheck);
        out.close();
    }

    private static void encryptFile(OutputStream out, String fileName, PGPPublicKey encKey, boolean armor, boolean withIntegrityCheck)
            throws IOException, NoSuchProviderException {

        if (armor) {
            out = new ArmoredOutputStream(out);
        }

        try {
            byte[] bytes = PGPExampleUtil.compressFile(fileName, CompressionAlgorithmTags.ZIP);
            PGPEncryptedDataGenerator encGen = new PGPEncryptedDataGenerator(
                    new JcePGPDataEncryptorBuilder(PGPEncryptedData.CAST5).setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom()).setProvider("BC"));
            encGen.addMethod(new JcePublicKeyKeyEncryptionMethodGenerator(encKey).setProvider("BC"));
            OutputStream cOut = encGen.open(out, bytes.length);
            cOut.write(bytes);
            cOut.close();
            if (armor) {
                out.close();
            }
        } catch (PGPException e) {
            System.err.println(e);
            if (e.getUnderlyingException() != null) {
                e.getUnderlyingException().printStackTrace();
            }
        }

    }


    //主方法
    public static void main(String[] s) throws Exception {

        Security.addProvider(new BouncyCastleProvider());
        boolean encryp = false;  //加密：true  解密：false
        if (encryp) {
            String outPath = "C:\\Users\\65454\\Desktop\\fsdownload\\kbank_billpayment_09032021.txt.gpg";
            String inputPath = "C:\\Users\\65454\\Desktop\\fsdownload\\kbank_billpayment_09032021.txt";
            String publicKeys = "C:\\EEE\\work\\对账\\对账文件\\kbank\\billpayment\\flashpay_kbankuat_pbk.asc";  //公钥地址
            PGPExampleUtil.encryptFile(outPath, inputPath, publicKeys, true, true);
        } else {
            String inputPath;
            String outPath;
            String address = "C:\\Users\\65454\\Desktop\\fsdownload";
            String password = "clZjSRCgwsrp8idr";  //私钥的Key
            String privateKeys = "C:\\EEE\\work\\对账\\对账文件\\文件解密密钥\\key\\key\\tra\\flm\\gnupg_kbankuat\\.gnupg_kbankuat\\secring.gpg";//私钥地址

            inputPath = "C:\\Users\\65454\\Desktop\\fsdownload\\kbank_billpayment_09032021.txt.gpg";
            //被加密的文件    ${channelcode}_${instcode}_ddMMyyyyy.txt
            outPath = address + "\\kbank_billpayment_09032023.txt";
            System.out.println("解密第一个文件，要解密的文件：" + inputPath + "，解密出来的文件" + outPath);
            PGPExampleUtil.decryptFile(inputPath, privateKeys, password.toCharArray(), outPath);

        }

    }

}