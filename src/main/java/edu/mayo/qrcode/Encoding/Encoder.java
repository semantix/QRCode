package edu.mayo.qrcode.Encoding;

/**
 * Created by dks02 on 6/3/15.
 */
public interface Encoder {

    public String encode(byte[] in);
    public byte[] decode(String in);
}
