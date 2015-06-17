package edu.mayo.qrcode.Encoding;

import com.google.common.io.BaseEncoding;

import java.net.URLDecoder;

/**
 * Created by dks02 on 6/3/15.
 */
public class URLEncoder implements Encoder {
    @Override
    public String encode(byte[] in)
    {
        try
        {
            return java.net.URLEncoder.encode(new String(in), "UTF-8");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public byte[] decode(String in)
    {
        try
        {
            return URLDecoder.decode(in, "UTF-8").getBytes();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
