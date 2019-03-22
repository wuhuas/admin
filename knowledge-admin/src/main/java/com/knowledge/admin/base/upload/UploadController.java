package com.knowledge.admin.base.upload;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.feilong.core.DatePattern;
import com.feilong.core.date.DateUtil;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.base.util.OSSUtil;

/**
 * 文件上传
 * @author francis
 *
 */
@Controller
@RequestMapping("/upload/")
public class UploadController extends BaseAdminController{
	
	private Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private OSSUtil ossUtil;
	
	@PostMapping("uploadImg")
	@ResponseBody
	public Result uploadImg(MultipartFile file){
		String folder="knowledge/image/"+DateUtil.toString(new Date(), DatePattern.yyyyMMdd)+"/";
		String imgUrl = ossUtil.saveImage(folder, file);
		return json(imgUrl);
	}
	
	@PostMapping("uploadFile")
	@ResponseBody
	public Result uploadFile(MultipartFile file){
		String folder="knowledge/file/";
		String fileUrl = ossUtil.multipartUpload(folder, file);
		return json(fileUrl);
	}
	
	@PostMapping("download")
	public void download(String fileName) {
		ossUtil.segmentDownload(fileName);
	}
	/*@PostMapping("ckUpload")
	public void ckUploadImg(HttpServletRequest request,HttpServletResponse response){
		String folder="content/"+DateUtil.toString(new Date(), DatePattern.yyyyMMdd)+"/";
		try{	
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
	                .getServletContext());
		if (multipartResolver.isMultipart(request)) {
			   MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	            // 取得request中的所有文件名
	            Iterator<String> iter = multiRequest.getFileNames();
	            while (iter.hasNext()) {
	            	 MultipartFile file = multiRequest.getFile(iter.next());
	                 if (file != null) {
	                    String imgUrl = ossUtil.saveImage(folder, file);
	         	        response.setContentType("text/html;charset=UTF-8");
	         	        String callback = request.getParameter("CKEditorFuncNum");
	         	        PrintWriter out = response.getWriter();
	         	        out.println("<script type=\"text/javascript\">");
	         	        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imgUrl + "',''" + ")");
	         	        out.println("</script>");
	         	        out.flush();
	         	        out.close();
	                 }
	            }
		}
		}catch(Exception e){
			logger.error("content:CK上傳失敗：{}",e.getMessage());
		}
	}*/
	
	@PostMapping("ckUpload")
	@ResponseBody
	public JSONObject ckUploadImg(HttpServletRequest request){
		String folder="content/"+DateUtil.toString(new Date(), DatePattern.yyyyMMdd)+"/";
		Map<String,Object> map = new HashMap<>();
		try{	
			 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
		                .getServletContext());
			if (multipartResolver.isMultipart(request)) {
				   MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		            // 取得request中的所有文件名
		            Iterator<String> iter = multiRequest.getFileNames();
		            while (iter.hasNext()) {
		            	 MultipartFile file = multiRequest.getFile(iter.next());
		                 if (file != null) {
		                    String imgUrl = ossUtil.saveImage(folder, file);
		                    map.put("uploaded", 1);
		                    map.put("fileName", file.getOriginalFilename());
		                    map.put("url", imgUrl);
		                    JSONObject json = new JSONObject(map);
		                    return json;
		                 }
		            }
			}
			}catch(Exception e){
				logger.error("content:CK上傳失敗：{}",e.getMessage());
			}
		map.put("uploaded", 1);
		map.put("fileName", "");
		map.put("url", "");
		map.put("error", new HashMap<>().put("message", "upload  error !!!!"));
		JSONObject json = new JSONObject(map);
        return json;
	}
}
