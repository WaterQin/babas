package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.query.product.BrandQuery;

import java.util.List;

/**
 * 品牌
 *
 * @author lx
 */
public interface BrandService {
    //获取品牌列表
    public Pagination getBrandListWithPage(Brand brand);

    //查询集合
    public List<Brand> getBrandList(BrandQuery brandQuery);

    //添加品牌
    public void addBrand(Brand brand);

    //删除品牌
    public void deleteBrandByKey(Integer id);

    //批量删除
    public void deleteByKeys(Integer[] ids);

    //根据id查询品牌
    public Brand selectById(Integer id);

    //修改品牌
    public void updateBrand(Brand brand);
}
