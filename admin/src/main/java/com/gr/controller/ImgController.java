package com.gr.controller;


import com.gr.service.IImgService;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 图片表 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/img")
public class ImgController {
    @Resource
    IImgService imgService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    String upload(@RequestPart("file") MultipartFile file) throws ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return imgService.upload(file);
    }
}
