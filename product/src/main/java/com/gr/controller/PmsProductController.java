package com.gr.controller;


import com.alibaba.fastjson.JSONObject;
import com.gr.pojo.PmsProduct;
import com.gr.pojo.PmsSkuValue;
import com.gr.pojo.PmsSpuValue;
import com.gr.pojo.PmsStock;
import com.gr.service.*;
import com.gr.util.ResultJson;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author guorui
 * @since 2021-07-09
 */
@RestController
@RequestMapping("/pms-product")
public class PmsProductController {
    @Resource
    IPmsProductService productService;
    @Resource
    IPmsBrandService brandService;
    @Resource
    IPmsCategoryService categoryService;
    @Resource
    IPmsSpuService spuService;
    @Resource
    IPmsSkuService skuService;
    @Resource
    AdminService adminService;
    @Resource
    IPmsSpuValueService spuValueService;
    @Resource
    IPmsSkuValueService skuValueService;
    @Resource
    IPmsStockService stockService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(productService.page(pageNo, pageSize, name),"加载数据成功");
    }

    @GetMapping("/listByCategory")
    ResultJson listByCategory(Integer pageNo, Integer pageSize, String categoryId) {
        return ResultJson.success(productService.pageByCategory(pageNo, pageSize, categoryId),"加载数据成功");
    }

    @GetMapping("/getData")
    ResultJson getData() {
        Map<String, List> map = new HashMap<>();
        map.put("brands", brandService.getAll());
        map.put("categorys", categoryService.getAll(0L));
        return ResultJson.success(map);
    }

    @GetMapping("/getAttr")
    ResultJson getAttr(Long[] categoryIds) {
        Map<String, List> map = new HashMap<>();
        map.put("spus", spuService.getByCategory(categoryIds));
        map.put("skus", skuService.getByCategory(categoryIds));
        return ResultJson.success(map);
    }

    @PostMapping("/add")
    @Transactional
    ResultJson add(PmsProduct pmsProduct, MultipartFile file, MultipartFile[] files, String[] spus, String[] skus, String[] stocks) throws IOException {
        pmsProduct.setImg(adminService.upload(file));
        List<String> piclist = new ArrayList<>();
        for (MultipartFile f : files) {
            MultipartFile tempFile = new MockMultipartFile("file", f.getOriginalFilename(), f.getContentType(), f.getBytes());
            piclist.add(adminService.upload(tempFile));
        }
        pmsProduct.setPics(piclist.toString().replaceAll("\\[", " ").replaceAll("\\]", " "));

        productService.save(pmsProduct);
        List<PmsSpuValue> spuValueList = new ArrayList<>();
        for (String spu : spus) {
            PmsSpuValue pmsSpuValue = JSONObject.parseObject(spu, PmsSpuValue.class);
            pmsSpuValue.setProductId(pmsProduct.getId());
            spuValueList.add(pmsSpuValue);
        }
        spuValueService.saveBatch(spuValueList);
        List<PmsSkuValue> skuValueList = new ArrayList<>();
        for (String sku : skus) {
            PmsSkuValue pmsSkuValue = JSONObject.parseObject(sku, PmsSkuValue.class);
            pmsSkuValue.setProductId(pmsProduct.getId());
            skuValueList.add(pmsSkuValue);
        }
        skuValueService.saveBatch(skuValueList);
        List<PmsStock> stockList = new ArrayList<>();
        for (String stock : stocks) {
            PmsStock pmsStock = JSONObject.parseObject(stock, PmsStock.class);
            pmsStock.setProductId(pmsProduct.getId());
            stockList.add(pmsStock);
        }
        stockService.saveBatch(stockList);
        return ResultJson.success("", "添加成功");
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(productService.getById(id));
    }
}
