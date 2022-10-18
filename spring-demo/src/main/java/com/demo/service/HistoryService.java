package com.demo.service;

import com.demo.dao.HistoryDao;
import com.demo.dao.UserDao;
import com.demo.entity.History;
import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryDao historyDao;

    public List<History> selectByID(String username){

        List<History> historyList = historyDao.selectByID(username);
        return historyList;

    }


    public boolean insert(String username,String fileName,String modelType,String time,String isBug) {
        return historyDao.insert(username, fileName, modelType,time, isBug);
    }
}
