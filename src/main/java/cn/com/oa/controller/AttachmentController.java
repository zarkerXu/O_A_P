package cn.com.oa.controller;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.oa.model.Attachment;
import cn.com.oa.service.AttachmentService;


@Controller
@RequestMapping("/attachment")
public class AttachmentController extends BaseController {
	@Resource
	AttachmentService attachmentService;
	/**
	 * 下载
	 * 
	 * @param value
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(
			@RequestParam(value = "id", required = true) String id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			Attachment attachment = attachmentService.find(id);
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			String downLoadPath = attachment.getUrl();
			String filename=attachment.getFileName();
			filename=filename.replaceAll(" ", "");
			filename=new String(attachment.getFileName().getBytes("utf-8"), "ISO8859-1");
			try {
				long fileLength = new File(downLoadPath).length();
//				response.setContentType("application/vnd.ms-word;");
				response.setHeader("Content-disposition", "filename="
						+ filename);
				response.setHeader("Content-Length", String.valueOf(fileLength));
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		}
}
