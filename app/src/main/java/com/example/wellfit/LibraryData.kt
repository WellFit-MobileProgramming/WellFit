package com.example.wellfit

import android.util.Log

data class Library(var kind:String, var name:String, var link:String)
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
            list.add(Library("lower_body","바벨 백스쿼트","https://www.youtube.com/results?search_query=%EB%B0%94%EB%B2%A8+%EB%B0%B1%EC%8A%A4%EC%BF%BC%ED%8A%B8+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","컨벤셔널 데드리프트","https://www.youtube.com/results?search_query=%EC%BB%A8%EB%B2%A4%EC%85%94%EB%84%90+%EB%8D%B0%EB%93%9C%EB%A6%AC%ED%94%84%ED%8A%B8+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","프론트 스쿼트","https://www.youtube.com/results?search_query=%ED%94%84%EB%A1%A0%ED%8A%B8+%EC%8A%A4%EC%BF%BC%ED%8A%B8+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","레그 프레스","https://www.youtube.com/results?search_query=%EB%A0%88%EA%B7%B8+%ED%94%84%EB%A0%88%EC%8A%A4+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","레그 컬","https://www.youtube.com/results?search_query=%EB%A0%88%EA%B7%B8+%EC%BB%AC+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","레그 익스텐션","https://www.youtube.com/results?search_query=%EB%A0%88%EA%B7%B8+%EC%9D%B5%EC%8A%A4%ED%85%90%EC%85%98+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","덤벨 런지","https://www.youtube.com/results?search_query=%EB%8D%A4%EB%B2%A8+%EB%9F%B0%EC%A7%80+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","스모 데드리프트","https://www.youtube.com/results?search_query=%EC%8A%A4%EB%AA%A8+%EB%8D%B0%EB%93%9C%EB%A6%AC%ED%94%84%ED%8A%B8+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","점프 스쿼트","https://www.youtube.com/results?search_query=%EC%A0%90%ED%94%84+%EC%8A%A4%EC%BF%BC%ED%8A%B8+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
            list.add(Library("lower_body","스미스머신 데드리프트","https://www.youtube.com/results?search_query=%EC%8A%A4%EB%AF%B8%EC%8A%A4%EB%A8%B8%EC%8B%A0+%EB%8D%B0%EB%93%9C%EB%A6%AC%ED%94%84%ED%8A%B8+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"))
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
                        Log.e("list",list.toString())
                    }
                }
            }
            return list
        }
    }
}