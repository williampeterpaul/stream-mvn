package twitter2influx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Utils {

    public static String[] fileToArray(String path) {
        String tmp = "";
        try {
            tmp = new String(readAllBytes(get(path)));
        } catch (IOException ex) {
            Logger.log(Utils.class.getName(), ex.getMessage());
        }
        return tmp.split(",");
    }

    public static Properties fileToProperties(String path) {
        InputStream inputStream;
        Properties properties = new Properties();
        try {
            inputStream = new FileInputStream(path);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException ex) {
            Logger.log(Utils.class.getName(), ex.getMessage());
        }
        return properties;
    }

    public static String stringToSHA256(String string) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.log(Utils.class.getName(), ex.getMessage());
        }

        messageDigest.update(string.getBytes());
        byte[] bytes = messageDigest.digest();

        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "x", bi);
    }
}
