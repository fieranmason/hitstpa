package hitstpa.model;

public class Sequence {

	private Integer id;
	private EventSafetyConstraint eventSafetyConstraint;
	private Event event;
	private Integer sequence;
	
	public Sequence(){}
	
	public Sequence(Integer id, EventSafetyConstraint eventSafetyConstraint, Event event, Integer sequence)
	{
		this.id = id;
		this.eventSafetyConstraint = eventSafetyConstraint;
		this.event = event;
		this.sequence = sequence;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventSafetyConstraint getEventSafetyConstraint() {
		return eventSafetyConstraint;
	}

	public void setEventSafetyConstraint(EventSafetyConstraint eventSafetyConstraint) {
		this.eventSafetyConstraint = eventSafetyConstraint;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	
}
