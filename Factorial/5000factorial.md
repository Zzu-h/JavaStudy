# 50000! 구하기

## FactorialArray class
FactorialArray class는 Factorial이라는 메소드로 factorial을 계산할 변수 n을 받아온다.    
이후 1부터 n까지 calFact라는 메소드로 factorial을 계산한다.    
기본적인 곱셈 방식을 채택했다.    
두 자리 이상의 곱셈에서 일의 자리 십의 자리 등으로 따로 계산하는 방식     
Ex)12*34     
12    
+ 34     
48    
       36     
       408    
<br>

---

<br>

## 생성한 멤버 변수
#### bigintger[]
팩토리얼의 계산 값을 정수로 저장하는 넉넉한 크기의 배열을 생성    
한 자리씩만 저장할 것이므로 정수의 최소단위 byte를 사용하여 배열을 생성한다.    

#### size
팩토리얼의 계산 값의 자릿수를 저장하는 변수 생성    
 <br>

 ---

<br>

## calFact 메소드 구현방식
#### calFact
n번째와 이전에 저장된 factorial 계산 값을 연산해주는 메소드.    
(1, 2, 3, …, n, n+1, … , 50,000)    

#### carry
자리올림을 할 정수의 저장 변수    
<br>

cal 변수에 계산 값의 i번째 자리와 n을 곱하고, i-1번째에서 올라오는 자리올림을 더해준다.    
biginteger의 i번째에 계산된 한 자리만 저장을 한다.(cal % 10)    
carry에 자리올림 할 정수를 저장한다.(cal / 10)    
<br>
for문 연산이 끝나고 최상위 자리의 연산 결과가 자리올림이 존재할 경우 biginteger의 자리를 늘린다. Size의 값을 증가시키고, carry의 한 자리씩 biginteger에 저장한다.

<br>

---

<br>

## mkFile 메소드
연산된 결과를 경로 “C:\\homework "위치에 factorial.txt 파일로 저장한다.
기존에 파일이 존재한다면 덮어씌우기를 실행하고
outputStream의 write메소드는 byte의 값을 전달하는데, factorial 연산 결과는 byte 배열로 저장하여 그대로 인자를 저장한다.
단, 아스키코드로 저장되기 때문에 biginteger각 요소마다 아스키 코드 값 ‘0’ = 48을 더해준다.

<br>

---

<br>

## 최종 time complexity
Factorial 메소드에서 n번의 calFact메소드를 호출한다.    
<br>
CalFact는 for문에서 size만큼의 곱셈 및 덧셈 및 할당 등의 연산을 수행하고, while문에서 할당 및 나머지 등의 상수 time 연산을 수행했다.    
<br>
그렇다면 최종적으로 Factorial메소드의 time complexity는 O(n*size)인데, size는 n보다 항상 작으므로, n * size < n * n = n2 -> O(n^2)이다.

---

<br>
<br>

# 초기 접근 방향 LinkedList
## FactorialLinkedLIst
처음에는 링크드 리스트로 구현하려 했다.    
전체적인 알고리즘은 위와 같이 곱셈의 방식과 동일하게 적용하였다.    
    
<br>

#### Algorithm
- LinkedLIst를 생성하여 무한한 정수를 저장할 공간을 만든다.
    - 이 Linked List는 정수를 4자리씩 읽고 연산한다.
    - linked list 한 bucket에 정수 4자리를 담는다.
    - ex) 정수 123,456 -> Linked List 12 - 3456
- 저장된 값과 index값을 곱셈을 수행한다. List * index (index = [1, n])
    - 최하위 4자리를 index와 곱셈을 하고 자리올림 저장한다.
    - 최하위 4자리 다음 4자리와 index를 곱하고 자리올림을 더한 후 또 올라갈 자리올림을 저장한다.
    - 위와 같은 과정을 반복한다.
- 저장된 값을 배출하기 위해 가운데 있는 정수는 만일 4자리가 아닌 3자리 이하라면, 자리를 표시하기 위한 0을 추가한다.
    - 1자리 정수 : 000을 추가
    - 2자리 정수 : 00을 추가
    - 3자리 정수 : 0을 추가

### 문제점
- 10000! 까지는 잘 구해지나, 그 이후부터는 정수가 적절히 저장되지 않는다.
    - 아직 이유는 모르지만, 링크드리스트의 저장소가 heap영역을 overflow해서라고 예상을 했을 때
    - 50000!의 계산 값의 자릿수 총 213,238
        - 4자리씩 나누면 53,309의 링크드 리스트의 bucket이 생성된다.
        - short로 구현했으므로 53,309 * short는 106,618 바이트를 잡는다.
    - 따라서, 저장소의 문제는 아니라고 판단된다.
- 아직 정확한 원인은 파악하지 못했다.

---
