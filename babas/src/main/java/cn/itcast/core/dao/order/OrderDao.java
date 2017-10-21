package cn.itcast.core.dao.order;

import cn.itcast.core.bean.order.Order;
import cn.itcast.core.query.order.OrderQuery;

import java.util.List;

public interface OrderDao {

    /**
     * 添加
     *
     * @param order
     */
    public Integer addOrder(Order order);

    /**
     * 根据主键查找
     *
     * @param
     */
    public Order getOrderByKey(Integer id);

    /**
     * 根据主键批量查找
     *
     * @param
     */
    public List<Order> getOrdersByKeys(List<Integer> idList);

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
    public Integer updateOrderByKey(Order order);

    /**
     * 分页查询
     *
     * @param orderQuery
     */
    public List<Order> getOrderListWithPage(OrderQuery orderQuery);

    /**
     * 集合查询
     *
     * @param orderQuery
     */
    public List<Order> getOrderList(OrderQuery orderQuery);

    /**
     * 总条数
     *
     * @param orderQuery
     */
    public int getOrderListCount(OrderQuery orderQuery);
}
