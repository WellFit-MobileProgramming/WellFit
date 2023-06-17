package com.example.wellfit

import android.util.Log

data class Library(var kind:String, var name:String, var content:String)
class LibraryData {
    companion object{
        var libraryList: ArrayList<Library>? = null
        fun initDataList() {
            var list = ArrayList<Library>()
            list.add(Library("arm","바벨 컬","https://www.youtube.com/results?search_query=%EB%B0%94%EB%B2%A8+%EC%BB%AC+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","덤벨 컬","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EC%BB%AC+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","덤벨 삼두 익스텐션","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EC%82%BC%EB%91%90+%EC%9D%B5%EC%8A%A4%ED%85%90%EC%85%98+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","덤벨 킥백","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%ED%82%A5%EB%B0%B1+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","덤벨 리스트 컬","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EB%A6%AC%EC%8A%A4%ED%8A%B8+%EC%BB%AC+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","덤벨 해머 컬","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%ED%95%B4%EB%A8%B8+%EC%BB%AC+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","케이블 푸시 다운","https://www.youtube.com/results?search_query=%EC%BC%80%EC%9D%B4%EB%B8%94+%ED%91%B8%EC%8B%9C+%EB%8B%A4%EC%9A%B4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","클로즈그립 푸시업","https://www.youtube.com/results?search_query=%ED%81%B4%EB%A1%9C%EC%A6%88%EA%B7%B8%EB%A6%BD+%ED%91%B8%EC%8B%9C%EC%97%85+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","이지바 컬","https://www.youtube.com/results?search_query=%EC%9D%B4%EC%A7%80%EB%B0%94+%EC%BB%AC+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("arm","케이블 컬","https://www.youtube.com/results?search_query=%EC%BC%80%EC%9D%B4%EB%B8%94+%EC%BB%AC+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","풀업","https://www.youtube.com/results?search_query=%ED%92%80%EC%97%85+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","바벨 로우","https://www.youtube.com/results?search_query=%EB%B0%94%EB%B2%A8+%EB%A1%9C%EC%9A%B0+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","덤벨 로우","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EB%A1%9C%EC%9A%B0+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","펜들레이 로우","https://www.youtube.com/results?search_query=%ED%8E%9C%EB%93%A4%EB%A0%88%EC%9D%B4+%EB%A1%9C%EC%9A%B0+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","시티드 로우 머신","https://www.youtube.com/results?search_query=%EC%8B%9C%ED%8B%B0%EB%93%9C+%EB%A1%9C%EC%9A%B0+%EB%A8%B8%EC%8B%A0+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","랫풀다운","https://www.youtube.com/results?search_query=%EB%9E%AB%ED%92%80%EB%8B%A4%EC%9A%B4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","친업","https://www.youtube.com/results?search_query=%EC%B9%9C%EC%97%85+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","하이퍼 익스텐션","https://www.youtube.com/results?search_query=%ED%95%98%EC%9D%B4%ED%8D%BC+%EC%9D%B5%EC%8A%A4%ED%85%90%EC%85%98+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","루마니안 데드리프트","https://www.youtube.com/results?search_query=%EB%A3%A8%EB%A7%88%EB%8B%88%EC%95%88+%EB%8D%B0%EB%93%9C%EB%A6%AC%ED%94%84%ED%8A%B8+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("back","시티드 케이블 로우","https://www.youtube.com/results?search_query=%EC%8B%9C%ED%8B%B0%EB%93%9C+%EC%BC%80%EC%9D%B4%EB%B8%94+%EB%A1%9C%EC%9A%B0+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","벤치프레스","https://www.youtube.com/results?search_query=%EB%B2%A4%EC%B9%98%ED%94%84%EB%A0%88%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","인클라인 벤치프레스","https://www.youtube.com/results?search_query=%EC%9D%B8%ED%81%B4%EB%9D%BC%EC%9D%B8+%EB%B2%A4%EC%B9%98%ED%94%84%EB%A0%88%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","덤벨 벤치프레스","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EB%B2%A4%EC%B9%98%ED%94%84%EB%A0%88%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","인클라인 덤벨 벤치프레스","https://www.youtube.com/results?search_query=%EC%9D%B8%ED%81%B4%EB%9D%BC%EC%9D%B8+%EB%8D%A4%EB%B2%A8+%EB%B2%A4%EC%B9%98%ED%94%84%EB%A0%88%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","딥스","https://www.youtube.com/results?search_query=%EB%94%A5%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","덤벨 플라이","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%ED%94%8C%EB%9D%BC%EC%9D%B4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","케이블 크로스오버","https://www.youtube.com/results?search_query=%EC%BC%80%EC%9D%B4%EB%B8%94+%ED%81%AC%EB%A1%9C%EC%8A%A4%EC%98%A4%EB%B2%84+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","체스트 프레스 머신","https://www.youtube.com/results?search_query=%EC%B2%B4%EC%8A%A4%ED%8A%B8+%ED%94%84%EB%A0%88%EC%8A%A4+%EB%A8%B8%EC%8B%A0+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","펙덱 플라이 머신","https://www.youtube.com/results?search_query=%ED%8E%99%EB%8D%B1+%ED%94%8C%EB%9D%BC%EC%9D%B4+%EB%A8%B8%EC%8B%A0+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("chest","푸시업","https://www.youtube.com/results?search_query=%ED%91%B8%EC%8B%9C%EC%97%85+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","바벨 백스쿼트","1. 다리를 어깨너비만큼 벌리고, 바벨을 등 뒤 어깨 위에 올려줍니다.\n" +
                    "2. 상체가 앞으로 숙여지지 않도록 코어에 힘을 준 상태로 엉덩이를 뒤로 빼며 앉습니다.\n" +
                    "3. 상체의 자세를 유지하면서, 발바닥으로 지면을 밀고 일어나면서 1번의 자세로 돌아옵니다."))
            list.add(Library("lower_body","컨벤셔널 데드리프트","1. 양발을 골반보다 약간 넓게 벌리고, 바벨의 그립을 어깨보다 조금 넓게하여 무릎과 팔이 겹치지 않도록 합니다.\n" +
                    "2. 등이 굽지 않도록 상체를 곧게 유지하면서, 발바닥으로 지면을 밀어올린다는 느낌으로 바벨을 들어 올립니다.\n" +
                    "3. 몸을 완전히 쭉 피고 엉덩이 근육을 수축합니다.\n" +
                    "4. 바벨을 들어올린 역순서대로 바닥으로 내려 시작 자세로 돌아갑니다."))
            list.add(Library("lower_body","프론트 스쿼트","1. 바벨을 어깨 너비로 잡고 쇄골 위에 올려둡니다.\n" +
                    "2. 시선은 정면을 유지하면서, 엉덩이를 뒤로 빼며 앉습니다.\n" +
                    "3. 가슴을 펴고 허리를 곧게 세워 상체를 편 상태로, 발바닥으로 지면을 지긋이 누르며 일어납니다."))
            list.add(Library("lower_body","레그 프레스","1. 엉덩이와 허리가 의자에 완전히 붙도록 밀착하여 앉고, 두 발을 어깨 너비만큼 벌려줍니다.\n" +
                    "2. 안전핀을 제거하고, 중량의 자극을 느끼며 무릎을 굽혀줍니다. 엉덩이와 허리가 뜨지않을 깊이까지 중량판을 내립니다.\n" +
                    "3. 복부에 힘을 유지한채로, 중량판을 지긋이 밀어 올립니다."))
            list.add(Library("lower_body","레그 컬","1. 머신에 엎드린 상태에서, 종아리 쪽의 패드가 아킬레스 건 바로 위쪽에 위치하도록 머신을 조정합니다.\n" +
                    "2. 발목을 세운 상태에서, 무릎을 굽혀 기구를 올려줍니다.\n" +
                    "3. 허리, 엉덩이, 햄스트링에 긴장감을 유지하면서 기구를 천천히 내립니다."))
            list.add(Library("lower_body","레그 익스텐션","1. 의자는 무릎 바로 아래에 올 수 있게, 패드는 발목 살짝 위에 위치할 수 있도록 머신을 조정합니다.\n" +
                    "2. 엉덩이가 떨어지지 않게 유지하면서, 허벅지의 힘으로 고정부위를 지긋이 밀어올립니다.\n" +
                    "3. 근육의 긴장을 유지하면서 천천히 무릎을 굽혀줍니다."))
            list.add(Library("lower_body","덤벨 런지","1. 양발을 골반 너비만큼 벌리고 덤벨을 양손에 든 상태로 상체를 곧게 펴고 섭니다.\n" +
                    "2. 한쪽 다리를 뻗어 앞으로 나가면서 두 무릎이 90도 각도를 이룰 때까지 엉덩이를 낮춰줍니다. 이때 상체와 등은 곧게 편 자세를 유지합니다.\n" +
                    "3. 앞발의 뒤꿈치에 무게 중심을 실어서 몸을 위쪽으로 밀어주며 원래 시작 자세로 돌아옵니다."))
            list.add(Library("lower_body","스모 데드리프트","1. 발끝이 바깥을 향한 상태로 다리를 넓게 벌리고 바벨 앞에 섭니다.\n" +
                    "2. 허벅지가 바닥에 수평이 될 때까지 엉덩이를 뒤로 뺀 상태에서, 양팔은 어깨너비보다 좁게 바벨을 잡습니다.\n" +
                    "3. 복압을 유지한 채로 엉덩이에 힘을 주며 다리를 펴줍니다.\n" +
                    "4. 상체를 포함한 온몸이 펴지면 동작을 완료하고, 바벨이 올라온 역순서대로 바닥에 내려 시작 자세로 돌아갑니다."))
            list.add(Library("lower_body","점프 스쿼트","1. 다리를 어깨너비만큼 벌리고 허리를 펴고 곧게 섭니다.\n" +
                    "2. 가슴을 편 상태로 엉덩이를 뒤로 빼며 앉습니다.\n" +
                    "3. 복근에 힘을 주고 골반을 밀어 올리면서 최대한 높이 뛰어오릅니다.\n" +
                    "4. 착지하면서 2번의 자세로 돌아갑니다."))
            list.add(Library("lower_body","스미스머신 데드리프트","1. 양발을 골반보다 약간 넓게 벌리고, 바벨의 그립을 어깨보다 조금 넓게하여 무릎과 팔이 겹치지 않도록 합니다.\n" +
                    "2. 등이 굽지 않도록 상체를 곧게 유지하면서, 발바닥으로 지면을 밀어올린다는 느낌으로 바벨을 들어 올립니다.\n" +
                    "3. 몸을 완전히 쭉 피고 엉덩이 근육을 수축합니다.\n" +
                    "4. 바벨을 들어올린 역순서대로 바닥으로 내려 시작 자세로 돌아갑니다."))
            list.add(Library("shoulder","오버헤드 프레스","https://www.youtube.com/results?search_query=%EC%98%A4%EB%B2%84%ED%97%A4%EB%93%9C+%ED%94%84%EB%A0%88%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","덤벨 숄더 프레스","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EC%88%84%EB%8D%94+%ED%94%84%EB%A0%88%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","덤벨 레터럴 레이즈","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EB%A0%88%ED%84%B0%EB%9F%B4+%EB%A0%88%EC%9D%B4%EC%A6%88+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","덤벨 프론트 레이즈","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%ED%94%84%EB%A1%A0%ED%8A%B8+%EB%A0%88%EC%9D%B4%EC%A6%88+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","덤벨 슈러그","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EC%8A%88%EB%9F%AC%EA%B7%B8+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","비하인드 넥 프레스","https://www.youtube.com/results?search_query=%EB%B9%84%ED%95%98%EC%9D%B8%EB%93%9C+%EB%84%A5+%ED%94%84%EB%A0%88%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","페이스 풀","https://www.youtube.com/results?search_query=%ED%8E%98%EC%9D%B4%EC%8A%A4+%ED%92%80+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","핸드스탠드 푸시업","https://www.youtube.com/results?search_query=%ED%95%B8%EB%93%9C%EC%8A%A4%ED%83%A0%EB%93%9C+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","케이블 리버스 플라이","https://www.youtube.com/results?search_query=%EC%BC%80%EC%9D%B4%EB%B8%94+%EB%A6%AC%EB%B2%84%EC%8A%A4+%ED%94%8C%EB%9D%BC%EC%9D%B4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("shoulder","벤트오버 덤벨 레터럴 레이즈","https://www.youtube.com/results?search_query=%EB%B2%A4%ED%8A%B8%EC%98%A4%EB%B2%84+%EB%8D%A4%EB%B2%A8+%EB%A0%88%ED%84%B0%EB%9F%B4+%EB%A0%88%EC%9D%B4%EC%A6%88+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            libraryList = list
        }
        fun getList(kind:String): ArrayList<Library>? {
            var list = ArrayList<Library>()
            if (libraryList != null){
                for(i in libraryList!!){
                    if(i.kind == kind){
                        list.add(i)
                    }
                }
            }
            return list
        }
    }
}