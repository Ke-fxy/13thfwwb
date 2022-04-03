package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.service.FaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author Ke
 * @Date 2022/3/24 19:56
 * @Description
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/face")
@CrossOrigin(value = "*",maxAge = 3600)
public class FaceController {

    @Resource
    FaceService faceService;

    @PostMapping(value = "/search")
    public CommonResult<String> search(@RequestBody HashMap<String,Object> map){

       return faceService.search(map);

    }

    @PostMapping(value = "/addFace")
    public CommonResult<String> addFace(@RequestBody HashMap<String,Object> map){

        return faceService.addFace(map);

    }

}