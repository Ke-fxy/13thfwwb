package com.cfs.service;

import com.cfs.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

/**
 * @Author Ke
 * @Date 2022/3/24 19:55
 * @Description
 * @Version 1.0
 */
@Service
@FeignClient(name = "nacos-provider")
public interface FaceService {

    @PostMapping(value = "/face/addFace")
    public CommonResult<String> addFace(@RequestBody HashMap<String,Object> map);

    @PostMapping(value = "/search")
    public CommonResult<String> search(@RequestBody HashMap<String,Object> map);

}
