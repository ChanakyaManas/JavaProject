package com.orm.Dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {

	private Integer pid;
	private String pname;
	private Integer pmaxq;
	private Integer pminq;
	private String pstore;
	private String pman;
	private String ptype;
	private Double pprice;
	private Double pgst;
	private List<VendorDTO> vpo;
}
