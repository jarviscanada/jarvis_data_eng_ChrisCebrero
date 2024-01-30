# Introduction

The project aims to provide a solution for monitoring and logging CPU usage using Docker containers, with data stored in a Postgres SQL database.
The intended users of this project include anyone who pulls the project and are interested in monitoring their CPU usage. Technologies used in this project are Bash, Docker, Git, Linux and Postgres.

# Quick Start

- `./scripts/psql_docker.sh create username password`
- `./scripts/psql_docker.sh start` if username and password are already created
- `psql -h localhost -U postgres -d host_agent -f sql/ddl.sql`
- `bash ./scripts/host_info.sh "localhost" 5432 "host_agent" "postgres" "password"`
- `bash ./script/host_usage.sh "localhost" 5432 "host_agent" "postgres" "password"`
- `crontab -e`
- `* * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log`


# Implementation
- I began by installing the necessary packages and configuring my development environment to ensure that the required tools and dependencies were in place. 
- Developed a startup script that handles the lifecycle of Docker instances. In addition, I created SQL scripts for table management, which focused on creating `host_info` and `host_usage` tables within the Postgres SQL database. Bash Scripts were then implemented to retrieve hardware specification and usage information and save the values into variables.
- These scripts gather the necessary data from the system and are inserted into the corresponding database tables.
- The project was then deployed, and automated using crontab in which it involved scheduling specific bash scripts to run every minute were set up.
- Installed necessary packages and configured my development environment
- Developed a startup script to handle the life cycle of Docker instances
- Created SQL scripts for table management, which focused on the creation of the `host_info` and `host_usage` tables
- Implemented bash scripts to retrieve hardware specifications and usage information from the system, and collected the values into variables for further processing
- Bash scripts were then utilized to insert the gathered data into the corresponding tables
- Set up automated tasks to run specific bash scripts to run every minute using Crontabs. This ensures regular updates of system data into the database

## Architecture
Image has been saved to the `assets` directory.

## Scripts
Shell script description and usage (use markdown code block for script usage)
- `psql_docker.sh`
    - Initiates the Docker Service to ensure it's up and running
    - If container exists, it outputs a message saying that it already exists
    - If container does not exist, script proceeds to the next step
    - If the command is to create a container, script creates the container and then runs it.
    - If the command is to start a container, script checks if container is already created, and then starts it. Otherwise it'll output a message indicating that the container needs to be created first

- `host_info.sh`
    - Grabs all the hardware specification information and inserts it into a PostgreSQL database table named `host_info`

- `host_usage.sh`
    - Collects system usage-related information and inserts it into a PostgreSQL Database table called `host_usage`

- `crontab`
    - Schedules a periodic crontab job to run the `./scripts/host_usage.sh` script every minute, therefore a new row entry will be added inside the host_usage database table

- `queries.sql`
    - Records and inserts information regarding other machines' hardware specifications and their CPU usage

## Database Modeling
- `host_info`
| title.           |       type|
| ---------------- | --------- |
| id               | serial    |
| hostname         | varchar   |
| cpu_number       | int       |
| cpu_architecture | varchar   |
| cpu_model        | varchar   |
| cpu_mhz.         | float.    |
| l2_cache.        | int4.     |
| timestamp.       | timestamp |
| total_mem.       | int.      |

- `host_usage`
| title.           |       type|
| ---------------- | --------- |
| timestamp        | timestamp |
| host_id          | varchar   |
| memory_free      | int       |
| cpu_idle         | varchar   |
| cpu_kernel       | varchar   |
| disk_io          | float.    |
| disk_available   | int4.     |

# Test
Before adding the actual content to the scripts, I would verify the information I was retrieving fit well with the schema types of the database.
I would make sure to print `bash -x` before executing each script to print each command and its arguments to better visualize the execution flow, and identify any issues in variable assignments or command executions. Furthermore I would manully verify the psql instance to make sure the correct information was added.


# Deployment
I utilized GitHub to host the source code and track any code changes. I containerized my application using Docker, as it encapsulates the application and its dependencies, making it easier to deploy across different environments. Furthermore, to deploy the monitoring application on the JRD instance, I used crontab to schedule the execution of the `./scripts/host_usage.sh` script every minute.

# Improvements
- Using tools like Jenkins to automate the deployment process, which could help reduce manual errors
- Having more robust error handling can help the application gracefully handle any unexpected scenarios
- Security considerations, i.e. make sure sensitive information such as credentials are handled properly
