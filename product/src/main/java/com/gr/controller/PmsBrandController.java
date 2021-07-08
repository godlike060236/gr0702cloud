package com.gr.controller;

import com.gr.pojo.PmsBrand;
import com.gr.service.AdminService;
import com.gr.service.IPmsBrandService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/pms-brand")
public class PmsBrandController {
    @Resource
    IPmsBrandService brandService;
    @Resource
    AdminService adminService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(brandService.page(pageNo, pageSize, name));
    }

    @PostMapping("/add")
    ResultJson add(PmsBrand pmsBrand, MultipartFile file) {
        pmsBrand.setLogo(adminService.upload(file));
        return ResultJson.success(brandService.save(pmsBrand), "添加品牌成功");
    }

    @GetMapping("getone")
    ResultJson getone(Long id) {
        return ResultJson.success(brandService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(PmsBrand pmsBrand, MultipartFile file) {
        if(file != null && file.getSize() > 0) {
            pmsBrand.setLogo(adminService.upload(file));
        }return ResultJson.success(brandService.updateById(pmsBrand),"修改用户成功");
    }

    @PostMapping("/del")
    ResultJson del(PmsBrand pmsBrand) {
        String message = pmsBrand.getActive() == 0 ? "删除品牌成功" : "恢复品牌成功";
        return ResultJson.success(brandService.updateById(pmsBrand),message);
    }
}
