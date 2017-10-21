package cn.itcast.core.dao.user;

import cn.itcast.core.bean.user.Employee;
import cn.itcast.core.query.user.EmployeeQuery;

import java.util.List;

public interface EmployeeDao {

    /**
     * 添加
     *
     * @param
     */
    public Integer addEmployee(Employee employee);

    /**
     * 根据主键查找
     *
     * @param
     */
    public Employee getEmployeeByKey(String id);

    /**
     * 根据主键批量查找
     *
     * @param
     */
    public List<Employee> getEmployeesByKeys(List<String> idList);

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
    public Integer updateEmployeeByKey(Employee employee);

    /**
     * 分页查询
     *
     * @param
     */
    public List<Employee> getEmployeeListWithPage(EmployeeQuery employeeQuery);

    /**
     * 集合查询
     *
     * @param
     */
    public List<Employee> getEmployeeList(EmployeeQuery employeeQuery);

    /**
     * 总条数
     *
     * @param
     */
    public int getEmployeeListCount(EmployeeQuery employeeQuery);
}
