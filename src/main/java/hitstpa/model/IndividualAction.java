package hitstpa.model;

public class IndividualAction {

	private int id;
	private Entity entity;
	
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
}
