package hitstpa.model;

public class Narrative {

	private Integer id;
	private Incident incident;
	private String narrative;
	private String reporter;
	
	public Narrative(){}
	
	public Narrative(Integer id, Incident incident, String narrative, String reporter){
		this.id = id;
		this.incident = incident;
		this.narrative = narrative;
		this.reporter = reporter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
}
