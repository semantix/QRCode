package edu.mayo.qrcode;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import net.jpountz.lz4.LZ4SafeDecompressor;

/**
 * Hello world!
 *
 */
public class VCFIDCompression
{
    public static void main( String[] args )
    {
        try
        {
            /*
            byte[] compressed = LZ4.compress(InputData.inputStr);
            String restored = LZ4.uncompress(compressed, compressed.length);
            System.out.println("LZ4 - Is this same=" + InputData.inputStr.equals(restored));
            */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
