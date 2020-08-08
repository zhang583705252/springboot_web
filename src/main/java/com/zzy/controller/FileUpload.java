package com.zzy.controller;




import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.LoggingUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Controller
@RequestMapping("/file")
@Slf4j
public class FileUpload {
   // private static final Logger logger = Logging.class;
    private static  final Logger logger = LoggerFactory.getLogger(FileUpload.class);

    @PostMapping("/upload")
    public String fileUp(MultipartFile aaa, HttpServletRequest request, String name) throws IOException {
         //设置文件路径
        URL url = ResourceUtils.getURL("classpath:");
        log.debug("url="+url);
        String path = url.getPath() + "static/files";
        //根据日期，生成一个子目录
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //将文件目录和动态生成的日期组合成一个新目录（每天动态生成一个日期文件夹）
        String newFilePath = path+"/"+datePath;
        File filePath= new File(newFilePath);
        //File filePath = new File(newFilePath);
        logger.debug("文件路径="+filePath.getPath());
        //获取文件的后缀
        String fileSuffix = FilenameUtils.getExtension(aaa.getOriginalFilename());
        //将文件重新起名（规则：上传时间+UUID）
        String filePrefix = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date())+UUID.randomUUID().toString().replace("-","");
        String newFileName = filePrefix+"."+fileSuffix;
        logger.debug("新文件名称为："+newFileName);
        if(!filePath.exists()){filePath.mkdirs();}
        aaa.transferTo(new File(filePath,newFileName));
        return "redirect:/upload.html";
    }
    @GetMapping("/download")
    public void fileDownload(String fileName, HttpServletResponse response){
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        //获取下载文件的路径
        try {
            String path = ResourceUtils.getURL("classpath:").getPath()+"/static/files";
            File file = new File(path, fileName);
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
            int num = IOUtils.copy(inputStream, outputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }



}
