package hitstpa.model;

public class Interaction {
	private Integer id;
	private EntityCouple entityCouple;
	
	public Interaction(){}
	
	public Interaction(Integer id, EntityCouple entityCouple)
	{
		
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public EntityCouple getEntityCouple()
	{
		return entityCouple;
	}
	
	public void setEntityCouple(EntityCouple entityCouple)
	{
		this.entityCouple = entityCouple;
	}
}
