# apache-kafka
### Using the below command starts the zookeeper and apache kafka.

* **Start Zookeepr Server** => zookeeper-server-start.bat ..\..\config\zookeeper.properties
  Port => 2181

*  **Start Kafka Server** => kafka-server-start.bat ..\..\config\server.properties
   Port => 9092

* **Kafka topic create** => kafka-topics.bat --create --topic mubarak-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3

* **Kafka producer create** => kafka-console-producer.bat --broker-list localhost:9092 --topic my-topic

* **Kafka consumer create** => kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my-topic --from-beginning
