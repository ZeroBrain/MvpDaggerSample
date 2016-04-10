# Mvp & Dagger 2 & Mockito Sample
Sample project to demonstrate MVP Pattern and Dagger 2, Mockito

- plain_component branch : 가장 기본적인 Dagger 적용법
- master branch : Component와 Subcomponent를 변경 할 수 있는 적용법
    * main/java/.../ui.legacysample : 레거시 코드의 싱글톤에 Dagger를 적용하는 예제
    * test/.../MainPageParserTest : D.I 생성자 주입이 적용한 코드의 UnitTest 예제
    * test/.../CastCardPresenterTest : Dagger를 통해 Mock을 주입하는 UnitTest 예제
    * test/.../MainHtmlQueryFragmentTest : InjectorCreator를 사용하여 Mock을 주입하는 UnitTest 예제
