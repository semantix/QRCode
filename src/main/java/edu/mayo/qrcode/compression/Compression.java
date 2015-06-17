package edu.mayo.qrcode.compression;

/**
 * Created by dks02 on 6/3/15.
 */
public interface Compression
{
    public byte[] compress(String string);
    public String uncompress(byte[] compressed, int decompressedLength);
}
