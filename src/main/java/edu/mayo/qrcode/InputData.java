package edu.mayo.qrcode;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import edu.mayo.qrcode.Encoding.Encoder;
import edu.mayo.qrcode.compression.Compression;
import edu.mayo.qrcode.utils.CS;
import edu.mayo.qrcode.utils.IDStringGenerator;
import edu.mayo.qrcode.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.lexemantix.utils.file.LMFileUtils;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by dks02 on 5/14/15.
 */
public class InputData
{
    private static String alphanums = "" + CS.NUMBERS + CS.CAPITAL + CS.SMALL;
    private static String alphanumsSpecials = alphanums + CS.SPECIALS_SAFE;
    private static String alphanumsAllSpecials = alphanumsSpecials + CS.SPECIALS_NEED_ENCODING;

    public static String [] charset = {"" +CS.NUMBERS, alphanums, alphanumsSpecials, alphanumsAllSpecials};
    public static boolean[] encode = {true, true, true, true};
    public static boolean[] compress = {true, true, true, true};
    private static String [] charsetDesc = {"Numbers", "Alphanumeric", "AlphanumericSpecial", "AlphanumericAllSpecial"};



    public static int idSizes[] = {2, 3, 4, 5};

    private static String getText(String charSet,
                                  String charSetDesc,
                                  int tokenSize,
                                  int totalIds) throws Exception
    {
        File file = new File(System.getProperty("user.dir") +
                                "/src/main/resources/data_" +
                                totalIds +
                                "_" + tokenSize +
                                "_" + charSetDesc + ".txt");

        //System.out.println("File=" + file.getCanonicalPath());
        if (!file.exists()) {
            file.createNewFile();
            String contents = IDStringGenerator.randomString(charSet, tokenSize, totalIds);
            LMFileUtils.setContents(file, contents, false);
        }

        return Files.toString(file, Charsets.UTF_8);
    }

    public static Result[][] getDataMatrix(int totalNumberOfIds, Encoder encoder, Compression compression) throws Exception
    {
        Result[][] results = new Result[charset.length][idSizes.length];

        for (int i=0; i < charset.length; i++)
            for (int j = 0; j < idSizes.length; j++)
            {
                String text = InputData.getText(charset[i], charsetDesc[i], idSizes[j], totalNumberOfIds);
                results[i][j] = new Result(charsetDesc[i], charset[i], text, idSizes[j], totalNumberOfIds);
                String method = "NOMETHOD";
                if (compress[i] && (compression != null))
                {
                    method = compression.getClass().getSimpleName();
                    results[i][j].compressed = compression.compress(text);
                    /*
                    String uncompressed = compression.uncompress(results1000[i][j].compressed, str1000.length());
                    System.out.println("Matches=" + str1000.equals(uncompressed));
                    System.out.print("\nOrig 100=" + str1000.substring(0, 100));
                    System.out.print("\nUncompress 100=" + ((uncompressed != null)?uncompressed.substring(0, 100):" null "));
                    */
                }

                if (encode[i] && (encoder != null))
                {
                    results[i][j].encoded = encoder.encode(results[i][j].compressed);

                    File efile = new File(System.getProperty("user.dir") +
                            "/src/main/resources/" + method + "_" +
                             + totalNumberOfIds +
                            "_" + idSizes[j] +
                            "_" + charsetDesc[i] + ".txt");

                    //System.out.println("Compressed File=" + efile.getCanonicalPath());
                    if (!efile.exists()) {
                        efile.createNewFile();
                    }
                    if (!StringUtils.isEmpty(results[i][j].encoded))
                        LMFileUtils.setContents(efile, results[i][j].encoded, false);
                }
                else
                {
                    File cfile = new File(System.getProperty("user.dir") +
                            "/src/main/resources/" + method + "_" +
                            + totalNumberOfIds +
                            "_" + idSizes[j] +
                            "_" + charsetDesc[i] + "_compressedONLY.txt");

                    //System.out.println("Compressed File=" + cfile.getCanonicalPath());
                    if (!cfile.exists()) {
                        cfile.createNewFile();
                    }
                    String cText = new String(results[i][j].compressed);
                    if (!StringUtils.isEmpty(cText))
                        LMFileUtils.setContents(cfile, cText, false);
                }
            }

        return results;
    }

    public static void printResults(Result[][] matrix)
    {
        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix[row].length; col++)
                System.out.println(matrix[row][col]);
    }
}
