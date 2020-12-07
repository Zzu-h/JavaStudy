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
