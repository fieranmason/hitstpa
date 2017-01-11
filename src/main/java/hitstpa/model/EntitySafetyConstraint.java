package hitstpa.model;

public class EntitySafetyConstraint {
	
	private Integer id;
	private Entity entity;
	
	public EntitySafetyConstraint(){}
	
	public EntitySafetyConstraint(Integer id, Entity entity) {
		this.id = id;
		this.entity = entity;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}
