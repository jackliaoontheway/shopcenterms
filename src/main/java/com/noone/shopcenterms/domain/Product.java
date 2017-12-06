package com.noone.shopcenterms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Product {

	private String sku;
	private String name;
	private String composition;
	private String produceCompany;
	private String companyMobile;
	private String companyCode;
	private String price;
	private String expiredDate;
	

	// -------------Auto generated start (Mon Dec 04 22:56:32 CST 2017)-------------//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date createdDate;

	private Long createdBy;

	private Date modifiedDate;

	private Long modifiedBy;

	public void setBaseField(Long operatorId) {
		Date now = new Date();
		if (this.getId() == null) {
			setCreatedBy(operatorId);
			setCreatedDate(now);
		}
		setModifiedBy(operatorId);
		setModifiedDate(now);
	}

	// -------------Auto generated end-------------//

}