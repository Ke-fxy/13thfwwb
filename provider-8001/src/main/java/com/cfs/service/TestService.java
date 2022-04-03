package com.cfs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Ke
 * @Date 2022/3/24 21:23
 * @Description
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class TestService {
}