package org.example.change.service;

import org.example.change.DAO.LSDAO;
import org.example.change.entity.LS;
import org.example.change.entity.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LSServiceImpl implements LSService {
    @Autowired
    private LSDAO lsdao;
    @Override
    public int save1(LS ls){
        return lsdao.save1(ls);
    }

    @Override
    public int save2(LS ls){
        return lsdao.save2(ls);
    }

    @Override
    public int save3(LS ls){
        return lsdao.save3(ls);
    }
    @Override
    public int save41(Msg msg){
        return lsdao.save41(msg);
    }
    @Override
    public int save42(Msg msg){
        return lsdao.save42(msg);
    }
    @Override
    public int save5(Msg msg){
        return lsdao.save5(msg);
    }
}
