package cn.itcast.core.dao.user;

import cn.itcast.core.bean.user.Buyer;
import cn.itcast.core.query.user.BuyerQuery;

import java.util.List;

public interface BuyerDao {

    /**
     * 添加
     *
     * @param buyer
     */
    public Integer addBuyer(Buyer buyer);

    /**
     * 根据主键查找
     *
     * @param
     */
    public Buyer getBuyerByKey(String id);

    /**
     * 根据主键批量查找
     *
     * @param
     */
    public List<Buyer> getBuyersByKeys(List<String> idList);

    /**
     * 根据主键删除
     *
     * @param
     */
    public Integer deleteByKey(String id);

    /**
     * 根据主键批量删除
     *
     * @param
     */
    public Integer deleteByKeys(List<String> idList);

    /**
     * 根据主键更新
     *
     * @param
     */
    public Integer updateBuyerByKey(Buyer buyer);

    /**
     * 分页查询
     *
     * @param
     */
    public List<Buyer> getBuyerListWithPage(BuyerQuery buyerQuery);

    /**
     * 集合查询
     *
     * @param
     */
    public List<Buyer> getBuyerList(BuyerQuery buyerQuery);

    /**
     * 总条数
     *
     * @param
     */
    public int getBuyerListCount(BuyerQuery buyerQuery);
}
