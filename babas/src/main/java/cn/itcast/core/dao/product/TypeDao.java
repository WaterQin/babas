package cn.itcast.core.dao.product;

import cn.itcast.core.bean.product.Type;
import cn.itcast.core.query.product.TypeQuery;

import java.util.List;

public interface TypeDao {

    /**
     * 添加
     *
     * @param type
     */
    public Integer addType(Type type);

    /**
     * 根据主键查找
     *
     * @param
     */
    public Type getTypeByKey(Integer id);

    /**
     * 根据主键批量查找
     *
     * @param
     */
    public List<Type> getTypesByKeys(List<Integer> idList);

    /**
     * 根据主键删除
     *
     * @param
     */
    public Integer deleteByKey(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param
     */
    public Integer deleteByKeys(List<Integer> idList);

    /**
     * 根据主键更新
     *
     * @param
     */
    public Integer updateTypeByKey(Type type);

    /**
     * 分页查询
     *
     * @param typeQuery
     */
    public List<Type> getTypeListWithPage(TypeQuery typeQuery);

    /**
     * 集合查询
     *
     * @param typeQuery
     */
    public List<Type> getTypeList(TypeQuery typeQuery);

    /**
     * 总条数
     *
     * @param typeQuery
     */
    public int getTypeListCount(TypeQuery typeQuery);
}
