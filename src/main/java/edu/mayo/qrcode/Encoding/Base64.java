package edu.mayo.qrcode.Encoding;

import com.google.common.io.BaseEncoding;
import sun.misc.BASE64Encoder;

/**
 * Created by dks02 on 6/3/15.
 */
public class Base64 implements Encoder
{

    @Override
    public String encode(byte[] in)
    {
        try
        {
            return BaseEncoding.base64().encode(in);
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
