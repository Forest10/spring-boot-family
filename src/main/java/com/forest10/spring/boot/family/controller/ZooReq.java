package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.common.AnnimalTypeEnum;
import lombok.Data;

/**
 * @author Forest10
 * @date 2019-05-12 18:19
 */
@Data
public class ZooReq {

    private AnnimalTypeEnum annimalTypeEnum;

}
