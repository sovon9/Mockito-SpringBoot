package coupon.CouponService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private String code;
	@Column
	private double discount;
	@Column(name = "exp_date")
	private String expDate;
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
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
