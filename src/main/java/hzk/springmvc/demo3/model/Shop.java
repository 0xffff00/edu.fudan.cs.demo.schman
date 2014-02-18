package hzk.springmvc.demo3.model;

import java.io.Serializable;

public class Shop implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8190840210114864482L;
	private int id;
	private String booth;
	private boolean isSpecialName;
	private int brandId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBooth() {
		return booth;
	}

	public void setBooth(String booth) {
		this.booth = booth;
	}

	public boolean getIsSpecialName() {
		return isSpecialName;
	}

	public void setIsSpecialName(boolean isSpecialName) {
		this.isSpecialName = isSpecialName;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

}
