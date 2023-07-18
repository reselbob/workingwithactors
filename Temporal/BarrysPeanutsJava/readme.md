# Under Construction


# Running the code:

## (1) Download the Temporal CLI (which includes the server)

If you do not have the Temporal server up and running already, run the following command: 

`curl -sSf https://temporal.download/cli.sh | sh`

---

## (2) Start the Temporal Server

Here is the command for starting the Temporal Server on a local Ubuntu machine. Execute the command in a terminal window.

`temporal server start-dev --ip 0.0.0.0`


---

## (3) Navigate to the application working directory

In a separate terminal window run:


```
cd ./app
```

## (4) Start the application

In yet another sepaarate terminal window run:

```
mvn exec:java -Dexec.mainClass="barryspeanuts.BarrysPeanutsExecutor"
```

