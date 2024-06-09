package product.Product.dto;

public class Coupon {

	private long id;
	private String code;
	private double discount;
	private String expDate;
	public Coupon() {
		super();
	}
	public Coupon(long id, String code, double discount, String expDate) {
		super();
		this.id = id;
		this.code = code;
		this.discount = discount;
		this.expDate = expDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", code=" + code + ", discount=" + discount + ", expDate=" + expDate + "]";
	}
	
}
