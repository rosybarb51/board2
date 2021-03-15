package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import board.dto.RestBoardDto;
import board.service.RestBoardService;

// @Controller 어노테이션과 @ResponseBody 어노테이션이 합쳐진 어노테이션임
// @ResponseBody 어노테이션은 View에 데이터를 직접 전송하는 어노테이션

@RestController
public class RestApiBoardController {
	
	@Autowired
	private RestBoardService restApiService;
	
//	리스트
	@RequestMapping(value="/api/board", method=RequestMethod.GET)
	public List<RestBoardDto> restApiOpenListBoard() throws Exception {
		return restApiService.selectRestBoardList();
	}
	
//	상세 글 보기 기능 (디테일)
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.GET)
	public RestBoardDto restApiOpenDetailBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		return restApiService.selectRestBoardDetail(boardIdx);
	}
	
//	글쓰기 기능
	@RequestMapping(value="/api/board/write", method=RequestMethod.POST)
//	보내기만 하면 끝이니까 반환값이 없어서 void 사용. 그래서 위에서 GET 안 쓰고 POST 썼다. 
//	데이터 가지고 가야해서 @RequestBody 사용함

//	@RequestBody 어노테이션은 메서드의 파라미터가 반드시 HTTP 패킷이 바디에 담겨 있어야 한다는 의미.
//	
	public void restApiInsertBoard(@RequestBody RestBoardDto board) throws Exception {
		restApiService.insertRestBoard(board);
	}
	
//	글 수정 기능
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.PUT)
	public String restApiUpdateBoard(@RequestBody RestBoardDto board) throws Exception {
		restApiService.updateRestBoard(board);
		
		return "redirect:/board";
	}
	
//	글 삭제 기능
	@RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.DELETE)
	public String restApiDeleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		restApiService.deleteRestBoard(boardIdx);
		
		return "redirect:/board";
	}
	
}
















































