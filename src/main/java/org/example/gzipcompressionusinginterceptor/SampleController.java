package org.example.gzipcompressionusinginterceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {

    @GetMapping("/get")
    public ResponseEntity<Map<String,Object>> getDetails(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
  HttpHeaders  headers = new HttpHeaders();
  headers.add("abc","def");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Map.of("data",map));

    }
}
