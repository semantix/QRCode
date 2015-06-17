package edu.mayo.qrcode.compression;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by dks02 on 6/3/15.
 */
public class GZip implements Compression
{

    @Override
    public byte[] compress(String string)
    {
        try
        {
            byte[] utfEncodedBytes = string.getBytes();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(baos))
            {
                gzipOutputStream.write(utfEncodedBytes);
                gzipOutputStream.finish();
            }
            return baos.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("GZIP compression failed: " + e, e);
        }
    }

    @Override
    public String uncompress(byte[] compressed, int decompressedLength) {
        return null;
    }
}
