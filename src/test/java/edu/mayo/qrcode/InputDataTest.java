package edu.mayo.qrcode;

import edu.mayo.qrcode.Encoding.Base64;
import edu.mayo.qrcode.Encoding.Base64ThenURLEncode;
import edu.mayo.qrcode.Encoding.URLEncoder;
import edu.mayo.qrcode.compression.Compression;
import edu.mayo.qrcode.compression.GZip;
import edu.mayo.qrcode.compression.LZ4;
import edu.mayo.qrcode.compression.Zip;
import edu.mayo.qrcode.utils.IDStringGenerator;
import edu.mayo.qrcode.utils.Result;
import junit.framework.TestCase;
import org.lexemantix.utils.file.LMFileUtils;

import java.io.File;

public class InputDataTest extends TestCase
{
    public void testGetDataMatrix() throws Exception
    {
        int[] totalIDs = {1000, 2000, 5000, 10000, 50000};
        Compression [] algos = {new Zip(), new LZ4(), new GZip()};

        File rfile = new File(System.getProperty("user.dir") +
                            "/src/main/reports/Statistics.csv");

        if (!rfile.exists())
            rfile.createNewFile();

        //LMFileUtils.setContents(rfile, Result.toStatisticsHeader(),false);

        StringBuffer buff = new StringBuffer(Result.toStatisticsHeader());

        for (int ti : totalIDs)
        {
            for (Compression algo : algos)
            {
                Result[][] results = InputData.getDataMatrix(ti, new Base64ThenURLEncode(), algo);
                assertNotNull(results);
                assertTrue(results.length == InputData.charset.length);
                assertTrue(results[0].length == InputData.idSizes.length);
                InputData.printResults(results);

                String algoName = algo.getClass().getSimpleName();

                for (int row = 0; row < results.length; row++)
                    for (int col = 0; col < results[row].length; col++)
                        buff.append("\n" + (results[row][col]).toStatistics(algoName));
            }
        }

        LMFileUtils.setContents(rfile, buff.toString(), true);

        System.out.print("DONE");
    }
}