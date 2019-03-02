#!/bin/bash
#filename: atlasJobs.sh
#author: Casmon Gordon 1800flowers.com

#validate arguements passed
if [[ $# -lt 1 ]]
then
   echo "arguements underflow. please specify action"
   exit 1
fi

#declare variables
task="invalid"
template_file="./Terminate_TEMPLATE.props"
config_file="./atlasJobs.properties"
base_dir=$HOME"/sandbox"
echo $base_dir

#file(s) location
sales_dir=$base_dir"/spdatalocal/SalesProcessor/config/"
amazon_dir=$base_dir"/spdatalocal/Amazon/scripts/"
fbottom_dir=$base_dir"/spdatalocal/fbottom/scripts/"
hd_fbottom_dir=$base_dir"/spdatalocal/HDFbottom/scripts/"

#determine type of task i.e. start | stop
if [ -n "$1" ] && [[ $1 =~ ^(stop|start)$ ]];
then
   task=$1
   echo "valid input $1"
else
   echo "invalid input. please specify valid task (start | stop)"
   exit 1
fi

function toggle_files {
  directory=$1
  fileNames=$2
  alpha=$3
  delta=$4

  #read the 'propertyKey' property list of files to rename
  for element in ${fileNames};
  do
      filename="${element//\"}"

      cmd_rename_existing="mv ${directory}${filename}${alpha} ${directory}${filename}${delta}"
      echo "command: rename existing: $cmd_rename_existing"
      eval $cmd_rename_existing

      if [ -n "$5" ] && [[ $5 =~ ^(true|yes)$ ]];
          then
            cmd_copy_terminate_prop="cp ${template_file} ${directory}${filename}"
            echo "command: copy template: $cmd_copy_terminate_prop"
            eval $cmd_copy_terminate_prop
      fi
  done
}

#create template file
if [ -f "$template_file" ]
then
   echo "$template_file found. using existing template"
else

cat >> $template_file  <<- "EOF"
IS_TERMINATE=true
#IS_TERMINATE=false
EOF

fi

#retrieve property file config info

if [ -f "$config_file" ]
then

  #retreive all properties
  while IFS='=' read -r key value
  do
    key=$(echo $key | tr '.' '_')
    eval "${key}='${value}'"
  done < "$config_file"

  export IFS=";"

else
  echo "config file $config_file not found."
  exit 1
fi

#activities for stopping the services

if [ $task = "stop" ]
then

    #toggle template property file(s)
    toggle_files $sales_dir "${files[@]}" "" "-" true

    #toggle script file(s)
    toggle_files $amazon_dir "${filesAmazon[@]}" "" "-"
    toggle_files $fbottom_dir "${filesFbottom[@]}" "" "-"
    toggle_files $hd_fbottom_dir "${filesHDFbottom[@]}" "" "-"

    echo "task $task completed."
    exit 0

fi

#activities for starting the services

if [ $task="start" ]
then

    #toggle template property file(s)
    toggle_files $sales_dir "${files[@]}" "-" ""

    #toggle script file(s)
    toggle_files $amazon_dir "${filesAmazon[@]}" "-" ""
    toggle_files $fbottom_dir "${filesFbottom[@]}" "-" ""
    toggle_files $hd_fbottom_dir "${filesHDFbottom[@]}" "-" ""

    echo "task $task completed."
    exit 0
fi

#activities for stopping the services

#end of script file
