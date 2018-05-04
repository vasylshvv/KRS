package test;

import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
@MultipartConfig(maxFileSize = 10000000)
public class TestUploadFile implements Controller{
	private MultipartHttpServletRequest multi;
	private MultipartFile mfile;
	private InputStream inputStream = null;
	private Blob blob;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/jsp/uploadFile.jsp");
		String submit = request.getParameter("upload");
		System.out.println("submit = "+submit);
		if(submit!=null){
			multi = (MultipartHttpServletRequest)request;
			mfile = multi.getFile("file");
			System.out.println("mfile.getOriginalFilename() = "+mfile.getOriginalFilename());
			inputStream = mfile.getInputStream();
			blob = Hibernate.createBlob(inputStream);
			String aaa = request.getParameter("param");
					System.out.println("aaa = "+aaa);
		}
		return modelAndView;
	}
}
