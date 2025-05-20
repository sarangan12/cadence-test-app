# Cadence Test Application

This repository demonstrates the ability to create a Cadence Workflow that performs the following activities.

1. Start an Order
   1. Get User Id
   2. Get Order Details
   3. Get Restuarant Id
2. With this order details, it waits for an external signal to move to the next step.
3. In the next step, it waits for 3 seconds and proceeds to the next step.
4. It starts a child workflow and displays the message.
5. The Workflow ends.

In this application, I have defined the `DeliverOrder`, `HandleEatsOrder` interfaces which are the Child Workflow and 
main workflow respectively. The `EatsOrderActivities` interface is for the activities. The `UberCadenceOrderMain` class
will be the orchestrator that unites all of the components.

## Output

![Screenshot 2025-05-19 175627](https://github.com/user-attachments/assets/18c9e08f-e6ce-485a-a007-a50ff60dc6be)

## Feedback on the existing documentation 

The overall framework is very good and the code base is relatively easy to understand. But, the existing documentation 
is not good and the experience of learning from the documentation is very frustrating. 

Let me give an example. The site has a Hello World Java video in which the user is not providing a uniform experience. 
Also the application is started in IDE. But, the invocation is happening in command line. That is not a unified experience.

The only way that I was able to complete the assignment is through looking into the code samples. But, the code samples
does not have a proper structure or documentation. The samples and the documentation needs a lot of work.
