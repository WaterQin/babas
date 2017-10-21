package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Img;
import cn.itcast.core.bean.product.Product;
import cn.itcast.core.bean.product.Sku;
import cn.itcast.core.dao.product.ProductDao;
import cn.itcast.core.query.product.ImgQuery;
import cn.itcast.core.query.product.ProductQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 商品事务层
 *
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductDao productDao;
    @Autowired
    ImgService imgService;
    @Autowired
    SkuService skuService;

    /**
     * 插入数据库
     *
     * @return
     */
    public Integer addProduct(Product product) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String no = df.format(new Date());
        //后台生成商品编号
        product.setNo(no);
        //后台生成添加时间
        product.setCreateTime(new Date());
        Integer i = productDao.addProduct(product);
        //保存图片补全图片信息
        product.getImg().setIsDef(1);
        product.getImg().setProductId(product.getId());
        //保存图片
        imgService.addImg(product.getImg());
        //保存Sku
        Sku sku = new Sku();
        sku.setProductId(product.getId());
        sku.setSkuPrice(0.00);
        sku.setStockInventory(0);
        sku.setSkuUpperLimit(1);
        sku.setLocation("");
        sku.setMarketPrice(0.00);
        sku.setCreateTime(new Date());
        sku.setLastStatus(1);
        sku.setSkuType(1);
        sku.setSales(0);
        for (String colorId : product.getColor().split(",")) {
            sku.setColorId(Integer.parseInt(colorId));
            for (String size : product.getSize().split(",")) {
                sku.setSize(size);
                skuService.addSku(sku);
            }
        }
        return i;
    }

    /**
     * 根据主键查找
     */
    @Transactional(readOnly = true)
    public Product getProductByKey(Integer id) {
        Product product = productDao.getProductByKey(id);
        ImgQuery imgQuery = new ImgQuery();
        imgQuery.setProductId(product.getId());
        imgQuery.setIsDef(1);
        List<Img> imgs = imgService.getImgList(imgQuery);
        product.setImg(imgs.get(0));
        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByKeys(List<Integer> idList) {
        return productDao.getProductsByKeys(idList);
    }

    /**
     * 根据主键删除
     *
     * @return
     */
    public Integer deleteByKey(Integer id) {
        return productDao.deleteByKey(id);
    }

    public Integer deleteByKeys(List<Integer> idList) {
        return productDao.deleteByKeys(idList);
    }

    /**
     * 根据主键更新
     *
     * @return
     */
    public Integer updateProductByKey(Product product) {
        return productDao.updateProductByKey(product);
    }

    /**
     * 根据条件查询分页查询
     *
     * @param productQuery 查询条件
     * @return
     */
    @Transactional(readOnly = true)
    public Pagination getProductListWithPage(ProductQuery productQuery) {
        Pagination p = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), productDao.getProductListCount(productQuery));
        List<Product> products = productDao.getProductListWithPage(productQuery);
        for (Product product : products) {
            ImgQuery imgQuery = new ImgQuery();
            imgQuery.setIsDef(1);
            imgQuery.setProductId(product.getId());
            List<Img> img = imgService.getImgList(imgQuery);
            if (img.size() > 0) {
                product.setImg(img.get(0));
            }
        }
        p.setList(products);
        return p;
    }

    @Transactional(readOnly = true)
    public List<Product> getProductList(ProductQuery productQuery) {
        return productDao.getProductList(productQuery);
    }
}
