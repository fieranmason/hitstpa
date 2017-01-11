package hitstpa.model;

public class Event {

	private Integer id;
	private Interaction interaction;
	private IndividualAction individualAction;
	private String name;
	private String description;
	
	private Event(){}
	
	public Event(Integer id, Interaction interaction, String name, String description)
	{
		this.id = id;
		this.interaction = interaction;
		this.name = name;
		this.description = description;
	}
	
	public Event(Integer id, IndividualAction individualAction, String name, String description)
	{
		this.id = id;
		this.individualAction = individualAction;
		this.name = name;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Interaction getInteraction() {
		return interaction;
	}

	public void setInteraction(Interaction interaction) {
		this.interaction = interaction;
	}

	public IndividualAction getIndividualAction() {
		return individualAction;
	}

	public void setIndividualAction(IndividualAction individualAction) {
		this.individualAction = individualAction;
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
