package com.gr.controller;

import com.gr.pojo.UmsUser;
import com.gr.service.IImgService;
import com.gr.service.IUmsUserService;
import com.gr.util.ResultJson;
import io.minio.errors.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-06-27
 */
@RestController
@RequestMapping("/ums-user")
public class UmsUserController {
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    IUmsUserService umsUserService;
    @Resource
    IImgService imgService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return ResultJson.success(umsUserService.page(pageNo, pageSize, name));
    }

    @PostMapping("/add")
    ResultJson add(UmsUser user, MultipartFile file) throws IOException, ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        user.setPassword(passwordEncoder.encode(user.getRawPassword()));
        user.setIcon(imgService.upload(file));
        System.out.println(file.getSize());
        return ResultJson.success(umsUserService.save(user), "添加用户成功");
    }

    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(umsUserService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(UmsUser user, MultipartFile file) throws ServerException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (file != null && file.getSize() > 0) {
            user.setIcon(imgService.upload(file));
        }
        return ResultJson.success(umsUserService.updateById(user), "修改用户成功");
    }

    @PostMapping("/del")
    ResultJson del(UmsUser user) {
        String message = user.getActive() == 0 ? "删除用户成功" : "恢复用户成功";
        return ResultJson.success(umsUserService.updateById(user), message);
    }

    @GetMapping("/index")
    String index() {
        return "这是Admin中的index方法";
    }

    @PostMapping("/login")
    ResultJson login(String username,String password) throws Exception {
        return ResultJson.success(umsUserService.login(username,password),"登陆成功");
    }
}
