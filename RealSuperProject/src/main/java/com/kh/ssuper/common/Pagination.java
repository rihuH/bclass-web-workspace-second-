package com.kh.ssuper.common;

public class Pagination {
	
	
	public static PageInfo getPageInfo (int listCount, int currentPage, int pageLimit, int boardLimit) {
		
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int maxPage = (int) Math.ceil((double)listCount / boardLimit);
		int endPage = Math.min(startPage + pageLimit - 1, maxPage);
		
		return new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
	}
	
	
	

}
