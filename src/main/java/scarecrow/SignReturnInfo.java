package scarecrow;


import java.util.ArrayList;

/**
 * 签名返回信息
 */
public class SignReturnInfo {
    //状态码
    private Integer code = 200;

    //描述
    private String msg = "";

    //签名
    private String sign = "";

    //参数列表
    private ArrayList<CreateSignBean> params;

    public SignReturnInfo(Integer code, String msg, ArrayList<CreateSignBean> params) {
        this.code = code;
        this.msg = msg;
        this.params = params;
    }

    public SignReturnInfo(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public ArrayList<CreateSignBean> getParams() {
        return params;
    }

    public void setParams(ArrayList<CreateSignBean> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "SignReturnInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", sign='" + sign + '\'' +
                ", params=" + params +
                '}';
    }
}