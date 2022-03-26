package com.cfs.service;

import com.cfs.entities.CommonResult;
import com.cfs.entities.Paper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * @Author Ke
 * @Date 2022/3/26 19:50
 * @Description
 * @Version 1.0
 */
@Service
@FeignClient(name = "nacos-provider")
public interface PaperService {

    @RequestMapping("/paper/addPaper")
    public CommonResult<String> addPaper(@RequestBody HashMap<String, Object> map);

    @RequestMapping("/paper/addPaperQuestion")
    public CommonResult<String> addPaperQuestion(@RequestBody HashMap<String, Object> map);

    @RequestMapping("/paper/deletePaper")
    public CommonResult<String> deletePaper(@RequestBody HashMap<String, Object> map);

    @RequestMapping("/paper/updatePaper")
    public CommonResult<String> updatePaper(@RequestBody HashMap<String, Object> map);

    @RequestMapping("/paper/getPaper")
    public CommonResult<Paper> getPaper(@RequestBody HashMap<String, Object> map);

    @RequestMapping("/paper/getAllPaper")
    public CommonResult<Object> getAllPaper(@RequestBody HashMap<String, Object> map);

    @RequestMapping("/paper/updatePaperQuestion")
    public CommonResult<String> updatePaperQuestion(@RequestBody HashMap<String, Object> map);

}
