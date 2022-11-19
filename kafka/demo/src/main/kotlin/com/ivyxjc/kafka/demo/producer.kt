package com.ivyxjc.tutorial.kafkademo


import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.Properties
import java.util.UUID

fun main() {

  val properties = Properties()
  properties.load(KafkaDemo::class.java.getResourceAsStream("/application.properties"))
  val producer = KafkaProducer<String, String>(properties)
  for (i in 1..10000) {
    val record = ProducerRecord("sample-topic", UUID.randomUUID().toString(), "value$i")
    producer.send(record)
  }
  producer.flush()
  producer.close()
}
