package com.wedonegood.hrtool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Controller
@RequestMapping(value = "/{locale:en|es|fr}")
public class InternationalController {
	
	private final static String VIEW_NAME = "international";
	 
    @GetMapping("/international")
    public String getInternationalPage() {
        return VIEW_NAME;
    }
}
