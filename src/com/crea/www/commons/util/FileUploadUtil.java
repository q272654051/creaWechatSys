package com.crea.www.commons.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * <p style="color:blue">
 * 用于文件上传 包括图片视频等
 * </p>
 * 
 * @author moon
 * 
 */
public class FileUploadUtil {

	/**
	 * 上传文件,是多文件上传,文件验证就在前端验证.比如限制文件上传格式 需要修改上传文件大小的话,就修改配置文件spring-mvc.xml
	 * 
	 * @param file
	 * @return
	 */
	public static boolean upLoadFile(CommonsMultipartFile file,
			HttpServletRequest request) {
		CommonsMultipartResolver mutilpartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// request如果是Multipart类型、
		if (mutilpartResolver.isMultipart(request)) {
			// 强转成 MultipartHttpServletRequest 
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			String projectAddress = request.getRealPath("/");
			// 获取文件名
			Iterator<String> it = multiRequest.getFileNames();
			while (it.hasNext()) {
				// 获取MultipartFile类型文件
				MultipartFile fileDetail = multiRequest.getFile(it.next());
				if (fileDetail != null) {
					String path = getFileAddress(fileDetail, projectAddress);
					File localFile = new File(path);
					try {
						// 将上传文件写入到指定文件出、核心！
						fileDetail.transferTo(localFile);
						// 非常重要、有了这个想做什么处理都可以 ,可以自己处理
						// fileDetail.getInputStream();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return true;

	}

	/**
	 * 创建或者获取文件目录,并且修改文件名称
	 * 
	 * @param fileDetail
	 * @return
	 */
	private static String getFileAddress(MultipartFile fileDetail,
			String projectAddress) {
		String[] fileNames = fileDetail.getOriginalFilename().split("\\.");
		String fileNamedir = projectAddress + "/yunfang/"
				+ FormatDateUtil.getTimestamp() + "/";
		File localFileDir = new File(fileNamedir);
		if (!localFileDir.exists()) {
			localFileDir.mkdirs();
		}
		String fileName = UUID.randomUUID() + "." + fileNames[1];
		String path = fileNamedir + fileName;
		return path;
	}

	/**
	 * 在网上下载东西
	 * 
	 * @param response
	 * @param destUrl
	 *            目标下载地址
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void downUrlFile(HttpServletResponse response, String destUrl)
			throws MalformedURLException, IOException,
			UnsupportedEncodingException {
		int param = destUrl.lastIndexOf("/");
		String fileName = destUrl.substring(param + 1, destUrl.length());
		// 建立链接
		URL url = new URL(destUrl);
		HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
		// 连接指定的资源
		httpUrl.connect();
		// 获取网络输入流
		BufferedInputStream bis = new BufferedInputStream(
				httpUrl.getInputStream());

		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ java.net.URLEncoder.encode(fileName, "UTF-8"));
		OutputStream out = response.getOutputStream();
		byte[] buf = new byte[1024];
		if (destUrl != null) {
			BufferedInputStream br = bis;
			int len = 0;
			while ((len = br.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			br.close();
		}
		out.flush();
		out.close();
	}

	/**
	 * 
	 * @param pathName
	 *            文件目录路径
	 * @return 包含文件名的List
	 * @description 获取目录下所有文件名称
	 */
	public static List<String> getFilesName(String pathName) {
		List<String> return_list = new ArrayList<String>();
		try {
			File file = new File(pathName);
			if (file.isDirectory()) {
				File[] file_arr = file.listFiles();
				for (int i = 0; i < file_arr.length; i++) {
					return_list.add(file_arr[i].getName());
				}
			}
		} catch (Exception e) {
		}
		return return_list;
	}
}
