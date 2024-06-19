# 2271213_정채운

## 1. 프로젝트 수행 목적

### 1.1 프로젝트 정의
- 위도, 경도를 이용한 지도메모 앱

### 1.2 프로젝트 배경
- 여행을 가거나, 식당을 찾아보거나, 주변 편의시설을 이용하려고 할 때, 인터넷을 찾아보면 광고를 위해 작성된 글들이 넘쳐난다.
- 이러한 광고들을 피해 해당 장소에 대한 의견을 알아보려고 할 때, 사람들이 자유롭게 작성한 짧은 메모가 도움이 될 것이다.

### 1.3 프로젝트 목표
- **장소위치 찾기**: Places API를 이용하여 어느 장소든 장소의 이름을 입력하는 것으로 위도, 경도값을 자동으로 도출
- **장소 지정하기**: Maps SDK for Android API를 이용하여 지도를 띄워 자세한 위치를 지정
- **메모 남기기**: 지정한 장소에 대한 간단한 메모와 별점을 남김
- **메모 확인하기**: 메모탭을 이용하여 사람들이 남긴 메모들을 확인
- **메모 검색하기**: 메모탭에서 원하는 내용을 검색하여 찾기

## 2. 프로젝트 개요

### 2.1 프로젝트 설명
1. 장소명을 입력하면 자동으로 해당 장소의 위도, 경도값을 입력해준다.
2. 해당 위도, 경도값으로 지도에서 정확한 위치를 지정한다.
3. 지정한 위치에 대한 간단한 설명과 별점을 남겨 저장한다.
4. 다른 사람들이 남긴 메모들을 확인한다.
5. 원하는 내용의 메모를 검색을 통해 확인한다.

### 2.2 결과물

- 메인 화면
- 
  <img src="https://github.com/jjas00n/Location_memo/assets/147312504/acce5dc1-fcf2-42e7-aac0-986866d8c48f" width="40%" height="40%">

- 메인화면에서 좌표를 얻은 모습
-
  ![Screenshot_20240619_202707](https://github.com/jjas00n/Location_memo/assets/147312504/712bf28f-7df1-4102-95b8-d6afc0dd6b18)

- 지도화면
-
  ![Screenshot_20240619_202717](https://github.com/jjas00n/Location_memo/assets/147312504/4b2423e0-9505-42e5-b9ec-f4ed4db8cc47)


- 지도에서 원하는 위치 지정
-
  ![Screenshot_20240619_202746](https://github.com/jjas00n/Location_memo/assets/147312504/28a2cf6d-0e22-42d5-a7d0-69e1a3bbc81b)

- 메모 작성
-
  ![Screenshot_20240619_202818](https://github.com/jjas00n/Location_memo/assets/147312504/f9b8b8d2-7117-4888-9800-4f77cd8c9385)

- 다른 사람이 작성한 메모 확인
- 
  <img src="https://github.com/jjas00n/Location_memo/assets/147312504/faadbc58-66ae-4656-a081-c33ac803809a" width="40%" height="40%">

- 검색하여 원하는 내용 찾기
- 
  <img src="https://github.com/jjas00n/Location_memo/assets/147312504/75ed383b-7df0-4ea4-9826-c8d3192a6cd4" width="40%" height="40%">

- 자세한 메모 내용 확인
- 
  <img src="https://github.com/jjas00n/Location_memo/assets/147312504/01728ef8-ca7f-4c82-8296-74214782b7c9" width="40%" height="40%">

### 2.3 기대효과
- 정보를 얻으려고 클릭한 긴 글에 끝에 광고였다는 내용이 적혀있다면, 시간도 소비되고 신뢰없는 정보가 들어오는 반면, 이 앱은 사용자들이 자유롭게 쓴 짧고 간결한 평가로 손 쉽게 정보를 얻을 수 있다.
- 장소에 대한 나의 의견을 써보고 다른 사람의 메모도 구경하며 의견의 차이를 확인할 수 있다.

### 2.4 시연영상
- [Youtube 동영상]
- https://youtu.be/IVGUJR84vEw
