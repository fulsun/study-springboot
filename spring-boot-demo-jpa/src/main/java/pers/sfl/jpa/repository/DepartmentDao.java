package pers.sfl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.sfl.jpa.entity.Department;

import java.util.List;


/**
 * <p>
 * User Dao
 * </p>
 *
 * @package: com.xkcoding.orm.jpa.repository
 * @description: Department Dao
 * @author: 76peter
 * @date: Created in 2019/10/1 18:07
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: 76peter
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department, Long> {
    /**
     * 根据层级查询部门
     *
     * @param level 层级
     * @return 部门列表
     */
    List<Department> findDepartmentsByLevels(Integer level);
}
