package Project;

import java.util.List;
import java.util.Scanner;

public class ErpPreview {
	private Scanner sc = new Scanner(System.in);

    public int getList() {
        System.out.println("-----------------------------------------");
        System.out.println("ERP 시스템의 직원 정보 관리 프로그램입니다.");
        System.out.println("사용하실 서비스의 번호를 입력해주세요.");
        System.out.println("1. 직원 등록 2. 직원 보기 3. 직원 검색 4. 직책 수정 \n5. 유급휴가 신청 6. 직원 정보 삭제 7. 시간외 근로 등록 8. 종료");
        System.out.print("번호 입력 : ");

        int choice = sc.nextInt();
        sc.nextLine(); 
        return choice;
    }

    // 직원 추가 (정규직 또는 계약직)
    public Person printSetPerson() {
        System.out.print("새 직원의 계약 기간을 구분하여 등록합니다. 번호를 입력하세요.\n1. 정규직 2. 계약직 : ");
        int type = sc.nextInt();
        sc.nextLine(); 

        System.out.println("이름과 직책을 입력하세요.");
        System.out.print("이름 : ");
        String name = sc.nextLine();
        System.out.print("직책 : ");
        String position = sc.nextLine();
        System.out.print("업무 시작일을 입력하세요. (YYYY-MM-DD) : ");
        String entry = sc.nextLine();
        System.out.print("전화번호를 입력하세요 : ");
        String contact = sc.nextLine();

        // 정규직 직원 추가
        if (type == 1) {
            Person p = new Person();
            p.setName(name);
            p.setPosition(position);
            p.setEntry(entry);
            p.setContact(contact);
            return p;
            
        // 계약직 직원 추가
        } else if (type == 2) {
            Temporary temp = new Temporary();
            temp.setName(name);
            temp.setPosition(position);
            temp.setEntry(entry);
            temp.setContact(contact);
            
            System.out.print("계약 기간을 월단위로 입력하세요. \n(1년 3개월 계약 -> 숫자 15만 입력) : ");
            int period = sc.nextInt();
            temp.setPeriod(period);
            
            return temp;
        } else {
            System.out.println("잘못된 선택입니다.");
            return null;
        }
    }

    // 직원 검색하기
    public String getName() {
        System.out.print("이름을 입력하세요: ");
        return sc.nextLine();
    }

    // 등록된 직원 출력
    public void printPersons(List<Person> persons) {
        if (persons.isEmpty()) {
            System.out.println("등록된 직원이 없습니다.");
        } else {
            for (Person p : persons) {
                System.out.println(p);
            }
        }
    }

    // 등록된 직원 출력 (단일 직원)
    public void printPersons(Person person) {
        if (person != null) {
            System.out.println(person);
        }
    }

    // 직책 수정
    public String getNewPosition() {
        System.out.print("새 직책을 입력하세요: ");
        return sc.nextLine();
    }

    // 직원 휴가 신청
    public void newVacay(ErpService esv) {
        System.out.println("휴가 신청 직원의 이름을 입력해주세요.");
        System.out.print("이름 : ");
        String name = sc.nextLine();
        
        Person person = esv.searchPerson(name).orElse(null);
        if (person == null) {
            System.out.println("직원을 찾을 수 없습니다.");
            return; 
        }
        
        System.out.print("신청할 휴가 시간을 입력해주세요 (최대 40시간) : ");
        int hours = sc.nextInt();
        sc.nextLine(); 

        if (hours > 40) {
            System.out.println("최대 40시간까지만 신청할 수 있습니다.");
        } else if (esv.applyForLeave(name, hours)) {
            System.out.println(name + "님의 " + hours + "시간의 휴가가 신청되었습니다.");
        } else {
            System.out.println(name + "님은 휴가를 신청할 수 없습니다. 남은 휴가 시간을 확인하세요.");
        }

        System.out.println("잔여 휴가 시간: " + person.getVacation() + "시간");
    }

    // 시간외 근로 (초과 근무) 등록
    public void registerOvertime(ErpService esv) {
        System.out.print("초과 근무 등록할 직원의 이름을 입력하세요: ");
        String name = sc.nextLine();

        Person person = esv.searchPerson(name).orElse(null);
        if (person == null) {
            System.out.println("직원을 찾을 수 없습니다.");
            return; 
        }

        System.out.print("등록할 초과 근무 시간을 입력하세요: ");
        int hours = sc.nextInt();
        sc.nextLine(); 

        if (esv.registerOvertime(name, hours)) {
            System.out.println(name + " 님의 초과 근무 " + hours + "시간이 등록되었습니다.");
        } else {
            System.out.println("초과 근무 등록에 실패하였습니다.");
        }
    }

    // 프로그램 종료시 종료 메세지 출력하기
    public void exit() {
        System.out.println("ERP 시스템이 종료됩니다.");
    }
}
