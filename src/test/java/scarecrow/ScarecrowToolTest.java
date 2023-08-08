package scarecrow;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScarecrowToolTest {
    @Test
    public void initTest() {
        HashMap param = new HashMap<String, Integer>();
        param.put("name", "Scarecrow");
        param.put("platform", "h5");
        param.put("timestamp", "1642144615");
        param.put("randstr", "yhpid");

        SignReturnInfo r= ScarecrowTool.createSign(param, "ABCDEFGHIJKLMN");
        System.out.println(r);
    }

    @Test
    public void curlGetTest() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String url = "http://www.lumenbase.com/app/login";
        String csn = Charset.defaultCharset().name();
        System.out.println(csn);
        byte [] list = "彭青松".getBytes("UTF-8");
        for (byte i : list) {
            System.out.println(i);
        }


        MessageDigest md = MessageDigest.getInstance("md5");
        md.update(list);
        byte[] bytes = md.digest();
        System.out.println("==================");
        for (byte i : bytes) {
            System.out.println(i);
        }


//        HashMap params = new HashMap();
//        params.put("userName", "18888888888");
//        params.put("userPwd", "123456");
//        params.put("randstr", "53646858");
//        params.put("platform", "h5");
//        params.put("time", "654321");
//        params.put("timestamp", "654321");
//
//        SignReturnInfo sign = ScarecrowTool.createSign(params, "ABCDEFGHIJKLMN");
//        if (sign.getCode() != 200) {
//            System.out.println(sign.getMsg());
//        } else {
//            params.put("sign", sign.getSign());
//            String content = ScarecrowHttpClient.doPost(url, params);
//            System.out.println(content);
//        }
    }

    @Test
    public void fileTest() {
        String path = "D:\\Image";
        ArrayList list = ScarecrowTool.readDirectoryAllFile(path);
        System.out.println(list);
    }
}
