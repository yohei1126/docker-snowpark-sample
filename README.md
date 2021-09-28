# Docker for Snowpark app

## Build a image

```
docker build -t docker-snowpark-sample .
```

## Run a container from the image

```
docker run --rm \
  -e PASSWORD="your-password" \
  -e URL="https://your-account-name.region.snowflakecomputing.com" \
  -e USER="your-user" \
  docker-snowpark-sample
```
