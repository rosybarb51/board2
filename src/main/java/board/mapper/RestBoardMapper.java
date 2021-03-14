package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.RestBoardDto;

@Mapper
public interface RestBoardMapper {
	List<RestBoardDto> selectRestBoardList() throws Exception;
	RestBoardDto selectRestBoardDetail(int boardIdx) throws Exception;
//	반환값 없으니까 void 붙이기
	void updateRestHitCount(int boardIdx) throws Exception;
	void insertRestBoard(RestBoardDto data) throws Exception;
	void updateRestBoard(RestBoardDto data) throws Exception;
	void deleteRestBoard(int boardIdx) throws Exception;
}
