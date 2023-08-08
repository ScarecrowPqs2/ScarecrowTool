package scarecrow;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Scarecrow 工具集
 */
public class ScarecrowTool {

    /**
     * Md5 加密
     * @param content
     * @return
     */
    public static String md5(String content) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(content.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String str = Integer.toHexString(b & 0xFF);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建签名
     * @param paramMap 參數列表
     * @param ik 盐值
     * @return
     */
    public static SignReturnInfo createSign(HashMap<String, String> paramMap, String ik) {
        ArrayList<CreateSignBean> tempParamList = new ArrayList();
        SignReturnInfo returnData = new SignReturnInfo(500, "fail", tempParamList);

        String mValue = "";
        ArrayList allKey = new ArrayList();
        try {
            for (Map.Entry<String, String> item : paramMap.entrySet()) {
                if(item.getValue().equals("") || item.getKey().equals("sign")) {
                    continue;
                }

                mValue = md5(item.getKey() + "_" + item.getValue());
                CreateSignBean tempData = new CreateSignBean(item.getKey(), item.getValue(), mValue);
                tempParamList.add(tempData);
                allKey.add(item.getKey());
            }

            if(!allKey.contains("randstr") || !allKey.contains("platform") || !allKey.contains("timestamp")) {
                returnData.setMsg("Missing parameter");
                return returnData;
            }

            Collections.sort(tempParamList);
            String paramStr = "";
            for (CreateSignBean tempItem: tempParamList) {
                paramStr += tempItem.getKey() + "=" + tempItem.getmValue() + "&";
            }
            paramStr = ik+trimBothChars(paramStr, "&")+ik;
            returnData.setSign(md5(paramStr));
            returnData.setMsg(paramStr);
            returnData.setCode(200);
        } catch (Error e) {
            returnData.setMsg(e.getMessage());
            return returnData;
        } catch (Exception e) {
            returnData.setMsg(e.getMessage());
            return returnData;
        }

        return returnData;
    }

    /**
     * 编译为HTTP请求参数字符串
     * @param paramMap
     * @return
     */
    public static String httpBuildQuery(HashMap<String, String> paramMap) {
        String paramStr = "";
        for (Map.Entry<String, String> item: paramMap.entrySet()) {
            paramStr += item.getKey() + "=" + item.getValue() + "&";
        }

        return trimBothChars(paramStr, "&");
    }

    /**
     * 去除前后指定
     * @param str
     * @param splitter
     * @return
     */
    public static String trimBothChars(String str, String splitter) {
        String regex = "^" + splitter + "*|" + splitter + "*$";
        return str.replaceAll(regex, "");
    }

    /**
     * 去除左边指定字符
     * @param str
     * @param splitter
     * @return
     */
    public static String ltrimBothChars(String str, String splitter) {
        String regex = "^" + splitter + "*";
        return str.replaceAll(regex, "");
    }

    /**
     * 去除右边指定字符
     * @param str
     * @param splitter
     * @return
     */
    public static String rtrimBothChars(String str, String splitter) {
        String regex = splitter + "*$";
        return str.replaceAll(regex, "");
    }

    /**
     * 获取某个路径下所有文件
     * @param path
     * @return
     */
    public static ArrayList readDirectoryAllFile(String path) {
        ArrayList fileList = new ArrayList();
        String tempPath = "";
        try {
            File file = new File(path);
            if (!file.exists()) {
                return fileList;
            }

            File[] tempAllFileList = file.listFiles();

            for (int i = 0; i < tempAllFileList.length; i++) {
                if (tempAllFileList[i].isFile()) {
                    fileList.add(tempAllFileList[i].getPath());
                } else if (tempAllFileList[i].isDirectory()) {
                    tempPath = tempAllFileList[i].getPath();
                    System.out.println(tempPath);
                    fileList.addAll(readDirectoryAllFile(tempPath));
                }
            }
        } catch (Exception e) {
            return new ArrayList();
        }
        return fileList;
    }

    /**
     * 获取文件后缀(文件类型)
     * @param fileName
     * @return
     */
    public static String getFileType(String fileName) {
        String[] strArray = fileName.split("\\.");
        int suffixIndex = strArray.length -1;
        return strArray[suffixIndex];
    }
}