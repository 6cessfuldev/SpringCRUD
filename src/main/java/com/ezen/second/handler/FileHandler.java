package com.ezen.second.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.second.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@AllArgsConstructor
@Component
public class FileHandler {	
	private final String UP_DIR ="D:\\upload";

	public List<FileVO> uploadFiles(MultipartFile[] files){
		
		LocalDate date = LocalDate.now();
		log.info(">>> date : "+date.toString());
		String today = date.toString(); //2022-12-27
		// -를 => 파일 구분자로 변경 2022-01-01 => 2022\\01\01
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		
		// 폴더가 있으면 생성X 없으면 생성O
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		List<FileVO> fList = new ArrayList<FileVO>();
		
		for (MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today);
			fvo.setFile_size(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			log.info(">>> fileName : "+ originalFileName);
			
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf("\\")+1);
			log.info(">>> only fileName : " + onlyFileName);
			fvo.setFile_name(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			//디스크에 저장할 파일객체 생성
			String fullFileName = uuid.toString()+"_"+onlyFileName;
			File storeFile = new File(folders,fullFileName);
			
			try {
				file.transferTo(storeFile);
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					File thumbNail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info(">>> file 생성 오류!!");
				e.printStackTrace();
			}
			fList.add(fvo);
		}
		
		return fList;
	}
	
	public int deleteFile(FileVO fvo) {
		log.info(UP_DIR+fvo.getSave_dir()+"/"+fvo.getUuid()+"_"+fvo.getFile_name());
		try {

			File file = new File(UP_DIR+File.pathSeparator+fvo.getSave_dir()+File.pathSeparator+fvo.getUuid()+"_"+fvo.getFile_name());
			log.info(file.toString());
			
	    	if( file.exists() ){
	    		if(file.delete()){
	    			System.out.println("파일삭제 성공");
	    		}else{
	    			System.out.println("파일삭제 실패");
	    		}
	    	}else{
	    		System.out.println("파일이 존재하지 않습니다.");
	    	}
	    	
	    	File tfile = new File(UP_DIR+File.pathSeparator+fvo.getSave_dir()+File.pathSeparator+fvo.getUuid()+"_th_"+fvo.getFile_name());
	    	
	    	if( tfile.exists() ){
	    		if(tfile.delete()){
	    			System.out.println("파일삭제 성공");
	    		}else{
	    			System.out.println("파일삭제 실패");
	    		}
	    	}else{
	    		System.out.println("파일이 존재하지 않습니다.");
	    	}
			
		} catch (Exception e) {
			log.info(">>> file 삭제 오류!!");
			e.printStackTrace();
		}
		
		return 1;
		
	}
	
	private boolean isImageFile(File storeFile) throws IOException {
		
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image")?true:false;
	}
}
