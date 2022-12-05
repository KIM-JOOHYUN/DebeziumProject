### ✔️ Debezium이란?

- Kafka Connector의 Source 커넥터의 집합
    - **CDC**를 사용하여 데이터베이스의 변경 사항을 수집
- Database의 Row 레벨의 변경 사항을 캡처하여 애플리케이션이 변경 내용을 보고 이를 처리할 수 있도록 해주는 분산 서비스
    - Row 레벨의 변경을 changed event stream에 기록 ⇒ 애플리케이션은 이 Stream을 통해 변경 이벤트을 순서대로 읽는다.
    - 지원 DBMS : MongoDB, **MySQL**, PostgreSQL, SQL Server, Oracle, Db2, Cassandra 등
