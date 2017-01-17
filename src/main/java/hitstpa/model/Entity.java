package hitstpa.model;

import java.util.List;

public class Entity {

	protected Integer id;
	protected Stereotype stereotype;
	protected String name;
	protected String description;
	protected List<IndividualAction> individualActions;
	
	public Entity(){}
	
	public Entity(int id, Stereotype stereotype, String name, String description) {
		this.id = id;
		this.stereotype = stereotype;
		this.name = name;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Stereotype getStereotype() {
		return stereotype;
	}

	public void setStereotype(Stereotype stereotype) {
		this.stereotype = stereotype;
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

	public List<IndividualAction> getIndividualActions() {
		return individualActions;
	}

	public void setIndividualActions(List<IndividualAction> individualActions) {
		this.individualActions = individualActions;
	}
}
