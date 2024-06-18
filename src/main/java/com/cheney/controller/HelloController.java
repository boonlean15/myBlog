package com.cheney.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping(value = "/list")
    public JSONObject list(){

        JSONObject object = new JSONObject();
        object.put("emos_num","EMOS-00000018");
        object.put("app_key","gZZIwITpDge5jODD");
        object.put("operate_theme","演示测试EMOS工单18");
        object.put("factory","华为");
        object.put("operate_proposer","nibaba");
        object.put("operate_proposer_tel","13512771777");
        object.put("exploit_unit","演示测试单位4");
        object.put("operate_content","演示测试EMOS工单8");
        object.put("schedule_operate_start_day","2022-11-07 09:00:00");
        object.put("schedule_operate_end_day","2022-11-07 09:00:00");
        object.put("audit_opinion","同意申请");
        object.put("audit_result","通过");
        object.put("audit_user","qwerty");
        object.put("audit_complete_time","2022-11-06 17:10:00");
        object.put("audit_commit_time","2022-11-06 09:00:00");
        object.put("template_name","erer");
        object.put("op_type","ererqqq");
        object.put("change_according_file","/cmp-file/order/e8b17dc6-fd21-46e6-9e0b-75b7c43d0ab2/硬件复位变更清单11_3_20230907160329.csv");
        object.put("change_scheme_file","/cmp-file/order/e8b17dc6-fd21-46e6-9e0b-75b7c43d0ab2/硬件复位变更清单11_3_20230907160329.csv");

        JSONArray rows = new JSONArray();
        rows.add(object);

        JSONObject data = new JSONObject();
        data.put("total",10001);
        data.put("rows", rows);
        JSONObject result = new JSONObject();
        result.put("data",data);
        result.put("status",200);

        return result;
    }


}
