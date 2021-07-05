package com.sugon.cloud.document.controller;

import com.sugon.cloud.document.entity.model.ResultModel;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Slf4j
@Api(tags = "图片服务接口")
@RestController
@RequestMapping("/img")
public class ImageController {

    @Value("${image.path}")
    private String imagePath;


    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @ApiOperation(value = "上传图片")
    public ResultModel upload(@ApiParam(name = "file", value = "图片") MultipartFile file) {

        ResultModel resultModel = new ResultModel();
        String fileType = getFileTailName(file.getOriginalFilename());
        String fileRealName = UUID.randomUUID() + fileType;
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            inputStream = file.getInputStream();
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];

            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(imagePath);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileRealName);
            resultModel.setContent(fileRealName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            log.error("upload img err", e);
            resultModel.setStatusCode(0);
        } catch (Exception e) {
            log.error("upload img err", e);
            resultModel.setStatusCode(0);
        } finally {
            // 完毕，关闭所有链接
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return resultModel;
    }

    /**
     * 获取文件后缀名称
     */
    private String getFileTailName(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
