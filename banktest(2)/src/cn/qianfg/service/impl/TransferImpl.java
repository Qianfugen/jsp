package cn.qianfg.service.impl;

import cn.qianfg.service.BankService;
import cn.qianfg.service.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferImpl implements Transfer {
    @Autowired
    private BankService bs;
    @Override
    public int transfer() {
        bs.save();
        bs.withdraw();

        return 1;
    }
}
