package ink.zhaibo.controller;

import ink.zhaibo.service.ISMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SMSController {

    @Autowired
    private ISMSService smsService;

    @ResponseBody
    @RequestMapping(value = "/send/{telephone}",method = RequestMethod.GET)
    public String sendMessage(@PathVariable String telephone){
        if(StringUtils.isEmpty(telephone)){
            return "手机号为空";
        }
        return smsService.sendMessage(telephone);
    }
}
