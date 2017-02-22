# telegram
A peer to peer chat client

[![Build Status](https://travis-ci.org/zowiehi/telegram.svg?branch=master)](https://travis-ci.org/zowiehi/telegram) [![Join the chat at https://gitter.im/telegramers/Lobby](https://badges.gitter.im/telegramers/Lobby.svg)](https://gitter.im/telegramers/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

---

Requires [Maven](https://maven.apache.org/)

To package into a `.jar`
```bash
$ mvn package
```

To run the `.jar` through the command line
```bash
$ java -jar target/telegram-1.0-SNAPSHOT.jar
```

Instead of using the command line, you can now just double-click on the `target/telegram-1.0-SNAPSHOT.jar` file.

---

To test
```bash
$ mvn test
$ mvn checkstyle:check
```
