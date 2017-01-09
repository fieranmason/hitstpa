package hitstpa.model;

public class Service {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubsetId() {
		return subsetId;
	}

	public void setSubsetId(Integer subsetId) {
		this.subsetId = subsetId;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSubsection() {
		return subsection;
	}

	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}

	public Integer getDtkConceptId() {
		return dtkConceptId;
	}

	public void setDtkConceptId(Integer dtkConceptId) {
		this.dtkConceptId = dtkConceptId;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	protected Integer id;
	protected Integer subsetId;
	protected String section;
	protected String subsection;
	protected Integer dtkConceptId;
	protected Integer code;
	protected String descriptor;
	
	public Service(){}
	
	public Service(int id, int subsetId, String section, String subsection, Integer dtkConceptId, Integer code, String descriptor) {
		this.id = id;
		this.subsetId = subsetId;
		this.section = section;
		this.subsection = subsection;
		this.dtkConceptId = dtkConceptId;
		this.code = code;
		this.descriptor = descriptor;
	}
}
