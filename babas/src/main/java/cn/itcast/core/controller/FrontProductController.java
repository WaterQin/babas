package cn.itcast.core.controller;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.*;
import cn.itcast.core.query.product.*;
import cn.itcast.core.service.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台商品列表
 * 测试
 * 商品详情页面
 *
 * @author lx
 */
@Controller
@RequestMapping(value = "/product/display")
public class FrontProductController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private FeatureService featureService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ImgService imgService;

    //商品列表页面
    @RequestMapping(value = "/list.shtml")
    public String list(Integer pageNo, Integer brandId, String brandName, Integer typeId, String typeName, ModelMap model) {
        //1:加载品牌 ,类型,材质
        FeatureQuery featureQuery = new FeatureQuery();
        List<Feature> features = featureService.getFeatureList(featureQuery);
        model.addAttribute("features", features);
        //2:分页参数
        StringBuffer params = new StringBuffer();
        //3:加载商品数据
        ProductQuery productQuery = new ProductQuery();
        //设置页码
        productQuery.setPageNo(Pagination.cpn(pageNo));
        productQuery.setPageSize(Product.FRONT_PAGE_SIZE);
        //设置Id倒排
        productQuery.orderbyId(false);
        //隐藏已选条件
        boolean flag = false;
        //条件Map窗口
        Map<String, String> query = new LinkedHashMap<String, String>();
        //品牌id
        if (null != brandId) {
            flag = true;
            productQuery.setBrandId(brandId);
            //显示在页面
            model.addAttribute("brandId", brandId);
            model.addAttribute("brandName", brandName);
            query.put("品牌", brandName);
            params.append("&").append("brandId=").append(brandId).append("&brandName=").append(brandName);
        } else {
            //加载品牌对象
            BrandQuery brandQuery = new BrandQuery();
            brandQuery.setFields("id,name");
            brandQuery.setIsDisplay(1);
            List<Brand> brands = brandService.getBrandList(brandQuery);
            model.addAttribute("brands", brands);
        }
        //类型ID
        if (null != typeId) {
            flag = true;
            productQuery.setTypeId(typeId);
            query.put("类型", typeName);
            //显示在页面
            model.addAttribute("typeId", typeId);
            model.addAttribute("typeName", typeName);
            params.append("&").append("typeId=").append(typeId).append("&typeName=").append(typeName);
        } else {
            //加载类型
            TypeQuery typeQuery = new TypeQuery();
            //指定查询哪些字段
            typeQuery.setFields("id,name");
            typeQuery.setIsDisplay(1);
            typeQuery.setParentId(1);
            List<Type> types = typeService.getTypeList(typeQuery);
            //显示在页面
            model.addAttribute("types", types);
        }
        model.addAttribute("flag", flag);
        //条件
        model.addAttribute("query", query);

        //加载带有分页的商品
        Pagination pagination = productService.getProductListWithPage(productQuery);
        //分页页面展示    //String params = "brandId=1&name=2014瑜伽服套装新款&pageNo=1";
        String url = "/product/display/list.shtml";
        pagination.pageView(url, params.toString());
        model.addAttribute("pagination", pagination);
        return "product/product";
    }

    //跳转到详情页面
    @RequestMapping(value = "/detail.shtml")
    public String detail(Integer id, ModelMap model) {
        //商品加载
        Product product = productService.getProductByKey(id);
        model.addAttribute("product", product);
        //图片的加载
        ImgQuery imgQuery = new ImgQuery();
        imgQuery.setProductId(id);
        imgQuery.setIsDef(1);
        List<Img> img = imgService.getImgList(imgQuery);
        model.addAttribute("img", img);
        //查询库存
        SkuQuery skuQuery = new SkuQuery();
        skuQuery.setProductId(id);
        List<Sku> skus = skuService.getStock(skuQuery);
        model.addAttribute("skus", skus);
        //去重复的颜色
        if (skus.size() > 0 && null != skus) {
            List<Integer> ids = new ArrayList<Integer>();
            for (Sku sku : skus) {
                if (!ids.contains(sku.getColorId())) {
                    ids.add(sku.getColorId());
                }
            }
            //吧不重复的color放入页面
            model.addAttribute("colors", colorService.getColorsByKeys(ids));
        }
        return "product/productDetail";
    }
}
