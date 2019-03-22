package com.knowledge.common.base.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.feilong.core.DatePattern;
import com.feilong.core.Validator;
import com.feilong.core.date.DateExtensionUtil;
import com.feilong.core.date.DateUtil;
import com.knowledge.common.base.config.exception.BusinessException;
import com.knowledge.common.base.enums.EnumResult;


@Component
@PropertySource("classpath:oss.yml")
public class OSSUtil {
	
	private static Logger logger = LoggerFactory.getLogger(OSSUtil.class);
	
	private static final String dateTimeFormatter = "yyyyMMddHHmmssSSS";
	
	// 文件类型
	private static List<String> fileTypes = new ArrayList<String>();
	
	private static List<String> bigFileTypes = new ArrayList<String>();
	
	static {
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("bmp");
		fileTypes.add("gif");
		fileTypes.add("png");
		fileTypes.add("svg");
		fileTypes.add("xls");
		fileTypes.add("xlsx");
		fileTypes.add("word");
		fileTypes.add("pdf");
		fileTypes.add("txt");
		fileTypes.add("docx");
		bigFileTypes.add("word");
		bigFileTypes.add("docx");
		bigFileTypes.add("pdf");
		bigFileTypes.add("txt");
	}
	
	@Value("${ossAccessBucketName}")
	private String ossAccessBucketName;

	@Value("${ossImageDomai}")
	private String ossImageDomai;
	
	@Value("${accessKeyId}")
	private String accessKeyId;
	
	@Value("${accessKeySecret}")
	private String accessKeySecret;
	
	@Value("${ossImgUrl}")
	private String ossImgUrl;
	
	/**
	 * 上传图片到云服务器
	 * 
	 */
	@SuppressWarnings("deprecation")
	public String saveImage(String folder, MultipartFile file)
			throws BusinessException {
		String ossDomaiUrl = null;
		InputStream content = null;
		ObjectMetadata meta = null;
		//String ext = imgType(file);
		String filename = file.getOriginalFilename();
		if(Validator.isNullOrEmpty(filename)) {
			throw new BusinessException(EnumResult.FILE_IS_NOTEMPTY);
		}
		logger.debug("OSS-------------------filename: "+filename);
		String[] str = filename.split("\\.");
		String name = str[0];
		String suffix = str[1];
		if (!fileTypes.contains(suffix)) {
			throw new BusinessException(EnumResult.FILE_UPLOAD_TYPE_ERROR);
		}
		OSSClient client = new OSSClient(ossImageDomai,accessKeyId,accessKeySecret);
		try {
			logger.info("BucketName:" + ossAccessBucketName.trim());
			content = file.getInputStream();
			// 创建上传Object的Metadata
			meta = new ObjectMetadata();
			// 必须设置getContentType
			meta.setContentType(file.getContentType());
			// 必须设置ContentLength
			long len = file.getSize();
			meta.setContentLength(len);
			StringBuffer sbUrl = new StringBuffer();
			sbUrl.append(folder);
			sbUrl.append(name+newDateTimeString()).append(".").append(suffix).toString();
			String key = sbUrl.toString();
			// 上传文件.
			PutObjectResult result = client.putObject(ossAccessBucketName, key, content, meta);
			ossDomaiUrl = getOssUrl(ossImgUrl, key);
			System.out.println("=================================================key="+key);
		} catch (Exception e) {
			logger.error("ossUrl:", e);
			e.getStackTrace();
			throw new BusinessException(EnumResult.FILE_UPLOAD_FAIL);
		} finally {
			if (content != null) {
				content = null;
			}
			if (meta != null) {
				meta = null;
			}
			client.shutdown();
		}
		return ossDomaiUrl;
	}
	
	public String saveImage(String folder, InputStream content)
			throws BusinessException {
		String ossDomaiUrl = null;
		ObjectMetadata meta = null;
		OSSClient client = new OSSClient(ossImageDomai,accessKeyId,accessKeySecret);
		try {
			logger.info("BucketName:" + ossAccessBucketName.trim());
			// 创建上传Object的Metadata
			meta = new ObjectMetadata();
			// 上传文件.
			PutObjectResult result = client.putObject(ossAccessBucketName, folder, content, meta);
			ossDomaiUrl = getOssUrl(ossImgUrl, folder);
		} catch (Exception e) {
			logger.error("ossUrl:", e);
			e.getStackTrace();
			throw new BusinessException(EnumResult.FILE_UPLOAD_FAIL);
		} finally {
			if (content != null) {
				content = null;
			}
			if (meta != null) {
				meta = null;
			}
			client.shutdown();
		}
		return ossDomaiUrl;
	}

	
	@SuppressWarnings("deprecation")
	public void segmentDownload(String fileName) {
		OSSClient client = new OSSClient(ossImageDomai,accessKeyId,accessKeySecret);
		try {
			// 下载请求，10个任务并发下载，启动断点续传。
			DownloadFileRequest downloadFileRequest = new DownloadFileRequest(ossAccessBucketName, fileName);
			String realPath="C://tmp//"+fileName;
			logger.debug("OSS-------------------下載文件路徑："+realPath);
			File realPathDirectory = new File(realPath);
			if (realPathDirectory == null || !realPathDirectory.exists()) {
				realPathDirectory.mkdirs();
			}
			downloadFileRequest.setDownloadFile(realPath);
			downloadFileRequest.setPartSize(10 * 1024 * 1024);
			downloadFileRequest.setTaskNum(10);
			downloadFileRequest.setEnableCheckpoint(true);
			//downloadFileRequest.setCheckpointFile("<yourCheckpointFile>");
			
			// 下载文件。
			DownloadFileResult downloadRes;
			
			downloadRes = client.downloadFile(downloadFileRequest);
			// 下载成功时，会返回文件元信息。
			ObjectMetadata objectMetadata = downloadRes.getObjectMetadata();
			
			logger.debug("OSS.1----："+objectMetadata.getETag());
            logger.debug("OSS.2----："+objectMetadata.getLastModified());
            logger.debug("OSS.3----："+objectMetadata.getUserMetadata().get("meta"));
			
		} catch (Throwable e) {
			e.printStackTrace();
			logger.debug("OSS::ERROR-------------------文件加载失败！");
			throw new BusinessException(EnumResult.FILE_DOWNLOAD_FAIL);
		} finally {
			// 关闭OSSClient。
			client.shutdown();
		}
		
	}
	
	protected static OSS client = null;
	
	/**
	 * 分片上傳
	 * @param folder 文件夹
	 * @param file 文件
	 * @return
	 */
	public String multipartUpload(String folder, MultipartFile file) {
		/**
		 * 判断文件大小
		 */
		if(file.getSize()/1024/1024<6){
			//小于6M，不采用分片上传
			return saveImage(folder, file);
			
		}else{
			//采用分片上传

			Date date = new Date();
			String ossDomaiUrl = null;
			//String ext = imgType(file);
			String filename = file.getOriginalFilename();
			if(Validator.isNullOrEmpty(filename)) {
				throw new BusinessException(EnumResult.FILE_IS_NOTEMPTY);
			}
			logger.debug("OSS:分片-------------------filename: "+filename);
			String[] str = filename.split("\\.");
			String name = str[0];
			String suffix = str[1];
			if (!bigFileTypes.contains(suffix)) {
				throw new BusinessException(EnumResult.FILE_UPLOAD_TYPE_ERROR);
			}
			ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
	        conf.setIdleConnectionTime(1000);
	        client = new OSSClientBuilder().build(ossImageDomai, accessKeyId, accessKeySecret, conf);
	        folder = folder + suffix+ "/"+DateUtil.toString(new Date(), DatePattern.yyyyMMdd)+"/";
	        long len = file.getSize();
	        try {
				StringBuffer sbUrl = new StringBuffer();
				sbUrl.append(folder);
				sbUrl.append(name+newDateTimeString()).append(".").append(suffix).toString();
				String key = sbUrl.toString();
				
				String uploadId = OSSUploadThread.claimUploadId(ossAccessBucketName, key);// key是文件名 
				logger.debug("OSS---------Claiming a new upload id " + uploadId + "\n");
				final long partSize = 5 * 1024 * 1024L;
				
				int partCount = (int) (len / partSize);
	            if (len % partSize != 0) {
	                partCount++;
	            }
	            if (partCount > 10000) {
	                throw new BusinessException(EnumResult.FILE_UPLOAD_TOTAL_PARTS);
	            } else {
	            	logger.debug("OSS---------拆分后的总数 " + partCount + "\n");
	            }
	            ExecutorService executorService = Executors.newFixedThreadPool(partCount);
	            for (int i = 0; i < partCount; i++) {
					// 起始point
					long startPos = i * partSize;
					// 判断当前partSize的长度 是否最后一块
					long curPartSize = (i + 1 == partCount) ? (len - startPos) : partSize;
					// 线程执行。将分好的文件块加入到list集合中()
					executorService.execute(new OSSUploadThread(file, startPos, curPartSize, i + 1, uploadId, key, ossAccessBucketName));
				}

	            /**
				 * 等待所有分片完毕
				 */
				// 关闭线程池（线程池不马上关闭），执行以前提交的任务，但不接受新任务。
				executorService.shutdown();
				System.err.println(executorService.isTerminated());
		        System.err.println(executorService.isShutdown());
				// 如果关闭后所有任务都已完成，则返回 true。
				while (!executorService.isTerminated()) {
					try {
						// 用于等待子线程结束，再继续执行下面的代码
						executorService.awaitTermination(5, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
	            
				/**
				 * partETags(上传块的ETag与块编号（PartNumber）的组合) 如果校验与之前计算的分块大小不同，则抛出异常
				 */
				logger.debug("OSS:分片编号对比："+OSSUploadThread.partETags.size() + " -----   " + partCount);
				if (OSSUploadThread.partETags.size() != partCount) {
					throw new IllegalStateException("OSS分块大小与文件所计算的分块大小不一致");
				} else {
					logger.debug("将要上传的文件名  " + key + "\n");
				}

				//完成分块上传
				OSSUploadThread.completeMultipartUpload(uploadId);
	        	
				//获取文件路径
		        ossDomaiUrl = getOssUrl(ossImgUrl, key);
			} catch (OSSException oe) {
	            logger.debug("Error Message: " + oe.getErrorMessage());
	            logger.debug("Error Code:    " + oe.getErrorCode());
	            logger.debug("Request ID:    " + oe.getRequestId());
	            logger.debug("Host ID:       " + oe.getHostId());
	            logger.debug("OSS::ERROR=========================分片上傳失敗");
	        } catch (ClientException ce) {
	            logger.debug("Error Message: " + ce.getMessage());
	            logger.debug("OSS::ERROR=========================分片上傳失敗");
	        } finally {
	        	OSSUploadThread.partETags.clear();
	        	// 关闭OSSClient。
				if (client != null) {
					client.shutdown();
				}
			}
	        logger.debug("OSS::END----文件大小："+(len/1024/1024)+"M ；分片上传耗时 :"+DateExtensionUtil.getIntervalSecond(date, new Date())+"m");
	        return ossDomaiUrl;
			
			
			
			
		}
		
	}
	
	
	public String newDateTimeString() {
		try {
			// 如果同一个form保存多个截图,那会有并发生成图片文件名问题是相同的.
			Thread.sleep(1);
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
		Date date = new Date();
		return new java.text.SimpleDateFormat(dateTimeFormatter).format(date);
	}
	
	
	/**
	 * 判断文件
	 * 
	 * @param file
	 * @return String
	 */
	public String imgType(MultipartFile file) {
		String contentType = file.getContentType();
		if (!contentType.contains("jpeg") && !contentType.contains("jpg") && !contentType.contains("png")) {
			String filename = file.getOriginalFilename().trim();
			if (Validator.isNotNullOrEmpty(filename) && filename.length() > 5) {
				contentType = filename.substring(filename.length() - 4, filename.length()).toLowerCase();
			} else {
				return "png";
			}
		}
		if (contentType == null) {
			return "";
		}
		if (contentType.contains("png")) {
			return "png";
		} else if (contentType.contains("gif")) {
			return "gif";
		} else if (contentType.contains("jpeg") || contentType.contains("jpg")) {
			return "jpg";
		} else {
			return "";
		}
	}
	
	public String getOssUrl(String ossPathUrl, String fileName) {
		return new StringBuffer().append(ossPathUrl).append("/").append(fileName).toString();
	}
	
	public String getOssAccessBucketName() {
		return ossAccessBucketName;
	}

	public String getOssImageDomai() {
		return ossImageDomai;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public String getOssImgUrl() {
		return ossImgUrl;
	}
	
}
