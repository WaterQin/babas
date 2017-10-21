package cn.itcast.core.controller.admin;

import cn.itcast.common.web.ResponseUtils;
import cn.itcast.core.bean.product.Color;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.query.product.SkuQuery;
import cn.itcast.core.service.product.ColorService;
import cn.itcast.core.service.product.SkuService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Qinshui on 2017/10/18.
 */

@Controller
@RequestMapping("/sku")
public class SkuController {
    @Autowired
    SkuService skuService;
    @Autowired
    ColorService colorService;

    //库存list
    @RequestMapping(value = "/list.do")
    public String list(Integer id, String no, ModelMap model) {
        SkuQuery skuQuery = new SkuQuery();
        skuQuery.setProductId(id);
        List<Sku> skus = skuService.getSkuList(skuQuery);
        for (Sku s : skus) {
            Color color = colorService.getColorByKey(s.getColorId());
            s.setColor(color);
        }
        model.addAttribute("sku", skus);
        model.addAttribute("pno", no);
        return "sku/list";
    }

    //修改保存库存
    @RequestMapping("/add.do")
    public void add(Sku sku, HttpServletResponse response) {
        skuService.updateSkuByKey(sku);
        JSONObject json = new JSONObject();
        json.put("message", "保存成功");
        ResponseUtils.renderJson(response, json.toString());
    }

}
