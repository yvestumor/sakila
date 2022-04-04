package vo;
public class SalesByFilmCategory {
	private String category;
	private double totalSales;
	
	@Override
	public String toString() {
		return "SalesByFilmCategory [category=" + category + ", totalSales=" + totalSales + "]";
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
}
