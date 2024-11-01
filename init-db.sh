#!/bin/bash

# Wait for MySQL to start
echo "Waiting for MySQL to start..."
sleep 10  # Adjust the sleep duration if needed

# Connect to MySQL and execute commands
mysql -u root -prootPassword <<EOF
CREATE DATABASE IF NOT EXISTS blogs_db;
GRANT ALL PRIVILEGES ON blogs_db.* TO 'admin'@'%';
FLUSH PRIVILEGES;
EOF

echo "Database and user permissions have been set up."
