package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into employee(name, username, password, phone, sex, id_number, create_time, update_time, create_user, update_user)" +
            "values (#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Employee employee);

    /**
     * 分页查询
     * @param start
     * @param employeePageQueryDTO
     * @return
     */
    List<Employee> rows(@Param("start") Integer start,
                        @Param("employeePageQueryDTO") EmployeePageQueryDTO employeePageQueryDTO);

    Long total(@Param("employeePageQueryDTO") EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 员工数据更新
     * 采用动态SQL写法
     * @param employee
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);

    /**
     * 根据员工ID找到员工信息
     * @param id
     * @return
     */
    @Select("select *from employee where id=#{id}")
    Employee getEmpInfoById(Integer id);
}
