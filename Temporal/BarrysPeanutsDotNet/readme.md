# Getting Barry's Gourment Peanuts Up and Running

## (1) Download the Temporal CLI (which includes the server)

`curl -sSf https://temporal.download/cli.sh | sh`

## (2) Start the Temporal Server

Here is the instructions for starting the Temporal Server on a local Ubuntu machine:

`temporal server start-dev --ip 0.0.0.0`

## (3) Start the Worflow

Make sure you are in the working directory:" `./Temporal/BarrysPeanutsDotNet`

Run the following command: `dotnet run workflow`

## (4) Start the Worker

Run the following command: `dotnet run worker`

Go the Web UI in a brower at `http://localhost:8233/`.

You'll output simiar to the following:
