package com.example.community.controller;

import com.example.community.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件控制器
 *
 * @author Tuoer
 * @date 2023/01/12
 */
@Controller
public class FileController {
    /**
     * 上传
     *
     * @param request 请求
     * @return {@link FileDto}
     * @throws IOException ioexception
     */
    @PostMapping("/file/upload")
    @ResponseBody
    public FileDto upload(HttpServletRequest request) throws IOException {
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("editormd-image-file");
        //保存file到本地
        FileDto fileDto = new FileDto();
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            file.transferTo(new File("E:/IDEA_JAVA/community/src/main/resources/static/file/" + originalFilename));
            fileDto.setSuccess(1);
            fileDto.setMessage("上传成功");
            fileDto.setUrl("/file/" + file.getOriginalFilename());
        }
        return fileDto;
    }
}
