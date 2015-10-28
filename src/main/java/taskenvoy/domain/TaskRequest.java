package taskenvoy.domain;

public class TaskRequest {
    private String id;
    private String data;
    
	public String getId() {
		return id;
	}
	public TaskRequest setId(String id_) {
		this.id = id_;
		return this;
	}
	public String getData() {
		return data;
	}
	public TaskRequest setData(String data_) {
		this.data = data_;
		return this;
	}
	
	@Override
	public String toString() {
		return "JobRequest [id_=" + id + ", data_=" + data + "]";
	}
}
