package biz;

import java.util.List;
import java.util.Map;

import bean.Board;
import dao.BoardDao;

public class BoardBiz {
	
	
	BoardDao bd=new BoardDao();
	/**
	 * ��̨��ʾ����������Ϣ
	 * @return
	 */
	public List<Board> bigBoardList(){
		List<Board> bigBoardList = bd.bigBoardList();
		return bigBoardList;
	}
	
	/**
	 * �޸��������Ϣ
	 * @param board
	 * @return
	 * @throws BizException 
	 */
	public void updateBigBoard(Board board) throws BizException {
		int updateBigBoard = bd.updateBigBoard(board);
		if(updateBigBoard<0) {
			throw new BizException("��������æ�����Ժ�����");
		}
		
	}

	/**
	 * ���������
	 * @param board
	 * @return
	 * @throws BizException 
	 */
	public void addBigBoard(Board board) throws BizException {
		int addBigBoard = bd.addBigBoard(board);
		if(addBigBoard<0) {
			throw new BizException("��������æ�����Ժ�����");
		}
		
	}

	/**
	 * ɾ�������
	 * @param board
	 * @return
	 * @throws BizException 
	 */
	public void delBigBoard(Board board) throws BizException {
		int delBigBoard = bd.delBigBoard(board);
		if(delBigBoard<0) {
			throw new BizException("��������æ�����Ժ�����");
		}
	}

	/**
	 * ȡ�����а����Ϣ
	 * @return
	 * @throws BizException 
	 */
	public Map<Integer, List<Board>> findAllBoard() throws BizException {
		
		
		Map<Integer, List<Board>> findAllBoard = bd.findAllBoard();
		if(findAllBoard==null) {
			throw new BizException("��������æ�����Ժ�����");
		}
		return findAllBoard;
	}
}
