package barryspeanuts;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class BarrysPeanutsWorkflowImpl implements BarrysPeanutsWorkflow {
    ActivityOptions options = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofSeconds(60))
        .build();

    private final BarryPeanutsActivities activity = Workflow.newActivityStub(BarryPeanutsActivities.class, options);

    @Override
    public void getBizProcess() {

        /**   
         * If there were other Activity methods they would be orchestrated here or from within other Activities.
         * This is a blocking call that returns only after the activity has completed.
         */
        return activity.composeGreeting(name);
    }
}

    
}
