package taskenvoy.domain;

public class ResponseMessage {
    private String message;
    
    public ResponseMessage() {
	}
    
    public ResponseMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
        return message;
    }

    public ResponseMessage setMessage(final String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                '}';
    }
}
