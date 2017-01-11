package hitstpa.model;

public class IncidentXSafetyConstraint {

	private Integer id;
	private SafetyConstraint safetyConstraint;
	private Incident incident;
	
	public IncidentXSafetyConstraint(){}
	
	public IncidentXSafetyConstraint(Integer id, SafetyConstraint safetyConstraint, Incident incident)
	{
		this.id = id;
		this.safetyConstraint = safetyConstraint;
		this.incident = incident;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SafetyConstraint getSafetyConstraint() {
		return safetyConstraint;
	}

	public void setSafetyConstraint(SafetyConstraint safetyConstraint) {
		this.safetyConstraint = safetyConstraint;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}
	
	
}
