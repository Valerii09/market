Данный репозиторий содержит код для приложения Market и дамп базы данных (MarketBd.sql). Ниже приведены инструкции по развертыванию приложения и базы данных на локальной машине.
Шаги по развертыванию
1. Скачивание проекта
git clone https://github.com/Valerii09/market.git
cd your-repository

2. Создание базы данных и восстановление из дампа
Создайте пустую базу данных и восстановите данные из вашего дампа с использованием утилиты командной строки psql:
createdb market
psql -U valerii1 -d market -a -f MarketBd.sql

3. Настройка подключения к базе данных
Откройте файл src/main/resources/application.properties и убедитесь, что ваши настройки подключения к базе данных соответствуют следующим:

spring.datasource.url=jdbc:postgresql://localhost:5432/market
spring.datasource.username=valerii1
spring.datasource.password=qwerty
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

4. Запуск приложения
Откройте проект в вашей среде разработки и запустите приложение. Приложение будет доступно по адресу http://localhost:8080/home.

5. Дополнительная информация для pgAdmin
Если вы предпочитаете использовать pgAdmin для управления базой данных:

  Откройте pgAdmin и подключитесь к вашему серверу PostgreSQL.
  Создайте новую базу данных с именем "market".
  Выберите новую базу данных и выполните импорт данных из дампа (MarketBd.sql).
  
