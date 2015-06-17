package edu.mayo.qrcode.utils;

/**
 * Created by dks02 on 6/3/15.
 */
public class Result
{
    public String characterSetName;
    public String charSet;
    public String uriString;
    public String encoded;
    public byte[] compressed;
    public int tokenSize;
    public int totalIdRequested;

    public Result(String setName, String set, String str, int idSize, int totalIdReq)
    {
        this.characterSetName = setName;
        this.charSet = set;
        this.uriString = str;
        this.tokenSize = idSize;
        this.totalIdRequested = totalIdReq;
    }

    public String toString()
    {
        String data = "\nuri={" + this.uriString + "}" +
                      "\nencoded={" + this.encoded + "}" +
                      "\ncompressed={" + ((this.compressed == null)? "null" : new String(this.compressed)) + "}";
        return "[Set:" + this.characterSetName +
                ", TokenSize=" + this.tokenSize +
                ", URILength=" + ((this.uriString != null)?this.uriString.length():0) +
                ", Compressed=" + ((this.compressed != null)? this.compressed.length:"Not Compressed") +
                ",Compressed & Encoded=" + ((this.encoded != null)?this.encoded.length():"Not Encoded") +
                ", totalIds=" + this.totalIdRequested +
                "]"
                //+ data
                ;
    }

    public String toStatistics(String algorithm)
    {
        return "\"" + this.characterSetName + "\"," +
                "\"" + this.totalIdRequested + "\"," +
                "\"" + this.tokenSize + "\"," +
                "\"" + algorithm + "\"," +
                "\"" + ((this.uriString != null)?this.uriString.length():0) + "\"," +
                "\"" + ((this.compressed != null)? this.compressed.length:"Not Compressed") + "\"," +
                "\"" + ((this.encoded != null)?this.encoded.length():"Not Encoded") + "\""
                ;
    }

    public static String toStatisticsHeader()
    {
        return "\"CharacterSet\"," +
                "\"Total IDs\"," +
                "\"ID Size\"," +
                "\"Algorithm\"," +
                "\"Uncompressed\"," +
                "\"Compressed\"," +
                "\"Compressed&Encoded\""
                ;
    }
}
