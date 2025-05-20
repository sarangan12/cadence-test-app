package org.example;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;

public class UberCadenceOrderMain {
    public static void main(String[] args) {
        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                        WorkflowClientOptions.newBuilder().setDomain("test-domain").build());

        // Get worker to poll the common task list.
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        final Worker workerForCommonTaskList = factory.newWorker("HandleEatsOrderTaskList");
        workerForCommonTaskList.registerWorkflowImplementationTypes(HandleEatsOrderImpl.class, DeliverOrderImpl.class);
        workerForCommonTaskList.registerActivitiesImplementations(new EatsOrderActivitiesImpl());
        // Start all workers created by this factory.
        factory.start();
    }
}
