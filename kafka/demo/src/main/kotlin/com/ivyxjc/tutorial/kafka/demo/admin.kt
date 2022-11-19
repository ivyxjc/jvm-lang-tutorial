package com.ivyxjc.tutorial.kafka.demo

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import java.util.Properties


fun main() {
  val properties = Properties()
  properties.load(KafkaDemo::class.java.getResourceAsStream("/application.properties"))
  val adminClient = AdminClient.create(properties)
  val result = adminClient.createTopics(listOf(NewTopic("sample-topic", 3, 1.toShort())))
  result.all().get()
}