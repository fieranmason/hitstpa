package hitstpa.model;

import java.util.ArrayList;

public class EntityCouple {

	private int id;
	private ArrayList<Entity> entityCouple;
	private static final int ENTITY_A_INDEX = 0;
	private static final int ENTITY_B_INDEX = 1;
	
	public EntityCouple(){}
	
	public EntityCouple(Integer id, Entity entityA, Entity entityB)
	{
		this.id = id;
		entityCouple = new ArrayList<Entity>();
		entityCouple.add(ENTITY_A_INDEX, entityA);
		entityCouple.add(ENTITY_B_INDEX, entityB);
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Entity getEntityA()
	{
		return entityCouple.get(ENTITY_A_INDEX);
	}
	
	public Entity getEntityB()
	{
		return entityCouple.get(ENTITY_B_INDEX);
	}
	
	public void setEntities(Entity entityA, Entity entityB )
	{
		entityCouple.add(ENTITY_A_INDEX, entityA);
		entityCouple.add(ENTITY_B_INDEX, entityB);
	}
}
