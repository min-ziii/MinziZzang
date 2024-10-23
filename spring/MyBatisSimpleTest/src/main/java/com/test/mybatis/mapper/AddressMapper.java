package com.test.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.test.mybatis.dto.AddressDTO;

public interface AddressMapper {

	@Select("select * from tblAddress where seq = #{seq}")
	AddressDTO get(String seq);

	List<AddressDTO> list();

}




