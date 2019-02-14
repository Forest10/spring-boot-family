package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.properties.CoreProperties;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28 占位
 */
@Slf4j
@RestController
public class BasicController {


    @Autowired
    private CoreProperties coreProperties;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/properties")
    public String properties() {
        return coreProperties.toString();
    }


    @GetMapping(value = "/getSearchQcode")
    public void getSearchQcode(HttpServletResponse resp, String str) throws IOException {
        String url = "https://www.baidu.com/s?wd=" + str;
        ServletOutputStream stream = null;
        try {
            int width = 2000;//图片的宽度
            int height = 2000;//高度
            stream = resp.getOutputStream();
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(m, "png", stream);
        } catch (WriterException e) {
            log.error("生成二维码失败:", e);
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }

    }
}
