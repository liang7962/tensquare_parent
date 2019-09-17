package entity;

public class Result<T> {

    private boolean flag;
    private Integer code;
    private String msg;
    private Object object;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Result() {
    }

    public Result(boolean flag, Integer code, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean flag, Integer code, String msg, Object object) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.object = object;
    }
}
