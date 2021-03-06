
## ๐ setup / start (macos & docker)

### โ redis server ์คํ

```bash
[ docker-compose.yml ]

version: '3'
services:
  redis:
    container_name: redis
    hostname: redis
    image: redis:6.2
    ports:
    - 6379:6379
```

```bash
> docker-compose up
```

### โ redis-cli ์คํ
```bash
> docker exec -it redis bash

> redis-cli
```

***

## ๐ ๊ธฐ๋ณธ ๋ช๋ น์ด 

### โ key-value ์ ์ฅ ๋ฐ ์กฐํ (set-get)
```bash
set a 10
get a 
```

### โ key ์ญ์  (del)
```bash
del key

del key1 key2 key3
```

### โ ๋ชจ๋  key ์กฐํ (keys *)
```bash
keys *
```

### โ ๋ชจ๋  ๋ฐ์ดํฐ ์ญ์ 
```bash
flushdb
```

### ๐ set option

#### โ ๋ง๋ฃ์๊ฐ ์ค์  ex
3์ดํ ๋ง๋ฃ๋๋ key-value ์ ์์ฑ
```bash
set key 10 ex 3 
```

#### โ ํน์  key์ ๋ง๋ฃ์๊ฐ ํ์ธ ttl
-1: ๋ง๋ฃ์๊ฐ์ด ์ค์ ๋์ง ์์ ๊ฒฝ์ฐ
-2: ๋ง๋ฃ๋ ๊ฒฝ์ฐ
>0: ๋ง๋ฃ๊น์ง ๋จ์ ์๊ฐ 
```bash
ttl key
```

#### โ ๊ธฐ์กด key๊ฐ ์ฌ์ฌ์ฉ nx, ny
```bash
set a b nx => key a์ ๊ฐ์ด ์๋ ๊ฒฝ์ฐ ์์  X (๊ธฐ์กด ๊ฐ์ด ์๋ค๋ฉด ๊ฐ์ ์ธํ)
set a b xx => key a์ ๊ฐ์ด ์๋ ๊ฒฝ์ฐ ์์  O (๊ธฐ์กด ๊ฐ์ด ์๋ค๋ฉด ๋ณ๊ฒฝ X)
```

### โ ํน์  key์ ๋งค์นญ๋๋ value๊ฐ ์กด์ฌํ๋์ง ํ์ธ
```bash
exists key
```
1 or 0

### โ ํน์  key๊ฐ์ value ์ฆ๊ฐ ๋ฐ ๊ฐ์
```bash
set a 10
incr a # 1์ฆ๊ฐ
get a  # "11"

decr a # 1๊ฐ์
```

```bash
set a 10
incrby a 10 # 10์ฆ๊ฐ
get a # "20"

decrbt a 10 # 10๊ฐ์
```

### ๐ ์๋ฃ๊ตฌ์กฐ - Hash
```bash
hset user:1 name kim age 27 city bucheon # ์ด๊ธฐํ

hget user:1 name # "kim" 
hget user:1 age # "27"

hdel user:1 name # name ์ญ์ 
```

#### โ Hash key์ ํด๋นํ๋ ๋ชจ๋  sub key-value ์กฐํ
```bash
hgetall user:1 # key-value ๋ชจ๋ ์กฐํ
hkeys user:1 # key๋ง ์กฐํ
hvals user:1 # value๋ง ์กฐํ
```
![](https://images.velog.io/images/dhk22/post/76d78918-682f-44a9-8eff-8266162ca807/image.png)

<br>

### ๐ ์๋ฃ๊ตฌ์กฐ - Queue
`rpush` -> `lpop` ๊ตฌ์กฐ๋ก `Queue` ์๋ฃ๊ตฌ์กฐ์ ๊ฐ์ด ๋์ํ๋๋ก ํ๋ค. (FIFO)

```bash
rpush queue 1 2 3
llen queue # "#"
lpop queue
lpop queue
lpop queue
```
![](https://images.velog.io/images/dhk22/post/b4286b72-c287-4568-8149-7cc582dcd534/image.png)


#### โ ๋ฒ์ ์กฐํ (lrange)
```bash
rpush queue 1 2 3 4 5
llen queue "5"
lrange queue 0 -1 # ์ฒ์๋ถํฐ ๋๊น์ง ์กฐํ
lrange queue 0 0 # 0๋ฒ์งธ ์กฐํ
```
![](https://images.velog.io/images/dhk22/post/127f5fe4-77c7-4706-8248-998887ddf211/image.png)

<br>

### ๐ ์๋ฃ๊ตฌ์กฐ - Stack
`lpush` -> `lpop` (LIFO)

```bash
lpush stack 1 2 3
lpop stack # "3"
lpop stack # "2"
lpop stack # "1"
```

![](https://images.velog.io/images/dhk22/post/8375882f-64a3-4ac1-bf53-10e6b8224806/image.png)

**์กฐํ๋ ๋์ผ**

<br>

### ๐ ์๋ฃ๊ตฌ์กฐ - Set

`Set`์ ์์๋ฅผ ์ ์งํ์ง ์๊ณ  ์ค๋ณต์ ํ์ฉํ์ง ์๋ ์๋ฃ๊ตฌ์กฐ์ด๋ค.

```bash
sadd set 1 1 2 2 3 3 1 1
scard set # set์ ํฌ๊ธฐ ํ์ธ =>"3" (1 2 3)
smembers set # set์ ๋ชจ๋  ์์ ใํ
```
![](https://images.velog.io/images/dhk22/post/a5a652c0-a6a9-4570-bedc-c3f03407b2b2/image.png)
```bash
sadd set 1 2 3
sismember set 1 # 1์ด set์ ์กด์ฌํ๋์ง ํ์ธ
srem set 1 # 1์ set์์ ์ญ์  (remove)
spop set # set์์ ๋๋คํ๊ฒ ํ ๊ฐ ์์๋ฅผ pop
```

<br>

### ๐ ์๋ฃ๊ตฌ์กฐ - SortedSet
๊ฐ์ค์น๋ฅผ ์ด์ฉํด์ ์์๋ฅผ ์ ์งํ๋ `Set`์ด๋ค.
๋ญํน ๊ธฐ๋ฅ์ ๊ตฌํํ  ๋ ์ฌ์ฉํ๋ฉด ์ข์ ๊ฒ ๊ฐ๋ค.

```bash
zadd name [๊ฐ์ค์น1] [key1] [๊ฐ์ค์น2] [key2] ... 
```

default ๋ ๊ฐ์ค์น ๊ธฐ์ค ์ค๋ฆ์ฐจ์์ด๋ค.


```bash
zadd 30 a 10 b 15 c
zrange zadd 0 -1

# b -> c -> a ์ ์ถ๋ ฅ
```
![](https://images.velog.io/images/dhk22/post/43dd9c30-f9f9-465f-ad00-950bcc933885/image.png)

#### โ ๋ด๋ฆผ์ฐจ์ ์ถ๋ ฅ (rev, reverse)
```bash
zadd 30 a 10 b 15 c
zrange zadd 0 -1 rev

# a -> c -> b ์ ์ถ๋ ฅ 
```
![](https://images.velog.io/images/dhk22/post/35db13c0-9abe-4eb6-832a-62df9d624758/image.png)

#### โ ๊ฐ์ค์น์ ํจ๊ป ๋ด๋ฆผ์ฐจ์ ์กฐํ (withscores)
```bash
zadd 30 a 10 b 15 c
zrange zadd 0 -1 rev withscores
```
![](https://images.velog.io/images/dhk22/post/dbacbaf3-01c1-47b1-83d6-1dbd38930951/image.png)

#### โ ๋ญํน ์กฐํ (zrank, zrevrank)

```bash
zrank ์ด๋ฆ key # key์ ์์๋ฅผ ๋ฐํ
````
์์๋ 0๋ถํฐ ์์ํ๋ค.
![](https://images.velog.io/images/dhk22/post/7eed47c7-4ca8-49ad-8605-9714cbfefe5b/image.png)

๋ด๋ฆผ์ฐจ์์ผ๋ก ๋ญํน์กฐํ
```bash
zrevrank ์ด๋ฆ key
```
๋ด๋ฆผ์ฐจ์์ผ๋ก ์กฐํํ ๊ฒฐ๊ณผ `c`๊ฐ 1๋ฑ(`0`)์ด๋ค.
![](https://images.velog.io/images/dhk22/post/45175b38-e053-4db4-83ac-b6096706592d/image.png)

#### โ ๊ฐ์ค์น ๊ธฐ์ค ์ต๋๊ฐ, ์ต์๊ฐ ์ ๊ฑฐ (zpopmax, zpopmin)

๊ฐ์ค์น ๊ธฐ์ค ์ต๋,์ต์๊ฐ ์์ ์ญ์ 
```bash
zpopmax ์ด๋ฆ
zpopmin ์ด๋ฆ
```







