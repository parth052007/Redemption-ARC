class encapsulation {

    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {

        if (age < 18) {
            System.out.println("Invalid age");
            return;
        }

        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {

        encapsulation e1 = new encapsulation();

        e1.setName("Parth");
        e1.setAge(20);

        System.out.println("Name is: " + e1.getName());
        System.out.println("Age is: " + e1.getAge());
    }
}