package Project;

public class Person {
	private String name;
    private String position;
    private String entry;
    private String contact;
    private int vacation;
    private int idx;
    private int overtimeHours;
    
    public Person() {
        this.vacation = 40; // 유급 휴가 총 40시간 규정
        this.overtimeHours = 0; // 시간외 근로 등록 위함
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getEntry() { return entry; }
    public void setEntry(String entry) { this.entry = entry; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public int getVacation() { return vacation; }
    public void setVacation(int vacation) { this.vacation = vacation; }

    public int getIdx() { return idx; }
    public void setIdx(int idx) { this.idx = idx; }
    
    public int getOvertimeHours() {
        return overtimeHours;
    }
    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours += overtimeHours; 
    }

    @Override
    public String toString() {
        return "Person [이름 : " + name + ", 직책 : " + position + ", 업무 시작일 : " + entry + ", 전화번호 : " + contact + ", 남은 유급휴가 시간 : " + vacation + ", 총 초과 근무 시간 : " + overtimeHours + "]";
    }
}
