# Getting Barry's Gourment Peanuts DotNet Version Up and Running
---

## (1) Download the Temporal CLI (which includes the server)

If you do not have the Temporal server up and running already, run the following command:

`curl -sSf https://temporal.download/cli.sh | sh`

---

## (2) Start the Temporal Server

Here is the command for starting the Temporal Server on a local Ubuntu machine. Execute the command in a terminal
window.

`temporal server start-dev --ip 0.0.0.0`

---

## (3) Start the Worflow

Make sure you are in the working directory: `./Temporal/BarrysPeanutsDotNet`

In another terminal window, run the following command: `dotnet run workflow`

---

## (4) Start the Worker

In a third terminal window, run the following command: `dotnet run worker`

---

## (5) Go the Web UI

In a brower go to the address `http://localhost:8233/` to open the Temporal UI web page

You'll output simiar to the following:

![Screenshot 2023-07-07 at 11 59 15 AM](https://github.com/reselbob/workingwithactors/assets/1110569/e2b01c67-6c19-4550-83f4-9a384adc0b69)
