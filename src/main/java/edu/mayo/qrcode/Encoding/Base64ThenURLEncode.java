package edu.mayo.qrcode.Encoding;

import com.google.common.io.BaseEncoding;

import java.net.URL;

/**
 * Created by dks02 on 6/3/15.
 */
public class Base64ThenURLEncode implements Encoder
{

    @Override
    public String encode(byte[] in)
    {
        try
        {
            String step1 = BaseEncoding.base64().encode(in);
            return new URLEncoder().encode(step1.getBytes());
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
            return BaseEncoding.base64().decode(in);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
