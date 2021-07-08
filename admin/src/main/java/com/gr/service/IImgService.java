package com.gr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gr.pojo.Img;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author guorui
 * @since 2021-07-01
 */
public interface IImgService extends IService<Img> {
    String upload(MultipartFile file) throws IOException, ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
