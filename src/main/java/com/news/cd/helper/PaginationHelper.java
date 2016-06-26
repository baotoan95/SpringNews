package com.news.cd.helper;

public class PaginationHelper {
	private int currentPage; // Trang hiện tại
	private int numbOfRecordsPerPage; // Tổng số record cho mỗi trang
	private int numbOfPageShow; // Số button phân trang cần show
	private int startIndex; // Index bắt đầu lấy ra trong csdl
	private int totalRecordsResult; // Tổng số record đã lấy ra trong csdl thỏa
									// điều kiện
	private StringBuilder paginationHTML; // mã HTML sinh ra sau khi tính toán

	public PaginationHelper(int totalRecordsResult, int currentPage,
			int numbOfRecordsPerPage, int numbOfPageShow) {
		this.totalRecordsResult = totalRecordsResult;
		this.currentPage = currentPage;
		this.numbOfRecordsPerPage = numbOfRecordsPerPage;
		this.numbOfPageShow = numbOfPageShow;
		this.paginationHTML = new StringBuilder();
		// Tính toán và generate HTML
		process();
	}
	
	public void setTotalRecordsResult(int totalRecordsResult) {
		this.totalRecordsResult = totalRecordsResult;
	}

	public String getHTML() {
		return paginationHTML.toString();
	}

	public int getMaxResult() {
		return numbOfRecordsPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	private void process() {
		// Số page tính dựa vào số records get từ csdl
		int totalPagesResult = totalRecordsResult / numbOfRecordsPerPage;
		if (totalRecordsResult % numbOfRecordsPerPage > 0) {
			totalPagesResult++;
		}

		// Tính số phân đoạn
		int totalSegments = totalPagesResult / numbOfPageShow;
		if (totalPagesResult % numbOfPageShow > 0) {
			totalSegments++;
		}

		// Nếu trang hiện tại > tổng số trang đã tính được
		// Đồng nghĩa với việc có 1 segment và gán trang hiện tại tại vị trí max
		// trong segment đó
		if (currentPage > totalPagesResult) {
			currentPage = totalPagesResult;
		}

		// Xác định trang hiện tại đang ở segment nào
		int currentSegment = currentPage / numbOfPageShow;
		if (currentPage % numbOfPageShow > 0) {
			currentSegment++;
		}

		// Tính index min và max tại segment hiện tại
		int indexMin = ((currentSegment - 1) * numbOfPageShow) + 1;
		int indexMax = indexMin + numbOfPageShow <= totalPagesResult ? indexMin
				+ numbOfPageShow - 1 : totalPagesResult;

		// Generate html
		generateHTML(indexMax, indexMin, totalSegments, currentSegment);

		// Tính startIndex
		startIndex = (numbOfRecordsPerPage * currentPage) - numbOfRecordsPerPage;// chauphi90
	}

	private void generateHTML(int indexMax, int indexMin, int totalSegments,
			int currentSegment) {
		paginationHTML.append("<ul classContainer>");
		// Nếu segment hiện tại > 1 tức có button previous
		if (currentSegment > 1) {
			paginationHTML.append("<li><a href='link" + (indexMin - 1)
					+ "' classStart>«</a></li>");
		}
		// Generate buttons
		for (int i = indexMin; i <= indexMax; i++) {
			if (i == currentPage) {
				paginationHTML.append("<li class='active'><a class='active'>" + i
						+ "</a></li>");
			} else {
				paginationHTML.append("<li><a href='link" + i + "' class>" + i
						+ "</a></li>");
			}
		}
		// Nếu segment hiện tại < tổng số segment tức có button next
		if (currentSegment < totalSegments) {
			paginationHTML.append("<li><a href='link" + (indexMax + 1)
					+ "' classEnd>»</a></li>");
		}
		paginationHTML.append("</ul>");
	}

}
