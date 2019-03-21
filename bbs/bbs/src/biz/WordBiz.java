package biz;

import dao.WordDao;

public class WordBiz {

	WordDao wd=new WordDao();
	/**
	 * 添加敏感词
	 * @param word
	 * @return
	 * @throws BizException 
	 */
	public void add(String word) throws BizException {
		if(word==null|| word.isEmpty()) {
			throw new BizException("敏感词不能为空");
		}
		int add = wd.add(word);
		if(add<0) {
			throw new BizException("添加失败");
		}
		
	}
	
}
