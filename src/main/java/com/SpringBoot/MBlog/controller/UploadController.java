package com.SpringBoot.MBlog.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.MBlog.util.FileDownload;
import com.SpringBoot.MBlog.vo.Result;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class UploadController {
	/**
	 * 根据需要缩放的大小和临时图数据流，缩放图片并存储在根目录
	 * @param size
	 * @param file
	 * @return
	 */
	@Value("${STORE_ROOT_PATH}")
	String StoreRootPath;//存储根目录
	
	@ResponseBody
	@RequestMapping("/post/upload")
	public Object upload(int size,MultipartFile file,HttpServletRequest requset) {
		
		try {
			
			BufferedImage Bimge = ImageIO.read(file.getInputStream());//创建图像
			int w = Bimge.getWidth();//获取原图片宽
			int h = Bimge.getHeight();//获取原图片高
			int maxSize = (int)Math.max(w, h);//比较那个更大
			int tow = w;
			int toh = h;
			
			if(maxSize > size) {//根据宽高间最大值不变，等比例缩放另一边
				if(w > h) {
					tow = size;
					toh = h*size/w;
				}else {
					tow = w*size/h;;
					toh = size;
				}
			}
			
			String fileName="/store/temp/"+UUID.randomUUID().toString()+".jpg";
			File tempFile = new File(StoreRootPath,fileName);//临时文件
			File parent=tempFile.getParentFile();
			if(!parent.exists()) {
				parent.mkdirs();//判断目录不存在就创建
			}
			Thumbnails.of(file.getInputStream())
					  .size(tow, toh)//缩放
					  .outputFormat("jpg")//修改图片类型 
					  .toFile(tempFile);//修改完成后保存路径 
			return Result.of(1, "上传成功", fileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Result.of(2, "上传失败");
	}
	
	/**
	 * 把图片回显到界面上
	 * @param size
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/store/temp/{fileName:.+}")
	public Object upload(@PathVariable String fileName,HttpServletRequest requset,HttpServletResponse response) {
		
		File file =new File(StoreRootPath,"/store/temp/"+fileName);
		try {
			FileDownload.forward(requset, response, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	@ResponseBody
	@RequestMapping("/store/avatar/**")
	public Object load(HttpServletRequest requset,HttpServletResponse response) {
		File file =new File(StoreRootPath,requset.getServletPath());
		try {
			FileDownload.forward(requset, response, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
