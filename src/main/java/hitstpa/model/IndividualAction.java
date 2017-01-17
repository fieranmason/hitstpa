package hitstpa.model;

public class IndividualAction {

	private int id;
	private Entity entity;
	private Event event;
	
	public IndividualAction(){}
	
	public IndividualAction(Integer id, Entity entity)
	{
		this.id = id;
		this.entity = entity;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Entity getEntity()
	{
		return entity;
	}
	
	public void setEntity(Entity entity)
	{
		this.entity = entity;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
