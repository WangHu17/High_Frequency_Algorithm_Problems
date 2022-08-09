import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-08-09 14:57
 */
public class Hash {

    private MessageDigest hash;

    public Hash(String algorithm) {
        try {
            hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hashCode(String input) {
        hash.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = hash.digest();
        return byteToString(bytes);
    }

    private String byteToString(byte[] bByte) {
        StringBuffer encrytStrBuf = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            if (Integer.toHexString(0xFF & bByte[i]).length() == 1) {
                encrytStrBuf.append("0").append(Integer.toHexString(0xFF & bByte[i]));
            } else {
                encrytStrBuf.append(Integer.toHexString(0xFF & bByte[i]));
            }
        }
        return encrytStrBuf.toString().toUpperCase();
    }
}
