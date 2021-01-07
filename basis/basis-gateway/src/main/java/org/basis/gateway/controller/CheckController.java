/**
 * Project Name:basis-gateway
 * File Name:ChecController.java
 * Package Name:org.basis.gateway.controller
 * Date:2020年3月22日下午3:38:16
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:ChecController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年3月22日 下午3:38:16 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@RestController
@Slf4j
public class CheckController {

    /**
     * example WW_verify_xR5Ggy8V2fLiYv9d
     *
     * @return
     */
    @GetMapping("/WW_verify_{fileName}.txt")
    public ResponseEntity<String> wwVerify(@PathVariable String fileName) {
        log.info("fileName:{}", fileName);
        return new ResponseEntity<>(fileName, HttpStatus.OK);
    }

    /**
     * example MP_verify_cK9hlziQGJrZKzoR
     *
     * @return
     */
    @GetMapping("/MP_verify_{fileName}.txt")
    public ResponseEntity<String> MPVerify(@PathVariable String fileName) {
        log.info("fileName:{}", fileName);
        return new ResponseEntity<>(fileName, HttpStatus.OK);
    }
}
