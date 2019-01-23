#!/bin/bash

/usr/lib/jvm/java-8-openjdk-amd64/bin/java -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=37283 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=localhost -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -javaagent:/snap/intellij-idea-ultimate/109/lib/idea_rt.jar=36747:/snap/intellij-idea-ultimate/109/bin -Dfile.encoding=UTF-8 -classpath /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/charsets.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/cldrdata.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/dnsns.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/icedtea-sound.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/jaccess.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/java-atk-wrapper.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/localedata.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/nashorn.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/sunec.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/zipfs.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jce.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jsse.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/management-agent.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/resources.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar:/home/telepchuk/Documents/przyjazne-szachy/server/target/classes:/home/telepchuk/.m2/repository/javax/mail/mail/1.4.7/mail-1.4.7.jar:/home/telepchuk/.m2/repository/javax/activation/activation/1.1/activation-1.1.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-actuator/2.0.6.RELEASE/spring-boot-starter-actuator-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter/2.0.6.RELEASE/spring-boot-starter-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot/2.0.6.RELEASE/spring-boot-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.0.6.RELEASE/spring-boot-autoconfigure-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.0.6.RELEASE/spring-boot-starter-logging-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/home/telepchuk/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/home/telepchuk/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.10.0/log4j-to-slf4j-2.10.0.jar:/home/telepchuk/.m2/repository/org/apache/logging/log4j/log4j-api/2.10.0/log4j-api-2.10.0.jar:/home/telepchuk/.m2/repository/org/slf4j/jul-to-slf4j/1.7.25/jul-to-slf4j-1.7.25.jar:/home/telepchuk/.m2/repository/javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar:/home/telepchuk/.m2/repository/org/yaml/snakeyaml/1.19/snakeyaml-1.19.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-actuator-autoconfigure/2.0.6.RELEASE/spring-boot-actuator-autoconfigure-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-actuator/2.0.6.RELEASE/spring-boot-actuator-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.9.7/jackson-databind-2.9.7.jar:/home/telepchuk/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.9.7/jackson-core-2.9.7.jar:/home/telepchuk/.m2/repository/org/springframework/spring-context/5.0.10.RELEASE/spring-context-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.9.7/jackson-datatype-jsr310-2.9.7.jar:/home/telepchuk/.m2/repository/io/micrometer/micrometer-core/1.0.7/micrometer-core-1.0.7.jar:/home/telepchuk/.m2/repository/org/hdrhistogram/HdrHistogram/2.1.10/HdrHistogram-2.1.10.jar:/home/telepchuk/.m2/repository/org/latencyutils/LatencyUtils/2.0.3/LatencyUtils-2.0.3.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-data-jpa/2.0.6.RELEASE/spring-boot-starter-data-jpa-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-aop/2.0.6.RELEASE/spring-boot-starter-aop-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/aspectj/aspectjweaver/1.8.13/aspectjweaver-1.8.13.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-jdbc/2.0.6.RELEASE/spring-boot-starter-jdbc-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/com/zaxxer/HikariCP/2.7.9/HikariCP-2.7.9.jar:/home/telepchuk/.m2/repository/org/springframework/spring-jdbc/5.0.10.RELEASE/spring-jdbc-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/javax/transaction/javax.transaction-api/1.2/javax.transaction-api-1.2.jar:/home/telepchuk/.m2/repository/org/hibernate/hibernate-core/5.2.17.Final/hibernate-core-5.2.17.Final.jar:/home/telepchuk/.m2/repository/org/jboss/logging/jboss-logging/3.3.2.Final/jboss-logging-3.3.2.Final.jar:/home/telepchuk/.m2/repository/org/hibernate/javax/persistence/hibernate-jpa-2.1-api/1.0.2.Final/hibernate-jpa-2.1-api-1.0.2.Final.jar:/home/telepchuk/.m2/repository/org/javassist/javassist/3.22.0-GA/javassist-3.22.0-GA.jar:/home/telepchuk/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar:/home/telepchuk/.m2/repository/org/jboss/jandex/2.0.3.Final/jandex-2.0.3.Final.jar:/home/telepchuk/.m2/repository/com/fasterxml/classmate/1.3.4/classmate-1.3.4.jar:/home/telepchuk/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar:/home/telepchuk/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.1.Final/hibernate-commons-annotations-5.0.1.Final.jar:/home/telepchuk/.m2/repository/org/springframework/data/spring-data-jpa/2.0.11.RELEASE/spring-data-jpa-2.0.11.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/data/spring-data-commons/2.0.11.RELEASE/spring-data-commons-2.0.11.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-orm/5.0.10.RELEASE/spring-orm-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-tx/5.0.10.RELEASE/spring-tx-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-beans/5.0.10.RELEASE/spring-beans-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:/home/telepchuk/.m2/repository/org/springframework/spring-aspects/5.0.10.RELEASE/spring-aspects-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-data-rest/2.0.6.RELEASE/spring-boot-starter-data-rest-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-json/2.0.6.RELEASE/spring-boot-starter-json-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.9.7/jackson-datatype-jdk8-2.9.7.jar:/home/telepchuk/.m2/repository/com/fasterxml/jackson/module/jackson-module-parameter-names/2.9.7/jackson-module-parameter-names-2.9.7.jar:/home/telepchuk/.m2/repository/org/springframework/data/spring-data-rest-webmvc/3.0.11.RELEASE/spring-data-rest-webmvc-3.0.11.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/data/spring-data-rest-core/3.0.11.RELEASE/spring-data-rest-core-3.0.11.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/hateoas/spring-hateoas/0.25.0.RELEASE/spring-hateoas-0.25.0.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/plugin/spring-plugin-core/1.2.0.RELEASE/spring-plugin-core-1.2.0.RELEASE.jar:/home/telepchuk/.m2/repository/org/atteo/evo-inflector/1.2.2/evo-inflector-1.2.2.jar:/home/telepchuk/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.9.0/jackson-annotations-2.9.0.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-web/2.0.6.RELEASE/spring-boot-starter-web-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-tomcat/2.0.6.RELEASE/spring-boot-starter-tomcat-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/8.5.34/tomcat-embed-core-8.5.34.jar:/home/telepchuk/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/8.5.34/tomcat-embed-el-8.5.34.jar:/home/telepchuk/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/8.5.34/tomcat-embed-websocket-8.5.34.jar:/home/telepchuk/.m2/repository/org/hibernate/validator/hibernate-validator/6.0.13.Final/hibernate-validator-6.0.13.Final.jar:/home/telepchuk/.m2/repository/javax/validation/validation-api/2.0.1.Final/validation-api-2.0.1.Final.jar:/home/telepchuk/.m2/repository/org/springframework/spring-web/5.0.10.RELEASE/spring-web-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-webmvc/5.0.10.RELEASE/spring-webmvc-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-expression/5.0.10.RELEASE/spring-expression-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/mysql/mysql-connector-java/5.1.47/mysql-connector-java-5.1.47.jar:/home/telepchuk/.m2/repository/org/projectlombok/lombok/1.16.22/lombok-1.16.22.jar:/home/telepchuk/.m2/repository/org/springframework/spring-core/5.0.10.RELEASE/spring-core-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-jcl/5.0.10.RELEASE/spring-jcl-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-security/2.0.6.RELEASE/spring-boot-starter-security-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-aop/5.0.10.RELEASE/spring-aop-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/security/spring-security-config/5.0.9.RELEASE/spring-security-config-5.0.9.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/security/spring-security-core/5.0.9.RELEASE/spring-security-core-5.0.9.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/security/spring-security-web/5.0.9.RELEASE/spring-security-web-5.0.9.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/boot/spring-boot-starter-data-redis/2.0.6.RELEASE/spring-boot-starter-data-redis-2.0.6.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/data/spring-data-redis/2.0.11.RELEASE/spring-data-redis-2.0.11.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/data/spring-data-keyvalue/2.0.11.RELEASE/spring-data-keyvalue-2.0.11.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-oxm/5.0.10.RELEASE/spring-oxm-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/springframework/spring-context-support/5.0.10.RELEASE/spring-context-support-5.0.10.RELEASE.jar:/home/telepchuk/.m2/repository/io/lettuce/lettuce-core/5.0.5.RELEASE/lettuce-core-5.0.5.RELEASE.jar:/home/telepchuk/.m2/repository/io/projectreactor/reactor-core/3.1.10.RELEASE/reactor-core-3.1.10.RELEASE.jar:/home/telepchuk/.m2/repository/org/reactivestreams/reactive-streams/1.0.2/reactive-streams-1.0.2.jar:/home/telepchuk/.m2/repository/io/netty/netty-common/4.1.29.Final/netty-common-4.1.29.Final.jar:/home/telepchuk/.m2/repository/io/netty/netty-transport/4.1.29.Final/netty-transport-4.1.29.Final.jar:/home/telepchuk/.m2/repository/io/netty/netty-buffer/4.1.29.Final/netty-buffer-4.1.29.Final.jar:/home/telepchuk/.m2/repository/io/netty/netty-resolver/4.1.29.Final/netty-resolver-4.1.29.Final.jar:/home/telepchuk/.m2/repository/io/netty/netty-handler/4.1.29.Final/netty-handler-4.1.29.Final.jar:/home/telepchuk/.m2/repository/io/netty/netty-codec/4.1.29.Final/netty-codec-4.1.29.Final.jar com.ps.server.ServerApplication
