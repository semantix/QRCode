package edu.mayo.qrcode.compression;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by dks02 on 6/3/15.
 */
public class Zip implements Compression {
    @Override
    public byte[] compress(String string)
    {
        try
        {
            Deflater compresser = new Deflater();
            compresser.setInput(string.getBytes());
            compresser.finish();
            //compresser.setStrategy(Deflater.DEFAULT_COMPRESSION);
            byte[] output = new byte[50000];
            compresser.deflate(output);

            //System.out.println("isFinished=" + compresser.finished() + " size=" + compresser.getBytesWritten());

            int outLen = (int) compresser.getBytesWritten();
            byte[] outArray = new byte[outLen];
            System.arraycopy(output, 0, outArray, 0, outLen);

            return outArray;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String uncompress(byte[] compressed, int decompressedLength)
    {
        try
        {
            Inflater decompresser = new Inflater();
            decompresser.setInput(compressed, 0, compressed.length);
            byte[] result = new byte[100];
            int resultLength = decompresser.inflate(result);
            decompresser.end();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
