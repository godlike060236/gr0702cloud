package com.gr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gr.mapper.ImgMapper;
import com.gr.pojo.Img;
import com.gr.service.IImgService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author guorui
 * @since 2021-07-01
 */
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements IImgService {
    // 获取配置文件中的内容
    @Value("${minio.endpoint}")
    String endpoint;
    @Value("${minio.username}")
    String username;
    @Value("${minio.password}")
    String password;
    @Override
    public String upload(MultipartFile file) throws IOException, ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 检验文件是否已经上传过
        String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
        long size = file.getSize();
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        QueryWrapper<Img> wrapper = new QueryWrapper<>();
        wrapper.eq("MD5", md5)
                .eq("size", size)
                .eq("suffix", suffix);
        Img img = this.getOne(wrapper);
        if (img != null) {
            return img.getUrl();
        } else {
            // 定义新文件名
            StringBuilder builder = new StringBuilder();
            LocalDateTime now = LocalDateTime.now();
            builder.append(now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS")));
            builder.append(RandomStringUtils.random(6, false, true));
            builder.append(".").append(suffix);
            // 构建 MinioClient对象
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(username, password).build();
            // 上传文件
            PutObjectArgs args = PutObjectArgs.builder()
                    .object(builder.toString())
                    .bucket("images")
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), 0)
                    .build();
            minioClient.putObject(args);
            img = new Img(md5,size,suffix,builder.toString());
            this.save(img);
            return builder.toString();
        }
    }
}
