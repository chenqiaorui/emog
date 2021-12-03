/**
 * 
 */
package com.emog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emog.model.City;

/**
 * @author ricky
 *
 */

@Mapper
public interface CityMapper {
	
	@Select("SELECT * FROM city WHERE state=#{state}")
	City findByState(@Param("state") String state);

}
