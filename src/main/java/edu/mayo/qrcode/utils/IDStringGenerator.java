package edu.mayo.qrcode.utils;

import java.util.Random;

/**
 * Created by dks02 on 6/3/15.
 */
public class IDStringGenerator
{
    static final int MAX_STRING_LENGTH = 10000000; // 10 million character long

    static Random rnd = new Random();

    public static String randomString(String charset,
                        int eachIdLength,
                        int numberOfIds)
    {
        int idLen = 1;
        int ids = 1;
        if (eachIdLength > 0)
            idLen = eachIdLength;

        if (numberOfIds > 0)
            ids = numberOfIds;

        int totalLength = ((idLen * ids) > MAX_STRING_LENGTH)? MAX_STRING_LENGTH : (idLen * ids);
        //System.out.println("Generating string for ID Length=" + idLen + ", number of Ids=" + ids + ", Total Length=" + totalLength);
        StringBuilder sb = new StringBuilder(totalLength);
        for( int i = 0; i < totalLength; i++ )
            sb.append( charset.charAt(rnd.nextInt(charset.length())) );
        return sb.toString();
    }
}
