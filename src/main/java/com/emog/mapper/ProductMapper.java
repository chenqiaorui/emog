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
public interface ProductMapper {
	
	@Select("SELECT * FROM pms_product WHERE name=#{name}")
	Product findByName(@Param("name") String name);
	
//	@Select("SELECT * FROM pms_product")
//	List<Product> listAll();
	
	@Select("SELECT * FROM pms_product")
	Page<Product> listAll(@Param("pageNum") Integer pageNum,
			               @Param("pageSize") Integer pageSize);
	
	@Delete("DELETE FROM pms_product WHERE name=#{name}")
	int delByName(@Param("name") String name);
	
	@Update("UPDATE pms_product SET name=#{name},price=#{price} WHERE id =#{id}")
	boolean UpdateById(ProductParam productParam);
	
	@Insert("INSERT INTO pms_product (`name`, `description`, `pic`, `price`, `stock`, `sales`) VALUES (#{name}, #{description}, #{pic}, #{price}, #{stock}, #{sales})")
	boolean insert(Product product);
	
}
