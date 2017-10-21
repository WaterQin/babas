package cn.itcast.core.dao.product;

import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.query.product.BrandQuery;

import java.util.List;

/**
 * 品牌
 *
 * @author lx
 */
public interface BrandDao {
    //List集合
    public List<Brand> getBrandListWithPage(Brand brand);

    //查询集合
    public List<Brand> getBrandList(BrandQuery brandQuery);

    //查询总记录数
    public int getBrandCount(Brand brand);

    //添加品牌
    public void addBrand(Brand brand);

    //删除品牌
    public void deleteBrandByKey(Integer id);

    //根据id查找品牌
    public Brand selectById(Integer id);

    //修改品牌
    public void updateBrand(Brand brand);

    //批量删除
    public void delectByIds(Integer[] ids);
}
