package com.forest10.spring.boot.family.service.common;

import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019-01-31 11:19
 */
@Service
public class CommonServiceWithOneSonResource extends AbstractServiceWithCommonService {

    //this inject method is optional.because AbstractServiceWithCommonService has used @Resource to inject ICommonInterface
//    @Autowired
//    public void inject(ICommonInterface iCommonInterface) {
//        super.iCommonInterface = iCommonInterface;
//    }

    public String commonSay() {
        return say();
    }

}
