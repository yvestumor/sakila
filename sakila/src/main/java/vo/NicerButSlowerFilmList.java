package vo;
public class NicerButSlowerFilmList {
	private int fid;
	private String title;
	private String description;
	private String category;
	private double price;
	private int length;
	private String rating;
	private String actors;
	
	@Override
	public String toString() {
		return "NicerButSlowerFilmList [fid=" + fid + ", title=" + title + ", description=" + description
				+ ", category=" + category + ", price=" + price + ", length=" + length + ", rating=" + rating
				+ ", actors=" + actors + "]";
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
}
