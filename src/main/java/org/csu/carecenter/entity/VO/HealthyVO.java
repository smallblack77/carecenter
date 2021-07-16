package org.csu.carecenter.entity.VO;

public class HealthyVO {

    //健康
    int custId;
    double temp;
    String pressure;
    double sugar;
    double weight;
    int pulse;
    String selfCare;
    String day;

    //客户基本信息
    Double height;
    String sex;
    String name;
    String attention;

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public String getSelfCare() {
        return selfCare;
    }

    public void setSelfCare(String selfCare) {
        this.selfCare = selfCare;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    @Override
    public String toString() {
        return "HealthyVO{" +
                "custId = " + custId +
                ", temp = " + temp +
                ", pressure =" + pressure +
                ", sugar =" + sugar +
                ", weight =" + weight +
                ", pulse =" + pulse +
                ", selfCare =" + selfCare +
                ", day =" + day +
                ", height =" + height +
                ", sex =" + sex +
                ", name =" + name +
                ", attention =" + attention +
                '}';
    }
}
