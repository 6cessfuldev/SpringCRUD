package com.ezen.second.repository;

import java.util.List;

import com.ezen.second.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> selectFile(int bno);

	int deleteFile(String uuid);

	FileVO selectFileByUuid(String uuid);

}
