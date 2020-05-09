package peipeia.club.community.exception;

public class CustomException  extends  RuntimeException{
    private  String message;
    private  Integer code;

    public Integer getCode() {
        return code;
    }

    public CustomException(CustomizeErrorCode errorCode) {
        this.message=errorCode.getMessage();
        this.code=errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
