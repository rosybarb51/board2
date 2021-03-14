package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.dto.RestBoardDto;
import board.service.RestBoardService;

@Controller
public class RestBoardController {
//	REST api란? - REpersentational State Transfer 의 약자로 기존의 웹 아키텍처가 HTTP 본래의 우수성을 잘 활용하지 못한다고 생각하여, 그 장점을 최대한 활용할 수 있는 아키텍처로 REST를 만듬, 해석 코드 따로 필요 없고 자기가 자동으로 동작하니까 웹 브라우저 뿐만 아니라 다른 작은 기기들에도 사용하기 편하다.
	
//	HTTP URI로 리소스를 정의하고 HTTP 메서드(POST, GET, PUT, DELETE)로 리소스에 대한 행위를 정의
	
//	리소스 : 서비스를 제공하는 시스템의 자원을 의미, 접속주소, URI로 정의(URI 는 명사를 사용)
	
//	REST API는 GET, POST, DELETE, PUT 에 각각의 기능(CRUD)을 부여하여 사용함
//	REST API 를 사용하면 접속주소가 같아도 아래의 메소드를 사용하는 방법에 따라서 달라진다.
//	POST : Create의 의미, 리소스를 생성
//	GET : Read의 의미, 해당 URI의 리소스를 조회
//	PUT : Update의 의미, 해당 URI의 리소스를 수정
//	DELETE : Delete의 의미 해당 URI의 리소스를 삭제
	
	
//	리소스가 '/members(주소)' 와 같이 존재한다고 할 경우, 기존 방식으로 데이터를 요청할 경우 GET /members/select/1(아이디) 형태로 사용하게 됨
//	기존 방식대로하여 삭제하고자 하면 GET /members/delete/1 이런 형식으로 사용해야 함
//	REST 방식으로 사용하면 조회 시 GET /members/1 로 사용 가능하고, 삭제 시 DELETE /members/1 로 사용이 가능함
	
//	URI 부분은 영문 소문자로만 표현 (관례임), 가독성을 위해서 '-'을 사용해도 상관없음(_는 안됨)
	
	@Autowired
	private RestBoardService restBoardService;
	
	
//	@RequestMapping 에서 기존에는 바로 주소만 넣었음, REST API에서는 @RequestMapping에 value와 method를 사용하여 View 영역과 매칭하고, REST api에서 조회 기능을 하는 GET 방식을 사용함
//	REST api에서는 method의 방식에 따라서 실행되는 역할이 다르기 때문에 method 부분이 반드시 필요함! 
//	@RequestMapping 어노테이션 대신 @GetMapping, @PostMapping, @PutMapping, @DeleteMapping 어노테이션을 사용하면 method 부분을 생략할 수 있음!
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ModelAndView openRestBoardList() throws Exception {
		ModelAndView mv = new ModelAndView("/board/RestBoardList");
		
		List<RestBoardDto> list = restBoardService.selectRestBoardList();
		mv.addObject("datas", list);
		
		return mv;
	}
	
//	기존 방식에서는 글번호를 입력할 경우 url 을 입력하고 ? 파라미터명=파라미터값 을 넣는 형태로 사용했지만 rest 방식에서는 url 이후에 /파라미터값을 넣는 형태로 사용
//	/board.do?boardIdx="1"(기존방식)  -> /board/1(rest api 방식)
//	@PathVariable("boardIdx") 는 boardIdx 라는 파라미터 값을 uri 부분에 있는 {boardIdx} 라는 것과 1 대 1로 연동 시킴.
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.GET)
	public ModelAndView openRestBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/restBoardDetail");
		
		RestBoardDto data = restBoardService.selectRestBoardDetail(boardIdx);
		mv.addObject("data", data);
		
		return mv;
	}
	
	
//	@RequestMapping에서 주소 부분인 value 값이 /board/write 를 사용한 메서드가 writeRestBoard, insertRestBoard 2개가 있지만 두 메서드는 각각 RequestMethod.Get, RequestMethod.POST 를 사용하고 있기 때문에 서로 다른 기능을 가지고 있음
	
//	사용자 입력을 위한 view 부분
	@RequestMapping(value="/board/wirte", method=RequestMethod.GET)
	public String writeRestBoardWrite() throws Exception {
		return "/board/restBoardWrite";
	}
	
//	사용자가 form 을 통해서 전송한 데이터를 넘겨받아 DB로 다시 전송하는 부분
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String insertRestBoard(RestBoardDto data) throws Exception {
		restBoardService.insertRestBoard(data);
		
		return "redirect:/board";
	}
	
//	@RequestMapping의 method가 PUT이기 때문에 DB 수정 기능을 사용함
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.PUT)
	public String updateRestBoard(RestBoardDto data) throws Exception {
		restBoardService.updateRestBoard(data);
		
		return "redirect:/board";
	}
	
//	@RequestMapping의 method를 DELETE로 사용하여 DB 삭제 기능 사용
	@RequestMapping(value="/board/{boardIdx}", method=RequestMethod.DELETE)
	public String deleteRestBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
		restBoardService.deleteRestBoard(boardIdx);
		
		return "redirect:/board";
	}
	
	@RequestMapping("/")
	public String index() {
		return "/board/index";
	}
}


























