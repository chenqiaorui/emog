/**
 * 
 */
package com.emog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emog.model.Product;

/**
 * @author ricky
 *
 */

@Mapper
public interface ProductMapper {
	
	@Select("SELECT * FROM pms_product WHERE name=#{name}")
	Product findByName(@Param("name") String name);

}
