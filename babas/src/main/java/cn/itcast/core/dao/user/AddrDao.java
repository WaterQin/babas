package cn.itcast.core.dao.user;

import cn.itcast.core.bean.user.Addr;
import cn.itcast.core.query.user.AddrQuery;

import java.util.List;

public interface AddrDao {

    /**
     * 添加
     *
     * @param
     */
    public Integer addAddr(Addr addr);

    /**
     * 根据主键查找
     *
     * @param
     */
    public Addr getAddrByKey(Integer id);

    /**
     * 根据主键批量查找
     *
     * @param
     */
    public List<Addr> getAddrsByKeys(List<Integer> idList);

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
    public Integer updateAddrByKey(Addr addr);

    /**
     * 分页查询
     *
     * @param
     */
    public List<Addr> getAddrListWithPage(AddrQuery addrQuery);

    /**
     * 集合查询
     *
     * @param
     */
    public List<Addr> getAddrList(AddrQuery addrQuery);

    /**
     * 总条数
     *
     * @param addrQuery
     */
    public int getAddrListCount(AddrQuery addrQuery);
}
