### 1. Клонирование и сборка
```bash
git clone <repository-url>
cd untitled2
mvn clean compile assembly:single
java -jar target/untitled2-1.0-SNAPSHOT-jar-with-dependencies.jar tickets.json
