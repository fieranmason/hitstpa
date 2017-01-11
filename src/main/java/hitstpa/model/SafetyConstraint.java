package hitstpa.model;

public class SafetyConstraint {

	private Integer id;
	private EntitySafetyConstraint entitySafetyConstraint;
	private EventSafetyConstraint eventSafetyConstraint;
	private String name;
	private String description;
	
	private SafetyConstraint(){}
	
	public SafetyConstraint(Integer id, EntitySafetyConstraint entitySafetyConstraint, String name, String description)
	{
		this.id = id;
		this.entitySafetyConstraint = entitySafetyConstraint;
		this.name = name;
		this.description = description;
	}
	
	public SafetyConstraint(Integer id, EventSafetyConstraint eventSafetyConstraint, String name, String description)
	{
		this.id = id;
		this.eventSafetyConstraint = eventSafetyConstraint;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntitySafetyConstraint getEntitySafetyConstraint() {
		return entitySafetyConstraint;
	}

	public void setEntitySafetyConstraint(EntitySafetyConstraint entitySafetyConstraint) {
		this.entitySafetyConstraint = entitySafetyConstraint;
	}

	public EventSafetyConstraint getEventSafetyConstraint() {
		return eventSafetyConstraint;
	}

	public void setEventSafetyConstraint(EventSafetyConstraint eventSafetyConstraint) {
		this.eventSafetyConstraint = eventSafetyConstraint;
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
	
}
