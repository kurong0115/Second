package biz;

import java.util.List;
import java.util.Map;

import bean.Board;
import dao.BoardDao;

public class BoardBiz {
	
	
	BoardDao bd=new BoardDao();
	/**
	 * 后台显示的主板块的信息
	 * @return
	 */
	public List<Board> bigBoardList(){
		List<Board> bigBoardList = bd.bigBoardList();
		return bigBoardList;
	}
	
	/**
	 * 修改主板块信息
	 * @param board
	 * @return
	 * @throws BizException 
	 */
	public void updateBigBoard(Board board) throws BizException {
		int updateBigBoard = bd.updateBigBoard(board);
		if(updateBigBoard<0) {
			throw new BizException("服务器繁忙，请稍后再试");
		}
		
	}

	/**
	 * 增加主板块
	 * @param board
	 * @return
	 * @throws BizException 
	 */
	public void addBigBoard(Board board) throws BizException {
		int addBigBoard = bd.addBigBoard(board);
		if(addBigBoard<0) {
			throw new BizException("服务器繁忙，请稍后再试");
		}
		
	}

	/**
	 * 删除主板块
	 * @param board
	 * @return
	 * @throws BizException 
	 */
	public void delBigBoard(Board board) throws BizException {
		int delBigBoard = bd.delBigBoard(board);
		if(delBigBoard<0) {
			throw new BizException("服务器繁忙，请稍后再试");
		}
	}

	/**
	 * 取出所有板块信息
	 * @return
	 * @throws BizException 
	 */
	public Map<Integer, List<Board>> findAllBoard() throws BizException {
		
		
		Map<Integer, List<Board>> findAllBoard = bd.findAllBoard();
		if(findAllBoard==null) {
			throw new BizException("服务器繁忙，请稍后再试");
		}
		return findAllBoard;
	}
}
