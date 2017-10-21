package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.dao.product.BrandDao;
import cn.itcast.core.query.product.BrandQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 品牌事务
 *
 * @author lx
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandDao brandDao;

    @Transactional(readOnly = true)
    public Pagination getBrandListWithPage(Brand brand) {
        //1:起始页  startRow = (pageNo - 1)*pageSize
        //2:每页数
        //3:总记录数
        Integer count = brandDao.getBrandCount(brand);
        Pagination pagination = new Pagination(brand.getPageNo(), brand.getPageSize(), count);
        //Brand集合
        pagination.setList(brandDao.getBrandListWithPage(brand));

        return pagination;
    }

    //查询品牌集合
    public List<Brand> getBrandList(BrandQuery brandQuery) {
        List<Brand> brand = brandDao.getBrandList(brandQuery);
        return brand;
    }

    //添加品牌
    public void addBrand(Brand brand) {
        brandDao.addBrand(brand);
    }

    //删除品牌
    public void deleteBrandByKey(Integer id) {
        brandDao.deleteBrandByKey(id);
    }

    //批量删除
    public void deleteByKeys(Integer[] ids) {
        brandDao.delectByIds(ids);
    }

    //根据id查询品牌
    public Brand selectById(Integer id) {
        Brand brand = brandDao.selectById(id);
        return brand;
    }

    //修改品牌
    public void updateBrand(Brand brand) {
        brandDao.updateBrand(brand);
    }
}
