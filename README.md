# springboot-boilerplate

## Prerequisites
1. VPN 연결 (https://confluence.hanwhalife.com/pages/viewpage.action?pageId=47584391)

## 보일러플레이트 활용시
1. setting.gradle파일에서 프로젝트명 변경  
    -> QueryDsl이 적용된 Repository class의 QEntity(QSample) import 패키지 경로에 프로젝트명 적용 되었는지 확인  
    -> (intellij에서 프로젝트네임 변경시 이부분은 자동리펙토링 해주지 않는 이슈 확인)

## 서버 런
1. gradle bootRun (entity 클래스 수정 발생시 gradle clean 필요)

## 주요 설정항목
1. mysql8
2. redis
3. queryDSL
4. springdoc-openapi-ui (swagger)
5. eureka-client
6. jpa
7. lombok
8. feign-client
9. Resilience4j (circuitbreaker)

## 문제해결
1. (QueryDSL 관련) 빌드 or 서버런시 QEntity 경로(심볼) 오류 나는 경우, 확인사항  
    -> intellij - File - Project Structure - Modules - {프로젝트명} - main - + Add Content Root - {root}/build/generated 폴더 추가.  
    -> Intellij Preferences - Build, Execution, Deployment - Build Tools - Gradle 에서 'Build and run using', 'Run tests using' 두 항목 설정 값 'IntelliJ IDEA' 으로 업데이트  
    -> Intellij Preferences - Build, Execution, Deployment - Compiler - Annotation Processors 에서 'Enable annotation processing' 체크  
