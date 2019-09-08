package com.github.byference.transactional.controller;

import com.github.byference.transactional.entity.Turnover;
import com.github.byference.transactional.service.TransactionalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Transactional {@link RestController}
 *
 * @author byference
 * @since 2019-09-08
 */
@RestController
@Slf4j
@AllArgsConstructor
public class TransactionalController {

    private final TransactionalService transactionalService;

    /**
     * 转账
     */
    @PostMapping("/transfer")
    public String transfer(Turnover turnover) {

        log.info("转账: {}", turnover);
        String result;
        try {
            transactionalService.transfer(turnover);
            result = "转账成功";
        } catch (Exception e) {
            log.error("转账异常" + e.getMessage(), e);
            result = "转账失败";
        }
        return result;
    }

}
