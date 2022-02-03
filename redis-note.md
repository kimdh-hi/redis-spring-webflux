
redis.io/commands

set - get

keys

scan
  scan 0 match user* count 3

del key
  del key1 key2 key3

flushdb

set key value ex 3
set key value px 3000

ttl key
  -1: 만료시간 설정 X
  -2: 만료됨

expire key 30

set a b ex 60
set a c
ttl a => -1 

set a b ex 60
set a c keepttl

set a b nx => key a에 값이 있는 경우 수정 X (기존 값이 없다면 값을 세팅)
set a b xx => key a에 값이 있는 경우 수정 O (기존 값이 없다면 변경 X)

exists key
1 or 0

set a 1
incr a
get a  => 2
decr a
get a => 1

incr a => 값이 없는 경우 1
  set a 0
  incr a

set a 10
incrby a 10
get a => 20  

set a 1.01
incrbyfloat a .1
get a => 1.11
incrbyfloat a .01
get a => 1.12
incrbyfloat a -.01
get a => 1.11  


hset key field1 value1 field2 value2
hset user:1 name kim age 27 city bucheon
hget user:1 name => kim
hget user:1 age => 27

hgetall user:1
hkeys user:1
hvals user:1
hexists user:1 name
hdel user:1 age


push / pop
lpush / rpush / lpop / rpop

### Queue (rpush -> lpop)
rpush items 1 2 3
llen items => 3
lrange items 0 -1 => "1","2","3"
lpop items => 1

> Redis를 이용한 Message Queue ex
- 결제 이벤트가 발생했을 때 redis에 결제정보를 담아서 lpush
- 결제를 처리하는 서비스(consumer)는 lpop해서 얻은 결제정보로 결제로직 수행


### Stack (rpush -> rpop)
rpush items 1 2 3 4 5
llen items => 5
rpop items => 5


### Set
sadd items 1 2 3
scard items => 3 (크기)
smembers items => "1", "2", "3"
sismembers items 1 => 1
srem items 1 (remove)
spop items (랜덤 pop)

Set intersection
sinter a b c => a b c 의 교집합

Set union
sunion a b c => a b c 의 합집합


### Sorted Set (z)
[zadd key 가중치1 값1 가중치2 값2 가중치3 값3]
zadd items 0 a 0 b 0 c
zcard items => 3
zincrby items 1 b

zrange items 0 -1 	  => 가중치 오름차순
zrange items 0 -1 rev => 가중치 내림차순
zrange items 0 0 rev withscores => 가중치가 가장 높은 원소를 가중치와 함께 출력

zrank items b    => 2 가중치 오름차순 순위
zrevrank items b => 0 가중치 내림차순 순위

zpopmax items => 가중치 최대값 pop
zpopmin items 


### Redis Transaction (multi, multiple command)
```
> watch key1 key2 ...
> multi

> ...
> ...

> exec or discard
```
exec: commit
discard: rollback


### Redis snapshot
```
background save
> bgsave
```
현재 경로에 `.rdb` 파일 생성







































