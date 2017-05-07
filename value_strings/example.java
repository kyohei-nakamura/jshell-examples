import redis.clients.jedis.Jedis;
Jedis jedis = new Jedis("localhost");
jedis.set("Se", "Central League")
jedis.set("Pa", "Pacific League")
jedis.get("Se")
jedis.getrange("Pa", 0, -8)
List<String> resultList = jedis.mget("Se", "Pa")
resultList.stream().forEach(string -> System.out.println(string))
jedis.flushAll()
/exit
