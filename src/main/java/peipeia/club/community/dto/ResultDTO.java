package peipeia.club.community.dto;

import lombok.Data;
import peipeia.club.community.exception.CustomException;
import peipeia.club.community.exception.CustomizeErrorCodeImpl;
import peipeia.club.community.model.User;

import java.util.List;

@Data
public class ResultDTO<T> {
    /**
     * code 错误代码
     */
    private  Integer code;
    /**
     * message 错误信息
     */
    private  String message;
    private T data;
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
        //请求成功返回代码200
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }
    public static<T>  ResultDTO okOf(Object t){
        ResultDTO resultDTO=new ResultDTO();
        //请求成功返回代码200
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }



}
