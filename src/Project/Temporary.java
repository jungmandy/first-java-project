package Project;

public class Temporary extends Person {
	private int period;

    public Temporary() {
        super(); // Person 호출
    }
    
    // 계약직 직원의 계약 기간 저장
    public int getPeriod() { return period; }
    public void setPeriod(int period) { this.period = period; }

    @Override
    public String toString() {
        return super.toString() + ", 계약 기간 : 총 " + period + "개월";
    }
}
