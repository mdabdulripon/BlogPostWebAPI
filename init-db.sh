#!/bin/bash

# Wait for MySQL to start
echo "Waiting for MySQL to start..."
sleep 10  # Adjust the sleep duration if needed

# Execute MySQL commands within the Docker container
docker exec -i mysql mysql -u root -prootPassword <<EOF
CREATE DATABASE IF NOT EXISTS blogpost_db;
GRANT ALL PRIVILEGES ON blogpost_db.* TO 'admin'@'%';
FLUSH PRIVILEGES;
EOF

echo "Database and user permissions have been set up."
