package taskenvoy.rest;

import service.TaskPushingService;
import taskenvoy.domain.ResponseMessage;
import taskenvoy.domain.TaskRequest;

import org.joda.time.DateTime;

import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;
import restx.security.RolesAllowed;
import restx.security.RestxSession;

import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Component @RestxResource @PermitAll
public class TaskSubmissionResource {
	TaskPushingService taskPushingService_ = null;
	
	public TaskSubmissionResource(@Named("TaskPushingService") TaskPushingService taskPushingService) {
		this.taskPushingService_ = taskPushingService;
	}

	/**
	 * Add a new task to the activemq
	 * @param submittedJob to be added
	 * @return submittedJob that was received
	 */
	@POST("/task")
	@PermitAll
    public ResponseMessage submitNewTaskToQue(TaskRequest submittedJob) {
		TaskRequest job = null; 
		try {
		
			job = taskPushingService_.pushToQue(submittedJob);
		//job = submittedJob;
		} catch(Exception e) {
			 return new ResponseMessage("Faileded to call service");
		}
		if (job!= null) {
			ResponseMessage m = new ResponseMessage("Success, Data " + job.toString());
			return m;
		}
        return new ResponseMessage("Failed");
    }
	
}
