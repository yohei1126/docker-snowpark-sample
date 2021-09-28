FROM hseeberger/scala-sbt:11.0.12_1.5.5_2.12.15 AS builder
COPY . /build
WORKDIR /build
RUN sbt stage

FROM openjdk:11.0.12-slim
WORKDIR /root/
COPY --from=builder /build/target/universal/stage .
CMD ["./bin/snowpark-docker-sample"]
