package com.gr.controller;


import com.gr.pojo.PmsCategory;
import com.gr.service.IPmsCategoryService;
import com.gr.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-08
 */
@RestController
@RequestMapping("/pms-category")
public class PmsCategoryController {
    @Resource
    IPmsCategoryService categoryService;

    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(categoryService.getByParentId(0L));
    }

    @GetMapping("/getListById")
    ResultJson list(Long id) {
        return ResultJson.success(categoryService.getAll(id));
    }

    @PostMapping("/add")
    ResultJson add(PmsCategory pmsCategory) {
        return ResultJson.success(categoryService.save(pmsCategory), "添加分类成功");
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(categoryService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(PmsCategory pmsCategory) {
        return ResultJson.success(categoryService.updateById(pmsCategory), "修改分类成功");
    }

    @PostMapping("/del")
    ResultJson del(PmsCategory pmsCategory) {
        String message = pmsCategory.getActive() == 0 ? "删除分类成功" : "恢复分类成功";
        return ResultJson.success(categoryService.updateById(pmsCategory), message);
    }
}
