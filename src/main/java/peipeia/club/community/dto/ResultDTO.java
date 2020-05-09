package peipeia.club.community.dto;

import lombok.Data;
import peipeia.club.community.exception.CustomException;
import peipeia.club.community.exception.CustomizeErrorCodeImpl;

@Data
public class ResultDTO {
    /**
     * code 错误代码
     */
    private  Integer code;
    /**
     * message 错误信息
     */
    private  String message;
    public  static  ResultDTO errorOf(Integer code,String message){
    ResultDTO resultDTO=new ResultDTO();
    resultDTO.setCode(code);
    resultDTO.setMessage(message);
    return resultDTO;
    }

    public static Object errorOf(CustomizeErrorCodeImpl errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDTO errorOf(CustomException e) {
        return  errorOf(e.getCode(),e.getMessage());
    }
    public static  ResultDTO okOf(){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }



}
