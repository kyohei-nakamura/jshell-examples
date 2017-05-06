# redis-example

| 製品 | バージョン |
|---|---|
| Ubuntu | 16.04 |
| Redis | 3.2 |
| OpenJDK | 9-internal |
| Jedis | 2.9 |

## インストール

1. Redisをインストールします（/opt配下）

```bash
$ cd /opt
$ sudo wget http://download.redis.io/releases/redis-3.2.8.tar.gz
$ sudo tar xzf redis-3.2.8.tar.gz
$ sudo ln -s redis-3.2.8 redis
$ cd /opt/redis
$ sudo make
```

1. OpenJDK9をインストールします（JShell利用）

```bash
$ sudo apt-get install openjdk-9-jdk
$ sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-9-openjdk-amd64/bin/java 1081
$ sudo update-alternatives --config java
# Path : /usr/lib/jvm/java-9-openjdk-amd64/bin/java を選択します
$ sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/java-9-openjdk-amd64/bin/javac 1081
$ sudo update-alternatives --config javac
# Path : /usr/lib/jvm/java-9-openjdk-amd64/bin/javac を選択します
$ sudo ln -s /usr/lib/jvm/java-9-openjdk-amd64/bin/jshell jshell
```

1. Jedisライブラリをダウンロードします

```bash
$ mkdir -p ~/redis-example/lib
$ cd ~/redis-example/lib
$ sudo wget http://central.maven.org/maven2/redis/clients/jedis/2.9.0/jedis-2.9.0.jar
$ sudo wget http://central.maven.org/maven2/org/apache/commons/commons-pool2/2.4.2/commons-pool2-2.4.2.jar
```

## Redis起動

```bash
$ cd /opt/redis
$ sudo src/redis-server redis.conf
# redis.confは必要に応じて修正する。
```

## JShell実行

```bash
$ cd ~/redis-example
$ jshell
|  Welcome to JShell -- Version 9-internal
|  For an introduction type: /help intro
```

1. ライブラリをクラスパスに追加する

```java
-> /classpath lib/jedis-2.9.0.jar
|  Path 'lib/jedis-2.9.0.jar' added to classpath

-> /classpath lib/commons-pool2-2.4.2.jar
|  Path 'lib/commons-pool2-2.4.2.jar' added to classpath
```

1. jedisパッケージをインポートする

```java
-> import redis.clients.jedis.*
|    Update modified method printf(String,Object...)
```

1. Jedisインスタンスを生成する

```java
-> Jedis jedis = new Jedis("localhost");
|  Added variable jedis of type Jedis with initial value redis.clients.jedis.Jedis@XXXXXXXX
```

1. コマンドを発行する

```java
-> jedis.set("foo", "bar");
|  Expression value is: "OK"
|    assigned to temporary variable $3 of type String

-> String value = jedis.get("foo");
|  Added variable value of type String with initial value "bar"
```

1. JShellを終了する

```java
-> /exit
|  Goodbye
```

