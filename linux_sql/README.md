
# Implementation
I began by installing the necessary packages and configuring my development environment to ensure that the required tools and dependencies were in place. I then developed a startup script that handles the lifecycle of Docker instances. In addition, I created SQL scripts for table management, which focused on creating host_info and host_usage tables in the Postgres SQL database. Bash Scripts were then implemented to retrieve host information and usage variables. These scripts gather the necessary data from the system and are inserted into the corresponding database tables. The project was then deployed, and automation using crontabs were set up. This involved scheduling the bash scripts to run every minute.

## Architecture
Image has been saved to the `assets` directory.

## Scripts
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh
- Initiates the Docker Service to ensure it's up and running
- If container exists, it outputs a message saying that it already exists
- If container does not exist, script proceeds to the next step
- If the command is to create a container, script creates the container and then runs it.
- If the command is to start a container, script checks if container is already created, and then starts it. Otherwise it'll output a message indicating that the container needs to be created first

- host_info.sh
- Grabs all the hardware specification information and inserts it into a PostgreSQL database table named `host_info`

- host_usage.sh
- Collects system usage-related information and inserts it into a PostgreSQL Database table called `host_usage`

- crontab
- Schedules a periodic crontab job to run the `./scripts/host_usage.sh` script every minute, therefore a new row entry will be added inside the host_usage database table

- queries.sql
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


                                                                                                                                                             17,0-1        41%

