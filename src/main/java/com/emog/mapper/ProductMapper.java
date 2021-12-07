/**
 * 
 */
package com.emog.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emog.dto.ProductParam;
import com.emog.model.Product;

/**
 * @author ricky
 *
 */

@Mapper
public interface ProductMapper {
	
	@Select("SELECT * FROM pms_product WHERE name=#{name}")
	Product findByName(@Param("name") String name);
	
	@Delete("DELETE FROM pms_product WHERE name=#{name}")
	int delByName(@Param("name") String name);
	
	@Update("UPDATE pms_product SET name=#{name},price=#{price} WHERE id =#{id}")
	boolean UpdateById(ProductParam productParam);

}
