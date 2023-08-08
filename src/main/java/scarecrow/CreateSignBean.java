package scarecrow;


/**
 * 创建签名Bean
 */
public class CreateSignBean implements Comparable<CreateSignBean>{
    public String key;
    public String value;
    public String mValue;

    public CreateSignBean() { }

    public CreateSignBean(String key, String value, String mValue) {
        this.key = key;
        this.value = value;
        this.mValue = mValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getmValue() {
        return mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public int compareTo(CreateSignBean o) {
        return this.getmValue().compareTo(o.getmValue());
    }

    @Override
    public String toString() {
        return "CreateSignBean{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", mValue='" + mValue + '\'' +
                '}';
    }
}