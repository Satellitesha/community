package peipeia.club.community.exception;

public enum CustomizeErrorCodeImpl implements CustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不要换一个试试"),
    TARGET_PARAM_FOUND(2002,"为选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务器冒烟了，请稍后再试！！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"你操作的评论不存在了"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"没有权限进行此操作"),
    NOTIFICATION_NOT_FOUND(2009,"消息不易而飞了")
            ;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    private  String message;
    private  Integer code;
    CustomizeErrorCodeImpl(Integer code, String message){
        this.message=message;
        this.code=code;
    }
}
