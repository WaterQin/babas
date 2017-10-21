package cn.itcast.core.controller.admin;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.*;
import cn.itcast.core.query.product.*;
import cn.itcast.core.service.product.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * 后台商品管理
 * 商品列表
 * 商品添加'
 * 商品上架
 *
 * @author lx
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @Autowired
    TypeService typeService;
    @Autowired
    ColorService colorService;
    @Autowired
    FeatureService featureService;

    //商品列表页面
    @RequestMapping(value = "/list.do")
    public String list(Integer pageNo, String name, Integer brandId, Integer isShow, ModelMap model) {
        //默认加载品牌
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setFields("id,name");
        brandQuery.setIsDisplay(1);
        //加载品牌
        List<Brand> brands = brandService.getBrandList(brandQuery);

        model.addAttribute("brands", brands);

        //获取图片的url

        //分页参数
        StringBuilder params = new StringBuilder();

        //商品条件对象
        ProductQuery productQuery = new ProductQuery();
        //1:判断条件是为Null
        if (StringUtils.isNotBlank(name)) {
            productQuery.setName(name);
            //要求模糊查询
            productQuery.setNameLike(true);

            params.append("&name=").append(name);

            //回显查询条件
            model.addAttribute("name", name);
        }
        //判断品牌ID
        if (null != brandId) {
            productQuery.setBrandId(brandId);
            params.append("&").append("brandId=").append(brandId);

            //回显查询条件
            model.addAttribute("brandId", brandId);
        }
        //判断上下架状态
        if (null != isShow) {
            productQuery.setIsShow(isShow);
            params.append("&").append("isShow=").append(isShow);

            //回显查询条件
            model.addAttribute("isShow", isShow);
        } else {
            productQuery.setIsShow(0);
            params.append("&").append("isShow=").append(1);
            //回显查询条件
            model.addAttribute("isShow", 0);
        }
        //设置页号
        productQuery.setPageNo(Pagination.cpn(pageNo));
        //设置每页数
        productQuery.setPageSize(5);

        //加载带有分页的商品
        Pagination pagination = productService.getProductListWithPage(productQuery);

        //分页页面展示
        String url = "/product/list.do";
        pagination.pageView(url, params.toString());

        model.addAttribute("pagination", pagination);

        return "product/list";
    }

    //跳转到添加页面
    @RequestMapping(value = "/toAdd.do")
    public String toAdd(ModelMap model) {
        //加载1)商品类型2)商品品牌3)材质4)颜色
        //加载类型
        TypeQuery typeQuery = new TypeQuery();
        typeQuery.setFields("id,name");
        typeQuery.setIsDisplay(1);
        typeQuery.setParentId(1);
        List<Type> type = typeService.getTypeList(typeQuery);
        model.addAttribute("type", type);
        //加载品牌
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.setFields("id,name");
        brandQuery.setIsDisplay(1);
        List<Brand> brand = brandService.getBrandList(brandQuery);
        model.addAttribute("brand", brand);
        //加载材质
        FeatureQuery featureQuery = new FeatureQuery();
        featureQuery.setIsDel(1);
        List<Feature> features = featureService.getFeatureList(featureQuery);
        model.addAttribute("features", features);
        //加载颜色大全
        ColorQuery colorQuery = new ColorQuery();
        colorQuery.setParentId(0);
        List<Color> colors = colorService.getColorList(colorQuery);
        model.addAttribute("colors", colors);
        return "product/add";
    }

    //商品添加
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public String add(Product product, Img img) {
        //1:商品 表   图片表   SKu表
        //将图片放入
        product.setImg(img);
        //保存商品  因为要控制事务 所以在商品的Service层一起保存
        productService.addProduct(product);
        return "redirect:/product/list.do";
    }


}
