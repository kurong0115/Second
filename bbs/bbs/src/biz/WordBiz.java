package biz;

import dao.WordDao;

public class WordBiz {

	WordDao wd=new WordDao();
	/**
	 * ÃÌº”√Ù∏–¥ 
	 * @param word
	 * @return
	 */
	public int add(String word) {
		return wd.add(word);
		
	}
	
}
