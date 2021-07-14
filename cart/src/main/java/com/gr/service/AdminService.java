package com.gr.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/*
* @RequestPart:文件类型必须加注释
* @RequestPart:文件类型必须加注释
* MULTIPART_FORM_DATA_VALUE支持文件上传
* */
@FeignClient("admin")
public interface AdminService {
    @RequestMapping(value = "/img/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart MultipartFile file);
}
