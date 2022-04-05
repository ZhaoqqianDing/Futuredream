package com.futuredreamtraveller.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EmissionMapper {
    double getEmissionByName(String type);
}
