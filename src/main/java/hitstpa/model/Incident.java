package hitstpa.model;

import java.util.Date;

public class Incident {

	private Integer id;
	private String name;
	private String description;
	private String outcome;
	private Date startTime;
	private Date endTime;
	private String vendorProductModel;
	
	public Incident(){}
	
	public Incident(Integer id, String name, String description, String outcome, Date startTime, Date endTime, String vendorProductModel)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.outcome = outcome;
		this.startTime = startTime;
		this.endTime = endTime;
		this.vendorProductModel = vendorProductModel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getVendorProductModel() {
		return vendorProductModel;
	}

	public void setVendorProductModel(String vendorProductModel) {
		this.vendorProductModel = vendorProductModel;
	}
	
	
}
