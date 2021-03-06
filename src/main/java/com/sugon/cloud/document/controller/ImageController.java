package com.sugon.cloud.document.controller;

import com.sugon.cloud.document.entity.model.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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

    @RequestMapping(method = RequestMethod.GET, value = "/package")
    @ApiOperation(value = "下载图片包")
    public String packageImg(HttpServletResponse servletResponse) {
        //打包img
        File downloadFile = new File("/opt/img.zip");
        if (downloadFile.exists()) {
            downloadFile.delete();
        }
        long startTime = System.currentTimeMillis();
        ZipMultiFile(imagePath, "/opt/img.zip");
        long endTime = System.currentTimeMillis();
        log.info("package waste time: {} ms", endTime - startTime);

        File file = new File("/opt/img.zip");
        FileInputStream fileInputStream = null;
        OutputStream os = null;
        try {
            fileInputStream = new FileInputStream(file);
            os = servletResponse.getOutputStream();
            byte[] buffer = new byte[1024 * 1024];
            int count = 0;
            while ((count = fileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, count);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if ( fileInputStream != null) {
                    fileInputStream.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /** 一次性压缩多个文件，文件存放至一个文件夹中*/
    private  void ZipMultiFile(String filepath ,String zippath) {
        try {
            File file = new File(filepath);// 要被压缩的文件夹
            File zipFile = new File(zippath);

            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            if(file.isDirectory()){
                File[] files = file.listFiles();
                //创建写入通道
                WritableByteChannel writableByteChannel = Channels.newChannel(zipOut);
                for(int i = 0; i < files.length; ++i){
                    FileChannel fileChannel = new FileInputStream(files[i]).getChannel();
                    String outFilePath = file.getPath() + File.separator + files[i].getName();
                    zipOut.putNextEntry(new ZipEntry(outFilePath.replace("\\", "/")));
                    fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
                 }
                writableByteChannel.close();
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/package")
    @ApiOperation(value = "上传图片包")
    public ResultModel uploadImgPackage(@ApiParam(name = "file", value = "图片") MultipartFile file) {
        ResultModel resultModel = new ResultModel();

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
            File tempFile = new File("/opt");
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + file.getOriginalFilename());
            resultModel.setContent(file.getOriginalFilename());
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
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //解压
        long startTime = System.currentTimeMillis();
        ZipContraMultiFile("/opt/package.zip", "/opt");
        long endTime = System.currentTimeMillis();
        log.info("unzip =======waste=======> {} ms", endTime - startTime);
        return resultModel;
    }

     private void ZipContraMultiFile(String zippath ,String outzippath){
        try {
            File file = new File(zippath);
            File outFile = null;
            ZipFile zipFile = new ZipFile(file);
            ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            InputStream input = null;
            OutputStream output = null;

            while((entry = zipInput.getNextEntry()) != null){
                log.info("====解压缩 " + entry.getName() + " 文件======");
                if (entry.isDirectory()) {
                    //create if don't exist
                    File directoryFile = new File(entry.getName());
                    if (!directoryFile.exists()) {
                        directoryFile.mkdir();
                    }
                    continue;
                }
                String outFilePath = entry.getName();
                outFilePath.replace("\\", "/");
                outFile = new File(outFilePath);
                if(!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdir();
                }
                if(!outFile.exists()){
                    outFile.createNewFile();
                }
                input = zipFile.getInputStream(entry);
                BufferedInputStream bis = new BufferedInputStream(input);
                output = new FileOutputStream(outFile);
                byte [] b = new byte[1024 * 1024];
                while( bis.read(b) != -1){
                    output.write(b);
                }
                bis.close();
                input.close();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(method = RequestMethod.POST, value = "/useless-drop")
    @ApiOperation(value = "删除未使用的图片")
    public ResultModel dropUselessImg() {
        ResultModel resultModel = new ResultModel();
        try {
            String[] cmdA = { "/opt/main"};
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line).append("\n");
            }
            resultModel.setContent(sb.toString());
            return resultModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
