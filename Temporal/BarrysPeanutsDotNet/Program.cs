using BarrysPeanuts;
using Temporalio.Client;
using Temporalio.Worker;

// Create a client to localhost on "default" namespace
string cnnStr = "localhost:7233";
Console.WriteLine("Connecting Barry's Peanuts on: {0}", cnnStr);
var client = await TemporalClient.ConnectAsync(new(cnnStr));

async Task RunWorkerAsync()
{
// Cancellation token to shutdown worker on ctrl+c
using var tokenSource = new CancellationTokenSource();
Console.CancelKeyPress += (_, eventArgs) =>
{
    tokenSource.Cancel();
    eventArgs.Cancel = true;
};

// Create an activity instance since we have instance activities. If we had
// all static activities, we could just reference those directly.
var activities = new BizActivities();

// Create worker with the activity and workflow registered
using var worker = new TemporalWorker(
    client,
    new TemporalWorkerOptions("barrys-peanuts-demo-queue").
        AddActivity(activities.Checkout).
        AddActivity(activities.Pay).
        AddActivity(activities.Ship).
        AddWorkflow<BizWorkflow>());

// Run worker until cancelled
Console.WriteLine("Running Barry's Peanuts on: {0}", cnnStr);
try
{
    await worker.ExecuteAsync(tokenSource.Token);
}
catch (OperationCanceledException)
{
    Console.WriteLine("Worker cancelled");
}
}

async Task ExecuteWorkflowAsync()
{
    Console.WriteLine("Executing workflow");
    await client.ExecuteWorkflowAsync(
        (BizWorkflow wf) => wf.RunAsync(),
        new(id: "barrys-peanuts-workflow-id", taskQueue: "barrys-peanuts-demo-queue"));
}

switch (args.ElementAtOrDefault(0))
{
    case "worker":
        await RunWorkerAsync();
        break;
    case "workflow":
        await ExecuteWorkflowAsync();
        break;
    default:
        throw new ArgumentException("Must pass 'worker' or 'workflow' as the single argument");
}