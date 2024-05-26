package com.orm.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class VendorPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vid;
	private String vname;
	private String vtan;
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Given pattern not supported : Pattern must be in * ABCDE1234F *")
	private String vpan;
	private String vloc;
    private String vdept;
}
