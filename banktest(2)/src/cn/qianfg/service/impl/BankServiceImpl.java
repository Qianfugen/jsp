package cn.qianfg.service.impl;

import cn.qianfg.dao.BankDao;
import cn.qianfg.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankDao bd;
    @Override
    public int save() {
        int flag=bd.save();
//        int i=10/0;
        return flag;
    }

    @Override
    public int withdraw() {
        int flag=bd.withdraw();
        return flag;
    }
}
