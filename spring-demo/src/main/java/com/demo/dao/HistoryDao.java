package com.demo.dao;

import com.demo.entity.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HistoryDao {

    List<History> selectByID(@Param("username") String username);

    boolean insert(@Param("username")String username,
                @Param("fileName")String fileName,
                @Param("modelType")String modelType,
                @Param("time")String time,
                @Param("isBug")String isBug);
}
