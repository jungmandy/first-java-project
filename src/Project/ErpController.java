package Project;

import java.util.Optional;

public class ErpController {
	private ErpPreview epv = new ErpPreview();
    private ErpService esv = new ErpService();

    public void show() {
        while (true) {
            int choice = epv.getList();

            switch (choice) {
               
                case 1: // 직원 등록
                    Person p = epv.printSetPerson();
                    if (p != null) {
                        esv.addPerson(p);
                        System.out.println("직원 등록 완료: " + p.getName());
                    }
                    break;

                case 2: // 직원 보기
                    epv.printPersons(esv.getPersons());
                    break;

                case 3: // 직원 검색
                	 String name = epv.getName();
                     Optional<Person> personOpt = esv.searchPerson(name);
                     personOpt.ifPresent(person -> {
                         epv.printPersons(person); 
                     });
                     personOpt.orElseGet(() -> {
                         System.out.println("직원을 찾을 수 없습니다.");
                         return null;
                     });
                     break;

                case 4: // 직책 수정
                	String editName = epv.getName();
                    Optional<Person> editPositionOpt = esv.searchPerson(editName);
                    if (editPositionOpt.isPresent()) {
                        String newPosition = epv.getNewPosition(); 
                        editPositionOpt.get().setPosition(newPosition);
                        System.out.println(editName + " 님의 직책이 수정되었습니다.");
                    } else {
                        System.out.println("직원을 찾을 수 없습니다.");
                    }
                    break;


                case 5: // 유급휴가 신청
                    epv.newVacay(esv);
                    break;

                case 6: // 직원 정보 삭제
                    String deleteName = epv.getName();
                    Optional<Person> personToRemove = esv.searchPerson(deleteName);
                    if (personToRemove.isPresent()) {
                        esv.removePerson(personToRemove.get());
                        System.out.println(deleteName + " 님의 정보가 삭제되었습니다.");
                    } else {
                        System.out.println("직원을 찾을 수 없습니다.");
                    }
                    break;

                case 7: // 시간외 근로 (초과 근무) 등록
                    epv.registerOvertime(esv);
                    break;
                    
                case 8: // 종료
                    epv.exit();
                    return;

                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
                    break;
            }
        }
    }
}
