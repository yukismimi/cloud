package com.yukismimi.payservice.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    DefaultAlipayClient alipayClient;

    @GetMapping("/return_uri")
    public Map<String, String[]> returnUrl(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        map.forEach((k,v) -> {
            System.out.println(k+":"+v);
        });
        return map;
    }

    @PostMapping("/notify_uri")
    public Map<String, String[]> getNotify(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        map.forEach((k,v) -> {
            System.out.println(k+":"+v);
        });
        return map;
    }

    @GetMapping("/pay")
    public ResponseEntity<String> pay() throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl("http://localhost:8080/gateway/alipay/return_uri");
        request.setNotifyUrl("http://localhost:8080/gateway/alipay/return_uri");
        request.setBizContent("{" +
                "\"out_trade_no\":\""+ System.currentTimeMillis() +"\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "\"total_amount\":\"1.00\"," +
                "\"subject\":\"陆总霸气之陆总定制豪华专用客机\"" +
                "  }");
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type","text/html;charset=utf8");
        return new ResponseEntity<>(response.getBody(), httpHeaders, HttpStatus.OK);
    }
}
