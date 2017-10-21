package cn.itcast.core.dao.order;

import cn.itcast.core.bean.order.Detail;
import cn.itcast.core.query.order.DetailQuery;

import java.util.List;

public interface DetailDao {

    /**
     * 添加
     *
     * @param
     */
    public Integer addDetail(Detail detail);

    /**
     * 根据主键查找
     *
     * @param
     */
    public Detail getDetailByKey(Integer id);

    /**
     * 根据主键批量查找
     *
     * @param
     */
    public List<Detail> getDetailsByKeys(List<Integer> idList);

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
    public Integer updateDetailByKey(Detail detail);

    /**
     * 分页查询
     *
     * @param
     */
    public List<Detail> getDetailListWithPage(DetailQuery detailQuery);

    /**
     * 集合查询
     *
     * @param
     */
    public List<Detail> getDetailList(DetailQuery detailQuery);

    /**
     * 总条数
     *
     * @param detailQuery
     */
    public int getDetailListCount(DetailQuery detailQuery);
}
