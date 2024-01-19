psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

lscpu_out=`lscpu`
vmstat_mb=$(vmstat --unit M)

hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out" | egrep '^CPU\(s\):' | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep 'Architecture' | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep 'Model name' | awk -F: '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep -o '^CPU MHz:\s*[0-9.]+' | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep -o 'L2 cache:[[:space:]]*[0-9]+' | awk '{print $3}')
timestamp=$(date "+%Y-%m-%d %H:%M:%S")
total_mem=$(echo "$vmstat_mb" | tail -1 | awk '{print $4}')

insert_stmt="INSERT INTO host_info(id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, timestamp, total_mem) VALUES(1, '$hostname', $cpu_number, '$cpu_architecture','$cpu_model', $cpu_mhz, $l2_cache, '$timestamp', $total_mem);"


export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?
