package edu.mayo.qrcode.compression;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

/**
 * Created by dks02 on 5/14/15.
 */
public class LZ4 implements Compression
{
    public byte[] compress(String str)
    {
        try
        {
            LZ4Factory factory = LZ4Factory.fastestInstance();
            byte[] data = str.getBytes("UTF-8");
            final int decompressedLength = data.length;
            // compress data
            LZ4Compressor compressor = factory.fastCompressor();
            int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
            byte[] compressed = new byte[maxCompressedLength];
            int compressedLength = compressor.compress(data, 0, decompressedLength, compressed, 0, maxCompressedLength);
            return compressed;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Error".getBytes();
    }

    public String uncompress(byte[] compressed, int decompressedLength)
    {
        try
        {
            LZ4Factory factory = LZ4Factory.fastestInstance();
            LZ4FastDecompressor decompressor = factory.fastDecompressor();
            byte[] restored = new byte[decompressedLength];
            int decomLength = decompressor.decompress(compressed, 0, restored, 0, decompressedLength);
            return new String(restored, "UTF-8");
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
        return "Error";
    }
}
