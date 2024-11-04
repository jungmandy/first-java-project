package Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ErpService {
	private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }

    // 직원 추가
    public void addPerson(Person p) {
        persons.add(p);
    }

    // 직원 검색
    public Optional<Person> searchPerson(String name) {
        for (Person person : persons) {
            if (person.getName().contains(name)) {
                return Optional.of(person);
            }
        }
        return Optional.empty(); 
    }

    // 유급 휴가 신청
    public boolean applyForLeave(String name, int hours) {
        Person person = searchPerson(name).orElse(null);
        if (person != null && hours <= person.getVacation()) {
            person.setVacation(person.getVacation() - hours);
            return true; 
        }
        return false; 
    }

    // 시간외 근로 (초과 근무) 등록
    public boolean registerOvertime(String name, int hours) {
        Person person = searchPerson(name).orElse(null);
        if (person != null) {
            person.setOvertimeHours(hours);
            return true; 
        }
        return false; 
    }
    
    // 직원 정보 삭제
    public void removePerson(Person person) {
        persons.remove(person);
    }
}
