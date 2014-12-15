package com.zjhcsoft.common.page;

public class PageUtil {
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final String PAGE_DESC = "↓";
	public static final String PAGE_ASC = "↑";
	public static final String PAGE_NULL = "&nbsp;&nbsp;";
	public static final String SESSION_PAGE_KEY = "page";

	public static Page inintPage(Long totalCount, Integer index, String value,
			Page sessionPage) {
		Page page = null;
		if (index.intValue() < 0) {
			page = new Page(totalCount);
		} else {
			Long everPage = Long.valueOf(value == null ? 10L : Long
					.parseLong(value));

			page = sessionPage;
			page.setEveryPage(everPage);
			page.setTotalCount(totalCount);
		}
		return page;
	}

	public static Page execPage(int index, String value, Page sessionPage) {
		Page page = sessionPage;

		page.pageState(index, value);
		return page;
	}
}