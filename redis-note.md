
## 📌 setup / start (macos & docker)

### ✅ redis server 실행

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

### ✅ redis-cli 실행
```bash
> docker exec -it redis bash

> redis-cli
```

***

## 📌 기본 명령어 

### ✅ key-value 저장 및 조회 (set-get)
```bash
set a 10
get a 
```

### ✅ key 삭제 (del)
```bash
del key

del key1 key2 key3
```

### ✅ 모든 key 조회 (keys *)
```bash
keys *
```

### ✅ 모든 데이터 삭제
```bash
flushdb
```

### 📌 set option

#### ✅ 만료시간 설정 ex
3초후 만료되는 key-value 쌍 생성
```bash
set key 10 ex 3 
```

#### ✅ 특정 key의 만료시간 확인 ttl
-1: 만료시간이 설정되지 않은 경우
-2: 만료된 경우
>0: 만료까지 남은 시간 
```bash
ttl key
```

#### ✅ 기존 key값 재사용 nx, ny
```bash
set a b nx => key a에 값이 있는 경우 수정 X (기존 값이 없다면 값을 세팅)
set a b xx => key a에 값이 있는 경우 수정 O (기존 값이 없다면 변경 X)
```

### ✅ 특정 key와 매칭되는 value가 존재하는지 확인
```bash
exists key
```
1 or 0

### ✅ 특정 key값의 value 증가 및 감소
```bash
set a 10
incr a # 1증가
get a  # "11"

decr a # 1갑소
```

```bash
set a 10
incrby a 10 # 10증가
get a # "20"

decrbt a 10 # 10감소
```

### 📌 자료구조 - Hash
```bash
hset user:1 name kim age 27 city bucheon # 초기화

hget user:1 name # "kim" 
hget user:1 age # "27"

hdel user:1 name # name 삭제
```

#### ✅ Hash key에 해당하는 모든 sub key-value 조회
```bash
hgetall user:1 # key-value 모두 조회
hkeys user:1 # key만 조회
hvals user:1 # value만 조회
```
![](https://images.velog.io/images/dhk22/post/76d78918-682f-44a9-8eff-8266162ca807/image.png)

<br>

### 📌 자료구조 - Queue
`rpush` -> `lpop` 구조로 `Queue` 자료구조와 같이 동작하도록 한다. (FIFO)

```bash
rpush queue 1 2 3
llen queue # "#"
lpop queue
lpop queue
lpop queue
```
![](https://images.velog.io/images/dhk22/post/b4286b72-c287-4568-8149-7cc582dcd534/image.png)


#### ✅ 범위 조회 (lrange)
```bash
rpush queue 1 2 3 4 5
llen queue "5"
lrange queue 0 -1 # 처음부터 끝까지 조회
lrange queue 0 0 # 0번째 조회
```
![](https://images.velog.io/images/dhk22/post/127f5fe4-77c7-4706-8248-998887ddf211/image.png)

<br>

### 📌 자료구조 - Stack
`lpush` -> `lpop` (LIFO)

```bash
lpush stack 1 2 3
lpop stack # "3"
lpop stack # "2"
lpop stack # "1"
```

![](https://images.velog.io/images/dhk22/post/8375882f-64a3-4ac1-bf53-10e6b8224806/image.png)

**조회는 동일**

<br>

### 📌 자료구조 - Set

`Set`은 순서를 유지하지 않고 중복을 허용하지 않는 자료구조이다.

```bash
sadd set 1 1 2 2 3 3 1 1
scard set # set의 크기 확인 =>"3" (1 2 3)
smembers set # set의 모든 원소 ㅈ회
```
![](https://images.velog.io/images/dhk22/post/a5a652c0-a6a9-4570-bedc-c3f03407b2b2/image.png)
```bash
sadd set 1 2 3
sismember set 1 # 1이 set에 존재하는지 확인
srem set 1 # 1을 set에서 삭제 (remove)
spop set # set에서 랜덤하게 한 개 원소를 pop
```

<br>

### 📌 자료구조 - SortedSet
가중치를 이용해서 순서를 유지하는 `Set`이다.
랭킹 기능을 구현할 때 사용하면 좋을 것 같다.

```bash
zadd name [가중치1] [key1] [가중치2] [key2] ... 
```

default 는 가중치 기준 오름차순이다.


```bash
zadd 30 a 10 b 15 c
zrange zadd 0 -1

# b -> c -> a 순 출력
```
![](https://images.velog.io/images/dhk22/post/43dd9c30-f9f9-465f-ad00-950bcc933885/image.png)

#### ✅ 내림차순 출력 (rev, reverse)
```bash
zadd 30 a 10 b 15 c
zrange zadd 0 -1 rev

# a -> c -> b 순 출력 
```
![](https://images.velog.io/images/dhk22/post/35db13c0-9abe-4eb6-832a-62df9d624758/image.png)

#### ✅ 가중치와 함께 내림차순 조회 (withscores)
```bash
zadd 30 a 10 b 15 c
zrange zadd 0 -1 rev withscores
```
![](https://images.velog.io/images/dhk22/post/dbacbaf3-01c1-47b1-83d6-1dbd38930951/image.png)

#### ✅ 랭킹 조회 (zrank, zrevrank)

```bash
zrank 이름 key # key의 순위를 반환
````
순위는 0부터 시작한다.
![](https://images.velog.io/images/dhk22/post/7eed47c7-4ca8-49ad-8605-9714cbfefe5b/image.png)

내림차순으로 랭킹조회
```bash
zrevrank 이름 key
```
내림차순으로 조회한 결과 `c`가 1등(`0`)이다.
![](https://images.velog.io/images/dhk22/post/45175b38-e053-4db4-83ac-b6096706592d/image.png)

#### ✅ 가중치 기준 최대값, 최소값 제거 (zpopmax, zpopmin)

가중치 기준 최대,최소값 원소 삭제
```bash
zpopmax 이름
zpopmin 이름
```







