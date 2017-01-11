package hitstpa.model;

public class EventSafetyConstraint {
	
	private Integer id;
	private Event event;
	
	public EventSafetyConstraint(){}
	
	public EventSafetyConstraint(Integer id, Event event) {
		this.id = id;
		this.event = event;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
