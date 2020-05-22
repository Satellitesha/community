package peipeia.club.community.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${file.uploadPath}")
    String uploadPath;
    @Value("${file.accessPath}")
    String accessPath;

    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject uploadImages(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                                   HttpServletRequest request, HttpServletResponse response){
        String trueFileName = file.getOriginalFilename();
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        String fileName = System.currentTimeMillis()+"_"+ RandomStringUtils.randomNumeric(5) +suffix;
        String path = uploadPath;
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject res = new JSONObject();
        res.put("url", accessPath+fileName);
        res.put("success", 1);
        res.put("message", "upload success!");
        return res;
    }
}
