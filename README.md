# Cogia
------------
Simply run `docker-compose up -d` and the containers will build.

------------

- Front-end @ http://localhost:3000
- API microservice @ http://localhost:8080
- Task-runner microservice @ http://localhost:8081

------------

- MySql database container `:3306.`
- RabbitMQ container `:15672.`


# Whats Missing.

- Integration Tests:

I wanted to do this as fast as possible since I already made you wait, the API microservice is fully Unit tested.

# Possible Improvements for the task.

- Adding Authentication through Bearer Tokens.
- Request Log | Error Log.
- Using an Exchange/Topics instead of 3 separate MessageQueues.
- Monitoring library.
		Ex: NewRelic.
- If integration tests are done, integrate the repo using CircleCI.
