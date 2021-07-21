package com.gr.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gr.config.ProductConfig;
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
import java.util.*;

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
    @Resource
    ProductConfig productConfig;

    @GetMapping("/backList")
    ResultJson backList(Integer pageNo, Integer pageSize) {
        return ResultJson.success(productService.page(new Page<>(pageNo,pageSize)), "加载数据成功");
    }

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name, String categoryId, String keyWord) {
        return ResultJson.success(productService.page1(pageNo, pageSize, name, categoryId, keyWord), "加载数据成功");
    }

    @PostMapping("/publish")
    ResultJson publish(PmsProduct pmsProduct) {
        String message = pmsProduct.getPublishStatus() == 1 ? "商品上架成功" : "商品下架成功";
        return ResultJson.success(productService.updateById(pmsProduct),message);
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
        return ResultJson.success(productService.getById(id),"加载数据成功");
    }

    @GetMapping("/getHotWords")
    ResultJson getHotWords() {
        List<PmsProduct> list = productService.getKeyWords();
        List<String> keyWords = new ArrayList<>();
        for (PmsProduct product : list) {
            String[] words = product.getKeywords().split(" ");
            keyWords.addAll(Arrays.asList(words));
        }
        String[] temp = keyWords.toArray(new String[0]);
        return ResultJson.success(productConfig.getHotWords(temp));
    }

}
