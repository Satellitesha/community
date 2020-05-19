package peipeia.club.community.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import peipeia.club.community.dto.ResultDTO;
import peipeia.club.community.exception.CustomException;
import peipeia.club.community.exception.CustomizeErrorCodeImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    Object handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        ResultDTO resultDTO = null;
        if ("application/json".equals(contentType)) {
            //返回json
            if (e instanceof CustomException) {
                resultDTO = ResultDTO.errorOf((CustomException) e);
            } else {
                resultDTO = (ResultDTO) ResultDTO.errorOf(CustomizeErrorCodeImpl.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {

            }
            return  null;
        } else {
            //错误页面跳转

            if (e instanceof CustomException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCodeImpl.SYS_ERROR);
            }
            return new ModelAndView("error");
        }
    }
}
