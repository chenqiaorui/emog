/**
 * 
 */
package com.emog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emog.dto.ProductParam;
import com.emog.model.Product;
import com.github.pagehelper.Page;

/**
 * @author ricky
 *
 */

@Mapper
public interface OrderMapper {
	
	/**
	 * 
	 * 
	 * 
	 * */
	@Select("SELECT * FROM pms_order WHERE num=#{num}")
	Product findByName(@Param("num") String num);
	
	@Select("SELECT * FROM pms_order")
	Page<Product> listAll(@Param("pageNum") Integer pageNum,
			               @Param("pageSize") Integer pageSize);
	
	@Delete("DELETE FROM pms_order WHERE name=#{name}")
	int delByName(@Param("name") String name);
	
	@Update("UPDATE pms_order SET name=#{name},price=#{price} WHERE id =#{id}")
	boolean UpdateById(ProductParam productParam);
	
	@Insert("INSERT INTO pms_order (`name`, `description`, `pic`, `price`, `stock`, `sales`) VALUES (#{name}, #{description}, #{pic}, #{price}, #{stock}, #{sales})")
	boolean insert(Product product);
	
}
