package ink.zhaibo.service;

import ink.zhaibo.rpc.RpcAliyunSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements ISMSService {

    @Autowired
    RpcAliyunSms rpcAliyunSms;

    @Override
    public String sendMessage(String telephone) {
        return rpcAliyunSms.sendMessage(telephone);
    }
}
