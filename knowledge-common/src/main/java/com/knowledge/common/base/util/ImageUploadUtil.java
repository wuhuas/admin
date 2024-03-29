package com.knowledge.common.base.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.feilong.core.util.RandomUtil;

public class ImageUploadUtil {
	// 图片类型
	private static List<String> fileTypes = new ArrayList<String>();

	static {
		fileTypes.add(".jpg");
		fileTypes.add(".jpeg");
		fileTypes.add(".bmp");
		fileTypes.add(".gif");
		fileTypes.add(".png");
		fileTypes.add(".xls");
		fileTypes.add(".xlsx");
		fileTypes.add(".word");
		fileTypes.add(".pdf");
	}

	/**
	 * 图片上传
	 * 
	 * @Title upload
	 * @param request
	 * @param DirectoryName
	 *            文件上传目录：比如upload(无需带前面的/) upload/news ..
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String upload(HttpServletRequest request, String DirectoryName) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 图片名称
		String fileName = null;
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				// int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 获得图片的原始名称
						String originalFilename = file.getOriginalFilename();
						// 获得图片后缀名称,如果后缀不为图片格式，则不上传
						String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
						if (!fileTypes.contains(suffix)) {
							continue;
						}
						// 获得上传路径的绝对路径地址(/upload)-->
						String realPath = "/opt/docker/nginx/data/ui/" + DirectoryName;
						System.out.println(realPath);
						// 如果路径不存在，则创建该路径
						File realPathDirectory = new File(realPath);
						if (realPathDirectory == null || !realPathDirectory.exists()) {
							realPathDirectory.mkdirs();
						}
						// 重命名上传后的文件名 111112323.jpg
						fileName = RandomUtil.createRandomWithLength(5) + suffix;
						// 定义上传路径 .../upload/111112323.jpg
						File uploadFile = new File(realPathDirectory + "/" + fileName);
						System.out.println(uploadFile);
						try {
							file.transferTo(uploadFile);
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				// 记录上传该文件后的时间
				// int finaltime = (int) System.currentTimeMillis();
				// System.out.println(finaltime - pre);
			}
		}
		return "http://119.23.201.55:8091/"+DirectoryName+"/"+fileName;
	}

	/**
	 * ckeditor文件上传功能，回调，传回图片路径，实现预览效果。
	 * 
	 * @Title ckeditor
	 * @param request
	 * @param response
	 * @param DirectoryName
	 *            文件上传目录：比如upload(无需带前面的/) upload/..
	 * @throws IOException
	 */
	public static void ckeditor(HttpServletRequest request, HttpServletResponse response, String DirectoryName) {
		String fileUrl = upload(request, DirectoryName);
		// 结合ckeditor功能
		// imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
		try {
			response.setContentType("text/html;charset=UTF-8");
			String callback = request.getParameter("CKEditorFuncNum");

			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileUrl + "',''" + ")");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}