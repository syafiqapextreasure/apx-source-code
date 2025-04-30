package com.ppk.topEntity.formsEntity.registration;

public class Encrypter {
    private static final String SEED = "ILMUSEKOLAH";

    private Encrypter() {
    }

    public static String encrypt(String szPlain) {
        return Encrypter.encrypt(szPlain, SEED);
    }

    public static String encrypt(String szPlain, String szSeed) {
        return Encrypter.process(szPlain, szSeed);
    }

    private static String process(String szPlain, String szSeed) {
        StringBuffer sb = new StringBuffer();
        int i = szSeed.length();
        int j = 0;
        while (j < szPlain.length()) {
            char c = szSeed.charAt(j % i);
            sb.append((char) (szPlain.charAt(j) ^ c));
            ++j;
        }
        return sb.toString();
    }
}
