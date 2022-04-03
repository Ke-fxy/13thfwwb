package com.cfs.controller;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Paper;
import com.cfs.service.PaperService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author Ke
 * @Date 2022/3/26 19:51
 * @Description
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/paper")
@CrossOrigin(value = "*",maxAge = 3600)
public class PaperController {

    @Resource
    PaperService paperService;

    @RequestMapping("/addPaper")
    public CommonResult<String> addPaper(@RequestBody HashMap<String, Object> map){

        return paperService.addPaper(map);

    }

    @RequestMapping("/addPaperQuestion")
    public CommonResult<String> addPaperQuestion(@RequestBody HashMap<String, Object> map){

        return paperService.addPaperQuestion(map);

    }

    @RequestMapping("/deletePaper")
    public CommonResult<String> deletePaper(@RequestBody HashMap<String, Object> map){

        return paperService.deletePaper(map);

    }

    @RequestMapping("/updatePaper")
    public CommonResult<String> updatePaper(@RequestBody HashMap<String, Object> map){

        return paperService.updatePaper(map);

    }

    @RequestMapping("/getPaper")
    public CommonResult<Paper> getPaper(@RequestBody HashMap<String, Object> map){

        return paperService.getPaper(map);

    }

    @RequestMapping("/getAllPaper")
    public CommonResult<Object> getAllPaper(@RequestBody HashMap<String, Object> map){

        return paperService.getAllPaper(map);

    }

    @RequestMapping("/updatePaperQuestion")
    public CommonResult<String> updatePaperQuestion(@RequestBody HashMap<String, Object> map){

        return paperService.updatePaperQuestion(map);

    }

}