package edu.mayo.qrcode.utils;

/**
 * Created by dks02 on 6/3/15.
 */
public enum CS
{
    CAPITAL("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    SMALL("abcdefghijklmnopqrstuvwxyz"),
    NUMBERS("0123456789"),
    SPECIALS_SAFE("-_"),
    SPECIALS_NEED_ENCODING(".,:;/?@$&=+");

    private final String text;

    private CS(final String text)
    {
        this.text = text;
    }

    public String toString()
    {
        return text;
    }
}
