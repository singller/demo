package com.zjx.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    public class ThymeleafController {

        @RequestMapping("/index")
        public String index() {
            return "thymeleaf/index";
        }
    }


//    @Autowired
//    AbstractParserService abstractParserService;
//
//    @GetMapping("/index")
//    public String index() {
//        abstractParserService.generateMerchantFile();
//        return "thymeleaf/index";
//    }


}
