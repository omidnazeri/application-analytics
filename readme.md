## Build Instructions
- build application with below command
```
./gradlew bootJar
```
- you can find the jar file at below location
```
{PROJECT_ROOT}/build/libs/analytics.jar
```
## Setup Instructions (Docker)
```
docker build -t onazeri/analytics .
docker run -p 9090:9090 onazeri/analytics
docker ps
docker logs onazeri/analytics
```

## Setup Instructions (Kubernetes)
```
docker build -t onazeri/analytics .
docker login
docker push onazeri/analytics

mkdir analytics
helm create analytics

kubectl create deployment analytics --image=onazeri/analytics --dry-run -o=yaml > analytics/templates/deployment.yaml
kubectl create service clusterip analytics --tcp=9090:9090 --dry-run -o=yaml > analytics/templates/service.yaml

helm upgrade --install --atomic --wait --set condition.ingress=false --set condition.ingress=false --set image.repository=onazeri/analytics,image.tag=latest,image.pullPolicy=Never analytics ./analytics/;

kubectl get all
```

## Setup Instructions (Simple)
- install java 8
```
# Ubuntu
apt install openjdk-8-jre-headless

# Centos
yum install java-1.8.0-openjdk
```
after installation you should run this command to find the location of java and we call it {JAVA_PATH} on the next steps
```
which java
```
- copy jar file on the server in an specific path which we call it {PROJECT_PATH} on the next steps
- create service
```
vi /etc/systemd/system/analytics-engine.service
```
put below lines on file and save it:
```
[Unit]
Description=Analytics Engine
After=network.target

[Service]
Type=simple
User=root
ExecStart={JAVA_PATH} -jar -Dlogging.file={PROJECT_PATH}/log/engine.log {PROJECT_PATH}/analytics.jar --spring.config.location=classpath:/application.properties
Restart=on-failure

[Install]
WantedBy=multi-user.target
```
- start service
```
systemctl enable analytics-engine
systemctl start analytics-engine
```
- check service log
```
tail -f {PROJECT_PATH}/log/engine.log 
```